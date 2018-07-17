package com.example.wonder.wonder_mvvm.view.newnote;

import com.example.wonder.wonder_mvvm.base.interfaces.IView;
import com.example.wonder.wonder_mvvm.data.model.Note;
import com.example.wonder.wonder_mvvm.data.model.NoteList;


public interface CreateNoteNavigator extends IView {

    void postNote(String note);
    void setNote(Note note);

}
