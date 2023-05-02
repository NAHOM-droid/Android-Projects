package com.example.mezmur;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SearchView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity  {
    private ExpandleListViewAdapter listViewAdapter;
    private ExpandableListView expanListView;
    ArrayList<String> zemerti;
    HashMap<String,List<String>> mezmurTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expanListView=findViewById(R.id.elv1);
        //listView=findViewById(R.id.lv1);
        try {
            NamesList();
            setMezmurTitle();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,mezmurlist);
       // listViewAdapter=new ExpandleListViewAdapter(this,zemerti,mezmurTitle);
       // expanListView.setAdapter(listViewAdapter);
        //listView.setAdapter(arrayAdapter);

        SingerAdapter adapter = new SingerAdapter(this,getData());
        expanListView.setAdapter(adapter);

        expanListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("family",groupPosition);
                intent.putExtra("mezmurName",mezmurTitle.get(zemerti.get(groupPosition)).get(childPosition));
                intent.putExtra("Child",childPosition);
                startActivity(intent);
                return false;

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchmenu, menu);
        MenuItem searchItem=menu.findItem(R.id.searchId);
        SearchView searchView=(SearchView)searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listViewAdapter.getFilter().filter(newText);
                return false;
            }
        });
            return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.zemertiId:
                break;
            case R.id.TitleID:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setMezmurTitle() throws IOException {
        InputStream inputStream = getAssets().open("title2");
        byte[] buffer=new byte[inputStream.available()];
        inputStream.read(buffer);
        String str= new String(buffer);
        mezmurTitle=new HashMap();
        ArrayList<String> titleList;
        String[]arr=str.split(";");
        for(int i=0;i<arr.length;i++){
            titleList=new ArrayList(Arrays.asList(arr[i].split(",")));
            mezmurTitle.put(zemerti.get(i),titleList);

        }

    }

    public void NamesList() throws IOException {
       zemerti=new ArrayList<>();
        InputStream inputStream = getAssets().open("zemerti3");
        byte[] buffer=new byte[inputStream.available()];
        inputStream.read(buffer);
        String str= new String(buffer);

        zemerti=new ArrayList<>(Arrays.asList(str.split(",")));


    }
    /*    public void fillList(){
       ArrayList<Arr>
               Scanner scan=new Scanner(new FileReader("title"));
        while(scan.hasNext()){
            namelist.add(scan.nextLine());


        return (String[]) namelist.toArray();
        }*/

    public ArrayList<Singer> getData(){
        ArrayList<Singer> s= new ArrayList<>();
        for(int i = 0;i < mezmurTitle.size();i++){
            s.add(new Singer(zemerti.get(i),(ArrayList<String>) mezmurTitle.get(zemerti.get(i))));
        }
        return s;
    }

}

