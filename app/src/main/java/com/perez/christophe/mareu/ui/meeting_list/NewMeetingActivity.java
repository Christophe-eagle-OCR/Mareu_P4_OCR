package com.perez.christophe.mareu.ui.meeting_list;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.perez.christophe.mareu.databinding.ActivityNewMeetingBinding;

public class NewMeetingActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityNewMeetingBinding binding;


    private void initUI() {
        binding = ActivityNewMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setCreateMeetingBtn();
        getSupportActionBar().setTitle("Nouvelle réunion");
    }

    private void setCreateMeetingBtn() {
        binding.activityNewMeetingCreateMeetingFab.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_new_meeting);
        initUI();
    }

    @Override
    public void onClick(View view) {
        if (view == binding.activityNewMeetingCreateMeetingFab) {
            onCreateMeeting();
        }
    }

    //todo : manque à creer la methode pour créer la reunion en passant DI et le Meetingrepository
    private void onCreateMeeting() {
        String object = binding.addMeetingObjectTextField.getEditText().getText().toString();
        String date = binding.addMeetingDateTextField.getEditText().getText().toString();
        String starTime = binding.addMeetingStartTimeTextField.getEditText().getText().toString();
        String endTime = binding.addMeetingEndTimeTextField.getEditText().getText().toString();
        String listOfParticipants = binding.addMeetingListOfParticipantsTextField.getEditText().getText().toString();
        String room = binding.addMeetingRoomTextField.getEditText().getText().toString();

        if (object.isEmpty()) {
            binding.addMeetingObjectTextField.setError("Merci de préciser l'objet de la réunion");
            return;
        }
        if (date.isEmpty()) {
            binding.addMeetingDateTextField.setError("Merci de préciser la date de la réunion");
            return;
        }
        if (starTime.isEmpty()) {
            binding.addMeetingStartTimeTextField.setError("Merci de préciser l'heure du début de la réunion");
            return;
        }
        if (endTime.isEmpty()) {
            binding.addMeetingEndTimeTextField.setError("Merci de préciser l'heure de fin de la réunion");
            return;
        }
        if (listOfParticipants.isEmpty()){
            binding.addMeetingListOfParticipantsTextField.setError("Merci de préciser les participants de la réunion");
            return;
        }
        if (room.isEmpty()){
            binding.addMeetingRoomTextField.setError("Merci de préciser le nom de la salle de réunion");
            return;
        }




    }
}