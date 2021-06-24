package com.example.collegediscussionforum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentViewResponseAdapter extends RecyclerView.Adapter<StudentViewResponseAdapter.ResponseViewHolder> {

    List<Student> responseList;
    Context context;

    public StudentViewResponseAdapter(List<Student> responseList, Context context) {
        this.responseList = responseList;
        this.context = context;
    }

    @NonNull
    @Override
    public ResponseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_view_discussion_response_item,parent,false);
        ResponseViewHolder responseViewHolder=new ResponseViewHolder(view);
        return responseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResponseViewHolder holder, int position) {
        holder.imageView.setImageBitmap(responseList.get(position).getImg());
        holder.studentName.setText("Student Name: "+responseList.get(position).getName());
        holder.responseDescription.setText(responseList.get(position).getDisResponse());
    }

    @Override
    public int getItemCount() {
        return responseList.size();
    }

    public class ResponseViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView studentName, responseDescription;

        //Button responseDelete;

        public ResponseViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.student_view_response_item_Image);
            studentName = itemView.findViewById(R.id.student_view_response_item_student_name);
            responseDescription = itemView.findViewById(R.id.student_view_response_item_description);

           // responseDelete = itemView.findViewById(R.id.student_view_response_item_delete_button);

        }
    }
}
