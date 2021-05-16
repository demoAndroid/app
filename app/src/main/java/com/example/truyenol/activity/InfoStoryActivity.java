package com.example.truyenol.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.truyenol.R;
import com.example.truyenol.activity.ModifyStory.MainActivity;

public class InfoStoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infostory);
    }
    public void showChapter(View view){
        Intent intent1 = new Intent(this, StoryChapterActivity.class);
        startActivity(intent1);
    }

}