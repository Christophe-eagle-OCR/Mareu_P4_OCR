package com.perez.christophe.mareu.ui.meeting_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.perez.christophe.mareu.databinding.ActivityMeetingListBinding;
import com.perez.christophe.mareu.di.DI;
import com.perez.christophe.mareu.model.Meeting;
import com.perez.christophe.mareu.repository.MeetingRepository;

import java.util.ArrayList;
import java.util.Arrays;

public class MeetingListActivity extends AppCompatActivity implements View.OnClickListener {


    private ActivityMeetingListBinding binding;
    private final ArrayList<Meeting> mMeetingArrayList = new ArrayList<>();
    private final MeetingRepository mMeetingRepository = DI.getMeetingRepository();
    private MeetingRecyclerViewAdapter mMeetingAdapter;

    private void initUI() {

        binding = ActivityMeetingListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setAddMeetingBtn();
        initRecyclerView();
        initData();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.activityMeetingListMeetingRecyclerview.setLayoutManager(layoutManager);

        mMeetingAdapter = new MeetingRecyclerViewAdapter(mMeetingArrayList);
        binding.activityMeetingListMeetingRecyclerview.setAdapter(mMeetingAdapter);
    }

    private void initData() {
        mMeetingArrayList.clear();
        mMeetingArrayList.addAll(mMeetingRepository.getMeetings());
        mMeetingAdapter.notifyDataSetChanged();

    }

    private void setAddMeetingBtn() {
        binding.activityMeetingsAddMeetingFab.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), NewMeetingActivity.class);
        if (view == binding.activityMeetingsAddMeetingFab) {
            startActivity(intent);

            Context context = getApplicationContext();
            Toast.makeText(context, "Pour ajouter une reunion", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
}