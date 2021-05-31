package com.example.truyenol.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.truyenol.R;
import com.google.android.material.chip.Chip;

public class InfoStoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infostory);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ImageView imgStory = (ImageView)findViewById(R.id.storyInfoImg);
        TextView nameStoryTxt = (TextView)findViewById(R.id.nameTxt);
        TextView authorTxt = (TextView)findViewById(R.id.authorTxt);
        TextView statusTxt = (TextView)findViewById(R.id.statusTxt);
        TextView descripTxt = (TextView)findViewById(R.id.descripTxt);
        Button readBtn = (Button)findViewById(R.id.readBtn);
        Button backBtn = (Button)findViewById(R.id.backBtn);
        Button type = (Button)findViewById(R.id.type);
        ImageView ava = (ImageView)findViewById(R.id.ava);
        if(bundle!=null){
            String linkAva = bundle.getString("linkAva");
            Glide.with((getApplicationContext())).load(linkAva).into(ava);
            String linkImg = bundle.getString("linkImg");
            Glide.with(getApplicationContext()).load(linkImg).into(imgStory);
            nameStoryTxt.setText(bundle.getString("nameStory"));
            authorTxt.setText(bundle.getString("author"));
            if(bundle.getBoolean("status")==true)
                statusTxt.setText("Còn tiếp");
            else statusTxt.setText("Hoàn thành");
            type.setText(bundle.getString("type"));
            descripTxt.setText(bundle.getString("description"));
        }


        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), StoryChapterActivity.class);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent2);
            }
        });

    }


}