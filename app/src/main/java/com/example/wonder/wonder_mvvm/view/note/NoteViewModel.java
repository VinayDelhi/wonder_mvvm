package com.example.wonder.wonder_mvvm.view.note;

import com.example.wonder.wonder_mvvm.base.BaseViewModel;
import com.example.wonder.wonder_mvvm.base.WonderObserver;
import com.example.wonder.wonder_mvvm.base.WonderObserverOptions;
import com.example.wonder.wonder_mvvm.data.model.NoteList;
import com.example.wonder.wonder_mvvm.data.repository.NoteRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class NoteViewModel extends BaseViewModel<NoteNavigator> {

    @Inject
    protected NoteRepository apiRepository;

    @Inject
    public NoteViewModel() {
    }

    public void getNotes() {
        WonderObserverOptions options = new WonderObserverOptions();
        options.setShowLoader(false);
        Observable<NoteList> notes = apiRepository.getNotes();
        if(notes!=null){
            handler.subscribe(notes, new WonderObserver<NoteList>() {
                @Override
                public void onNext(NoteList noteList) {
                    super.onNext(noteList);
                    view.setNoteList(noteList);
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
