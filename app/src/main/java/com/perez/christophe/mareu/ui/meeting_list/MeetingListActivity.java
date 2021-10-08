package com.perez.christophe.mareu.ui.meeting_list;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.perez.christophe.mareu.R;
import com.perez.christophe.mareu.databinding.ActivityMeetingListBinding;
import com.perez.christophe.mareu.di.DI;
import com.perez.christophe.mareu.model.Meeting;
import com.perez.christophe.mareu.repository.MeetingRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MeetingListActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,View.OnClickListener, MeetingRecyclerViewAdapter.DeleteItemListener {


    private ActivityMeetingListBinding binding;
    private List<Meeting> mMeetingList = new ArrayList<>();
    private final MeetingRepository mMeetingRepository = DI.getMeetingRepository();
    private MeetingRecyclerViewAdapter mMeetingAdapter;

    private void initUI() {

        binding = ActivityMeetingListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setButton();
        initData();
        initRecyclerView();

    }

    private void initData() {
        mMeetingList.clear();
        mMeetingList.addAll(mMeetingRepository.getMeetings());
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.listMeetingRv.setLayoutManager(layoutManager);

        mMeetingAdapter = new MeetingRecyclerViewAdapter(mMeetingList, this);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.listMeetingRv.getContext(),
                layoutManager.getOrientation());
        binding.listMeetingRv.addItemDecoration(dividerItemDecoration);

        binding.listMeetingRv.setAdapter(mMeetingAdapter);
    }

    private void setButton() {
        binding.addMeetingFab.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), NewMeetingActivity.class);
        if (view == binding.addMeetingFab) {
            startActivity(intent);

            Context context = getApplicationContext();
            Toast.makeText(context, "Pour ajouter une reunion", Toast.LENGTH_SHORT).show();
        }
    }

    // For delete itemn meeting on display
    @Override
    public void onDeleteItem(int position, Meeting meeting) {
        mMeetingRepository.deleteMeeting(meeting);
        mMeetingAdapter.notifyItemRemoved(position);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
        mMeetingAdapter.notifyDataSetChanged();
    }

    // for display Filter menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //for generate handle click item filter menu selection
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_date:
                dateDialog();
                return true;
            case R.id.filter_room:
                roomDialog();
                return true;
            case R.id.filter_reset:
                resetFilter();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void resetFilter() {
        mMeetingList.clear();
        mMeetingList.addAll(mMeetingRepository.getMeetings());
        binding.listMeetingRv.getAdapter().notifyDataSetChanged();
    }

    //todo implementation roomDialog
    private void roomDialog() {
    }

    // DatePicker for filter date
    private void dateDialog() {
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "date picker filter");
    }

    // For DatePicker - filter date,
    // Use onDateSet (Callback/rappel) to send the date to methode getMeetingFilteredByDate (qui filtre en fonction de la date) and show it here.
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        //String filterDateString = simpleDateFormat.format(c.getTime());
        String filterDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        mMeetingList.clear();
       // mMeetingList.addAll(mMeetingRepository.getMeetingFilteredByDate(c.getTime()));
        mMeetingList.addAll(mMeetingRepository.getMeetingFilteredByDate(filterDateString));
        binding.listMeetingRv.getAdapter().notifyDataSetChanged();
    }
}