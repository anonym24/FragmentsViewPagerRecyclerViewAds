package com.google.fragmentsviewpagerrecyclerviewads;

import java.util.UUID;

public class MyItem {

    private UUID mId;
    private String mTitle;

    public MyItem(String title) {
        this(UUID.randomUUID());
        mTitle = title;
    }

    public MyItem(UUID id) {
        mId = id;
    }
    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }
}