package com.example.truyenol.activity.ModifyStory;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import com.example.truyenol.database.DatabaseHandler;
import com.example.truyenol.model.Chapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ModifyChapter extends AppCompatActivity {
    private Spinner chapterSpn;
    private EditText contentTxt, nameChapTxt;
    private Button saveBtn, confBtn,addBtn,delBtn,modBtn;
    private ArrayList<Chapter> chapterList;
    private int chapterNumber,idStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_chapter);
        //Gán biến view
        getInital();
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addChapter();
            }
        });
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delChapter();
            }
        });
        modBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modChapter();
            }
        });
    }
    public void getInital(){
        nameChapTxt = findViewById(R.id.nameChapTxt);
        chapterSpn = findViewById(R.id.chapterSpn);
        contentTxt = findViewById(R.id.contentTxt);
        addBtn=findViewById(R.id.addBtn);
        delBtn=findViewById(R.id.delBtn);
        modBtn=findViewById(R.id.modBtn);
        saveBtn = findViewById(R.id.saveBtn);
        confBtn = findViewById(R.id.confBtn);
        contentTxt.setEnabled(false);
        nameChapTxt.setEnabled(false);
        chapterSpn.setEnabled(false);
        saveBtn.setEnabled(false);
        confBtn.setEnabled(false);
        Intent intent=this.getIntent();
        chapterNumber=Integer.parseInt(intent.getStringExtra("chapterNumber"));
        idStory=Integer.parseInt(intent.getStringExtra("id"));
    }
    public void addChapter(){
        contentTxt.setEnabled(true);
        nameChapTxt.setEnabled(true);
        saveBtn.setEnabled(true);
        addBtn.setEnabled(false);
        delBtn.setEnabled(false);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content=contentTxt.getText().toString();
                String name=nameChapTxt.getText().toString();
                DatabaseHandler db=new DatabaseHandler(getBaseContext());
                if(content.isEmpty()||name.isEmpty()){
                    Toast.makeText(ModifyChapter.this,"Không bỏ trống nội dung",Toast.LENGTH_SHORT).show();
                }else{
                    db.addChapter(new Chapter(name,content),idStory);
                    db.close();
                    contentTxt.setEnabled(false);
                    nameChapTxt.setEnabled(false);
                    saveBtn.setEnabled(false);
                    addBtn.setEnabled(true);
                    delBtn.setEnabled(true);
                    Toast.makeText(ModifyChapter.this,"Thêm chapter thành công",Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
    public void delChapter(){
        new AlertDialog.Builder(this)
                .setTitle("Xóa chapter")
                .setMessage("Bạn có chắc muốn xóa chapter?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHandler db=new DatabaseHandler(getBaseContext());
                        db.deleteChapter(chapterList.get(chapterList.size()-1).getId());
                        db.close();
                        Toast.makeText(ModifyChapter.this,"Xóa thành công!",Toast.LENGTH_SHORT).show();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton("no", null)
                .show();

    }
    public void modChapter(){
        DatabaseHandler db=new DatabaseHandler(getBaseContext());
        chapterList=db.getChapters(idStory);
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=1;i<=chapterList.size();i++){ list.add(i); }
        ArrayAdapter<Integer> adapter=new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item,list);
        chapterSpn.setAdapter(adapter);
        chapterSpn.setEnabled(true);
        confBtn.setEnabled(true);
        addBtn.setEnabled(false);
        delBtn.setEnabled(false);
        confBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBtn.setEnabled(true);
                chapterSpn.setEnabled(false);
                confBtn.setEnabled(false);
                nameChapTxt.setEnabled(true);
                contentTxt.setEnabled(true);
                Chapter chapter=chapterList.get(Integer.parseInt(chapterSpn.getSelectedItem().toString())-1);
                nameChapTxt.setText(chapter.getNameChapter());
                contentTxt.setText(chapter.getContent());
                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String content=contentTxt.getText().toString();
                        String name=nameChapTxt.getText().toString();
                        if(content.isEmpty()||name.isEmpty()){
                            Toast.makeText(ModifyChapter.this,"Không bỏ trống!", Toast.LENGTH_SHORT).show();
                        }else{
                            chapter.setNameChapter(name);chapter.setContent(content);
                            db.updateChapter(chapter);
                            db.close();
                            Toast.makeText(ModifyChapter.this,"Lưu thành công!", Toast.LENGTH_SHORT).show();
                            contentTxt.setEnabled(false);
                            nameChapTxt.setEnabled(false);
                            saveBtn.setEnabled(false);
                            addBtn.setEnabled(true);
                            delBtn.setEnabled(true);
                        }
                    }
                });
            }
        });

    }

}