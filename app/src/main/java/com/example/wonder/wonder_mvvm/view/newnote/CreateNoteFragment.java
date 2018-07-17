package com.example.wonder.wonder_mvvm.view.newnote;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wonder.wonder_mvvm.R;
import com.example.wonder.wonder_mvvm.application.di.component.WonderFragmentComponent;
import com.example.wonder.wonder_mvvm.application.global.ActionBarMode;
import com.example.wonder.wonder_mvvm.base.WonderBaseActionBar;
import com.example.wonder.wonder_mvvm.base.WonderFragment;
import com.example.wonder.wonder_mvvm.data.model.Note;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Emitter;
import io.reactivex.Observable;

public class CreateNoteFragment extends WonderFragment implements CreateNoteNavigator {

    @Inject
    CreateNoteViewModel createNoteViewModel;

    @BindView(R.id.fragment_note_bottom_submit_btn)
    TextView tvNextNavigation;
    @BindView(R.id.fragment_create_note_et_desc)
    EditText etNoteDescription;

    private Emitter<Note>  createdNoteEmitter;

    @Override
    protected void inject(WonderFragmentComponent injector) {
        injector.inject(this);
    }

    @Override
    protected int fragmentLayout() {
        return R.layout.fragment_create_note;
    }

    @Override
    protected void onViewManaged(View view) {
        createNoteViewModel.setView(this);
        tvNextNavigation.setOnClickListener(this);
    }

    @Override
    protected void setActionBar(WonderBaseActionBar actionBar) {
        actionBar.setTitle("Create Note");
        actionBar.setActionBarMode(ActionBarMode.MODE_BACK_BUTTON);
        actionBar.show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fragment_note_bottom_submit_btn){
          if(etNoteDescription.getText()!=null && !etNoteDescription.getText().equals("")){
              postNote(etNoteDescription.getText().toString());
          }
        }
    }

    @Override
    public void postNote(String note) {
      createNoteViewModel.postNote(note);
    }

    @Override
    public void setNote(Note note) {
        if (note != null && createdNoteEmitter!=null) {
                createdNoteEmitter.onNext(note);
                //selectedHabitEmitter.onComplete();
                getWonderFragmentManager().popBackStack();
        }
        //Toast.makeText(getActivity(), note.getDescription(), Toast.LENGTH_LONG).show();
    }

    public Observable<Note> getCreatedNote() {
        return Observable.create(emitter -> createdNoteEmitter = emitter);
    }
}
