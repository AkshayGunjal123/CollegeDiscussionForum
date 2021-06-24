package com.example.collegediscussionforum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class FeedbackDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "StudentFeedbackDB";

    //Student Table
    private static final String STUDENT_FEEDBACK_TABLE = "student_feedback_table";

    private static final String KEY_Id = "feedback_id";
    private static final String KEY_STUDENT_Image = "student_image";
    private static final String KEY_STUDENT_Name = "student_name";
    private static final String KEY_STUDENT_Email = "student_email";
    private static final String KEY_STUDENT_Feedback = "student_feedback";

    String queryFeedback="CREATE TABLE "+ STUDENT_FEEDBACK_TABLE +" (" +
            KEY_Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_STUDENT_Image + " BLOB, " +
            KEY_STUDENT_Name + " TEXT, " +
            KEY_STUDENT_Email + " TEXT, " +
            KEY_STUDENT_Feedback + " TEXT " + ")";


    public FeedbackDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(queryFeedback);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+STUDENT_FEEDBACK_TABLE);
        onCreate(db);
    }

    public long addFeedback(Student student){

        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap imageToStored = student.getImg();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageToStored.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte imageInByte[] = byteArrayOutputStream.toByteArray();

        try {


            ContentValues contentValues=new ContentValues();
            contentValues.put(KEY_STUDENT_Image,imageInByte);
            contentValues.put(KEY_STUDENT_Name,student.getName());
            contentValues.put(KEY_STUDENT_Email,student.getEmail());
            contentValues.put(KEY_STUDENT_Feedback,student.getFeedback());

            long result = db.insert(STUDENT_FEEDBACK_TABLE,null,contentValues);
            db.close();
            return result;
        }catch(Exception e3){
            e3.printStackTrace();
        }
        return 0;
    }

    public Cursor getFeedback(){

        SQLiteDatabase db = this.getReadableDatabase();

        try {
            String query = "SELECT * FROM " + STUDENT_FEEDBACK_TABLE;
            Cursor cursor = db.rawQuery(query, null);

            return cursor;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteFeedbackWithId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            // String query = "DELETE * FROM table_book_issue where student_id ='"+id+"' ";
            db.delete(STUDENT_FEEDBACK_TABLE,KEY_Id +" = "+id,null);
            return true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}

