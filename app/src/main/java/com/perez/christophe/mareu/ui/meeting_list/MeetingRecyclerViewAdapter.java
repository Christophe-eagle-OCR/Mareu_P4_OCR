package com.perez.christophe.mareu.ui.meeting_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.perez.christophe.mareu.R;
import com.perez.christophe.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christophe on 03/09/2021.
 */
public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder> {


    private final List<Meeting> mMeetings;

    private final DeleteItemListener callback;

    public MeetingRecyclerViewAdapter(List<Meeting> meetings, DeleteItemListener deleteItemListener) {
        mMeetings = meetings;
        callback = deleteItemListener;
    }


    // Interface for callback to delete item
    public interface DeleteItemListener{
        void onDeleteItem(int position,Meeting meeting);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meeting_list, parent, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        holder.displayMeeting(meeting);

        // on click , delete item ( callback.onDeleteItem)
        holder.mDeleteRoonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onDeleteItem(holder.getAdapterPosition(), meeting);

                Context context = v.getContext();
                Toast.makeText(context, "La réunion a été supprimée", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mMeetings.size();
    }


    /**
     * Provide a reference to the type of view that you are using
     * (custon ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mObjectOfMeeting;
        public final TextView mStartTimeMeeting;
        public final TextView mRoom;
        public final TextView mParticipants;
        public final ImageView mPictureOfRoon;
        public final ImageButton mDeleteRoonButton;

        public ViewHolder(View itemView) {
            super(itemView);
            mObjectOfMeeting = itemView.findViewById(R.id.item_meeting_object_of_meeting);
            mStartTimeMeeting = itemView.findViewById(R.id.item_meeting_start_time_meeting);
            mRoom = itemView.findViewById(R.id.item_meeting_room);
            mParticipants = itemView.findViewById(R.id.item_meeting_participants);
            mPictureOfRoon = itemView.findViewById(R.id.item_meeting_picture_room);
            mDeleteRoonButton = itemView.findViewById(R.id.item_meeting_delete_room_button);
        }


        public void displayMeeting(Meeting meeting) {
            // mettre ce que l'on affiche dans l'item , puis setText et get..

            mObjectOfMeeting.setText(meeting.getObject()+"   -");
            mStartTimeMeeting.setText(meeting.getStartTime()+"-"+meeting.getEndTime()+"   -");
            mRoom.setText( meeting.getRoom().getNameOfRoom());
            mParticipants.setText(meeting.getParticipants());
            mPictureOfRoon.setColorFilter(meeting.getRoom().getColorOfRoom());
            mDeleteRoonButton.setVisibility(View.VISIBLE);
        }
    }
}
