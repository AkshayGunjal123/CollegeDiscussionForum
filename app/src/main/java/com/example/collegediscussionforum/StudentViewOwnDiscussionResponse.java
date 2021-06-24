package com.example.collegediscussionforum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class StudentViewOwnDiscussionResponse extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_own_discussion_response);

        final String student_email = getIntent().getExtras().getString("user_email_id");

        recyclerView = findViewById(R.id.student_view_own_discussion_recyclerView);

        DiscussionDB discussionDB = new DiscussionDB(StudentViewOwnDiscussionResponse.this);

        StudentViewOwnDiscussionAdapter studentViewOwnAdapter = new StudentViewOwnDiscussionAdapter(discussionDB.getDiscussionWithEmail(student_email),this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(studentViewOwnAdapter);
    }
}