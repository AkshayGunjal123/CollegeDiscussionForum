package com.example.collegediscussionforum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class StudentHome extends AppCompatActivity {

    RecyclerView recyclerView;
    Button btn_logout;
    List<HomeItemModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        recyclerView = findViewById(R.id.student_home_recyclerView);
        btn_logout = findViewById(R.id.student_logout);

        final String user_email = getIntent().getExtras().getString("user_email_id");

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHome.this,HomeActivity.class));
            }
        });

        list = new ArrayList<>();
        list.add(new HomeItemModel(R.drawable.discussion_img1,"Student's Discussion Topics"));
        list.add(new HomeItemModel(R.drawable.discussion_img2,"Create Your Discussion Topic"));
        list.add(new HomeItemModel(R.drawable.discussion,"Your Discussion"));
        list.add(new HomeItemModel(R.drawable.student_feedback,"Your Feedback"));

        StudentHomeAdapter studentHomeAdapter=new StudentHomeAdapter(list,user_email,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        recyclerView.setAdapter(studentHomeAdapter);

    }
}