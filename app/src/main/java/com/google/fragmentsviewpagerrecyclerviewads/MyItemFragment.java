package com.google.fragmentsviewpagerrecyclerviewads;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.UUID;

public class MyItemFragment extends Fragment {

    private static final String ITEM_ID = "item_id";
    private AdView mAdView;
    private MyItem mMyItem;

    public static MyItemFragment newInstance(UUID itemId) {
        Bundle args = new Bundle();
        args.putSerializable(ITEM_ID, itemId);
        MyItemFragment fragment = new MyItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        UUID itemId = (UUID) getArguments().getSerializable(ITEM_ID);
        MyItemsList mMyItemsList = MyItemsList.get(getActivity());
        mMyItem = mMyItemsList.getItem(itemId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_myitem, container, false);

        ((TextView) v.findViewById(R.id.item_title)).setText(mMyItem.getTitle());

        mAdView = (AdView) v.findViewById(R.id.adViewSecond);
        AdRequest adRequest = ((MyItemPagerActivity) getActivity()).getAdRequest();
        mAdView.loadAd(adRequest);

        return v;
    }

    @Override
    public void onPause() {
        mAdView.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        mAdView.resume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        mAdView.destroy();
        super.onDestroy();
    }
}