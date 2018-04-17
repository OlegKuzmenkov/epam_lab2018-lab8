package com.oleg_kuzmenkov.android.twofragmentsapplication;

import java.io.Serializable;

public class Note implements Serializable {

    private String mIdNote;
    private String mTitleNote;
    private String mDateOfCreationNote;
    private String mTextNote;

    public Note() {
    }

    public String getIdNote() {
        return mIdNote;
    }

    public void setIdNote(String idNote) {
        mIdNote = idNote;
    }

    public String getTitleNote() {
        return mTitleNote;
    }

    public void setTitleNote(String titleNote) {
        mTitleNote = titleNote;
    }

    public String getTextNote() {
        return mTextNote;
    }

    public void setTextNote(String textNote) {
        mTextNote = textNote;
    }

    public String getDateOfCreationote() {
        return mDateOfCreationNote;
    }

    public void setDateOfCreationote(String dateOfCreationote) {
        mDateOfCreationNote = dateOfCreationote;
    }
}
