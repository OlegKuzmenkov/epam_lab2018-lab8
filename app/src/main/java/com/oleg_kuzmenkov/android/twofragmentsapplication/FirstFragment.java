package com.oleg_kuzmenkov.android.twofragmentsapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.io.Serializable;

import java.util.List;

public class FirstFragment extends Fragment {

    private static final String BUNDLE_CONTENT = "bundle_content";

    private RecyclerView mRecyclerView;
    private NoteAdapter mAdapter;
    private onSomeEventListener someEventListener;

    public interface onSomeEventListener {
        void someEvent(String s);
    }

    public static FirstFragment newInstance(List<Note> content) {
        FirstFragment fragment = new FirstFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable(BUNDLE_CONTENT, (Serializable) content);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            someEventListener = (onSomeEventListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_first, container, false);
        mRecyclerView = v.findViewById(R.id.note_recycler_view);

        if (getArguments() != null && getArguments().containsKey(BUNDLE_CONTENT))
            init((List<Note>) getArguments().getSerializable(BUNDLE_CONTENT));
        else
            throw new IllegalArgumentException("Must be created through newInstance(...)");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }

    /**
     * Initialize our RecyclerView
     */
    private void init(List<Note> notes){
        if(mAdapter == null)
            mAdapter = new NoteAdapter(notes);

        mRecyclerView.setAdapter(mAdapter);
        //  mAdapter.notifyDataSetChanged();
    }

    private class NoteAdapter extends RecyclerView.Adapter<NoteHolder> {

        private List<Note> mNotes;

        public NoteAdapter(List<Note> notes) {
            mNotes = notes;
        }

        @Override
        public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.note_view, parent, false);
            return new NoteHolder(view);
        }

        @Override
        public void onBindViewHolder(NoteHolder holder, int position) {
            Note note = mNotes.get(position);
            holder.bindNote(note);
        }

        @Override
        public int getItemCount() {
            return  mNotes.size();
        }
    }

    private class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mIdNoteTextView;
        private TextView mTitleNoteTextView;
        private TextView mDateOfCreationNoteView;

        private Note mNote;

        public NoteHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            mIdNoteTextView = itemView.findViewById(R.id.note_id);
            mTitleNoteTextView = itemView.findViewById(R.id.note_title);
            mDateOfCreationNoteView = itemView.findViewById(R.id.note_date_of_creation);
        }

        /**
         * Bind note's content
         */
        public void bindNote(Note note) {
            mNote = note;
            mIdNoteTextView.setText(mNote.getIdNote());
            mTitleNoteTextView.setText(mNote.getTitleNote());
            mDateOfCreationNoteView.setText(mNote.getDateOfCreationote());
        }

        @Override
        public void onClick(View view) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                someEventListener.someEvent(mNote.getIdNote());
            else {
                Intent intent = new Intent(getActivity(), ViewElementOfListActivity.class);
                intent.putExtra(BUNDLE_CONTENT, mNote);
                startActivity(intent);
            }
        }
    }
}
