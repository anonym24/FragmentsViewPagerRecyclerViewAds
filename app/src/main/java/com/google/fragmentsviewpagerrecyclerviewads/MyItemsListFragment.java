package com.google.fragmentsviewpagerrecyclerviewads;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import java.util.ArrayList;
import java.util.List;

public class MyItemsListFragment extends Fragment {

    private AdView mAdView;
    private ItemAdapter mItemAdapter;
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myitem_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.item_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        mAdView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdView.resume();
        updateUI();
    }

    @Override
    public void onPause() {
        mAdView.pause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mAdView.destroy();
        super.onDestroy();
    }

    public void updateUI() {
        MyItemsList myItemsList = MyItemsList.get(getActivity());
        List<MyItem> myItems = myItemsList.getMyItems();
        if (mItemAdapter == null) {
            mItemAdapter = new ItemAdapter(myItems);
            mRecyclerView.setAdapter(mItemAdapter);
        } else {
            mItemAdapter.notifyDataSetChanged();
        }
    }

    private class ItemHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mTextView;
        private MyItem mItem;

        public ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTextView = (TextView) itemView.findViewById(R.id.item_title);
        }

        public void bindItem(MyItem item) {
            mItem = item;
            mTextView.setText(mItem.getTitle());
        }

        @Override
        public void onClick(View v) {
            Intent intent = MyItemPagerActivity.newIntent(getActivity(), mItem.getId());
            startActivity(intent);
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
        private List<MyItem> mMyItems;

        public ItemAdapter(List<MyItem> myItems) {
            mMyItems = myItems;
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item, parent, false);
            return new ItemHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            MyItem myItem = mMyItems.get(position);
            holder.bindItem(myItem);
        }

        @Override
        public int getItemCount() {
            return mMyItems.size();
        }
    }
}