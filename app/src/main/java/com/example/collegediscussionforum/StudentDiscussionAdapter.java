package com.example.collegediscussionforum;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class StudentDiscussionAdapter extends RecyclerView.Adapter<StudentDiscussionAdapter.DiscussionViewHolder> {

    private List<Student> discussionList;
    private Context context;

    public StudentDiscussionAdapter(List<Student> discussionList, Context context) {
        this.discussionList = discussionList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentDiscussionAdapter.DiscussionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_discussion_item,parent,false);
        DiscussionViewHolder discussionViewHolder=new DiscussionViewHolder(view);
        return discussionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentDiscussionAdapter.DiscussionViewHolder holder, int position) {

        holder.imageView.setImageBitmap(discussionList.get(position).getDisImage());
        holder.studentName.setText("Student Name: "+discussionList.get(position).getName());
        holder.studentEmail.setText("Email: "+discussionList.get(position).getEmail());
        holder.discussionCategory.setText("Discussion Category: "+discussionList.get(position).getDisCategory());
        holder.discussionTopic.setText("Discussion Topic: "+discussionList.get(position).getDisTopic());
        int disCategoryId = discussionList.get(position).getId();

        holder.btn_response.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context.getApplicationContext(),"Please Sign in...",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(context.getApplicationContext(),StudentSignIn.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return discussionList.size();
    }

    public class DiscussionViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView studentName,studentEmail,discussionCategory,discussionTopic;

        Button btn_response;

        public DiscussionViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.student_discussion_Image);
            studentName = itemView.findViewById(R.id.student_discussion_name);
            studentEmail = itemView.findViewById(R.id.student_discussion_email);
            discussionCategory = itemView.findViewById(R.id.student_discussion_category);
            discussionTopic = itemView.findViewById(R.id.student_discussion_topic);

            btn_response = itemView.findViewById(R.id.student_discussion_response);

        }
    }
}
