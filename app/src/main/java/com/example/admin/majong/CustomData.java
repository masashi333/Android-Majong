package com.example.admin.majong;

import android.graphics.Bitmap;
import android.widget.ArrayAdapter;

/**
 * Created by admin on 2014/10/17.
 */
public class CustomData {
    private Bitmap imageData_;
    private String textData_;
    private ArrayAdapter<String> adapter_;

    public void setAdapter(ArrayAdapter<String> adapter){
        adapter_ = adapter;
    }

    public ArrayAdapter<String> getAdapter(){
       return adapter_;
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

    public String getTextData() {
        return textData_;
    }

}
