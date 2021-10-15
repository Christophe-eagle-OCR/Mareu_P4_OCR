package com.perez.christophe.mareu.ui.meeting_list;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.perez.christophe.mareu.R;
import com.perez.christophe.mareu.databinding.ActivityNewMeetingBinding;
import com.perez.christophe.mareu.di.DI;
import com.perez.christophe.mareu.model.Meeting;
import com.perez.christophe.mareu.model.Room;
import com.perez.christophe.mareu.repository.MeetingRepository;
import com.perez.christophe.mareu.repository.RoomGenerator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NewMeetingActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, View.OnClickListener, AdapterView.OnItemSelectedListener {

    ActivityNewMeetingBinding binding;

    private MeetingRepository mMeetingRepository;

    private int position;


    private void initUI() {
        binding = ActivityNewMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setButton();
        getSupportActionBar().setTitle("Nouvelle réunion");
        mMeetingRepository = DI.getMeetingRepository();
        initSpinnerRooms();
        initDatePicker();
        initTimePicker();
        initSpinnerDuration();
    }

    private void setButton() {
        binding.createMeetingFab.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_new_meeting);
        initUI();
    }

    @Override
    public void onClick(View view) {
        if (view == binding.createMeetingFab) {
            onCreateMeeting();
        }
    }

    // Create a new meeting
    private void onCreateMeeting() {
        String object = binding.objectTextField.getEditText().getText().toString();
        String date = binding.dateTextField.getEditText().getText().toString();
        String starTime = binding.startTimeTextField.getEditText().getText().toString();
        String endTime = binding.endTimeTextField.getEditText().getText().toString();
        String listOfParticipants = binding.listOfParticipantsTextField.getEditText().getText().toString();


        if (object.isEmpty()) {
            binding.objectTextField.setError("Merci de préciser l'objet de la réunion");
            return;
        }
        if (date.isEmpty()) {
            binding.dateTextField.setError("Merci de préciser la date de la réunion");
            return;
        }
        if (starTime.isEmpty()) {
            binding.startTimeTextField.setError("Merci de préciser l'heure du début de la réunion");
            return;
        }
        if (endTime.isEmpty()) {
            binding.endTimeTextField.setError("Merci de préciser l'heure de fin de la réunion");
            return;
        }
        if (listOfParticipants.isEmpty()) {
            binding.listOfParticipantsTextField.setError("Merci de préciser les participants de la réunion");
            return;
        }

        //todo : Pb du type de variable pour la date , la list des participants, et la room !!
        // actuellement j'ai mis la date  en "String" et la list des participants en "String",
        // fait UN "SPINNER" menu deroulant  pour la liste des rooms
        // fait : POUR LA DATE , on peut faire un "DatePicker"
        // fait : Pour l'heure , on peut faire un "TimePicker"
        // fait : pour la durée de la reunion , fait un spinner (on peut faire aussi un TimePicker )
        //pour la liste des participants : edit texte ou spinner et 1 btn plus, ou chips ?


        // Pour créer la reunion ( en recuperant dans RoomGenerator le nom de la salle et sa couleur associée suivant sa "position" choisie dans le spinner avec la methode onItemSelected)
        mMeetingRepository.createMeeting(new Meeting(object, date, starTime, endTime, listOfParticipants, RoomGenerator.generateListOfRoons().get(position)));
        Toast.makeText(this, "Bravo , la réunion a été créée", Toast.LENGTH_SHORT).show();
        finish();
    }

    // Spinner : List of rooms ( to RoomGenerator )
    private void initSpinnerRooms() {
        Spinner spinner = findViewById(R.id.spinner_list_of_rooms);

        // 1a- Create an ArrayAdapter using the string array (in resources Strings) and a default spinner layout
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        //R.array.list_of_meeting_rooms, android.R.layout.simple_spinner_item);

        // 1b- Create an ArrayAdapter pour recuperer ma liste de salles de la class RoomsGenerator
        List<Room> roomList = new ArrayList<>(RoomGenerator.generateListOfRoons());
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, roomList);

        // 2- Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner. Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(adapter);

        // 3- To define the selection event handler for a spinner, implement the AdapterView.OnItemSelectedListener interface
        // 4- Then you need to specify the interface implementation by calling
        spinner.setOnItemSelectedListener(this);
    }

    // 5- When the user selects an item from the drop-down,
    // the Spinner object receives an on-item-selected event.(voir @Override methodes onItemSelected et OnNothingSelected)
    @Override //5-1
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // An item was selected. You can retrieve the selected item using
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

        //Todo : supprimer les lignes ci-dessous
        //Get text from selected item's position & set it to TextView
        //String room = binding.addMeetingRoomTextField.getEditText().setText(parent.getItemAtPosition(position).toString());
        //room.setText(parent.getItemAtPosition(position).toString());
        //Objects.requireNonNull(binding.addMeetingRoomTextField.getEditText()).setText(parent.getItemAtPosition(position).toString());

        this.position = position;
    }

    @Override //5-2
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback spinner
    }

    // DatePicker
    private void initDatePicker() {
        binding.selectDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }

    // For DatePicker,
    // Use onDateSet (Callback/rappel) to send the date in this activity and show it here.
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        //String currentDateString = simpleDateFormat.format(c.getTime());
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        binding.date2TextField.setText(currentDateString);
    }

    // TimePicker
    private void initTimePicker() {
        binding.selectTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
    }

    // For TimePicker,
    // Use onTimeteSet (Callback/rappel) to send the time in this activity and show it here.
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        //binding.startTime2TextField.setText(hourOfDay + "h" + minute);
        binding.startTime2TextField.setText(String.format("%02dh%02d", hourOfDay, minute));
    }

    // Spinner : List of meeting durations ( list in resources_values )
    private void initSpinnerDuration() {

        // 1a- Create an ArrayAdapter using the string array (in resources Strings) and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.meeting_duration_array, android.R.layout.simple_spinner_item);

        // 2- Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner . Setting the ArrayAdapter data on the Spinner
        binding.endTime2TextField.setAdapter(adapter);

        //spinner item click handler
        binding.endTime2TextField.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // An item was selected. You can retrieve the selected item using
                String text = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
                //todo : le Toast ne s'affiche pas , pourquoi ?
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
