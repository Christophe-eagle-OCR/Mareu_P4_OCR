package com.perez.christophe.mareu.ui.meeting_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.perez.christophe.mareu.databinding.ActivityMeetingListBinding;

public class MeetingListActivity extends AppCompatActivity implements View.OnClickListener {


    private ActivityMeetingListBinding binding;


    private void initUI() {

        binding = ActivityMeetingListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setAddMeetingBtn();
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
}