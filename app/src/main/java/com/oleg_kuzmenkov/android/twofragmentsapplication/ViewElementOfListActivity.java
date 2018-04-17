package com.oleg_kuzmenkov.android.twofragmentsapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

public class ViewElementOfListActivity extends FragmentActivity {

    private static final String BUNDLE_CONTENT = "bundle_content";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_element_of_list);

        Note note = (Note)getIntent().getSerializableExtra(BUNDLE_CONTENT);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = SecondFragment.newInstance(note);
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }
}
