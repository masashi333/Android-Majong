package com.example.admin.majong;

import android.graphics.Bitmap;
import android.widget.ArrayAdapter;

import java.util.Calendar;

/**
 * Created by admin on 2014/10/17.
 */
public class CustomData {
    private Bitmap imageData_;
    private String textData_;
    private String textData2_;
    private ArrayAdapter<String> adapter_;
    private String name[];

    public void setAdapter(ArrayAdapter<String> adapter){
        adapter_ = adapter;
    }

    public ArrayAdapter<String> getAdapter(){
       return adapter_;
    }

    public void setNameData(String name[]){
        this.name = name;
    }
    public String[] getNameData(){
        return name;
    }
    public void setImagaData(Bitmap image) {
        imageData_ = image;
    }

    public Bitmap getImageData() {
        return imageData_;
    }

    public void setTextData(String text) {
        textData_ = text;
    }
    public void setTextData2(String text) {
        textData2_ = text;
    }
    public String getTextData() {
        return textData_;
    }
    public String getTextData2() {
        return textData2_;
    }

}
