package com.example.mezmur;

import java.util.ArrayList;

public class Singer {
    public String SingerName;
    public int SingerImageId;
    public ArrayList<String> mezmurTitles = new ArrayList<>();

    public Singer(String singerName, ArrayList<String> mezmurTitles) {
        SingerName = singerName;
        this.mezmurTitles = mezmurTitles;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "SingerName='" + SingerName + '\'' +
                '}';
    }


}
