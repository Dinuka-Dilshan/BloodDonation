package com.example.blooddonation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>  {

    private List<User> userList;

    public CustomAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder viewHolder, int position) {
        viewHolder.getNames().setText(userList.get(position).getName());
        viewHolder.getAddress().setText(userList.get(position).getAddress());
        viewHolder.getBlood_group().setText(userList.get(position).getBloodGroup());
        viewHolder.getImage().setImageResource(R.drawable.index);
        viewHolder.getContact_no().setText(Integer.toString(userList.get(position).getPhoneNumber()));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView name;
        private final TextView contact_no;
        private final TextView address;
        private final TextView blood_group;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //
                }
            });
            image = (ImageView) v.findViewById(R.id.recycle_image);
            name = (TextView) v.findViewById(R.id.recycle_name);
            contact_no = (TextView) v.findViewById(R.id.recycle_contact_no);
            address = (TextView) v.findViewById(R.id.recycle_address);
            blood_group = (TextView) v.findViewById(R.id.recycle_blood_group);
        }

        public ImageView getImage() {
            return image;
        }

        public TextView getNames() {
            return name;
        }

        public TextView getContact_no() {
            return contact_no;
        }

        public TextView getAddress() {
            return address;
        }

        public TextView getBlood_group() {
            return blood_group;
        }
    }
}
