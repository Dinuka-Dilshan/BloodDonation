package com.example.blooddonation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BloodRequestCustomAdapter extends RecyclerView.Adapter<BloodRequestCustomAdapter.ViewHolder> {
    private List<BloodRequest> requestList;

    public BloodRequestCustomAdapter(List<BloodRequest> requestList) {
        this.requestList = requestList;
    }


    @Override
    public BloodRequestCustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.blood_request_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BloodRequestCustomAdapter.ViewHolder viewHolder, int position) {
        viewHolder.getName().setText(requestList.get(position).getName());
        viewHolder.getDescription().setText(requestList.get(position).getDescription());
        viewHolder.getPhone().setText(requestList.get(position).getPhone());
        viewHolder.getTown().setText(requestList.get(position).getTown());
    }



    @Override
    public int getItemCount() {
        return requestList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
;
        private final TextView name;
        private final TextView description;
        private final TextView phone;
        private final TextView town;

        public ViewHolder(View v) {
            super(v);

            name = v.findViewById(R.id.blood_request_name);
            description = v.findViewById(R.id.blood_request_description);
            phone = v.findViewById(R.id.blood_request_phone);
            town = v.findViewById(R.id.blood_request_town);
        }

        public TextView getName() {
            return name;
        }

        public TextView getDescription() {
            return description;
        }

        public TextView getPhone() {
            return phone;
        }

        public TextView getTown() {
            return town;
        }
    }
}
