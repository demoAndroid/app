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
import com.example.truyenol.model.Chapter;
import com.example.truyenol.model.Story;
import com.example.truyenol.model.User;

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
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_AUTHOR = "author";
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
                COLUMN_ID +" INTEGER PRIMARY KEY autoincrement, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_FULLNAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_LINKAVA + " TEXT, " +
                COLUMN_POSITION + " TEXT)";

        String sqlQuery2 = "CREATE TABLE " + TABLE_STORY +"( " +

                COLUMN_IDSTORY +" INTEGER PRIMARY KEY autoincrement, " +
                COLUMN_NAMESTORY + " TEXT, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_LINKIMG + " TEXT, " +
                COLUMN_NUMBERCHAPTER + " TEXT)";

        String sqlQuery3 = "CREATE TABLE " + TABLE_COMMENT +"( " +
                COLUMN_IDCOMMENT +" integer primary key autoincrement, " +
                COLUMN_COMMENT + " TEXT, " +
                COLUMN_RATING_COMMENT + " REAL, " +
                COLUMN_IDUSER_COMMENT + " INTEGER, " +
                COLUMN_IDSTORY_COMMENT + " INTEGER, " +
                "FOREIGN KEY (" + COLUMN_IDUSER_COMMENT + ") REFERENCES " +
                TABLE_USER + "(" +COLUMN_ID + "), "+
                "FOREIGN KEY (" + COLUMN_IDSTORY_COMMENT + ") REFERENCES " +
                TABLE_STORY + "(" +COLUMN_IDSTORY + "))";

        String sqlQuery4 = "CREATE TABLE " + TABLE_CHAPTER +"( " +
                COLUMN_IDCHAPTER +" INTEGER primary key autoincrement, " +
                COLUMN_IDCHAPTER_STORY + " INTEGER, " +
                COLUMN_NAMECHAPTER + " TEXT, " +
                COLUMN_CONTENT + " TEXT, " +
                "FOREIGN KEY (" + COLUMN_IDCHAPTER_STORY + ") REFERENCES " +
                TABLE_STORY + "(" +COLUMN_IDSTORY + "))";

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
        values.put(COLUMN_DESCRIPTION, story.getDescription());
        values.put(COLUMN_AUTHOR, story.getAuthor());
        values.put(COLUMN_LINKIMG, story.getLinkImg());
        values.put(COLUMN_NUMBERCHAPTER, story.getNumberChapter());

        db.insert(TABLE_STORY,null,values);
