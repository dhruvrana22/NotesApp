package com.example.notesapp.helpers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.notesapp.R;
import com.example.notesapp.models.Note;

import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<Note> {

    public NoteAdapter(@NonNull Context context, ArrayList<Note> resource) {
        super(context, 0, resource);
        Log.e("NotesApp", String.valueOf(resource == null));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Note note = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_layout, parent, false);
        }

        TextView title;
        title = (TextView) convertView.findViewById(R.id.title);
        TextView content = (TextView) convertView.findViewById(R.id.content);
        TextView user = (TextView) convertView.findViewById(R.id.user);

        title.setText(note.getTitle());
        content.setText(note.getContent());
//        user.setText(String.format("%s", note.getUserName()));

        return convertView;
    }
}