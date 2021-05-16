package com.example.truyenol.activity.ModifyStory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.truyenol.R;
import com.example.truyenol.model.Chapter;

import java.util.ArrayList;

public class ModifyChapter extends AppCompatActivity {
    private Spinner chapterSpn;
    private EditText contentTxt;
    private Button saveBtn,confBtn;
    private ArrayList<Chapter> chapterList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_chapter);
        //Gán biến view
        chapterSpn=findViewById(R.id.chapterSpn);
        contentTxt=findViewById(R.id.contentTxt);
        saveBtn=findViewById(R.id.saveBtn);
        confBtn=findViewById(R.id.confBtn);
        //Set chapterList
        Intent intent=ModifyChapter.this.getIntent();
        int n=Integer.parseInt(intent.getStringExtra("storyId"));
        String[] oneToN=new String[n];
        chapterList=new ArrayList<Chapter>();
        for(int i=0;i<n;i++){
            chapterList.add(new Chapter("",""));
            oneToN[i]=""+(i+1);
        }
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item, oneToN);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chapterSpn.setAdapter(adapter);
        confBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void setChapter(String text){

    }
}