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

public class AdminViewDiscussionResponse extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView imageView;
    TextView discussionTopic,studentName,studentEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_discussion_response);

        recyclerView = findViewById(R.id.admin_view_discussion_response_recyclerView);

        imageView = findViewById(R.id.admin_view_discussion_response_Image);

        studentName = findViewById(R.id.admin_view_discussion_student_name);
        studentEmail = findViewById(R.id.admin_view_discussion_student_email);

        discussionTopic = findViewById(R.id.admin_view_discussion_topic);

        final int discussion_id = getIntent().getExtras().getInt("discussion_id");

        DiscussionDB discussionDB=new DiscussionDB(AdminViewDiscussionResponse.this);

        Student student = discussionDB.getDiscussionWithId(discussion_id);

        if(student!=null){
            imageView.setImageBitmap(student.getDisImage());
            studentName.setText(student.getName());
            studentEmail.setText(student.getEmail());
            discussionTopic.setText(student.getDisTopic());
        }else{
            Toast.makeText(AdminViewDiscussionResponse.this,"Discussion Image is not being selected from database...! discussion Id : "+discussion_id,Toast.LENGTH_LONG).show();
        }

        DiscussionResponseDB discussionResponseDB=new DiscussionResponseDB(AdminViewDiscussionResponse.this);
        Cursor cursor = discussionResponseDB.getDiscussionResponseWithId(discussion_id);

        List<Student> responseList = new ArrayList<>();

        StudentDBHelper studentDBHelper=new StudentDBHelper(AdminViewDiscussionResponse.this);


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

        AdminViewDiscussionResponseAdapter adminAdapter = new AdminViewDiscussionResponseAdapter(responseList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adminAdapter);

    }
}