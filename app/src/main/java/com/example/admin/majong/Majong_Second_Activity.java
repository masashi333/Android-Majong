package com.example.admin.majong;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2014/10/30.
 */
public class Majong_Second_Activity extends Activity {

    static final String TAG = "SQlite";
    static DBAdapter_Seiseki dbAdapterSeiseki;
    static SeisekiListAdapter listAdapter;
    static List<Majong_seiseki> seisekiList = new ArrayList<Majong_seiseki>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // インテントを取得
        Intent intent = getIntent();
        // インテントに保存されたデータを取得
        String title = intent.getStringExtra("title");
        //データベースアダプターを作成
        dbAdapterSeiseki = new DBAdapter_Seiseki(Majong_Second_Activity.this,title);
        listAdapter = new SeisekiListAdapter();

        loadSeiseki();
        setContentView(R.layout.activity_majong__second__activity);
        //ActionBarをGetしてTabModeをセット
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setDisplayShowHomeEnabled(false);

        actionBar.addTab(actionBar.newTab()
                .setText("成績詳細")
                .setTabListener(new TabListener<Seiseki_Syousai_Fragment>(
                        this, "tag1", Seiseki_Syousai_Fragment.class,title)));

        actionBar.addTab(actionBar.newTab()
                .setText("勝ち金")
                .setTabListener(new TabListener<Seiseki_Win_Money_Fragment>(
                        this, "tag2", Seiseki_Win_Money_Fragment.class,title)));
    /*actionBar.addTab(actionBar.newTab())
            .setText("日付⇨日数")
    .setTabListener(new TabListener<SecondTabFragment>(
            this, "tag2", SecondTabFragment.class)));

    actionBar.addTab(actionBar.newTab()
            .setText("記念日")
    .setTabListener(new TabListener<ThirdTabFragment>(
            this, "tag3", ThirdTabFragment.class)));
*/
    }
    protected void loadSeiseki(){
        seisekiList.clear();

        // Read
        dbAdapterSeiseki.open();
        Cursor c = dbAdapterSeiseki.getAllSeiseki();

        startManagingCursor(c);

        if(c.moveToFirst()){
            do {
                Majong_seiseki seiseki = new Majong_seiseki(
                        c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_GAMEID)),
                        c.getString(c.getColumnIndex(DBAdapter_Seiseki.COL_MEMBER)),
                        c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI))
                );
                seisekiList.add(seiseki);
            } while(c.moveToNext());
        }

        stopManagingCursor(c);
        dbAdapterSeiseki.close();

        listAdapter.notifyDataSetChanged();
    }
    private class SeisekiListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return seisekiList.size();
        }

        @Override
        public Object getItem(int position) {
            return seisekiList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if(v==null){
                LayoutInflater inflater =
                        (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.table_row, null);
            }
            Majong_seiseki seiseki = (Majong_seiseki)getItem(position);
            if(seiseki != null){
                TextView rowtext2 = (TextView)v.findViewById(R.id.rowtext2);
                TextView rowtext3 = (TextView)v.findViewById(R.id.rowtext3);
                TextView rowtext4 = (TextView)v.findViewById(R.id.rowtext4);

                /*rowtext2.setText(seiseki.getSeiseki1());
                rowtext3.setText(seiseki.getSeiseki2());
                rowtext4.setText(seiseki.getSeiseki3());
*/
            }
            return v;
        }

    }
}








