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
    private EditText contentTxt,nameChapTxt,chapNumberTxt;
    private Button saveBtn,confBtn,conf1Btn;
    private ArrayList<Chapter> chapterList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_chapter);
        //Gán biến view
        nameChapTxt=findViewById(R.id.nameChapTxt);
        chapNumberTxt=findViewById(R.id.chapNumberTxt);
        chapterSpn=findViewById(R.id.chapterSpn);
        contentTxt=findViewById(R.id.contentTxt);
        saveBtn=findViewById(R.id.saveBtn);
        conf1Btn=findViewById(R.id.conf1Btn);
        confBtn=findViewById(R.id.confBtn);
        contentTxt.setEnabled(false);
        nameChapTxt.setEnabled(false);
        chapterSpn.setEnabled(false);
        saveBtn.setEnabled(false);
        confBtn.setEnabled(false);
        //Set chapterList
        Intent intent=ModifyChapter.this.getIntent();
        int n=Integer.parseInt(intent.getStringExtra("chapNumber"));
        String[] oneToN=new String[n];
        chapterList=new ArrayList<Chapter>();
        for(int i=0;i<n;i++){
            chapterList.add(new Chapter("",""));
            oneToN[i]=""+(i+1);
        }
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item, oneToN);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chapterSpn.setAdapter(adapter);
        conf1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmChapterNumber();
            }
        });
        confBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChapters(chapterSpn.getSelectedItem().toString());

            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSaveResult();
            }
        });
    }
    public void setChapters(String chapterNumber){
        if(confBtn.getText().toString()=="Chọn"){
           chapterSpn.setEnabled(false);
           saveBtn.setEnabled(false);
           confBtn.setText("OK");
        }
        else{
            confBtn.setText("Chọn");
            chapterList.set(Integer.parseInt(chapterNumber)-1,new Chapter(nameChapTxt.getText().toString(),contentTxt.getText().toString()));
        }
    }
    public void doSaveResult(){
        Intent intent=new Intent();
         for(Integer i=0;i<2*chapterList.size();i+=2){
            intent.putExtra(i.toString(),chapterList.get(i).getNameChapter());i++;
             intent.putExtra(i.toString(),chapterList.get(i).getNameChapter());
         }
         setResult(RESULT_OK,intent);
         finish();
    }
    public void confirmChapterNumber(){
        if(conf1Btn.getText().toString()=="OK"){
            conf1Btn.setText("Hủy");
            contentTxt.setEnabled(true);
            nameChapTxt.setEnabled(true);
            chapterSpn.setEnabled(true);
            saveBtn.setEnabled(true);
            confBtn.setEnabled(true);
        }else{
            conf1Btn.setText("OK");
            contentTxt.setEnabled(false);
            nameChapTxt.setEnabled(false);
            chapterSpn.setEnabled(false);
            saveBtn.setEnabled(false);
            confBtn.setEnabled(false);
        }
    }
}