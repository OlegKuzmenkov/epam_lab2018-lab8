package com.oleg_kuzmenkov.android.twofragmentsapplication;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;


public class SecondFragment extends Fragment {

    private static final String BUNDLE_CONTENT = "bundle_content";
    private static final String TAG = "Message";

    private TextView mIdNote;
    private TextView mIdTitle;
    private TextView mDateOfCreationNote;
    private TextView mTextNote;
    private Button mAnimationButton;

    public static SecondFragment newInstance(Note note) {
        SecondFragment fragment = new SecondFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable(BUNDLE_CONTENT, note);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_second, container, false);

        mAnimationButton = v.findViewById(R.id.animation_button);
        mIdNote = v.findViewById(R.id.note_id);
        mIdTitle = v.findViewById(R.id.note_title);
        mDateOfCreationNote = v.findViewById(R.id.note_date_of_creation);
        mTextNote = v.findViewById(R.id.note_text);

        if (getArguments() != null && getArguments().containsKey(BUNDLE_CONTENT)) {
            setContent((Note) getArguments().getSerializable(BUNDLE_CONTENT));
        }
        else {
            throw new IllegalArgumentException("Must be created through newInstance(...)");
        }

        mAnimationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               animateFragment(v);
            }
        });

        Log.i(TAG, "Second fragment return view!!!");
        return v;
    }

    /**
     * Animate all of fragment's content
     */
    private void animateFragment(View view){
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "rotationY", 0.0f, 360f);
        animation.setDuration(1600);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();
    }

    /**
     * Set the received content in the fragment's view
     */
    private void setContent(Note note){
        if(note != null) {
            mIdNote.setText(note.getIdNote());
            mIdTitle.setText(note.getTitleNote());
            mDateOfCreationNote.setText(note.getDateOfCreationote());
            mTextNote.setText(note.getTextNote());
        }
    }
}
