package com.example.truyenol.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.truyenol.R;


public class MainDangKy extends AppCompatActivity {

    EditText editDangkyTK,editDangkyMK,editDangkyEmail,editDangkyAva;
    Button btndangky,btntrolai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_ky);

        Signin();
    }
    private void Signin(){

        editDangkyTK = findViewById(R.id.edituser);
        editDangkyMK = findViewById(R.id.editpass);
        editDangkyEmail = findViewById(R.id.editemail);

    }
}