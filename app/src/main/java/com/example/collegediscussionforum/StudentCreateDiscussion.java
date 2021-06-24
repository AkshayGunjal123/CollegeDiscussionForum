package com.example.collegediscussionforum;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class StudentCreateDiscussion extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 100 ;
    private Uri imageFilePath;
    private Bitmap imageToStored;

    TextView Student_Name,Student_Roll_No,Student_Email,Student_Course,Student_Contact;
    Button btn_add_discussion,btn_select_discussion_image;

    EditText discussionTopic;
    Spinner discussionCategorySpinner;
    ImageView discussionImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_create_discussion);

        final String user_email = getIntent().getExtras().getString("user_email_id");

        Student_Name = findViewById(R.id.your_name);
        Student_Roll_No = findViewById(R.id.your_roll_no);
        Student_Email = findViewById(R.id.your_email);
        Student_Course = findViewById(R.id.your_course);
        Student_Contact = findViewById(R.id.your_contact);

        discussionCategorySpinner = findViewById(R.id.student_discussion_category);

        String discussionCategoryString[]={"Exam Regarding Discussion","Technical Discussion","Subject Discussion","Social Discussion","Query Discussion"};

        ArrayAdapter<String> discussionAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,discussionCategoryString);
        discussionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        discussionCategorySpinner.setAdapter(discussionAdapter);

        discussionTopic = findViewById(R.id.student_discussion_topic);

        btn_select_discussion_image = findViewById(R.id.select_your_discussion_img);
        btn_add_discussion = findViewById(R.id.add_your_discussion_button);

        discussionImage = findViewById(R.id.your_discussion_image);

        StudentDBHelper studentDBHelper=new StudentDBHelper(StudentCreateDiscussion.this);
        Student student = studentDBHelper.getStudentWithEmail(user_email);
        if(student!=null){
            Student_Name.setText(student.getName());
            Student_Roll_No.setText(student.getRoll_no());
            Student_Email.setText(student.getEmail());
            Student_Course.setText(student.getCourse());
            Student_Contact.setText(student.getContact());

        }else{
            Toast.makeText(StudentCreateDiscussion.this,"Student is not being selected from database...! email : "+user_email,Toast.LENGTH_LONG).show();
        }

        btn_select_discussion_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDiscussionImage();
            }
        });


        btn_add_discussion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = Student_Name.getText().toString();
                String rollNo = Student_Roll_No.getText().toString();
                String email = Student_Email.getText().toString();
                String course = Student_Course.getText().toString();
                String contact = Student_Contact.getText().toString();

                String category = discussionCategorySpinner.getSelectedItem().toString();
                String topic = discussionTopic.getText().toString();

                if(discussionImage.getDrawable()==null && imageToStored==null){
                    Toast.makeText(StudentCreateDiscussion.this,"Select Image Properly Or Image has not been selected..",Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(topic)) {
                    Student_Name.setError("please enter topic");
                }else {

                    Student student=new Student(imageToStored,name,rollNo,email,course,contact,category,topic);

                    DiscussionDB discussionDB=new DiscussionDB(StudentCreateDiscussion.this);
                    long l = discussionDB.addDiscussion(student);

                    if(l!=-1){
                        Toast.makeText(StudentCreateDiscussion.this, "Your Discussion Topic Added Successfully...", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(StudentCreateDiscussion.this, StudentHome.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(StudentCreateDiscussion.this, "Something Went Wrong...!!! l = "+l, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    private void selectDiscussionImage(){

        try {
            Intent intent=new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent,PICK_IMAGE_REQUEST);

        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        try {
            super.onActivityResult(requestCode, resultCode, data);

            if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data!=null && data.getData()!=null){
                imageFilePath = data.getData();
                imageToStored = MediaStore.Images.Media.getBitmap(getContentResolver(), imageFilePath);
                discussionImage.setImageBitmap(imageToStored);
            }
        }catch (Exception exception1){
            exception1.printStackTrace();
        }
    }
}