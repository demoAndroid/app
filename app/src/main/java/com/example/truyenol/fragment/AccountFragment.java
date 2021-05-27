package com.example.truyenol.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.truyenol.R;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class AccountFragment extends Fragment {
    int idUser;
    String fullname, position,email,linkAva;
    @SuppressLint("SdCardPath")
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_fragment,container,false);
        Bundle bundle = getArguments();
        if(bundle!=null){
            idUser = bundle.getInt("idUser");
            fullname = bundle.getString("fullname");
            position = bundle.getString("position");
            email = bundle.getString("email");
            linkAva = bundle.getString("linkAva");
        }
        ImageView avaUser = (ImageView)view.findViewById(R.id.avaUser);
        Glide.with(getContext()).load("/document/image:32").into(avaUser);
        TextView nameUser = (TextView)view.findViewById(R.id.nameUser);
        nameUser.setText(fullname);
        TextView roleTxt = (TextView)view.findViewById(R.id.roleTxt);
        roleTxt.setText(position);
        return view;
    }
}
