package com.example.collegediscussionforum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<HomeItemModel> homeItemList;

    Button studentSignUp,studentSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.home_recyclerView);

        studentSignUp = findViewById(R.id.student_sign_up_button);
        studentSignIn = findViewById(R.id.student_sign_in_button);


        studentSignUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(HomeActivity.this,StudentSignUp.class));
           }
       });

       studentSignIn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(HomeActivity.this,StudentSignIn.class));
           }
       });


        homeItemList = new ArrayList<>();
        homeItemList.add(new HomeItemModel(R.drawable.discussion,"Discussion"));
        homeItemList.add(new HomeItemModel(R.drawable.student_feedback,"Feedback"));
        homeItemList.add(new HomeItemModel(R.drawable.admin_log,"Admin Sign in"));
        homeItemList.add(new HomeItemModel(R.drawable.student_logo,"Student Sign up"));
        homeItemList.add(new HomeItemModel(R.drawable.user_logo,"Student Sign in"));

        HomeRecyclerView homeItemAdapter = new HomeRecyclerView(homeItemList,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        recyclerView.setAdapter(homeItemAdapter);
    }
}