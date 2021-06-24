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
import android.widget.Toast;

public class StudentSignUp extends AppCompatActivity {

    private EditText studentName,studentRollNo,studentEmail,studentContact,studentPassword,studentConfirm;
    private Spinner courseSpinner;
    private ImageView imageView;

    Button btn_submit,btn_select_image;

    private static  final int PICK_IMAGE_REQUEST = 100;
    private Uri imageFilePath;
    private Bitmap imageToStored;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);

        studentName = findViewById(R.id.student_full_name);
        studentRollNo = findViewById(R.id.student_roll_no);
        studentEmail = findViewById(R.id.student_email);
        studentContact = findViewById(R.id.student_contact);
        studentPassword = findViewById(R.id.student_password);
        studentConfirm = findViewById(R.id.student_confirm);

        imageView = findViewById(R.id.student_image);

        courseSpinner = findViewById(R.id.studentCourse);
        String courseString[]={"MCA","MBA","BBA","BCA","BTech","MTech"};

        ArrayAdapter<String> courseAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,courseString);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(courseAdapter);


        btn_select_image = findViewById(R.id.student_select_img);
        btn_submit = findViewById(R.id.student_btn_sign_up);

        btn_select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = studentName.getText().toString();
                String rollNo = studentRollNo.getText().toString();
                String email = studentEmail.getText().toString();
                String course = courseSpinner.getSelectedItem().toString();
                String contact = studentContact.getText().toString();
                String password = studentPassword.getText().toString();
                String confirm = studentConfirm.getText().toString();


                if(imageView.getDrawable()==null && imageToStored==null){
                    Toast.makeText(StudentSignUp.this,"Select Image Properly Or Image has not been selected..",Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(name)) {
                    studentName.setError("please enter name");
                }else if (TextUtils.isEmpty(rollNo)) {
                    studentRollNo.setError("please enter roll no");
                }else if (TextUtils.isEmpty(email)) {
                    studentEmail.setError("please enter email");
                }else if (TextUtils.isEmpty(contact)) {
                    studentContact.setError("please enter contact");
                }else if (TextUtils.isEmpty(password)) {
                    studentPassword.setError("please enter password");
                }else if (!password.equals(confirm)) {
                    studentConfirm.setError("password and confirm password wrong!");
                }else{

                    Student student=new Student(imageToStored,name,rollNo,email,course,contact,password);

                    StudentDBHelper studentDBHelper=new StudentDBHelper(StudentSignUp.this);
                    long l = studentDBHelper.addStudent(student);
                    if(l!=-1){
                        Toast.makeText(StudentSignUp.this, "Account Has Created...", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(StudentSignUp.this, StudentSignIn.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(StudentSignUp.this, "Something Went Wrong...!!! l = "+l, Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }

    private void selectImage(){
        try {
            Intent intent=new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent,PICK_IMAGE_REQUEST);

        }catch(Exception excetion){
            excetion.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        try {
            super.onActivityResult(requestCode, resultCode, data);

            if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data!=null && data.getData()!=null){
                imageFilePath = data.getData();
                imageToStored = MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);
                imageView.setImageBitmap(imageToStored);
            }
        }catch (Exception exception1){
            exception1.printStackTrace();
        }
    }
}