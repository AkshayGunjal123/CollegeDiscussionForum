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

public class AdminViewDiscussionAdapter extends RecyclerView.Adapter<AdminViewDiscussionAdapter.DiscussionViewHolder> {

    List<Student> studentList;
    Context context;

    public AdminViewDiscussionAdapter(List<Student> studentList, Context context) {
        this.studentList = studentList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminViewDiscussionAdapter.DiscussionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_view_discussion_item,parent,false);
        DiscussionViewHolder discussionViewHolder=new DiscussionViewHolder(view);
        return discussionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminViewDiscussionAdapter.DiscussionViewHolder holder, int position) {

        holder.imageView.setImageBitmap(studentList.get(position).getDisImage());
        holder.studentName.setText(studentList.get(position).getName());
        holder.studentEmail.setText(studentList.get(position).getEmail());
        holder.discussionCategory.setText(studentList.get(position).getDisCategory());
        holder.discussionTopic.setText(studentList.get(position).getDisTopic());

        int dis_id = studentList.get(position).getId();

        holder.btn_delete_discussion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiscussionDB discussionDB =new DiscussionDB(context.getApplicationContext());
                boolean b1 = discussionDB.deleteDiscussionWithId(dis_id);

                DiscussionResponseDB discussionResponseDB=new DiscussionResponseDB(context.getApplicationContext());
                boolean b2 = discussionResponseDB.deleteDiscussionResponseWithId(dis_id);

                if(b1 && b2){
                    Toast.makeText(context.getApplicationContext(),"This Topic Deleted",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context.getApplicationContext(),"This topic has not been deleted...!",Toast.LENGTH_LONG).show();
                }
            }
        });

        holder.btn_view_response.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context.getApplicationContext(),AdminViewDiscussionResponse.class);
                intent.putExtra("discussion_id",dis_id);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class DiscussionViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView studentName,studentEmail,discussionCategory,discussionTopic;

        Button btn_delete_discussion,btn_view_response;

        public DiscussionViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.admin_student_discussion_item_Image);
            studentName = itemView.findViewById(R.id.admin_student_discussion_name);
            studentEmail = itemView.findViewById(R.id.admin_student_discussion_email);
            discussionCategory = itemView.findViewById(R.id.admin_student_discussion_category);
            discussionTopic = itemView.findViewById(R.id.admin_student_discussion_topic);

            btn_view_response = itemView.findViewById(R.id.admin_student_discussion_response_button);
            btn_delete_discussion = itemView.findViewById(R.id.admin_student_discussion_delete_button);


        }
    }
}
