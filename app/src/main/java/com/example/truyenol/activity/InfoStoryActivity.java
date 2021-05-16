package com.example.truyenol.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.truyenol.R;
import com.example.truyenol.database.DatabaseHandler;

public class InfoStoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infostory);
        DatabaseHandler db=new DatabaseHandler(getBaseContext());
        db.addStoryTest();
        final ImageView storyImg = (ImageView)findViewById(R.id.storyImg);
        final TextView nameStoryTxt = (TextView)findViewById(R.id.nameStoryTxt);
        final TextView authorTxt = (TextView)findViewById(R.id.authorTxt);
        final TextView statusTxt = (TextView)findViewById(R.id.statusTxt);
        final Button typeBtn = (Button)findViewById(R.id.typeBtn);
        final ImageView avatarImg = (ImageView)findViewById(R.id.avaImg);
        final TextView contentTxt = (TextView)findViewById(R.id.contentTxt);
        final EditText commentTxt = (EditText) findViewById(R.id.commentTxt);
        final Button commentBtn = (Button)findViewById(R.id.commentBtn);
        final Button showCommentBtn = (Button) findViewById(R.id.showComBtn);
        final Button backBtn = (Button) findViewById(R.id.backBtn);
        final Button readBtn = (Button) findViewById(R.id.readBtn);
        final Button likeBtn = (Button) findViewById(R.id.likeBtn);
    }
    public void showChapter(View view){
        Intent intent1 = new Intent(this, StoryChapterActivity.class);
        startActivity(intent1);
    }

}