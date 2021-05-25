package com.example.truyenol.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.truyenol.R;
import com.example.truyenol.adapter.RankingAdapter;
import com.example.truyenol.database.DatabaseHandler;
import com.example.truyenol.model.Story;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RankingFragment extends Fragment {
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ranking_fragment, container, false);
        DatabaseHandler db1 = new DatabaseHandler(getContext());
        final ArrayList<Story> list1 = db1.getTopStory();
        ListView lv1 = (ListView) view.findViewById(R.id.listTopStory);
        RankingAdapter adapter = new RankingAdapter(getContext(), list1);
        lv1.setAdapter(adapter);
        return view;
    }
}