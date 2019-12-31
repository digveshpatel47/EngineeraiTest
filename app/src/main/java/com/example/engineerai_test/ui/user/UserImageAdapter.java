package com.example.engineerai_test.ui.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.engineerai_test.R;
import com.example.engineerai_test.ui.utility.Utility;

import java.util.ArrayList;

public class UserImageAdapter extends RecyclerView.Adapter<UserImageAdapter.ViewHolder> {

    private ArrayList<String> usersImageArrayList;

    UserImageAdapter(ArrayList<String> usersImageArrayList) {
        this.usersImageArrayList = usersImageArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user_image, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String usersImage = usersImageArrayList.get(position);
        Utility.loadImage(holder.ivUserImageList, usersImage);


    }

    @Override
    public int getItemCount() {
        return usersImageArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView ivUserImageList;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivUserImageList =  itemView.findViewById(R.id.ivUserImageList);
        }
    }
}
