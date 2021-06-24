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

public class HomeRecyclerView extends RecyclerView.Adapter<HomeRecyclerView.HomeViewHolder> {

    private List<HomeItemModel> homeItemList;
    private Context context;

    public HomeRecyclerView(List<HomeItemModel> homeItemList, Context context) {
        this.homeItemList = homeItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeRecyclerView.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_item,parent,false);
        HomeViewHolder homeViewHolder=new HomeViewHolder(view);
        return homeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerView.HomeViewHolder holder, int position) {
        holder.imageView.setImageResource(homeItemList.get(position).getImg());
        holder.textView.setText(homeItemList.get(position).getTitle());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(homeItemList.get(position).getTitle().equals("Admin Sign in")){
                    Intent intent=new Intent(context.getApplicationContext(),AdminSignIn.class);
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intent);
                }else if(homeItemList.get(position).getTitle().equals("Discussion")){
                    Intent intent=new Intent(context.getApplicationContext(),StudentDiscussionRecord.class);
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intent);
                }else if(homeItemList.get(position).getTitle().equals("Feedback")){
                    Intent intent=new Intent(context.getApplicationContext(),HomeFeeback.class);
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intent);
                }else if(homeItemList.get(position).getTitle().equals("Student Sign in")){
                    Intent intent=new Intent(context.getApplicationContext(),StudentSignIn.class);
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intent);
                }else if(homeItemList.get(position).getTitle().equals("Student Sign up")){
                    Intent intent=new Intent(context.getApplicationContext(),StudentSignUp.class);
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeItemList.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        LinearLayout linearLayout;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.item_Image);
            textView = itemView.findViewById(R.id.item_Title);
            linearLayout = itemView.findViewById(R.id.home_item_linear_layout_id);
        }

    }

}
