package com.example.admin.majong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 2014/10/17.
 */

public class CustomAdapter_seiseki_table extends ArrayAdapter<CustomData> {
    private LayoutInflater layoutInflater_;

    public CustomAdapter_seiseki_table(Context context, int textViewResourceId, List<CustomData> objects) {
        super(context, textViewResourceId, objects);
        layoutInflater_ = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 特定の行(position)のデータを得る
        CustomData item = (CustomData) getItem(position);

        // convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
        if (null == convertView) {
            convertView = layoutInflater_.inflate(R.layout.custom_listview3, null);


            // CustomDataのデータをViewの各Widgetにセットする
        /*ImageView imageView;
        imageView = (ImageView) convertView.findViewById(R.id.image);
        imageView.setImageBitmap(item.getImageData());*/

            TextView textView;
            textView = (TextView) convertView.findViewById(R.id.title);
            textView.setText(item.getTextData());
            //テーブルレイアウト作成する関数呼び出し
            initTableLayout(convertView);
        }
            return convertView;

    }
    //テーブルレイアウトを作成する
    private void initTableLayout(View convertview) {
        TableLayout tableLayout = (TableLayout) convertview.findViewById(R.id.tablelayout);

        TableRow tableRow1 = new TableRow(getContext());
        tableLayout.addView(tableRow1);
        tableRow1.addView(createButton("セル１－１"));
        tableRow1.addView(createButton("セル１－２"));

        TableRow tableRow2 = new TableRow(getContext());
        tableLayout.addView(tableRow2);
        tableRow2.addView(createButton("セル２－１"));
        tableRow2.addView(createButton("セル２－２"));

    }
    //ボタンを作る
    private Button createButton(String text) {
        Button button = new Button(getContext());
        button.setText(text);
        return button;
    }
}

