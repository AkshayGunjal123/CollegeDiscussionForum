package com.example.collegediscussionforum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DiscussionResponseDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "DiscussionResponseDB";

    //Student Table
    private static final String DISCUSSION_RESPONSE_TABLE = "discussion_response_table";

    private static final String KEY_Id = "response_id";
    private static final String KEY_REQUEST_STUDENT_Name = "request_student_name";
    private static final String KEY_REQUEST_STUDENT_Email = "request_student_email";
    private static final String KEY_RESPONSE_STUDENT_Name = "response_student_course";
    private static final String KEY_RESPONSE_STUDENT_Email = "response_student_email";

    private static final String KEY_DISCUSSION_Id = "discussion_id";
    private static final String KEY_DISCUSSION_Category = "discussion_category";
    private static final String KEY_DISCUSSION_Topic = "discussion_topic";
    private static final String KEY_DISCUSSION_Response = "discussion_response";

    String queryResponse="CREATE TABLE "+ DISCUSSION_RESPONSE_TABLE +" (" +
            KEY_Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_REQUEST_STUDENT_Name + " TEXT, " +
            KEY_REQUEST_STUDENT_Email + " TEXT, " +
            KEY_RESPONSE_STUDENT_Name + " TEXT, " +
            KEY_RESPONSE_STUDENT_Email + " TEXT, " +
            KEY_DISCUSSION_Id + " TEXT, " +
            KEY_DISCUSSION_Category + " TEXT, " +
            KEY_DISCUSSION_Topic + " TEXT, " +
            KEY_DISCUSSION_Response + " TEXT " + ")";

    public DiscussionResponseDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(queryResponse);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DISCUSSION_RESPONSE_TABLE);
        onCreate(db);
    }

    public long addDiscussionResponse(Student student){

        SQLiteDatabase db = this.getWritableDatabase();
     /*
        Bitmap imageToStored = student.getDisImage();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageToStored.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte imageInByte[] = byteArrayOutputStream.toByteArray();
    */
        try {

            ContentValues contentValues=new ContentValues();
            contentValues.put(KEY_REQUEST_STUDENT_Name,student.getRequestStudentName());
            contentValues.put(KEY_REQUEST_STUDENT_Email,student.getRequestStudentEmail());
            contentValues.put(KEY_RESPONSE_STUDENT_Name,student.getResponseStudentName());
            contentValues.put(KEY_RESPONSE_STUDENT_Email,student.getResponseStudentEmail());
            contentValues.put(KEY_DISCUSSION_Id,student.getDisCategoryId());
            contentValues.put(KEY_DISCUSSION_Category,student.getDisCategory());
            contentValues.put(KEY_DISCUSSION_Topic,student.getDisTopic());
            contentValues.put(KEY_DISCUSSION_Response,student.getDisResponse());
            long result = db.insert(DISCUSSION_RESPONSE_TABLE,null,contentValues);
            db.close();
            return result;
        }catch(Exception e3){
            e3.printStackTrace();
        }
        return 0;
    }

    public List<Student> getDiscussionResponse(){

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM discussion_response_table";
        Cursor cursor = db.rawQuery(query, null);

        //Toast.makeText(context, "Cursor : " + cursor + " data : "+data+" Table : "+table, Toast.LENGTH_LONG).show();

        List<Student> discussionList = new ArrayList<>();

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

            discussionList.add(discussion);
        }
        return discussionList;
    }


    public Cursor getDiscussionResponseWithId(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        String dis_id = String.valueOf(id);
      try {
          String query = "SELECT * FROM discussion_response_table where discussion_id = '" + dis_id + "'";
          Cursor cursor = db.rawQuery(query, null);
          //Toast.makeText(context, "Cursor : " + cursor + " data : "+data+" Table : "+table, Toast.LENGTH_LONG).show();
         return cursor;
      }catch(Exception exception){
          exception.printStackTrace();
      }
        return null;
    }

    public boolean deleteDiscussionResponseWithId(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            // String query = "DELETE * FROM table_book_issue where student_id ='"+id+"' ";

            db.delete(DISCUSSION_RESPONSE_TABLE,KEY_DISCUSSION_Id +" = "+id,null);
            return true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
