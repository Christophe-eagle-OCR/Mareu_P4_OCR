package com.perez.christophe.mareu.ui.meeting_list;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.perez.christophe.mareu.databinding.ActivityNewMeetingBinding;
import com.perez.christophe.mareu.di.DI;
import com.perez.christophe.mareu.model.Meeting;
import com.perez.christophe.mareu.model.Room;
import com.perez.christophe.mareu.repository.MeetingRepository;

import java.util.Date;

public class NewMeetingActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityNewMeetingBinding binding;

    private MeetingRepository mMeetingRepository;


    private void initUI() {
        binding = ActivityNewMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setCreateMeetingBtn();
        getSupportActionBar().setTitle("Nouvelle réunion");
        mMeetingRepository = DI.getMeetingRepository();
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

    // Create a new meeting
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
        if (listOfParticipants.isEmpty()) {
            binding.addMeetingListOfParticipantsTextField.setError("Merci de préciser les participants de la réunion");
            return;
        }
        if (room.isEmpty()) {
            binding.addMeetingRoomTextField.setError("Merci de préciser le nom de la salle de réunion");
            return;
        }

        //todo : Pb du type de variable pour la date , la list des participants, et la room !!
        // actuellement j'ai mis la date  en "String" et la list des participants en "String",
        // actuellement pour la room elle sera créée avec son constructeur avec new Room et des paramettres en "dur"
        // IL FAUDRA METTRE UN "SPINNER" menu deroulant  pour la liste des rooms
        //pour la liste des participants : edit texte ou spinner  et 1 btn plus
        // POUR LA DATE , on peut faire un "DatePicker"
        // Pour l'heure , on peut faire un "TimePicker"
        //
        //pour créer la reunion ( en passant par le di et le repo)
        mMeetingRepository.createMeeting(new Meeting(object,date,starTime,endTime,listOfParticipants,new Room(1,room,0xff81D4FA)));
        //mMeetingRepository.createMeeting(new Meeting(object,date,starTime,endTime,listOfParticipants,room));
        Toast.makeText(this, "Bravo , la réunion a été créée", Toast.LENGTH_SHORT).show();
        finish();


    }
}