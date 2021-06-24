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

public class AdminHome extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<HomeItemModel> adminItemModel;

    private Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        btn_logout = findViewById(R.id.admin_logout);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this,HomeActivity.class));
            }
        });


        recyclerView = findViewById(R.id.admin_recyclerView);
        adminItemModel = new ArrayList<>();
        adminItemModel.add(new HomeItemModel(R.drawable.discussion_img1,"Student's Discussion"));
        adminItemModel.add(new HomeItemModel(R.drawable.student_feedback,"Student's Feedback"));
        adminItemModel.add(new HomeItemModel(R.drawable.user_logo,"Student's Record"));


        AdminAdapter adminAdapter=new AdminAdapter(adminItemModel,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adminAdapter);


    }
}