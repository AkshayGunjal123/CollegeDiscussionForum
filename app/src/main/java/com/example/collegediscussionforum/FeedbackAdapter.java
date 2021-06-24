package com.example.collegediscussionforum;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder>{

    List<Student> feedbackList;

    public FeedbackAdapter(List<Student> feedbackList) {
        this.feedbackList = feedbackList;
    }

    @NonNull
    @Override
    public FeedbackAdapter.FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_feedback_item,parent,false);
        FeedbackViewHolder feedbackViewHolder=new FeedbackViewHolder(view);
        return feedbackViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackAdapter.FeedbackViewHolder holder, int position) {
        holder.name.setText("Student Name: "+feedbackList.get(position).getName());
        holder.email.setText("Email: "+feedbackList.get(position).getEmail());
        holder.feedback.setText("Feedback: "+feedbackList.get(position).getFeedback());
        holder.imageView.setImageBitmap(feedbackList.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return feedbackList.size();
    }

    public class FeedbackViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,email,feedback;

        public FeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.student_feedback_name);
            email = itemView.findViewById(R.id.student_feedback_email);
            feedback = itemView.findViewById(R.id.student_feedback_feedback);

            imageView = itemView.findViewById(R.id.student_feedback_item_Image);
        }
    }
}
