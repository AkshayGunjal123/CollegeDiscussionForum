package com.example.collegediscussionforum;

import android.content.Context;
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

public class AdminFeedbackAdapter extends RecyclerView.Adapter<AdminFeedbackAdapter.AdminFeedbackViewHolder> {

    List<Student> feedbackList;
    Context context;

    public AdminFeedbackAdapter(List<Student> feedbackList, Context context) {
        this.feedbackList = feedbackList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminFeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_feedback_item,parent,false);
        AdminFeedbackViewHolder adminFeedbackViewHolder=new AdminFeedbackViewHolder(view);
        return adminFeedbackViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminFeedbackViewHolder holder, int position) {
        holder.name.setText("Student Name: "+feedbackList.get(position).getName());
        holder.email.setText("Email: "+feedbackList.get(position).getEmail());
        holder.feedback.setText("Feedback: "+feedbackList.get(position).getFeedback());
        holder.imageView.setImageBitmap(feedbackList.get(position).getImg());
        int id = feedbackList.get(position).getId();
        holder.btn_delete_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FeedbackDB feedbackDB=new FeedbackDB(context.getApplicationContext());
                boolean b = feedbackDB.deleteFeedbackWithId(id);

                if(b){
                    Toast.makeText(context.getApplicationContext(),"Feedback Has Been Deleted Successfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context.getApplicationContext(),"Something Went Wrong...!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedbackList.size();
    }

    public class AdminFeedbackViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,email,feedback;

        Button btn_delete_feedback;
        public AdminFeedbackViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.admin_feedback_name);
            email = itemView.findViewById(R.id.admin_feedback_email);
            feedback = itemView.findViewById(R.id.admin_feedback_feedback);

            imageView = itemView.findViewById(R.id.admin_feedback_item_Image);

            btn_delete_feedback = itemView.findViewById(R.id.admin_feedback_item_button);
        }
    }
}
