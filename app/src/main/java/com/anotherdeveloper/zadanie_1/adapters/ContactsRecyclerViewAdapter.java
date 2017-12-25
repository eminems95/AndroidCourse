package com.anotherdeveloper.zadanie_1.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anotherdeveloper.zadanie_1.R;
import com.anotherdeveloper.zadanie_1.activities.A2;
import com.anotherdeveloper.zadanie_1.models.Contact;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Marcin on 2017-12-20.
 * :)
 */

public class ContactsRecyclerViewAdapter extends RecyclerView.Adapter<ContactsRecyclerViewAdapter.ViewHolder> {
    private List<Contact> items;
    private int itemLayout;
    private Context context;

    public ContactsRecyclerViewAdapter(List<Contact> items, int itemLayout, Context context) {
        this.items = items;
        this.itemLayout = itemLayout;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Contact item = items.get(position);
        String nameAndSurname = item.getFirstName() + " " + item.getLastName();
        holder.name.setText(nameAndSurname);
        holder.number.setText(item.getPhone());
        holder.email.setText(item.getEmail());


        Picasso.with(holder.image.getContext()).load(item.getAvatar()).error(R.drawable.lenny).into(holder.image);

        holder.itemView.setTag(item);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, A2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name", holder.name.getText());
                intent.putExtra("number", holder.number.getText());
                intent.putExtra("email", holder.email.getText());
                intent.putExtra("image", item.getAvatar());
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

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void remove(Contact item) {
        int position = items.indexOf(item);
        items.remove(position);
        notifyItemRemoved(position);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row_profile_photo_image_view)
        ImageView image;
        @BindView(R.id.row_name_text_view)
        TextView name;
        @BindView(R.id.row_number_text_view)
        TextView number;
        @BindView(R.id.row_email_text_view)
        TextView email;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
