package com.example.wonder.wonder_mvvm.application.di.component;

import com.example.wonder.wonder_mvvm.application.di.scope.ActivityScope;
import com.example.wonder.wonder_mvvm.view.newnote.CreateNoteFragment;
import com.example.wonder.wonder_mvvm.view.note.NoteFragment;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class)
public interface WonderFragmentComponent {
    void inject(NoteFragment noteFragment);
    void inject(CreateNoteFragment createNoteFragment);

}
