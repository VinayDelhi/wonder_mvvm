package com.example.wonder.wonder_mvvm.view.note;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.wonder.wonder_mvvm.R;
import com.example.wonder.wonder_mvvm.application.di.component.WonderFragmentComponent;
import com.example.wonder.wonder_mvvm.application.global.ActionBarMode;
import com.example.wonder.wonder_mvvm.base.WonderBaseActionBar;
import com.example.wonder.wonder_mvvm.base.WonderFragment;
import com.example.wonder.wonder_mvvm.base.WonderObserver;
import com.example.wonder.wonder_mvvm.data.model.Note;
import com.example.wonder.wonder_mvvm.data.model.NoteList;
import com.example.wonder.wonder_mvvm.view.newnote.CreateNoteFragment;
import com.example.wonder.wonder_mvvm.view.note.adapter.NoteListAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class NoteFragment extends WonderFragment implements NoteNavigator {

    @Inject
    NoteViewModel noteViewModel;
    @BindView(R.id.fragment_note_rv_item)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_note_bottom_next_btn)
    TextView tvNextNavigation;

    @Inject
    NoteListAdapter noteListAdapter;

    private List<Note> noteList;
    private Note note;

    @Override
    protected void inject(WonderFragmentComponent injector) {
        injector.inject(this);
    }

    @Override
    protected int fragmentLayout() {
        return R.layout.fragment_note;
    }

    @Override
    protected void loadData(Bundle bundle) {
        super.loadData(bundle);
        String json  = bundle.getString("noteList");
        Gson gson = new Gson();
        Type type = new TypeToken<List<Note>>(){}.getType();
        noteList = gson.fromJson(json, type);
    }

    @Override
    protected void onViewManaged(View view) {

        noteViewModel.setView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tvNextNavigation.setOnClickListener(this);
        if(noteList == null || noteList.size()== 0){
            noteViewModel.getNotes();
        }else{
            noteListAdapter.setNotes(noteList);
            recyclerView.setAdapter(noteListAdapter);
        }
    }

    @Override
    public void setNoteList(NoteList noteListFromServer) {
        noteList = noteListFromServer.getNotes();
        if(noteList!=null){
            noteListAdapter.setNotes(noteList);
            recyclerView.setAdapter(noteListAdapter);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fragment_note_bottom_next_btn){
            CreateNoteFragment createNoteFragment = new CreateNoteFragment();
            createNoteFragment.getCreatedNote().subscribe(new WonderObserver<Note>() {
                @Override
                public void onNext(Note note) {
                    super.onNext(note);
                    if(note!=null && noteList!=null){
                        noteList.add(note);
                        saveNoteListInBundle(noteList);
                    }

                }
            });
            getWonderFragmentManager().addToBackStack(createNoteFragment);
        }
    }

    @Override
    protected void setActionBar(WonderBaseActionBar actionBar) {
        actionBar.setTitle("Note List");
        actionBar.setActionBarMode(ActionBarMode.MODE_NONE_BUTTON);
        actionBar.show();
    }

    @Override
    public void saveNoteListInBundle(List<Note> noteList) {
        Bundle args = new Bundle();
        Gson gson = new Gson();
        Type type = new TypeToken<List<Note>>() {}.getType();
        String json = gson.toJson(noteList, type);
        args.putString("noteList", json);
        this.setArguments(args);
    }
}
