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

public class StudentViewOwnDiscussionAdapter extends RecyclerView.Adapter<StudentViewOwnDiscussionAdapter.OwnViewHolder> {

    List<Student> discussionList;
    Context context;

    public StudentViewOwnDiscussionAdapter(List<Student> discussionList, Context context) {
        this.discussionList = discussionList;
        this.context = context;
    }

    @NonNull
    @Override
    public OwnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_view_own_discussion_item,parent,false);
        OwnViewHolder ownViewHolder=new OwnViewHolder(view);
        return ownViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OwnViewHolder holder, int position) {
        holder.imageView.setImageBitmap(discussionList.get(position).getDisImage());
        holder.discussionCategory.setText(discussionList.get(position).getDisCategory());
        holder.discussionTopic.setText(discussionList.get(position).getDisTopic());

        int id = discussionList.get(position).getId();

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiscussionDB discussionDB =new DiscussionDB(context.getApplicationContext());
                boolean b1 = discussionDB.deleteDiscussionWithId(id);

                DiscussionResponseDB discussionResponseDB=new DiscussionResponseDB(context.getApplicationContext());
                boolean b2 = discussionResponseDB.deleteDiscussionResponseWithId(id);

                if(b1 && b2){
                    Toast.makeText(context.getApplicationContext(),"Your Topic Deleted",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context.getApplicationContext(),"Your topic has not been deleted...!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return discussionList.size();
    }

    public class OwnViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView discussionCategory,discussionTopic;

        Button btn_delete;

        public OwnViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.student_view_own_discussion_item_Image);
            discussionCategory = itemView.findViewById(R.id.student_view_own_discussion_item_category);
            discussionTopic = itemView.findViewById(R.id.student_view_own_discussion_item_topic);

            btn_delete = itemView.findViewById(R.id.student_delete_response_item_response_button);
        }
    }
}
