package com.example.collegediscussionforum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StudentViewDiscussionResponse extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView discussionTopic;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_discussion_response);

        recyclerView = findViewById(R.id.student_view_discussion_response_recyclerView);
        imageView = findViewById(R.id.student_view_discussion_response_Image);
        discussionTopic = findViewById(R.id.student_view_discussion_topic);

       // final String response_student_email = getIntent().getExtras().getString("response_student_email");
        final int discussion_id = getIntent().getExtras().getInt("discussion_id");

        DiscussionDB discussionDB=new DiscussionDB(StudentViewDiscussionResponse.this);
        Student student = discussionDB.getDiscussionWithId(discussion_id);

        if(student!=null){
            imageView.setImageBitmap(student.getDisImage());
            discussionTopic.setText(student.getDisTopic());
        }else{
            Toast.makeText(StudentViewDiscussionResponse.this,"Discussion Image is not being selected from database...! discussion Id : "+discussion_id,Toast.LENGTH_LONG).show();
        }


        DiscussionResponseDB discussionResponseDB=new DiscussionResponseDB(StudentViewDiscussionResponse.this);
        Cursor cursor = discussionResponseDB.getDiscussionResponseWithId(discussion_id);

        List<Student> responseList = new ArrayList<>();

        StudentDBHelper studentDBHelper=new StudentDBHelper(StudentViewDiscussionResponse.this);

        while(cursor.moveToNext())
        {
            Student discussion=new Student();
            discussion.setId(Integer.parseInt(cursor.getString(0)));
            discussion.setRequestStudentName(cursor.getString(1));
            discussion.setRequestStudentEmail(cursor.getString(2));
            discussion.setResponseStudentName(cursor.getString(3));
            discussion.setResponseStudentEmail(cursor.getString(4));
            discussion.setDisCategoryId(cursor.getString(5));
            discussion.setDisCategory(cursor.getString(6));
            discussion.setDisTopic(cursor.getString(7));
            discussion.setDisResponse(cursor.getString(8));

            Student studentData = studentDBHelper.getStudentWithEmail(discussion.getRequestStudentEmail());
            discussion.setImg(studentData.getImg());
            discussion.setName(studentData.getName());
            responseList.add(discussion);
        }

        StudentViewResponseAdapter studentViewResponseAdapter=new StudentViewResponseAdapter(responseList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(studentViewResponseAdapter);
    }
}