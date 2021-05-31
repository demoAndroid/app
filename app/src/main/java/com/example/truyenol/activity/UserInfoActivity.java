package com.example.truyenol.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.truyenol.R;

public class UserInfoActivity extends AppCompatActivity {
    int idUser;
    String fullname, position,email,linkAva;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            idUser = bundle.getInt("idUser");
            fullname = bundle.getString("fullname");
            position = bundle.getString("position");
            email = bundle.getString("email");
            linkAva = bundle.getString("linkAva");
        }

        ImageView avaInfo = (ImageView)findViewById(R.id.avaInfo);
        Glide.with(getApplicationContext()).load(linkAva).into(avaInfo);
        TextView fullnameTxt = (TextView)findViewById(R.id.fullnameTxt);
        fullnameTxt.setText(fullname);
        TextView emailTxt = (TextView)findViewById(R.id.emailTxt);
        emailTxt.setText(email);
    }
}