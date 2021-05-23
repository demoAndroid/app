package com.example.truyenol.activity.ModifyStory;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truyenol.R;
import com.example.truyenol.model.Chapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ModifyChapter extends AppCompatActivity {
    private Spinner chapterSpn;
    private EditText contentTxt, nameChapTxt;
    private Button saveBtn, confBtn;
    private ArrayList<Chapter> chapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_chapter);
        //Gán biến view
        nameChapTxt = findViewById(R.id.nameChapTxt);
        chapterSpn = findViewById(R.id.chapterSpn);
        contentTxt = findViewById(R.id.contentTxt);
        saveBtn = findViewById(R.id.saveBtn);
        confBtn = findViewById(R.id.confBtn);
        contentTxt.setEnabled(false);
        nameChapTxt.setEnabled(false);
        chapterSpn.setEnabled(false);
        saveBtn.setEnabled(false);
        confBtn.setEnabled(false);
        confBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index=Integer.parseInt(chapterSpn.getSelectedItem().toString())-1;
                setChapters(index);

            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSaveResult();
            }
        });
    }

    public void setChapters(int index) {
        if (chapterSpn.isEnabled() == true) {
            chapterSpn.setEnabled(false);
            contentTxt.setEnabled(true);
            contentTxt.setText(chapterList.get(index).getContent());
            nameChapTxt.setText(chapterList.get(index).getNameChapter());
            nameChapTxt.setEnabled(true);
            confBtn.setText("OK");

        } else {
            confBtn.setText("Chọn");
            nameChapTxt.setEnabled(false);
            contentTxt.setEnabled(false);
            chapterSpn.setEnabled(true);
            chapterList.set(index, new Chapter(nameChapTxt.getText().toString(), contentTxt.getText().toString()));
        }
    }

    public void doSaveResult() {
        Intent intent = new Intent();
        for (Integer i = 0,z=0; i < chapterList.size(); i++) {
            intent.putExtra(z.toString(), chapterList.get(i).getNameChapter());z++;
            intent.putExtra(z.toString(), chapterList.get(i).getContent());z++;
        }
        intent.putExtra("chapterNumber", String.valueOf(chapterList.size()));
        setResult(RESULT_OK, intent);
        finish();
    }

}