package com.example.truyenol.activity.ModifyStory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.truyenol.R;
import com.example.truyenol.database.DatabaseHandler;
import com.example.truyenol.model.Story;

import java.util.ArrayList;

public class ModifyStory extends AppCompatActivity {
    private EditText typeTxt, nameStoryTxt, desTxt, authorTxt, chapterNumberTxt;
    private TextView linkTxt;
    private Button linkImg, saveBtn;
    private Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_story);
        linkTxt = findViewById(R.id.linkTxt);
        typeTxt = findViewById(R.id.typeTxt);
        nameStoryTxt = findViewById(R.id.nameStoryTxt);
        desTxt = findViewById(R.id.desTxt);
        authorTxt = findViewById(R.id.authorTxt);
        chapterNumberTxt = findViewById(R.id.chapterNumberTxt);
        linkImg = findViewById(R.id.linkAvaBtn);
        saveBtn = findViewById(R.id.saveBtn);
        getStory();
        linkImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageLink();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStory();
            }
        });
    }

    public void getImageLink() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, 1);
    }

    public void saveStory() {
        DatabaseHandler db = new DatabaseHandler(getBaseContext());
        story.setAuthor(authorTxt.getText().toString());
        story.setChapters(new ArrayList<>());
        story.setDescription(desTxt.getText().toString());
        story.setType(typeTxt.getText().toString());
        story.setLinkImg(linkTxt.getText().toString());
        story.setNumberChapter(Integer.parseInt(chapterNumberTxt.getText().toString()));
        story.setNameStory(nameStoryTxt.getText().toString());
        db.updateStory(story);
        db.close();
        Toast.makeText(this, "Save data success!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    public void getStory() {
        Intent intent = this.getIntent();
        DatabaseHandler db = new DatabaseHandler(getBaseContext());
        story = db.getStoriesById(Integer.parseInt(intent.getStringExtra("id")));
        typeTxt.setText(story.getType());
        nameStoryTxt.setText(story.getNameStory());
        desTxt.setText(story.getDescription());
        authorTxt.setText(story.getAuthor());
        chapterNumberTxt.setText(String.valueOf(story.getNumberChapter()));
        linkTxt.setText(story.getLinkImg());
        db.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            linkTxt.setText(data.getData().getPath());
        }
    }
}