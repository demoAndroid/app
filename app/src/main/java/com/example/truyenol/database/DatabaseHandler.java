package com.example.truyenol.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.truyenol.R;
import com.example.truyenol.adapter.StoryAdapter;
import com.example.truyenol.model.Story;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";
    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database name
    private static final String DATABASE_NAME = "appDocTruyen";

    //Table user
    private static final String TABLE_USER = "user";

    private static final String COLUMN_ID = "idUser";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_FULLNAME = "fullName";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_LINKAVA = "linkAva";
    private static final String COLUMN_POSITION = "position";

    //Table story
    private static final String TABLE_STORY = "story";

    private static final String COLUMN_IDSTORY = "idStory";
    private static final String COLUMN_NAMESTORY = "nameStory";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_LINKIMG = "linkImg";
    private static final String COLUMN_NUMBERCHAPTER = "numberChapter";

    //Table comment
    private static final String TABLE_COMMENT = "comment";

    private static final String COLUMN_IDCOMMENT = "idComment";
    private static final String COLUMN_IDSTORY_COMMENT = "idStory";
    private static final String COLUMN_IDUSER_COMMENT = "idUser";
    private static final String COLUMN_COMMENT = "comment";
    private static final String COLUMN_RATING_COMMENT = "rating";

    //Table chapter
    private static final String TABLE_CHAPTER = "chapter";

    private static final String COLUMN_IDCHAPTER = "idChapter";
    private static final String COLUMN_IDCHAPTER_STORY = "idStory";
    private static final String COLUMN_NAMECHAPTER = "nameChapter";
    private static final String COLUMN_CONTENT = "content";

    private final Context context;

    public DatabaseHandler(Context context)  {
        super(context, DATABASE_NAME, null, 1);
        Log.d("DB Manager","DB Manager");

        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery1 = "CREATE TABLE " + TABLE_USER +"( " +
                COLUMN_ID +" integer primary key autoincrement, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_FULLNAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_LINKAVA + " TEXT, " +
                COLUMN_POSITION + " TEXT)";

        String sqlQuery2 = "CREATE TABLE " + TABLE_STORY +"( " +
                COLUMN_IDSTORY +" integer primary key autoincrement, " +
                COLUMN_NAMESTORY + " TEXT, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_STATUS + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_LINKIMG + " TEXT, " +
                COLUMN_NUMBERCHAPTER + " TEXT, " +
                COLUMN_RATING + " TEXT)";

        String sqlQuery3 = "CREATE TABLE " + TABLE_COMMENT +"( " +
                COLUMN_IDCOMMENT +" integer primary key autoincrement, " +
                COLUMN_COMMENT + " TEXT, " +
                COLUMN_RATING_COMMENT + " REAL, " +
                COLUMN_IDUSER_COMMENT + " INTEGER, " +
                COLUMN_IDSTORY_COMMENT + " INTEGER, " +
                "FOREIGN KEY (" + COLUMN_IDUSER_COMMENT + ") REFERENCES " +
                TABLE_USER + "(" +COLUMN_ID + "), "+
                "FOREIGN KEY (" + COLUMN_IDSTORY_COMMENT + ") REFERENCES " +
                TABLE_USER + "(" +COLUMN_IDSTORY + "))";

        String sqlQuery4 = "CREATE TABLE " + TABLE_CHAPTER +"( " +
                COLUMN_IDCHAPTER +" INTEGER primary key autoincrement, " +
                COLUMN_IDCHAPTER_STORY + " INTEGER, " +
                COLUMN_NAMECHAPTER + " TEXT, " +
                COLUMN_CONTENT + " TEXT, " +
                "FOREIGN KEY (" + COLUMN_IDCHAPTER_STORY + ") REFERENCES " +
                TABLE_USER + "(" +COLUMN_IDSTORY + "))";

        db.execSQL(sqlQuery1);
        db.execSQL(sqlQuery2);
        db.execSQL(sqlQuery3);
        db.execSQL(sqlQuery4);
        Toast.makeText(context, "Create Database successfully", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_STORY);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CHAPTER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_COMMENT);
        onCreate(db);
        Toast.makeText(context, "Drop successfully", Toast.LENGTH_SHORT).show();
    }
    public void addStory(Story story){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMESTORY, story.getNameStory());
        values.put(COLUMN_TYPE, story.getType());
        values.put(COLUMN_STATUS, story.getStatus());
        values.put(COLUMN_DESCRIPTION, story.getDescription());
        values.put(COLUMN_AUTHOR, story.getAuthor());
        values.put(COLUMN_RATING, story.getRating());
        values.put(COLUMN_LINKIMG, story.getLinkImg());
        values.put(COLUMN_NUMBERCHAPTER, story.getNumberChapter());

        db.insert(TABLE_STORY,null,values);
        db.close();
    }
    public void addStoryTest(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMESTORY,"Phàm nhân tu tiên");
        values.put(COLUMN_TYPE, "Tiên hiệp");
        values.put(COLUMN_STATUS, "Còn tiếp");
        values.put(COLUMN_DESCRIPTION, "Phàm Nhân Tu Tiên là một câu chuyện Tiên Hiệp kể về Hàn Lập - Một người bình thường nhưng lại gặp vô vàn cơ duyên để bước đi trên con đường tu tiên, không phải anh hùng - cũng chẳng phải tiểu nhân, Hàn Lập từng bước khẳng định mình... Liệu Hàn Lập và người yêu có thể cùng bước trên con đường tu tiên và có một cái kết hoàn mỹ? Những thử thách nào đang chờ đợi bọn họ?");
        values.put(COLUMN_AUTHOR, "Vong Ngữ");
        values.put(COLUMN_RATING, "7");
        values.put(COLUMN_LINKIMG, "R.drawable.image1");
        values.put(COLUMN_NUMBERCHAPTER, "3000");
        db.insert(TABLE_STORY,null,values);
        db.close();
    }
    public ArrayList<Story> getTienHiep(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Story> listTh = new ArrayList<>();
        Cursor cursor =  db.rawQuery( "select * from "+TABLE_STORY+" WHERE "+COLUMN_TYPE+" ='Tiên hiệp' LIMIT 6" , null );
        if (cursor.moveToFirst()) {
            do {
                Story story = new Story();
                story.setId(cursor.getInt(0));
                story.setNameStory(cursor.getString(1));
                story.setType(cursor.getString(2));
                story.setStatus(cursor.getString(3));
                story.setDescription(cursor.getString(4));
                story.setAuthor(cursor.getString(5));
                story.setLinkImg(cursor.getString(6));
                story.setNumberChapter(cursor.getString(7));
                story.setRating(cursor.getString(8));
                listTh.add(story);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listTh;
    }
    public ArrayList<Story> getStoryHome(){
        ArrayList<Story> listStory = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT "+ COLUMN_NAMESTORY +", "+COLUMN_LINKIMG+ ", "+COLUMN_LINKIMG+ " FROM "+TABLE_STORY+" LIMIT 6";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()) {
            do {
                Story story = new Story();
                story.setId(cursor.getInt(0));
                story.setNameStory(cursor.getString(1));
                story.setLinkImg(cursor.getString(7));
                listStory.add(story);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listStory;
    }
    public Story getStoryById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM story WHERE idStory ="+id+"",null);
        if (cursor != null)
            cursor.moveToFirst();

        Story story = new Story(cursor.getInt(0),cursor.getString(1),
                cursor.getString(2),cursor.getString(3),cursor.getString(4),
                cursor.getString(5),cursor.getString(6),cursor.getString(7),
                cursor.getString(8));
        cursor.close();
        db.close();
        return story;
    }
    public ArrayList<Story> getAllStory() {

        ArrayList<Story> listStory = new ArrayList<Story>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STORY;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Story story = new Story();
                story.setId(cursor.getInt(0));
                story.setNameStory(cursor.getString(1));
                story.setType(cursor.getString(2));
                story.setStatus(cursor.getString(3));
                story.setDescription(cursor.getString(4));
                story.setAuthor(cursor.getString(5));
                story.setLinkImg(cursor.getString(6));
                story.setNumberChapter(cursor.getString(7));
                story.setRating(cursor.getString(8));
                listStory.add(story);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listStory;
    }
    public List<Story> getNameStory() {

        List<Story> listStory = new ArrayList<Story>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_STORY;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Story story = new Story();
                story.setNameStory(cursor.getString(1));
                listStory.add(story);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listStory;
    }
}
