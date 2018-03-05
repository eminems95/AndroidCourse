package com.anotherdeveloper.androidcourse.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anotherdeveloper.androidcourse.R;
import com.anotherdeveloper.androidcourse.activities.DetailsActivity;
import com.anotherdeveloper.androidcourse.models.Contact;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Marcin on 2017-12-20.
 * :)
 */

public class ContactsRecyclerViewAdapter extends RecyclerView.Adapter<ContactsRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Contact> items;

    public ContactsRecyclerViewAdapter(ArrayList<Contact> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Contact item = items.get(position);
        final Context context = holder.itemView.getContext();
        String nameAndSurname = item.getFirstName() + " " + item.getLastName();
        holder.name.setText(nameAndSurname);
        holder.number.setText(item.getPhone());
        holder.email.setText(item.getEmail());



        Picasso.with(holder.email.getContext())
                .load(item.getAvatar())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(holder.image, getImageOnline(holder, item, context));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent(holder, item);
                setItemAlpha(item, holder);
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                remove(item);
                return true;
            }
        });
    }

    public void updateContactListItems(List<Contact> employees) {
        final ContactsDiffCallback diffCallback = new ContactsDiffCallback(this.items, employees);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.items.clear();
        this.items.addAll(employees);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    private Callback getImageOnline(final ViewHolder holder, final Contact item, final Context context) {
        return new Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError() {
                //Try again online if cache failed
                Picasso.with(context)
                        .load(item.getAvatar())
                        .error(R.drawable.lenny)
                        .into(holder.image, new Callback() {
                            @Override
                            public void onSuccess() {
                            }

                            @Override
                            public void onError() {
                                Log.v("Picasso", "Could not fetch image");
                            }
                        });
            }
        };
    }


    @NonNull
    private Intent getIntent(ViewHolder holder, Contact item) {
        Intent intent = new Intent(holder.itemView.getContext(), DetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("name", holder.name.getText());
        intent.putExtra("number", holder.number.getText());
        intent.putExtra("email", holder.email.getText());
        intent.putExtra("image", item.getAvatar());
        return intent;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void remove(Contact item) {
        int position = items.indexOf(item);
        items.remove(position);
        notifyItemRemoved(position);
    }

    private void setItemAlpha(Contact item, ViewHolder holder) {
        if (setIfContactWasSeen(item)) {
            holder.itemView.setAlpha(0.7f);
            Log.d("after flag change", String.valueOf(item.isSeen()));
        }
    }

    private boolean setIfContactWasSeen(Contact item) {
        if (!item.isSeen()) {
            item.setSeen(true);
        }
        return item.isSeen();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageview_contacts_profilephoto)
        ImageView image;
        @BindView(R.id.textview_contacts_username)
        TextView name;
        @BindView(R.id.textview_contacts_phonenumber)
        TextView number;
        @BindView(R.id.textview_contacts_email)
        TextView email;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
