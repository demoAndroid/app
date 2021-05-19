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
    private EditText storyTxt,typeTxt,authorTxt,desTxt;
    private TextView linkAvaTxt;
    private Button linkAvaBtn,addChapterBtn,saveBtn;
    private Spinner chapNumberSpn;
    private ArrayList<Chapter> chapterList;
    private int chapterNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Gán biến View
        setContentView(R.layout.activity_add_story);
        storyTxt=findViewById(R.id.nameStoryTxt);
        typeTxt=findViewById(R.id.typeTxt);
        authorTxt=findViewById(R.id.authorTxt);
        desTxt=findViewById(R.id.desTxt);
        linkAvaTxt=findViewById(R.id.linkTxt1);
        linkAvaBtn=findViewById(R.id.linkAvaBtn);
        addChapterBtn=findViewById(R.id.addChapterBtn);
        saveBtn=findViewById(R.id.saveBtn);
        //Set linkAvaBtn
        addChapterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doAddChapter();
            }
        });
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
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,1);

    }
    public void doAddChapter(){
        Intent intent=new Intent(this,ModifyChapter.class);
        startActivityForResult(intent,2);

    }
    public void saveStory(){
        Story story=new Story();
        DatabaseHandler db=new DatabaseHandler(getBaseContext());
        story.setAuthor(authorTxt.getText().toString());
        story.setChapters(chapterList);
        story.setDescription(desTxt.getText().toString());
        story.setType(typeTxt.getText().toString());
        story.setLinkImg(linkAvaTxt.getText().toString());
        story.setNumberChapter(chapterNumber);
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
            String path= data.getData().getPath();
            linkAvaTxt.setText(path);
        }
        if(requestCode==2&&resultCode==RESULT_OK){
            chapterList=new ArrayList<Chapter>();
            chapterNumber =Integer.parseInt(data.getStringExtra("chapterNumber"));
            for(Integer i=0;i<2*Integer.parseInt(data.getStringExtra("chapterNumber"));i++){
                String nameChapter=data.getStringExtra(i.toString());i++;
                String content=data.getStringExtra(i.toString());
                chapterList.add(new Chapter(nameChapter,content));
            }
        }
    }
}