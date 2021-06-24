package com.example.collegediscussionforum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class HomeFeeback extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_feeback);

        recyclerView = findViewById(R.id.home_student_feedback_recyclerView);

        FeedbackDB feedbackDB=new FeedbackDB(HomeFeeback.this);
        Cursor cursor = feedbackDB.getFeedback();

        List<Student> feedbackList=new ArrayList<>();

        while(cursor.moveToNext())
        {
            Student student=new Student();

            student.setId(cursor.getInt(0));
            byte byteArrayImage [] = cursor.getBlob(1);
            //ByteArrayInputStream byteArray = new ByteArrayInputStream(byteArrayImage);
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArrayImage,0, byteArrayImage.length);
            student.setImg(bitmap);
            student.setName(cursor.getString(2));
            student.setEmail(cursor.getString(3));
            student.setFeedback(cursor.getString(4));

            feedbackList.add(student);
        }

        FeedbackAdapter feedbackAdapter=new FeedbackAdapter(feedbackList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(feedbackAdapter);
    }
}