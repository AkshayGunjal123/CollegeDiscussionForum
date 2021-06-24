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

public class StudentHomeAdapter extends RecyclerView.Adapter<StudentHomeAdapter.StudentHomeViewHolder> {

    List<HomeItemModel> studentList;
    Context context;
    String user_email;
    public StudentHomeAdapter(List<HomeItemModel> studentList,String user_email ,Context context) {
        this.studentList = studentList;
        this.context = context;
        this.user_email = user_email;
    }

    @NonNull
    @Override
    public StudentHomeAdapter.StudentHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.student_home_recycler_item,parent,false);
        StudentHomeViewHolder studentHomeViewHolder=new StudentHomeViewHolder(view);
        return studentHomeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHomeAdapter.StudentHomeViewHolder holder, int position) {

        holder.imageView.setImageResource(studentList.get(position).getImg());
        holder.textView.setText(studentList.get(position).getTitle());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(studentList.get(position).getTitle().equals("Create Your Discussion Topic")){
                    Intent intent=new Intent(context.getApplicationContext(),StudentCreateDiscussion.class);
                    intent.putExtra("user_email_id",user_email);
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intent);
                }else if(studentList.get(position).getTitle().equals("Student's Discussion Topics")){
                    Intent intent=new Intent(context.getApplicationContext(),StudentViewDiscussion.class);
                    intent.putExtra("user_email_id",user_email);
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intent);
                }else if(studentList.get(position).getTitle().equals("Your Discussion")){
                    Intent intent=new Intent(context.getApplicationContext(),StudentViewOwnDiscussionResponse.class);
                    intent.putExtra("user_email_id",user_email);
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intent);
                }else if(studentList.get(position).getTitle().equals("Your Feedback")){
                    Intent intent=new Intent(context.getApplicationContext(),StudentFeedback.class);
                    intent.putExtra("user_email_id",user_email);
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class StudentHomeViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        LinearLayout linearLayout;

        public StudentHomeViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.student_home_item_Image);
            textView = itemView.findViewById(R.id.student_home_item_Title);
            linearLayout = itemView.findViewById(R.id.student_home_item_linear_layout_id);

        }
    }
}
