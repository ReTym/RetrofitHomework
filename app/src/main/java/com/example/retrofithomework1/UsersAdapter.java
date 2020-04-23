package com.example.retrofithomework1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private List<User> users;

    public void setData(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UsersAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.user_item;

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        UserViewHolder viewHolder = new UserViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            User user = users.get(position);
            String content = "";
            content += "ID: " + user.getId() + "\n";
            content += "User ID: " + user.getUserId() + "\n";
            content += "Title: " + user.getTitle() + "\n";
            content += "Text: " + user.getBody();

            holder.listItemUserView.setText(content);
            holder.site.setText(R.string.site_name);
    }

    @Override
    public int getItemCount() {
        if (users == null)
            return 0;
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        TextView listItemUserView;
        TextView site;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            listItemUserView = itemView.findViewById(R.id.tv_user_item);
            site = itemView.findViewById(R.id.tv_site);
        }
    }
}
