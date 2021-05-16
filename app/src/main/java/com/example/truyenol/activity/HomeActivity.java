package com.example.truyenol.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.truyenol.R;
import com.example.truyenol.adapter.StoryAdapter;
import com.example.truyenol.database.DatabaseHandler;
import com.example.truyenol.model.Story;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    TextView type1Txt = (TextView)findViewById(R.id.type1Txt);
    GridView grid1 = (GridView)findViewById(R.id.gridView1) ;
    ArrayList<Story> tienHiep = new ArrayList<Story>();
    StoryAdapter storyAdapter;
    public void showTienHiep(){
        DatabaseHandler db = new DatabaseHandler(getBaseContext());
        Cursor cursor1 = db.getTienHiep();
        while (cursor1.moveToNext()){

            int id = cursor1.getInt(0);
            String nameStory = cursor1.getString(1);
            String type = cursor1.getString(2);
            String status = cursor1.getString(3);
            String description = cursor1.getString(4);
            String author = cursor1.getString(5);
            float rating = cursor1.getFloat(6);
            String linkImg = cursor1.getString(7);
            String numberChapter = cursor1.getString(8);



            tienHiep.add(new Story(id,nameStory,type,status,description,author,rating,linkImg,numberChapter));

            storyAdapter = new StoryAdapter(getApplicationContext(),tienHiep);
            grid1.setAdapter(storyAdapter);
        }
        cursor1.moveToFirst();
        cursor1.close();



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        showTienHiep();
    }

}