package com.example.wonder.wonder_mvvm.view.note.adapter.holder;

import android.view.ViewGroup;
import android.widget.TextView;


import com.example.wonder.wonder_mvvm.R;
import com.example.wonder.wonder_mvvm.base.recycler.WonderRecyclerHolder;
import com.example.wonder.wonder_mvvm.data.model.Note;

import butterknife.BindView;
public class NoteListHolder extends WonderRecyclerHolder {

    @BindView(R.id.item_note_tv_desc)
    TextView tvTitle;
    private Note data;

    public NoteListHolder(ViewGroup parent) {
        super(R.layout.item_note, parent);
    }

    public void setData(Note data) {
        this.data = data;
        tvTitle.setText(data.getDescription());

    }
}
