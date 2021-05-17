package com.example.truyenol.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truyenol.R;
import com.example.truyenol.adapter.StoryAdapter;
import com.example.truyenol.database.DatabaseHandler;
import com.example.truyenol.model.Story;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    TextView type1Txt;
    GridView list1;
    ArrayList<Story> listStory;
    DatabaseHandler db;
    StoryAdapter storyAdapter;
    protected void showTienHiep()
    {

        DatabaseHandler db1 = new DatabaseHandler(getBaseContext());
        final ArrayList<Story> list1 = db1.getTienHiep();
        GridView lv1= (GridView)findViewById(R.id.gridView1);
        StoryAdapter adapter = new StoryAdapter(this,list1);
        lv1.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        showTienHiep();
    }

}