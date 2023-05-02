package com.example.mezmur;

import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.RequiresApi;

public class ExpandleListViewAdapter extends BaseExpandableListAdapter implements Filterable {
    private Context context;
    private List<String>chapterlist;
    private List<String>zemerti;
    private HashMap<String,List<String>>topiclist;

    public ExpandleListViewAdapter(Context context, List<String> chapterlist, HashMap<String, List<String>> topiclist) {
        this.context = context;
        this.chapterlist = chapterlist;
        this.topiclist = topiclist;
        zemerti=new ArrayList<>(chapterlist);

    }

    @Override
    public int getGroupCount() {
        return this.chapterlist.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.topiclist.get(this.chapterlist.get(groupPosition)).size();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<String> getChapterlist() {
        return chapterlist;
    }

    public void setChapterlist(List<String> chapterlist) {
        this.chapterlist = chapterlist;
    }

    public HashMap<String, List<String>> getTopiclist() {
        return topiclist;
    }

    public void setTopiclist(HashMap<String, List<String>> topiclist) {
        this.topiclist = topiclist;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.chapterlist.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.topiclist.get(this.chapterlist.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String chapterTitle =(String)getGroup(groupPosition);
        if(convertView==null){
            LayoutInflater inflator=(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflator.inflate(R.layout.chapterlist,null);
        }
        TextView Chaptertv=convertView.findViewById(R.id.ch);
        Chaptertv.setText(chapterTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String topicTitle =(String)getChild(groupPosition,childPosition);
        if(convertView==null){
            LayoutInflater inflator=(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflator.inflate(R.layout.topiclist,null);
        }
        TextView topictv=convertView.findViewById(R.id.topictv);
        topictv.setText(topicTitle);
        topictv.setTextColor(Color.BLACK);
        convertView.setBackgroundColor(Color.argb(255,214, 210, 210));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public Filter getFilter() {

        return exampleFilter;
    }
    private Filter exampleFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<String> filteredList=new ArrayList<>();
            if(constraint==null || constraint.length()==0){
                filteredList.addAll(zemerti);
            }
            else{
                String FilterPattern =constraint.toString().trim();
                for(String Item: zemerti){
                    if(Item.contains(FilterPattern)){
                        filteredList.add(Item);
                    }
                }

            }
            FilterResults results=new FilterResults();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            zemerti.clear();
            zemerti.addAll((Collection<? extends String>) results.values);
            notifyDataSetChanged();


        }
    };
}
