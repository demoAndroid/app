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
    }

    public void doSetLinkAva(){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,1);

    }
    public void doAddChapter(){
        Intent intent=new Intent(this,ModifyChapter.class);
        intent.putExtra("chapNumber",chapNumberSpn.getSelectedItem().toString());
        startActivityForResult(intent,2);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==RESULT_OK){
            String path= data.getData().getPath();
            linkAvaTxt.setText(path);
        }
        if(requestCode==2&&requestCode==RESULT_OK){
            for(Integer i=0;i<2*chapterList.size();i+=2){
                data.getStringExtra(i.toString());i++;
                data.getStringExtra(i.toString());
            }

        }
    }
}