package com.example.collegediscussionforum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class StudentViewDiscussion extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_discussion);

        recyclerView = findViewById(R.id.student_view_discussion_recyclerView);

        final String user_email = getIntent().getExtras().getString("user_email_id");

        DiscussionDB discussionDB=new DiscussionDB(StudentViewDiscussion.this);

        StudentResponseAdapter responseAdapter=new StudentResponseAdapter(discussionDB.getDiscussionRecord(),user_email,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(responseAdapter);

    }
}