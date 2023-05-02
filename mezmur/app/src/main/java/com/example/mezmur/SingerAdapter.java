package com.example.mezmur;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SingerAdapter extends BaseExpandableListAdapter {

    private Context c;
    private ArrayList<Singer> singers;
    private LayoutInflater inflater;

    public SingerAdapter(Context c, ArrayList<Singer> singers) {
        this.c = c;
        this.singers = singers;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return singers.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return singers.get(groupPosition).mezmurTitles.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return singers.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return singers.get(groupPosition).mezmurTitles.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.custom_listview_singer,null);
        }

        Singer s = (Singer)getGroup(groupPosition);
        TextView name = (TextView) convertView.findViewById(R.id.name_textview);
        ImageView img = (ImageView) convertView.findViewById(R.id.singer_imageview);
        String name1 = s.SingerName;
        name.setText(name1);
        convertView.setBackgroundColor(Color.GRAY);
        img.setImageResource(R.drawable.davinci_code);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.custom_listview_new,null);
        }
        String child = (String) getChild(groupPosition,childPosition);
        TextView name = (TextView) convertView.findViewById(R.id.name_textview);
        ImageView img = (ImageView) convertView.findViewById(R.id.singer_imageview);
        name.setText(child);
        String singerName = getGroup(groupPosition).toString();

        img.setImageResource(R.drawable.harry_potter);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}


