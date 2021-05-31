package com.example.truyenol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.truyenol.R;
import com.example.truyenol.model.Story;
import com.example.truyenol.model.User;

import java.util.ArrayList;
import java.util.List;

public class TruyenAdapter extends BaseAdapter {
    private final Context context;
    private List<Story> list = new ArrayList<>();
    private User user = new User();
    private ArrayList<Story> listTruyen;

    public TruyenAdapter(Context context, ArrayList<Story> listTruyen){
        this.context = context;
        this.listTruyen = listTruyen;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.show1story, null);

        TextView nameStoryTxt = convertView.findViewById(R.id.nameHomeTxt);
        nameStoryTxt.setText(list.get(position).getNameStory());
        ImageView imgStory = convertView.findViewById(R.id.imgHome);
        Glide.with(context).load(list.get(position).getLinkImg()).into(imgStory);
        return convertView;
    }
    public void filterList(ArrayList<Story> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }
}

