package com.google.fragmentsviewpagerrecyclerviewads;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import java.util.List;
import java.util.UUID;

public class MyItemPagerActivity extends AppCompatActivity {
    private static final String EXTRA_ITEM_ID =
            "com.google.fragmentsviewpagerrecyclerviewads.item_id";

    private List<MyItem> mMyItems;
    private AdRequest adRequest;

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, MyItemPagerActivity.class);
        intent.putExtra(EXTRA_ITEM_ID, crimeId);
        return intent;
    }

    public AdRequest getAdRequest() {
        return adRequest;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        UUID itemId = (UUID) getIntent().getSerializableExtra(EXTRA_ITEM_ID);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.activity_item_view_pager);

        MyItemsList mMyItemsList = MyItemsList.get(this);
        mMyItems = mMyItemsList.getMyItems();
        FragmentManager fragmentManager = getSupportFragmentManager();

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-0~0");

        adRequest = new AdRequest.Builder().build();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                MyItem myItem = mMyItems.get(position);
                return MyItemFragment.newInstance(myItem.getId());
            }

            @Override
            public int getCount() {
                return mMyItems.size();
            }
        });

        for (int i = 0; i < mMyItems.size(); i++) {
            if (mMyItems.get(i).getId().equals(itemId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}