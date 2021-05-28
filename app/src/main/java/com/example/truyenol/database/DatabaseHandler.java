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
                    ,(cursor.getInt(6)>countChapters(id))?true:false
                    ,cursor.getString(3),cursor.getString(4)
                    ,0,cursor.getString(5),cursor.getInt(6)));
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
                    ,cursor.getString(3),cursor.getString(4)
                    ,0,cursor.getString(5),cursor.getInt(6));
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
    public void deleteChapter(int idChapter){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_CHAPTER,COLUMN_IDCHAPTER+"=?",new String[]{String.valueOf(idChapter)});
        db.close();  
    }
    public void addStoryTest(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMESTORY,"Thần Đạo Đan Tôn");
        values.put(COLUMN_TYPE, "Tiên hiệp");
        values.put(COLUMN_STATUS, "Hoàn Thành");
        values.put(COLUMN_DESCRIPTION, "Lăng Hàn - Một Đan Đế đại danh đỉnh định mang trong thân mình tuyệt thế công pháp vì truy cầu bước cuối, xé bỏ tấm màn thành thần nhưng thất bại đã phải bỏ mình. Thế nhưng ông trời dường như không muốn tuyệt dường người, Lăng Hàn đã được trọng sinh vào một thiếu niên cùng tên và điều may mắn nhất là \"Bất Diệt Thiên Kinh\" ấn ký vẫn còn nằm nguyên trong tâm thức hắn.\n" +
                "\n" +
                "Từ nay về sau sóng gió cuộn trào nổi lên, Đan Đế ngày xưa bây giờ phải cùng tranh phong với vô số thiên tài trẻ tuổi, lại bắt đầu một truyền thuyết mới như để chứng minh với trời đất: Ta, là người mạnh nhất");
        values.put(COLUMN_AUTHOR, "Cô Đơn Địa Phi");
        values.put(COLUMN_RATING, "7.1");
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

    public Story getStoryById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM story WHERE idStory ="+ id+ "",null );
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

    public int deleteStory(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_STORY,  COLUMN_IDSTORY + "=?", new String[]{String.valueOf(id)});
    }
    public void  addChapter(Chapter chapter,int storyid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IDCHAPTER_STORY, storyid);
        values.put(COLUMN_NAMECHAPTER, chapter.getNameChapter());
        values.put(COLUMN_CONTENT, chapter.getContent());
        db.insert(TABLE_CHAPTER,null,values);
        db.close();
    }
    public void  addChapterTest(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IDCHAPTER_STORY,"10");
        values.put(COLUMN_NAMECHAPTER,"Chương 1: Sơn biên tiểu thôn");
        values.put(COLUMN_CONTENT,"\"Anh ngố\" trợn trừng hai mắt, nhìn chằm chằm vào nóc nhà được tạo thành từ cỏ dại và bùn trộn lẫn. Toàn thân hắn được trùm bởi một cái áo bông đã cũ, ố vàng, nhìn không còn ra hình dạng ban đâu, phảng phất tán phát ra một ít mùi ẩm mốc.\n" +
                "\n" +
                "Bên cạnh hắn còn có một người nữa, là nhị ca Hàn Chú, đang ngủ rất say sưa. Thỉnh thoảng có tiếng ngáy nhè nhẹ phát ra từ đó.\n" +
                "\n" +
                "Cách giường chừng nửa trượng, là một vách tường đất đổ nát, vì thời gian đã quá lâu, trên vách tường đã xuất hiện vài vết nứt dài. Từ những vết nứt đó, loáng thoáng truyền đến thanh âm oán thán của Hàn mẫu, ngoài ra còn có thanh âm Hàn phụ đang hút thuốc rất là hấp dẫn.\n" +
                "\n" +
                "'Anh ngố' từ từ nhắm đôi mắt có chút bức bối lại. Muốn thật nhanh chìm vào giấc ngủ sâu. Trong lòng hắn biết rõ ràng, nếu bây giờ mà còn không ngủ ngay, ngày mai không thể nào dậy sớm được, cũng không thể cùng đám bạn cùng đi đốn củi được.\n" +
                "\n" +
                "\"Anh ngố\" họ Hàn tên Lập, loại danh tự có ý nghĩa như thế này cha mẹ hắn không có khả năng đưa ra. Cái này là do phụ thân hắn dùng rượu oa đầu chế bởi thô lương, cầu lão Trương trong thôn đặt cho.\n" +
                "Lão Trương khi còn trẻ, đã từng làm thư đồng mấy năm cho một nhà có tiền trong thành. Là người duy nhất trong thôn nhận biết được vài chữ. Tên gọi của hầu hết tiểu hài tử trong thôn, đều là do lão Trương đặt cho.\n" +
                "\n" +
                "Hàn Lập bị người trong thôn gọi là \"Anh ngố\" không phải là do hắn ngố hay đần thật sự, ngược lại, hắn còn là đứa trẻ thông minh nhất làng, ngoài ra trông hắn so với những đứa trẻ khác trong làng không có gì khác biệt. Trừ những người trong nhà ra, hắn rất ít khi nghe thấy nguời ta gọi tên chính thức Hàn Lập của hắn, mà hầu như chỉ là \"Anh ngố\", và cái tên \"Anh ngố\" này vẫn được sử dụng cho đến tận bây giờ.\n" +
                "\n" +
                "Sở dĩ Hàn Lập bị mọi người ban cho hỗn danh \"anh ngố\" là vì trong thôn vốn đã có một \"anh ngốc\" rồi.\n" +
                "\n" +
                "Điều này cũng không có gì to tát cả, tất cả những đứa trẻ khác trong thôn đều có hỗn danh như \"cẩu oa\" hay \"nhị đản\", so với danh tự \"anh ngố\" thì còn khó nghe hơn.\n" +
                "\n" +
                "Cũng bởi vậy, Hàn Lập mặc dù không thích cách xưng hô này nhưng cũng chỉ có thể tự an ủi mình mà thôi.\n" +
                "\n" +
                "Hàn Lập bề ngoài trông không được vừa mắt, da tay thì đen đúa, đích thực là một đứa trẻ bình thường chốn làng quê. Tuy nhiên, nội tâm của cậu bé thì không hề nông nổi, so với những đứa bé cùng lứa tuổi thì chín chắn hơn nhiều. Hắn từ nhỏ đã hướng tới thế giới phồn hoa bên ngoài, mơ rằng có một ngày, hắn có thể ra khỏi thôn làng, đi xem xem cái thế giới phù hoa mà lão Trương thường nói đến.\n" +
                "\n" +
                "Khi Hàn Lập nghĩ đến ý tưởng này, hắn không dám đề cập ra cho người khác biết. Nếu không, nhất định làm cho mọi người trong thôn cảm thấy ngạc nhiên, một tiểu hài tử miệng còn chưa khô mùi sữa, thế mà dám mơ tưởng đến những ý nghĩ xa vời mà ngay cả một người lớn cũng chưa dám nghĩ đến. Cần phải biết rằng, những đưa trẻ khác cùng tuổi với Hàn Lập thì tầm tuổi này chỉ biết đuổi gà, bắt chó, tất nhiên là ở đây sẽ không nói đến những kẻ có ý nghĩ tha huơng cầu thực.\n" +
                "\n" +
                "Gia đình Hàn Lập có bảy miệng ăn, trên hắn có hai vị huynh trưởng, một tỷ tỷ, hắn trong nhà đứng thứ tư, ngoài ra hắn còn có một tiểu muội muội nữa. Năm nay hắn vừa mới mười tuổi, gia cảnh bần hàn, cả năm cũng không có mấy bữa được ăn no. Mọi người trong nhà đều chỉ mong được ăn đủ no, mặc đủ ấm.\n" +
                "\n" +
                "Hàn Lập lúc này, đang mơ mơ màng màng, tuy ngủ mà chưa ngủ, trong đầu vẫn còn phảng phất ý niệm: Ngày mai lên núi, nhất định sẽ mang về cho tiểu muội muội mà hắn yêu thương nhất thật nhiều hồng tương quả*, loại quả mà muội muội hắn thích nhất.\n" +
                "\n" +
                "Vào giữa trưa ngày thứ hai, Hàn Lập dưới ánh nắng chói trang, lưng gùi bó củi cao bằng nửa người hắn, trước ngực thì ôm một nắm đầy hồng tương quả, đang từ ngọn núi trở về nhà. Lúc này, hắn không hề biết rằng trong nhà đang có một vị khách đến chơi, mà vị khách này, chính là người cải biến vận mệnh của hắn.\n" +
                "\n" +
                "Vị quí khách này, cùng hắn có mối quan hệ huyết thống rất gần, ông ta chính là tam thúc ruột của hắn.\n" +
                "\n" +
                "Nghe nói, trong vùng, tại tửu lâu ở tiểu thành phụ cận, được nguời ta tín nhiệm đề bạt làm đại chưởng quĩ, chính là người mà cha mẹ hắn thường nói. Hàn gia trong vòng trăm năm trở lại đây, mới lại có thể xuất hiện một người như tam thúc của Hàn Lập.\n" +
                "\n" +
                "Hàn Lập từ nhỏ cho đến giờ, gặp mặt vị tam thúc này cũng chỉ vài lần. Đại ca của hắn được đi theo một lão thợ rèn trong thành để học việc cũng là do vị tam thúc này giới thiệu cho. Vị tam thúc này còn thường xuyên giấu mọi người cấp cho cha mẹ hắn đồ ăn thức uống, chiếu cố tận tình gia đình hắn. Cũng chính vì vậy, ấn tượng của Hàn Lập đối với vị tam thúc này rất là tốt, hắn cũng biết rằng tuy cha mẹ hắn không nói ra miệng nhưng trong tâm cũng rất cảm kích.\n" +
                "\n" +
                "Đại ca hắn chính là niềm kiêu hãnh của cả nhà, nghe nói làm thợ rèn học đồ, không kể ăn ở, mỗi tháng còn nhận được ba mươi đồng bạc trắng, đợi đến lúc xuất sư, có người thuê thì tiền kiếm được còn nhiều hơn nữa.\n" +
                "\n" +
                "Mỗi khi cha mẹ đề cập đến đại ca, thần thái đều bay bổng, trông khác hẳn so với thường ngày. Hàn Lập tuổi tuy nhỏ, nhưng cũng hâm mộ không thôi, công việc vừa lòng sớm đã có rồi, đó chính là theo một vị thủ nghệ sư phó trong tiểu thành học tập nấu ăn, sau đó sẽ trở thành một người nấu ăn có tay nghề.\n" +
                "\n" +
                "Ngay khi Hàn Lập nhìn thấy một người toàn thân diện y phục mới, khuôn mặt béo tròn, thì biết ngay đó là tam thúc của mình, tâm lý vô cùng hưng phấn.\n" +
                "\n" +
                "Bỏ lại đám củi ra sau nhà xong, liền tiến lên nhà làm lễ tham kiến tam thúc, ngoan ngoãn cất tiếng chào: \"tam thúc hảo\", rồi sau đó đứng yên một bên, nghe phụ mẫu và tam thúc trò chuyện phiếm.\n" +
                "\n" +
                "Tam thúc cười cười nhìn Hàn Lập, đánh giá hắn một hồi, luôn miệng khen hắn những lời như là \"nghe lời\" với \"hiểu việc\", sau đó lại quay đầu lại, tiếp tục trò chuyện với phụ mẫu hắn về mục đích chuyến đi lần này của lão.\n" +
                "\n" +
                "Hàn Lập tuổi còn chưa lớn hẳn, nên khi nghe tam thúc nói hắn cũng không hiểu hết, chỉ là hiểu được đại khái mà thôi.\n" +
                "\n" +
                "Nguyên lai là tam thúc của hắn làm việc ở một tiểu lâu, mà tiểu lâu này lại thuộc về một bang phái giang hồ có tên là \"Thất huyền môn\". Môn phái này chia ra làm ngoại môn và nội môn. Cách đây không lâu, tam thúc của hắn cũng đã chính thức trở thành đệ tử ngoại môn của môn phái đó, và có thể đứng ra đề cử hài đồng nhỏ tuổi ( từ bảy đến mười hai tuổi) tham gia khảo nghiệm chiêu thu đệ tử của Thất huyền môn.\n" +
                "\n" +
                "Cứ năm năm một lần, Thất huyền môn lại tổ chức kỳ thi chiêu lãm đệ tử, mà cuộc thi này sẽ được tổ chức trong tháng tới. Với một người không khéo, nhanh nhạy lại không có con cái, tự nhiên phải nghĩ đến đứa cháu Hàn Lập có độ tuổi thích hợp rồi.\n" +
                "\n" +
                "Cha của Hàn Lập vốn thận trọng, nghe đến những từ như \"giang hồ\" \"môn phái\", trong lòng có chút do dự, không đưa ra được chủ ý, liền vớ ngay lấy điếu cày, rít lên mấy tiếng \"ba tháp\" \"ba tháp\", sau đó ngồi yên tại chỗ, không nói câu gì nữa.\n" +
                "\n" +
                "Theo lời tam thúc nói, thì trong phương viên mấy trăm dặm, Thất huyền môn là môn phái xếp thứ nhất, thứ hai gì đó.\n" +
                "\n" +
                "Chỉ cần trở thành đệ tử nội môn, chẳng những sau này, vừa miễn phí tập võ, vừa không phải lo lắng chuyện ăn uống, mà mỗi tháng còn kiếm được một hai lượng bạc lẻ nữa. Hơn nữa cho dù không trúng tuyển trở thành đệ tử nội môn, thì cũng có hi vọng trở thành đệ tử ngoại môn giống như tam thúc của hắn, chuyên lo việc làm sinh ý cho Thất huyền môn.\n" +
                "\n" +
                "Nghe đến có khả năng mỗi tháng kiếm được một hai lượng bạc trắng, lại còn có thể có cơ hội trở thành người như tam thúc, Hàn phụ cuối cùng cũng đưa ra chủ ý, đáp ứng lời đề nghị của tam thúc.\n" +
                "\n" +
                "Tam thúc thấy Hàn phụ đáp ứng rồi, trong lòng rất cao hứng. Liền lưu lại vài lượng bạc, nói tháng sau sẽ đến dẫn Hàn Lập đi, trong khoảng thời gian này, cho hắn ăn uống tốt hơn một chút, bồi bổ thân thể để còn có thể tham gia ứng thí. Cuối cùng, tam thúc cùng cha mẹ hắn chào tạm biệt nhau, xoa xoa đầu hắn rồi ra khỏi cửa đi về.\n" +
                "\n" +
                "Hàn Lập mặc dù nghe không hoàn toàn hiểu những gì tam thúc vừa nói, nhưng có khả năng vào thành, có thể kiếm tiền thì hắn nghe là hiểu được.\n" +
                "\n" +
                "Nguyện vọng của hắn từ trước đến nay, mắt thấy có thể thực hiện được, hắn trong mấy buổi tối liên tiếp, hưng phấn nên ngủ không yên.\n" +
                "\n" +
                "Một tháng sau, tam thúc của hắn theo đúng thời gian ước định quay lại thôn để dẫn Hàn Lập đi. Trước khi đi, Hàn phụ dặn dò, động viên, chúc phúc Hàn Lập: Làm người phải thành thật, gặp chuyện thì nên nhẫn nhịn, không nên cùng người khác tranh chấp, mà Hàn mẫu dặn hắn cần phải chú ý thân thể, ăn ngủ cho tốt.\n" +
                "\n" +
                "Ngồi trên xe ngựa, nhìn thân ảnh cha mẹ xa dần, Hàn Lập cắn chặt hàm răng, cố gắng kìm nén không cho nước mắt chảy ra ngoài.\n" +
                "\n" +
                "Tuy hắn so với những đứa trẻ cùng trang lứa thì chín chắn hơn, nhưng dù sao thì hắn vẫn còn là một đứa trẻ mới mười tuổi, lần đầu tiên xa nhà làm cho hắn cảm thấy có chút bàng hoàng, thương cảm. Trong tâm lý còn non dại của hắn đã hạ quyết tâm, đợi đến khi kiếm được tiền rồi sẽ ngay lập tức rong ngựa trở về với cha mẹ, không xa rời nhau nữa.\n" +
                "\n" +
                "Hàn Lập từ trước không có nghĩ đến, chỉ là sau khi rời khỏi thôn làng hắn mới nhận ra, tiền đã không còn ý nghĩa với hắn như lúc xưa nữa rồi. Mà hắn mặc nhiên không biết rằng, con đường vận mệnh của hắn không giống như những người bình thường. Con đường tu tiên của hắn bắt đầu từ đây.\n" +
                "\n" +
                "* hồng tương quả: Hình như là quả trứng cá");
        db.insert(TABLE_CHAPTER,null,values);
        db.close();
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

