package com.google.fragmentsviewpagerrecyclerviewads;

import android.support.v4.app.Fragment;

public class MyItemsListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new MyItemsListFragment();
    }
}