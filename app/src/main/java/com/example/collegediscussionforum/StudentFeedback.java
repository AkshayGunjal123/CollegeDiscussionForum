package com.example.collegediscussionforum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StudentFeedback extends AppCompatActivity {

    ImageView imageView;
    TextView studentName,studentEmail;

    EditText studentFeedback;

    Button feedback_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_feedback);

        imageView = findViewById(R.id.student_feedback_item_Image);
        studentName = findViewById(R.id.feedback_student_name);
        studentEmail = findViewById(R.id.feedback_student_email);
        studentFeedback = findViewById(R.id.feedback_student_feedback);


        feedback_btn = findViewById(R.id.feedback_add_feedback_button);

        final String user_email = getIntent().getExtras().getString("user_email_id");

        StudentDBHelper studentDBHelper=new StudentDBHelper(StudentFeedback.this);
        Student student = studentDBHelper.getStudentWithEmail(user_email);

        if(student!=null) {
            studentName.setText(student.getName());
            studentEmail.setText(student.getEmail());
            imageView.setImageBitmap(student.getImg());
        }else{
            Toast.makeText(StudentFeedback.this,"Student Record is not being accessed",Toast.LENGTH_LONG).show();
        }

        feedback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedback = studentFeedback.getText().toString();

                if (TextUtils.isEmpty(feedback)) {
                    studentName.setError("please enter feedback");
                }else{

                    FeedbackDB feedbackDB=new FeedbackDB(StudentFeedback.this);
                    Student student2 = new Student();
                    student2.setName(studentName.getText().toString());
                    student2.setImg(student.getImg());
                    student2.setEmail(student.getEmail());
                    student2.setFeedback(studentFeedback.getText().toString());
                    feedbackDB.addFeedback(student2);
                    long l = studentDBHelper.addStudent(student2);

                    if(l!=-1){
                        Toast.makeText(StudentFeedback.this, "Your Feedback Added...", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(StudentFeedback.this, StudentHome.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(StudentFeedback.this, "Something Went Wrong...!!! l = "+l, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}