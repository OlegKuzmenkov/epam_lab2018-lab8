package com.oleg_kuzmenkov.android.twofragmentsapplication;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements FirstFragment.onSomeEventListener {

    private List<Note> mNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.first_fragment_container);

        if (fragment == null) {
            fragment = FirstFragment.newInstance(mNotes);
            fm.beginTransaction().add(R.id.first_fragment_container, fragment).commit();
        }
        // if orientation is a landscape so views both fragments on the screen
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Fragment fragmentSecond = fm.findFragmentById(R.id.second_fragment_container);
            if (fragmentSecond == null) {
                fragmentSecond = SecondFragment.newInstance(mNotes.get(0));
                fm.beginTransaction().add(R.id.second_fragment_container, fragmentSecond).commit();
                //getSupportFragmentManager().executePendingTransactions();
            }
        }
    }

    /**
     * Processing selection in the RecyclerView
     */
    @Override
    public void someEvent(String event) {

        Note note = getNote(event);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragmentSecond = fm.findFragmentById(R.id.second_fragment_container);

        TextView mIdNote = fragmentSecond.getView().findViewById(R.id.note_id);
        TextView mTitleNote = fragmentSecond.getView().findViewById(R.id.note_title);
        TextView mDateOfCreationNote = fragmentSecond.getView().findViewById(R.id.note_date_of_creation);
        TextView mTextNote = fragmentSecond.getView().findViewById(R.id.note_text);

        mIdNote.setText(note.getIdNote());
        mTitleNote.setText(note.getTitleNote());
        mDateOfCreationNote.setText(note.getDateOfCreationote());
        mTextNote.setText(note.getTextNote());

    }

    /**
     * Initialize our list of notes
     */
    private void init(){

        mNotes = new ArrayList<>();

        Note note = new Note();
        note.setIdNote("Note #1");
        note.setTitleNote("Go to shop");
        note.setDateOfCreationote("10.04.2018");
        note.setTextNote("Buy bread, tomatoes and butter");
        mNotes.add(note);

        note = new Note();
        note.setIdNote("Note #2");
        note.setTitleNote("Go to the post office");
        note.setDateOfCreationote("10.04.2018");
        note.setTextNote("Buy two letters and send one of them to the parents");
        mNotes.add(note);

        note = new Note();
        note.setIdNote("Note #3");
        note.setTitleNote("Read last news");
        note.setDateOfCreationote("11.04.2018");
        note.setTextNote("Read more about changes in passenger traffic and sports news ");
        mNotes.add(note);

        note = new Note();
        note.setIdNote("Note #4");
        note.setTitleNote("Buy a present");
        note.setDateOfCreationote("12.04.2018");
        note.setTextNote("Buy a gift for a friend on his birthday");
        mNotes.add(note);

        note = new Note();
        note.setIdNote("Note #5");
        note.setTitleNote("Pay the bills");
        note.setDateOfCreationote("12.04.2018");
        note.setTextNote("Pay for internet and phone");
        mNotes.add(note);
    }

    /**
     * Get note by id
     */
    public Note getNote(String id) {
        for(Note note: mNotes) {
            if(note.getIdNote().equals(id))
                return note;
        }
        return null;
    }

}
