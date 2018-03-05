package com.anotherdeveloper.androidcourse.adapters;

import android.support.v7.util.DiffUtil;

import com.anotherdeveloper.androidcourse.models.Contact;

import java.util.List;

/**
 * Created by Marcin on 2018-01-27.
 * :)
 */

public class ContactsDiffCallback extends DiffUtil.Callback {

    private final List<Contact> mOldContactList;
    private final List<Contact> mNewContactList;

    public ContactsDiffCallback(List<Contact> mOldContactList, List<Contact> mNewContactList) {
        this.mOldContactList = mOldContactList;
        this.mNewContactList = mNewContactList;
    }

    @Override
    public int getOldListSize() {
        return mOldContactList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewContactList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldContactList.get(oldItemPosition).getId() == mNewContactList.get(
                newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final Contact oldContact = mOldContactList.get(oldItemPosition);
        final Contact newContact = mNewContactList.get(newItemPosition);

        return oldContact.getId().equals(newContact.getId());
    }
}
