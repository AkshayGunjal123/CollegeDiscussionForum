package com.example.collegediscussionforum;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.AdminViewHolder> {

    List<HomeItemModel> adminItemList;
    Context context;

    public AdminAdapter(List<HomeItemModel> adminItemList, Context context) {
        this.adminItemList = adminItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminAdapter.AdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_recycler_item,parent,false);
        AdminViewHolder adminViewHolder=new AdminViewHolder(view);
        return adminViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminAdapter.AdminViewHolder holder,int position) {

        holder.imageView.setImageResource(adminItemList.get(position).getImg());
        holder.textView.setText(adminItemList.get(position).getTitle());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(adminItemList.get(position).getTitle().equals("Student's Record")){
                    Intent intent=new Intent(context.getApplicationContext(),AdminStudentRecord.class);
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intent);
                }else if(adminItemList.get(position).getTitle().equals("Student's Feedback")){
                    Intent intent=new Intent(context.getApplicationContext(),AdminFeedback.class);
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intent);
                }else if(adminItemList.get(position).getTitle().equals("Student's Discussion")){
                    Intent intent=new Intent(context.getApplicationContext(),AdminViewDiscussion.class);
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return adminItemList.size();
    }

    public class AdminViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        LinearLayout linearLayout;

        public AdminViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.admin_item_Image);
            textView = itemView.findViewById(R.id.admin_item_Title);
            linearLayout = itemView.findViewById(R.id.admin_item_linear_layout_id);

        }
    }

}
