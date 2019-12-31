package com.example.engineerai_test.ui.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.engineerai_test.R;
import com.example.engineerai_test.data.user.response.UsersItem;
import com.example.engineerai_test.ui.utility.Utility;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private ArrayList<UsersItem> usersItemArrayList;

    UserAdapter(ArrayList<UsersItem> usersItemArrayList) {
        this.usersItemArrayList = usersItemArrayList;
    }

    void setUsersItemArrayList(ArrayList<UsersItem> usersItemArrayList) {
        this.usersItemArrayList = usersItemArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UsersItem usersItem = usersItemArrayList.get(position);
        holder.tvUserName.setText(usersItem.getName());
        Utility.loadCircularImage(holder.ivUserImage, usersItem.getImage());

        if(usersItem.getItems().size() > 0){
            GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.rvUserImage.getContext(),2);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if((usersItem.getItems().size() % 2) == 0){
                        return 1;
                    }else if(position == 0){
                        return 2;
                    }else{
                        return 1;
                    }
                }
            });
            holder.rvUserImage.setLayoutManager(gridLayoutManager);
            UserImageAdapter userImageAdapter = new UserImageAdapter((ArrayList<String>) usersItem.getItems());
            holder.rvUserImage.setAdapter(userImageAdapter);
        }

    }

    @Override
    public int getItemCount() {
        return usersItemArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView ivUserImage;
        AppCompatTextView tvUserName;
        RecyclerView rvUserImage;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUserName = itemView.findViewById(R.id.tvUserName);
            ivUserImage =  itemView.findViewById(R.id.ivUserImage);
            rvUserImage =  itemView.findViewById(R.id.rvUserImage);
        }
    }
}
