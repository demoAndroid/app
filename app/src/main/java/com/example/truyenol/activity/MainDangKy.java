package com.example.truyenol.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.truyenol.R;
import com.example.truyenol.database.databaseHandler;
import com.example.truyenol.model.user;


import java.io.IOException;
import java.util.List;




public class MainDangKy extends AppCompatActivity {
    EditText editDangkyTK,editDangkyMK,editDangkyFull,editDangkyEmail;
    Button btndangky,btntrolai,btnanh;
    ImageView imgPhoto;
    databaseHandler databaseHandler;
    int REQUEST_CODE_CAMERA = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_ky);

        Signin();
        databaseHandler = new databaseHandler(this);
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editDangkyTK.getText().toString();
                String password = editDangkyMK.getText().toString();
                String email = editDangkyEmail.getText().toString();
                String fullname = editDangkyFull.getText().toString();
//                String avatar = imgPhoto.getI().toString();
                user user1= CreateUser();
                if (username.equals("") || password.equals("") || email.equals("") || fullname.equals("") ){
                    Log.e("Thông báo","Chưa nhập đầy đủ thông tin");
                }
                else {
                    databaseHandler.addTaikhoan(user1);
                    Toast.makeText(MainDangKy.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();

                }
            }
        });
        btnanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chonanh = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(chonanh, REQUEST_CODE_CAMERA);
            }
        });
        btntrolai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trolai = new Intent( MainDangKy.this,MainDangNhap.class);
                startActivity(trolai);
            }
        });

    }

    private user CreateUser(){

        String username = editDangkyTK.getText().toString();
        String password = editDangkyMK.getText().toString();
        String fullname = editDangkyFull.getText().toString();
        String email = editDangkyEmail.getText().toString();
//        String avatar = editDangkyAva.getText().toString();
        String position = "nhân viên";

        user tk = new user(username,password,fullname,email,position);
        return tk;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CAMERA&& resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgPhoto.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void Signin(){

        editDangkyTK = findViewById(R.id.edituser);
        editDangkyMK = findViewById(R.id.editpass);
        editDangkyEmail = findViewById(R.id.editemail);
        imgPhoto = findViewById(R.id.choseimage);
        btnanh = findViewById(R.id.btnchonanh);
        btndangky = findViewById(R.id.dangky);
        btntrolai = findViewById(R.id.trolai);
        editDangkyFull = findViewById(R.id.editfullname);

    }

}