package com.example.wonder.wonder_mvvm.data.repository;

import com.example.wonder.wonder_mvvm.data.model.Note;
import com.example.wonder.wonder_mvvm.data.model.NoteList;
import com.example.wonder.wonder_mvvm.data.model.NotePostReq;
import com.example.wonder.wonder_mvvm.data.network.ApiResponse;
import com.example.wonder.wonder_mvvm.data.network.ApiServices;
import com.example.wonder.wonder_mvvm.repository.LocalRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class NoteRepository {

    @Inject
    protected ApiServices apiServices;

    @Inject
    protected LocalRepository localRepository;

    @Inject
    public NoteRepository() {
    }

    public Observable<NoteList> getNotes() {

        return getNoteList();

        /*return apiServices.getNotes(
                "abc",
                null, null
        ).map(ApiResponse::getData);*/
    }


    public Observable<Note> postNote(String note) {

        return getNote(note);

       /* if(note!=null){
            HashMap<String, NotePostReq> mapNote = new HashMap<>();
            NotePostReq notePostReq = new NotePostReq();
            notePostReq.setDescription(note);
            mapNote.put("description",notePostReq);
            return apiServices.postNote(
                    "abc",mapNote
            ).map(ApiResponse::getData);
        }

        return null;*/

    }

    private Observable<NoteList> getNoteList(){
        NoteList noteList = new NoteList();
        List<Note> notes = new ArrayList<>();
        for(int i=0; i<10; i++){
            Note note = new Note();
            note.setDescription("India (IAST: Bhārat), also called the Republic of India (IAST: Bhārat Gaṇarājya),[19][e] is a country in South Asia. It is the seventh-largest country by area, the second-most populous country (with over 1.2 billion people), and the most populous democracy in the world.");
            notes.add(note);
        }
        noteList.setNotes(notes);
        return Observable.fromArray(noteList);


    }

// Dummy Method
    private Observable<Note> getNote(String noteStr){
        Note note = new Note();
        note.setDescription(noteStr);
        return Observable.fromArray(note);
    }

}
