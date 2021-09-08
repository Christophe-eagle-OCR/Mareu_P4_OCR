package com.perez.christophe.mareu.ui.meeting_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.perez.christophe.mareu.R;

public class MeetingListActivity extends AppCompatActivity {

    private FloatingActionButton mAddMeetingButton;

    //private ActivityMeetingListBinding mBinding;


    /*private void initUI() {

        mBinding = ActivityMeetingListBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        setButton();
    }*/

    /*private void setButton() {
        mBinding.activityMeetingsAddMeetingFab.setOnClickListener(this);
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);
        //initUI();
        setAddMeetingBtn();

    /*@Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(),NewMeetingActivity.class);
        if (view == mBinding.activityMeetingsAddMeetingFab) {
            startActivity(intent);
        }
     */
    }

    private void setAddMeetingBtn() {
        mAddMeetingButton = findViewById(R.id.activity_meetings_add_meeting_fab);
        mAddMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), NewMeetingActivity.class);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context, " Pour ajouter une reunion", Toast.LENGTH_SHORT).show();

            }
        });
    }
}