//        Cursor cursor=db.query(TABLE_STORY,new String[]{"MAX("+COLUMN_IDSTORY+")"},null,null,null,null,null);
//        if(cursor!=null)
//            cursor.moveToFirst();
//        addChapters(story.getChapters(),Integer.parseInt(cursor.getString(0)));
//        cursor.close();
        db.close();
    }
    public void addChapter(Chapter chapter,int idStory){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_IDCHAPTER_STORY, idStory);
        values.put(COLUMN_NAMECHAPTER,chapter.getNameChapter());
        values.put(COLUMN_CONTENT, chapter.getContent());
        db.insert(TABLE_CHAPTER,null,values);
        db.close();
    }
    public ArrayList<Chapter> getChapters(int idStory){
        ArrayList<Chapter> chapters=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_CHAPTER,new String[]{COLUMN_IDCHAPTER_STORY,COLUMN_NAMECHAPTER,COLUMN_CONTENT},
                COLUMN_IDCHAPTER_STORY+"=?",new String[]{String.valueOf(idStory)},null,null,null);
        if(cursor.moveToFirst()) do{
            Chapter chapter=new Chapter(cursor.getString(1), cursor.getString(2));
            chapter.setId(cursor.getInt(0));
            chapters.add(chapter);
        }while(cursor.moveToNext());
        cursor.close();
        return chapters;
    }
    public int countChapters(int idStory){
        ArrayList<Chapter> chapters=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_CHAPTER,new String[]{COLUMN_NAMECHAPTER,COLUMN_CONTENT},
                COLUMN_IDCHAPTER_STORY+"=?",new String[]{String.valueOf(idStory)},null,null,null);
        int result=cursor.getColumnCount();
        cursor.close();
        return result;
    }
    public void updateChapter(Chapter chapter){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(COLUMN_NAMECHAPTER,chapter.getNameChapter());
        value.put(COLUMN_CONTENT,chapter.getContent());
        db.update(TABLE_CHAPTER,value,COLUMN_IDCHAPTER+"=?",new String[]{String.valueOf(chapter.getId())});
        db.close();
    }
    public ArrayList<Story> getStoriesByName(String nameStory){
        ArrayList<Story> stories=new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_STORY,new String[]{COLUMN_IDSTORY,COLUMN_NAMESTORY,COLUMN_TYPE,COLUMN_DESCRIPTION,COLUMN_AUTHOR,COLUMN_LINKIMG,COLUMN_NUMBERCHAPTER}
                ,COLUMN_NAMESTORY+" LIKE "+"?",new String[]{"%"+nameStory+"%"},null,null,null);
        if(cursor.moveToFirst())do{
            int id= cursor.getInt(0);
            stories.add(new Story(id
                    ,cursor.getString(1),cursor.getString(2)
                    ,(cursor.getInt(6)>countChapters(cursor.getInt(0)))?true:false
                    ,cursor.getString(3),cursor.getString(4),
                    cursor.getString(5),cursor.getInt(6)));
        }while(cursor.moveToNext());
        return stories;
    }
    public Story getStoriesById(int idStory){
        Story story;
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_STORY,new String[]{COLUMN_IDSTORY,COLUMN_NAMESTORY,COLUMN_TYPE,COLUMN_DESCRIPTION,COLUMN_AUTHOR,COLUMN_LINKIMG,COLUMN_NUMBERCHAPTER}
                ,COLUMN_IDSTORY+" =?",new String[]{String.valueOf(idStory)},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
            story=new Story(cursor.getInt(0)
                    ,cursor.getString(1),cursor.getString(2)
                    ,(cursor.getInt(6)>countChapters(cursor.getInt(0)))?true:false
                    ,cursor.getString(3),cursor.getString(4),
                    cursor.getString(5),cursor.getInt(6));
            cursor.close();
            return story;
        }else return null;
    }
    public void updateStory(Story story){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_NAMESTORY,story.getNameStory());
        values.put(COLUMN_TYPE,story.getType());
        values.put(COLUMN_DESCRIPTION,story.getDescription());
        values.put(COLUMN_AUTHOR,story.getAuthor());
        values.put(COLUMN_LINKIMG,story.getLinkImg());
        values.put(COLUMN_NUMBERCHAPTER,story.getNumberChapter());
        db.update(TABLE_STORY,values,COLUMN_IDSTORY+"=?",new String[]{String.valueOf(story.getId())});
        db.close();
    }
    public int deleteChapter(int idChapter){
        SQLiteDatabase db=this.getWritableDatabase();
        int result=db.delete(TABLE_CHAPTER,COLUMN_IDCHAPTER+"=?",new String[]{String.valueOf(idChapter)});
        db.close();
        return result;
    }
    public void addStoryTest(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMESTORY,"Thần Đạo Đan Tôn");
        values.put(COLUMN_TYPE, "Tiên hiệp");
        values.put(COLUMN_DESCRIPTION, "Lăng Hàn - Một Đan Đế đại danh đỉnh định mang trong thân mình tuyệt thế công pháp vì truy cầu bước cuối, xé bỏ tấm màn thành thần nhưng thất bại đã phải bỏ mình. Thế nhưng ông trời dường như không muốn tuyệt dường người, Lăng Hàn đã được trọng sinh vào một thiếu niên cùng tên và điều may mắn nhất là \"Bất Diệt Thiên Kinh\" ấn ký vẫn còn nằm nguyên trong tâm thức hắn.\n" +
                "\n" +
                "Từ nay về sau sóng gió cuộn trào nổi lên, Đan Đế ngày xưa bây giờ phải cùng tranh phong với vô số thiên tài trẻ tuổi, lại bắt đầu một truyền thuyết mới như để chứng minh với trời đất: Ta, là người mạnh nhất");
        values.put(COLUMN_AUTHOR, "Cô Đơn Địa Phi");
        values.put(COLUMN_LINKIMG, "https://static.8cache.com/cover/o/eJzLyTDW180MdfWNqgzzcqzM1w8rLI0oLjK2yMry1HeEAi8jE_1yU9-oqLIUi6QiA_1yI0NT3QxjIyNdz2QTIwDMDBQC/than-dao-dan-ton.jpg");
        values.put(COLUMN_NUMBERCHAPTER, "5357");
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
                story.setStatus((cursor.getInt(6)>countChapters(cursor.getInt(0)))?true:false);
                story.setDescription(cursor.getString(3));
                story.setAuthor(cursor.getString(4));
                story.setLinkImg(cursor.getString(5));
                story.setNumberChapter(cursor.getInt(6));
                listTh.add(story);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listTh;
    }
    public ArrayList<Story> getNgonTinh(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Story> listTh = new ArrayList<>();
        Cursor cursor =  db.rawQuery( "select * from "+TABLE_STORY+" WHERE "+COLUMN_TYPE+" ='Ngôn Tình' LIMIT 6" , null );
        if (cursor.moveToFirst()) {
            do {
                Story story = new Story();
                story.setId(cursor.getInt(0));
                story.setNameStory(cursor.getString(1));
                story.setType(cursor.getString(2));
                story.setStatus((cursor.getInt(6)>countChapters(cursor.getInt(0)))?true:false);
                story.setDescription(cursor.getString(3));
                story.setAuthor(cursor.getString(4));
                story.setLinkImg(cursor.getString(5));
                story.setNumberChapter(cursor.getInt(6));
                listTh.add(story);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listTh;
    }
    public ArrayList<Story> getTopStory(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Story> listTh = new ArrayList<>();
        Cursor cursor =  db.rawQuery( "select * from " + TABLE_STORY + " ORDER BY rating DESC LIMIT 20" , null );
        if (cursor.moveToFirst()) {
            do {
                Story story = new Story();
                story.setId(cursor.getInt(0));
                story.setNameStory(cursor.getString(1));
                story.setType(cursor.getString(2));
                story.setStatus((cursor.getInt(6)>countChapters(cursor.getInt(0)))?true:false);
                story.setDescription(cursor.getString(3));
                story.setAuthor(cursor.getString(4));
                story.setLinkImg(cursor.getString(5));
                story.setNumberChapter(cursor.getInt(6));
                listTh.add(story);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listTh;
    }

    public Story getStoryById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM story WHERE idStory ="+ id+ "",null );
        if (cursor != null)
            cursor.moveToFirst();

        Story story = new Story(cursor.getInt(0),cursor.getString(1),
                cursor.getString(2),(cursor.getInt(6)>countChapters(cursor.getInt(0)))?true:false,cursor.getString(3),
                cursor.getString(4),cursor.getString(5),cursor.getInt(6));
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
                story.setStatus((cursor.getInt(6)>countChapters(cursor.getInt(0)))?true:false);
                story.setDescription(cursor.getString(3));
                story.setAuthor(cursor.getString(4));
                story.setLinkImg(cursor.getString(5));
                story.setNumberChapter(cursor.getInt(6));

                listStory.add(story);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listStory;
    }

    public int deleteStory(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_STORY,  COLUMN_IDSTORY + "=?", new String[]{String.valueOf(id)});
    }

    public ArrayList<Chapter> getChapterByIdStory(int idStory) {

        ArrayList<Chapter> listChapter = new ArrayList<Chapter>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CHAPTER + " WHERE idStory = " + idStory;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Chapter chapter = new Chapter();
                chapter.setId(cursor.getInt(0));
                chapter.setNameChapter(cursor.getString(2));
                chapter.setContent(cursor.getString(3));
                listChapter.add(chapter);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listChapter;
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(  "SELECT * FROM "+TABLE_USER,null );
        return res;
    }
    public  void  addTaikhoan(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,user.getUsername());
        values.put(COLUMN_PASSWORD,user.getPassword());
        values.put(COLUMN_FULLNAME,user.getFullName());
        values.put(COLUMN_EMAIL,user.getEmail());
        values.put(COLUMN_LINKAVA,user.getLinkAva());
        values.put(COLUMN_POSITION,user.getPosition());
        db.insert(TABLE_USER,null,values);
        db.close();
        Log.e("ADD TK","TC");
    }
}

