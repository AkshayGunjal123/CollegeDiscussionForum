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

public class StudentResponseAdapter extends RecyclerView.Adapter<StudentResponseAdapter.ResponseViewHolder> {

    private List<Student> discussionList;
    private Context context;
    private String user_email;

    public StudentResponseAdapter(List<Student> discussionList,String user_email ,Context context) {
        this.discussionList = discussionList;
        this.user_email = user_email;
        this.context = context;
    }


    @NonNull
    @Override
    public StudentResponseAdapter.ResponseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_view_discussion_item,parent,false);
        ResponseViewHolder responseViewHolder=new ResponseViewHolder(view);
        return responseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentResponseAdapter.ResponseViewHolder holder, int position) {

        holder.imageView.setImageBitmap(discussionList.get(position).getDisImage());
        holder.studentName.setText("Student Name: "+discussionList.get(position).getName());
        holder.studentEmail.setText("Email: "+discussionList.get(position).getEmail());
        holder.discussionCategory.setText("Discussion Category: "+discussionList.get(position).getDisCategory());
        holder.discussionTopic.setText("Discussion Topic: "+discussionList.get(position).getDisTopic());
        int discussion_id = discussionList.get(position).getId();

        holder.btn_give_response.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context.getApplicationContext(),"Please Sign in...",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(context.getApplicationContext(),StudentDiscussionResponse.class);
                intent.putExtra("discussion_id",discussion_id);
                intent.putExtra("response_student_email",user_email);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });

        holder.btn_view_response.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context.getApplicationContext(),StudentViewDiscussionResponse.class);
                intent.putExtra("discussion_id",discussion_id);
               // intent.putExtra("response_student_email",user_email);

                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return discussionList.size();
    }

    public class ResponseViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView studentName,studentEmail,discussionCategory,discussionTopic;

        Button btn_give_response, btn_view_response;

        public ResponseViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.student_response_item_Image);
            studentName = itemView.findViewById(R.id.student_response_item_name);
            studentEmail = itemView.findViewById(R.id.student_response_item_email);
            discussionCategory = itemView.findViewById(R.id.student_response_item_category);
            discussionTopic = itemView.findViewById(R.id.student_response_item_topic);

            btn_give_response = itemView.findViewById(R.id.student_give_response_item_response_button);
            btn_view_response = itemView.findViewById(R.id.student_view_response_item_response_button);
        }
    }
}
