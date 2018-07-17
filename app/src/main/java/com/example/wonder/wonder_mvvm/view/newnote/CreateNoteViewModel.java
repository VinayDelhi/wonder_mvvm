package com.example.wonder.wonder_mvvm.view.newnote;

import com.example.wonder.wonder_mvvm.base.BaseViewModel;
import com.example.wonder.wonder_mvvm.base.WonderObserver;
import com.example.wonder.wonder_mvvm.base.WonderObserverOptions;
import com.example.wonder.wonder_mvvm.data.model.Note;
import com.example.wonder.wonder_mvvm.data.repository.NoteRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CreateNoteViewModel extends BaseViewModel<CreateNoteNavigator> {

    @Inject
    protected NoteRepository apiRepository;

    @Inject
    public CreateNoteViewModel() {
    }

    public void postNote(String createdNote) {
        WonderObserverOptions options = new WonderObserverOptions();
        options.setShowLoader(false);
        Observable<Note> note = apiRepository.postNote(createdNote);
        if(note!=null){
            handler.subscribe(note, new WonderObserver<Note>() {
                @Override
                public void onNext(Note note) {
                    super.onNext(note);
                    view.setNote(note);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    view.showError(e);
                }
            });
        }

    }
}
