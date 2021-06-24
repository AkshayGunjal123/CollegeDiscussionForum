package com.example.collegediscussionforum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class StudentDiscussionRecord extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_discussion_record);

        recyclerView = findViewById(R.id.student_discussion_topic_record_recyclerView);

        DiscussionDB discussionDB=new DiscussionDB(StudentDiscussionRecord.this);
        StudentDiscussionAdapter studentDiscussionAdapter=new StudentDiscussionAdapter(discussionDB.getDiscussionRecord(),this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(studentDiscussionAdapter);

    }
}