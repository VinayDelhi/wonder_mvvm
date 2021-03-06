package com.example.wonder.wonder_mvvm.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NoteList {
    @SerializedName("notes")
    @Expose
    private List<Note> notes = null;

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
