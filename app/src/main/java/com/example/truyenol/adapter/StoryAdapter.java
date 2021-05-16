package com.example.truyenol.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.truyenol.R;
import com.example.truyenol.database.DatabaseHandler;
import com.example.truyenol.model.Story;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class StoryAdapter extends BaseAdapter {
    private final Context context;
    private ArrayList<Story> listStory = new ArrayList<Story>();

    public StoryAdapter(Context context, ArrayList<Story> listStory) {
        this.context = context;
        this.listStory = listStory;
    }
    @Override
    public int getCount() {
        return listStory.size();
    }

    @Override
    public Object getItem(int position) {
        return listStory.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        TextView nameStoryTxt;
        ImageView storyImg;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        //Gọi viewHolder


        //Tạo đối tượng layoutInflater giúp get layout ra
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.show1story,null);


        viewHolder.nameStoryTxt = convertView.findViewById(R.id.nameHomeTxt);
        viewHolder.storyImg = convertView.findViewById(R.id.imgHome);
        convertView.setTag(viewHolder);

        //Lấy dữ liệu
        Story truyen = (Story) getItem(position);
        viewHolder.nameStoryTxt.setText(truyen.getNameStory());

        Picasso.get().load(truyen.getLinkImg()).placeholder(R.drawable.image1).error(R.drawable.image5).into(viewHolder.storyImg);
        return convertView;
    }
    public void filterList(ArrayList<Story> filteredList){
        listStory = filteredList;
        notifyDataSetChanged();
    }
}
