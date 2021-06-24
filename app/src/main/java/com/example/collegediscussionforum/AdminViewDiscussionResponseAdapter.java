package com.example.collegediscussionforum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminViewDiscussionResponseAdapter extends RecyclerView.Adapter<AdminViewDiscussionResponseAdapter.AdminResponseViewHolder> {

    List<Student> responseList;
    Context context;

    public AdminViewDiscussionResponseAdapter(List<Student> responseList, Context context) {
        this.responseList = responseList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminResponseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_view_discussion_response_item,parent,false);
        AdminResponseViewHolder adminResponseViewHolder=new AdminResponseViewHolder(view);
        return adminResponseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminResponseViewHolder holder, int position) {
        holder.imageView.setImageBitmap(responseList.get(position).getImg());
        holder.studentName.setText("Student Name: "+responseList.get(position).getName());
        holder.responseDescription.setText("Response: "+responseList.get(position).getDisResponse());
    }

    @Override
    public int getItemCount() {
        return responseList.size();
    }

    public class AdminResponseViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView studentName, responseDescription;


        public AdminResponseViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.admin_view_response_item_Image);
            studentName = itemView.findViewById(R.id.admin_view_response_item_student_name);
            responseDescription = itemView.findViewById(R.id.admin_view_response_item_description);

        }
    }
}
