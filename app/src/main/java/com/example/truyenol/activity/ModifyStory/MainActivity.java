package com.example.truyenol.activity.ModifyStory;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.truyenol.R;
import com.example.truyenol.database.DatabaseHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    private Button addBtn;
    private Button modifyBtn;
    private Button deleteBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addBtn=findViewById(R.id.addBtn);
        modifyBtn=findViewById(R.id.modifyBtn);
        deleteBtn=findViewById(R.id.deleteBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddStoryActivity();
            }
        });
        modifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openModifyStoryActivity();
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeleteStoryActivity();
            }
        });

    }
    public void openAddStoryActivity(){
        Intent intent=new Intent(this,AddStory.class);
        startActivity(intent);
    }
    public void openModifyStoryActivity(){
        Intent intent=new Intent(this,ModifyStory.class);
        startActivity(intent);
    }
    public void openDeleteStoryActivity(){
        Intent intent=new Intent(this,DeleteStory.class);
        startActivity(intent);
    }

}