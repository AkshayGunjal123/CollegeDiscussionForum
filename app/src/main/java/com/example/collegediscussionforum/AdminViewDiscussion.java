package com.example.collegediscussionforum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class AdminViewDiscussion extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_discussion);

        recyclerView = findViewById(R.id.admin_view_discussion_recyclerView);

        DiscussionDB discussionDB=new DiscussionDB(AdminViewDiscussion.this);

        AdminViewDiscussionAdapter adminViewDiscussionAdapter = new AdminViewDiscussionAdapter(discussionDB.getDiscussionRecord(),this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adminViewDiscussionAdapter);
    }
}