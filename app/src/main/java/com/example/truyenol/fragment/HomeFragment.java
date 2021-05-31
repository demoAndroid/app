package com.example.truyenol.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.truyenol.R;
import com.example.truyenol.activity.InfoStoryActivity;
import com.example.truyenol.adapter.StoryAdapter;
import com.example.truyenol.database.DatabaseHandler;
import com.example.truyenol.model.Story;
import com.example.truyenol.model.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    int idUser;
    String fullname, position,email,linkAva;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        Bundle bundle = getArguments();
        if(bundle!=null){
            idUser = bundle.getInt("idUser");
            fullname = bundle.getString("fullname");
            position = bundle.getString("position");
            email = bundle.getString("email");
            linkAva = bundle.getString("linkAva");
        }
        User user = new User(idUser,null,null,fullname,email,linkAva,position);
        DatabaseHandler db1 = new DatabaseHandler(getContext());
        final ArrayList<Story> list1 = db1.getNewStory();
        GridView lv1 = (GridView) view.findViewById(R.id.gridView1);
        StoryAdapter adapter = new StoryAdapter(getContext(), list1,user);
        lv1.setAdapter(adapter);

        final ArrayList<Story> list2 = db1.getAllStory();
        GridView lv2 = (GridView) view.findViewById(R.id.gridView2);
        StoryAdapter adapter1 = new StoryAdapter(getContext(), list2,user);
        lv2.setAdapter(adapter1);
        return view;
    }
}

