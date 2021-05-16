package com.example.truyenol.activity.ModifyStory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.truyenol.R;

import java.util.Collections;

public class AddStory extends AppCompatActivity {
    private EditText storyTxt,typeTxt,authorTxt,desTxt;
    private TextView linkAvaTxt;
    private Button linkAvaBtn,addChapterBtn;
    private Spinner chapNumberSpn;
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
        chapNumberSpn=findViewById(R.id.spinner);
        //Set spinner
        String[] oneTo100=new String[100];
        for(int i=0;i<100;i++) oneTo100[i]=""+(i+1);
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item, oneTo100);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chapNumberSpn.setAdapter(adapter);
        //Set linkAvaBtn
        linkAvaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void doSetLinkAva(){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}