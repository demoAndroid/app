package com.example.truyenol.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.truyenol.R;
import com.example.truyenol.adapter.TruyenAdapter;
import com.example.truyenol.database.DatabaseHandler;
import com.example.truyenol.model.Story;

import java.util.ArrayList;

public class TimKiemActivity extends AppCompatActivity {

    ArrayList<Story> arrayList;
    ArrayList<Story> StoryArrayList;
    ListView listView;
    EditText edt;
    TruyenAdapter StoryAdapter;
    DatabaseHandler DatabaseHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);



        listView =findViewById(R.id.listviewTimKiem);
        edt = findViewById(R.id.edit_search);


        init();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TimKiemActivity.this,InfoStoryActivity.class);
                String tenTruyen = arrayList.get(position).getNameStory();
                String noidung = arrayList.get(position).getDescription();
                intent.putExtra("tenTruyen", tenTruyen);
                intent.putExtra("moTa", noidung);
                startActivity(intent);
            }
        });

        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });
    }

    private void filter(String text){
        arrayList.clear();
        ArrayList<Story> filteredList = new ArrayList<>();

        for(Story item : StoryArrayList){
            if(item.getNameStory().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);

                arrayList.add(item);
            }

        }
        StoryAdapter.filterList(filteredList);
    }

    private void init(){
        StoryArrayList = new ArrayList<>();

        arrayList = new ArrayList<>();

        DatabaseHandler = new DatabaseHandler(this);

        Cursor cursor = DatabaseHandler.getData2();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String tenTruyen = cursor.getString(1);
            String theLoai = cursor.getString(2);
            boolean trangThai = Boolean.parseBoolean(cursor.getString(3));
            String moTa = cursor.getString(4);
            String tacGia = cursor.getString(5);
            String anh = cursor.getString(6);
            int chap = cursor.getInt(7);


            StoryArrayList.add(new Story(id,tenTruyen,theLoai,trangThai,moTa,tacGia,anh,chap));


            arrayList.add(new Story(id,tenTruyen,theLoai,trangThai,moTa,tacGia,anh,chap));




            StoryAdapter = new TruyenAdapter(getApplicationContext(),StoryArrayList);
            listView.setAdapter(StoryAdapter);
        }
        cursor.moveToFirst();
        cursor.close();


    }
}