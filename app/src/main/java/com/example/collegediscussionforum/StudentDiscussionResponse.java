package com.example.collegediscussionforum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StudentDiscussionResponse extends AppCompatActivity {

    TextView discussionCategory,discussionTopic;
    TextView response_Student_Name;

    String request_Student_Name,request_Student_Email;

    EditText studentResponse;

    ImageView imageView;

    Button addResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_discussion_response);

        response_Student_Name = findViewById(R.id.student_discussion_response_name);
        discussionCategory = findViewById(R.id.student_discussion_response_category);
        discussionTopic = findViewById(R.id.student_discussion_response_topic);

        imageView = findViewById(R.id.student_discussion_response_Image);

        studentResponse = findViewById(R.id.student_discussion_your_response);

        final String response_student_email = getIntent().getExtras().getString("response_student_email");
        final int discussion_id = getIntent().getExtras().getInt("discussion_id");

        //Toast.makeText(StudentDiscussionResponse.this,"response_student_email: "+response_student_email +" discussion Id : "+ discussion_id,Toast.LENGTH_LONG).show();

        DiscussionDB discussionDB=new DiscussionDB(StudentDiscussionResponse.this);

        Student student1 = discussionDB.getDiscussionWithId(discussion_id);

        if(student1!=null){

            request_Student_Name = student1.getName();
            request_Student_Email = student1.getEmail();
            discussionCategory.setText(student1.getDisCategory());
            discussionTopic.setText(student1.getDisTopic());
            imageView.setImageBitmap(student1.getDisImage());

        }else{
            Toast.makeText(StudentDiscussionResponse.this,"Request Student is not being selected from database...! discussion Id : "+discussion_id,Toast.LENGTH_LONG).show();
        }


        StudentDBHelper studentDBHelper=new StudentDBHelper(StudentDiscussionResponse.this);
        Student student = studentDBHelper.getStudentWithEmail(response_student_email);
        if(student!=null){
            response_Student_Name.setText(student.getName());
        }else{
            Toast.makeText(StudentDiscussionResponse.this,"Response Student is not being selected from database...! email : "+response_student_email,Toast.LENGTH_LONG).show();
        }

        addResponse = findViewById(R.id.student_discussion_response);

        addResponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String responseDescription = studentResponse.getText().toString();
                if (TextUtils.isEmpty(responseDescription)) {
                    studentResponse.setError("please your response...!");
                }else{
                    DiscussionResponseDB discussionResponseDB=new DiscussionResponseDB(StudentDiscussionResponse.this);
                    Student student=new Student();
                    student.setRequestStudentName(request_Student_Name);
                    student.setRequestStudentEmail(request_Student_Email);
                    student.setRequestStudentName(response_Student_Name.getText().toString());
                    student.setRequestStudentEmail(response_student_email);
                    String dis_id = String.valueOf(discussion_id);
                    student.setDisCategoryId(dis_id);
                    student.setDisCategory(discussionCategory.getText().toString());
                    student.setDisTopic(discussionTopic.getText().toString());
                    student.setDisResponse(responseDescription);

                    long l = discussionResponseDB.addDiscussionResponse(student);

                    if(l!=-1){
                        Toast.makeText(StudentDiscussionResponse.this, "Your Response Added...", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(StudentDiscussionResponse.this, StudentHome.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(StudentDiscussionResponse.this, "Something Went Wrong...!!! l = "+l, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


    }
}