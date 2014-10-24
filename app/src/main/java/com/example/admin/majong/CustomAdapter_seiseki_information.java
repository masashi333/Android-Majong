package com.example.admin.majong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 2014/10/17.
 */

public class CustomAdapter_seiseki_information extends ArrayAdapter<CustomData> {
    private LayoutInflater layoutInflater_;

    public CustomAdapter_seiseki_information(Context context, int textViewResourceId, List<CustomData> objects) {
        super(context, textViewResourceId, objects);
        layoutInflater_ = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 特定の行(position)のデータを得る
        CustomData item = (CustomData) getItem(position);

        // convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
        if (null == convertView) {
            convertView = layoutInflater_.inflate(R.layout.custom_listview, null);


            // CustomDataのデータをViewの各Widgetにセットする
        /*ImageView imageView;
        imageView = (ImageView) convertView.findViewById(R.id.image);
        imageView.setImageBitmap(item.getImageData());

        TextView textView;
        textView = (TextView) convertView.findViewById(R.id.text);
        textView.setText(item.getTextData());*/

            Spinner spinner;
            spinner = (Spinner) convertView.findViewById(R.id.spinner);
            spinner.setAdapter(item.getAdapter());
        }
        return convertView;
    }


}



