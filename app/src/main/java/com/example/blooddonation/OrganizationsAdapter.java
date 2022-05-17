package com.example.blooddonation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.util.List;

public class OrganizationsAdapter extends RecyclerView.Adapter<OrganizationsAdapter.ViewHolder> {

    private List<Organizer> organizers;
    static EditText name;
    static TextView venue;
    static EditText time;


    public OrganizationsAdapter(List<Organizer> organizers) {
        this.organizers = organizers;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_organization, parent, false);
        return new OrganizationsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getName().setText(organizers.get(position).getName());
        holder.getTime().setText(organizers.get(position).getTime());
        holder.setId(organizers.get(position).getId());
    }


    @Override
    public int getItemCount() {
        return organizers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView time;

        private String id;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.findViewById(R.id.edit_organizer).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OrganizationsAdapter.name.setText(getName().getText().toString());
                    OrganizationsAdapter.time.setText(getTime().getText().toString());
                    OrganizationsAdapter.venue.setText("Select location");
                    Organizations.updateId = id;
                }
            });

            name = (TextView) v.findViewById(R.id.org_name);
            time = (TextView) v.findViewById(R.id.org_time);
        }

        public TextView getName() {
            return name;
        }

        public void setId(String id) {
            this.id = id;
        }

        public TextView getTime() {
            return time;
        }


    }
}
