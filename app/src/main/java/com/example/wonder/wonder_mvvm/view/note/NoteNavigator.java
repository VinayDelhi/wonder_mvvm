package com.example.wonder.wonder_mvvm.view.note;

import com.example.wonder.wonder_mvvm.base.interfaces.IView;
import com.example.wonder.wonder_mvvm.data.model.Note;
import com.example.wonder.wonder_mvvm.data.model.NoteList;

import java.util.List;


public interface NoteNavigator extends IView {

    void setNoteList(NoteList noteList);
    void saveNoteListInBundle(List<Note> noteList);

}
