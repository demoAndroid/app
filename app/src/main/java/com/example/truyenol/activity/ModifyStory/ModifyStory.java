package com.example.truyenol.activity.ModifyStory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.truyenol.R;

public class ModifyStory extends AppCompatActivity {
    private EditText typeTxt,nameStoryTxt,desTxt,authorTxt,chapterNumberTxt;
    private TextView linkTxt;
    private Button linkImg,saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_story);
        linkTxt=findViewById(R.id.linkTxt);
        typeTxt=findViewById(R.id.typeTxt);
        nameStoryTxt=findViewById(R.id.nameStoryTxt);
        desTxt=findViewById(R.id.desTxt);
        authorTxt=findViewById(R.id.authorTxt);
        chapterNumberTxt=findViewById(R.id.chapterNumberTxt);
        linkImg=findViewById(R.id.linkAvaBtn);
        saveBtn=findViewById(R.id.saveBtn);
        linkImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageLink();
            }
        });
    }
    public void getImageLink(){
        Intent intent= new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==RESULT_OK){
            linkTxt.setText(data.getData().getPath());
        }
    }
}