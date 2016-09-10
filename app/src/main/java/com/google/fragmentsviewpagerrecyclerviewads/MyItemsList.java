package com.google.fragmentsviewpagerrecyclerviewads;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MyItemsList {
    private static MyItemsList sMyItemsList;
    private ArrayList<MyItem> mMyItems;

    public static MyItemsList get(Context context) {
        if (sMyItemsList == null) {
            sMyItemsList = new MyItemsList(context);
        }
        return sMyItemsList;
    }

    private MyItemsList(Context context) {
        mMyItems = new ArrayList<>();
        mMyItems.add(new MyItem(context.getString(R.string.item1)));
        mMyItems.add(new MyItem(context.getString(R.string.item2)));
        mMyItems.add(new MyItem(context.getString(R.string.item3)));
        mMyItems.add(new MyItem(context.getString(R.string.item4)));
        mMyItems.add(new MyItem(context.getString(R.string.item5)));
        mMyItems.add(new MyItem(context.getString(R.string.item6)));
        mMyItems.add(new MyItem(context.getString(R.string.item7)));
        mMyItems.add(new MyItem(context.getString(R.string.item8)));
        mMyItems.add(new MyItem(context.getString(R.string.item9)));
    }

    public List<MyItem> getMyItems() {
        return mMyItems;
    }

    public MyItem getItem(UUID id) {
        for (MyItem myItem : mMyItems) {
            if (myItem.getId().equals(id)) {
                return myItem;
            }
        }
        return null;
    }
}