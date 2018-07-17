package com.example.wonder.wonder_mvvm.view.note.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.wonder.wonder_mvvm.base.recycler.WonderRecyclerAdapter;
import com.example.wonder.wonder_mvvm.data.model.Note;
import com.example.wonder.wonder_mvvm.view.note.adapter.holder.NoteListHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class NoteListAdapter extends WonderRecyclerAdapter<NoteListHolder> {

    private List<Note> notes = new ArrayList<>();
    private Note note;

    @Inject
    NoteListAdapter() {
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public NoteListHolder onCreateViewHolder(Context context, ViewGroup parent, int itemLayout) {
        return new NoteListHolder(parent);
    }

    @Override
    public void onBindVH(NoteListHolder holder, int position) {

        holder.setData(notes.get(position));

    }

    @Override
    protected int getCount() {
        return notes.size();
    }

    public void setNote(Note note){
        if(note!=null){
            notes.add(note);
            notifyDataSetChanged();
        }

    }

    public Note getNote(){

        return note;
    }
}
