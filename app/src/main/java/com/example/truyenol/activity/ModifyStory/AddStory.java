package com.example.truyenol.activity.ModifyStory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.truyenol.R;
import com.example.truyenol.database.DatabaseHandler;
import com.example.truyenol.model.Chapter;
import com.example.truyenol.model.Story;

import java.util.ArrayList;
import java.util.Collections;

public class AddStory extends AppCompatActivity {
    private EditText storyTxt,typeTxt,authorTxt,desTxt,chapNumberTxt;
    private TextView linkAvaTxt;
    private Button linkAvaBtn,saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Gán biến View
        setContentView(R.layout.activity_add_story);
        storyTxt=findViewById(R.id.nameStoryTxt);
        chapNumberTxt=findViewById(R.id.chapterNumberTxt);
        typeTxt=findViewById(R.id.typeTxt);
        authorTxt=findViewById(R.id.authorTxt);
        desTxt=findViewById(R.id.desTxt);
        linkAvaTxt=findViewById(R.id.linkTxt1);
        linkAvaBtn=findViewById(R.id.linkAvaBtn);
        saveBtn=findViewById(R.id.saveBtn);
        //Set linkAvaBtn
        linkAvaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSetLinkAva();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStory();
            }
        });
    }

    public void doSetLinkAva(){
        Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        startActivityForResult(intent,1);

    }
    public void saveStory(){
        Story story=new Story();
        DatabaseHandler db=new DatabaseHandler(getBaseContext());
        story.setAuthor(authorTxt.getText().toString());
        story.setChapters(new ArrayList<>());
        story.setDescription(desTxt.getText().toString());
        story.setType(typeTxt.getText().toString());
        story.setLinkImg(linkAvaTxt.getText().toString());
        story.setNumberChapter(Integer.parseInt(chapNumberTxt.getText().toString()));
        story.setNameStory(storyTxt.getText().toString());
        db.addStory(story);
        db.close();
        Toast.makeText(this,"Save data success!",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,MainActivity.class);
        finish();
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==RESULT_OK){
            String path= data.getData().toString();
            linkAvaTxt.setText(path);
        }
    }
}