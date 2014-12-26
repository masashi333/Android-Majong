package com.example.admin.majong;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2014/10/30.
 */
public class Seiseki_Win_Money_Fragment extends Fragment {
    static int column1_total;
    static int column2_total;
    static int column3_total;
    static DBAdapter_Seiseki dbAdapterSeiseki;
    static List<Majong_seiseki> seisekiList = new ArrayList<Majong_seiseki>();
    static String title;
    static String name1;
    static String name2;
    static String name3;
    static int rate_input=3;
    static int tip_input=50;
    static TextView total1_textview;
    static TextView total2_textview;
    static TextView total3_textview;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Set_Save("rate",String.valueOf(rate_input),getActivity());
        Set_Save("tip",String.valueOf(tip_input),getActivity());
        /*// ここで値を受け取ってる
        title = getArguments().getString("title");
        dbAdapter = new DBAdapter(getActivity(),title);*/
        //loadSeiseki();


    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_win_money, container, false);
        // ここで値を受け取ってる
        title = getArguments().getString("title");
        dbAdapterSeiseki = new DBAdapter_Seiseki(getActivity(),title);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        /**
         * Called when the activity is first created.
         */
        getActivity().setContentView(R.layout.fragment_win_money);
        //成績表の名前を更新
        final TableLayout tableLayout = (TableLayout) getActivity().findViewById(R.id.tablelayout);
        TextView textView_name1 = (TextView) tableLayout.findViewById(R.id.name1);
        TextView textView_name2 = (TextView) tableLayout.findViewById(R.id.name2);
        TextView textView_name3 = (TextView) tableLayout.findViewById(R.id.name3);
        dbAdapterSeiseki.open();
        String sql = "select * from seiseki where gameid=1";
        Cursor c = dbAdapterSeiseki.db.rawQuery(sql, null);
        c.moveToFirst();
        name1 = c.getString(c.getColumnIndex(DBAdapter_Seiseki.COL_MEMBER));
        textView_name1.setText(name1);
        c.moveToNext();
        name2 = c.getString(c.getColumnIndex(DBAdapter_Seiseki.COL_MEMBER));
        textView_name2.setText(name2);
        c.moveToNext();
        name3 = c.getString(c.getColumnIndex(DBAdapter_Seiseki.COL_MEMBER));
        textView_name3.setText(name3);
        dbAdapterSeiseki.close();

        //レート、チップを現在の値に更新
        EditText rate_input_edittext = (EditText) getActivity().findViewById(R.id.rate_input);
        EditText tip_input_edittext = (EditText) getActivity().findViewById(R.id.tip_input);
        rate_input_edittext.setText(String.valueOf(rate_input));
        tip_input_edittext.setText(String.valueOf(tip_input));



        //合計を表示
        syuushi_show();
        tip_show();
        total_show();
        culc_rate_and_tip();


    }



        private void syuushi_show() {
            TableLayout tableLayout = (TableLayout) getActivity().findViewById(R.id.tablelayout);
            TableRow tableRow = (TableRow) getActivity().getLayoutInflater().inflate(R.layout.table_row_total, null);
            TextView textView1 = (TextView) tableRow.findViewById(R.id.rowtext2);
            TextView textView2 = (TextView) tableRow.findViewById(R.id.rowtext3);
            TextView textView3 = (TextView) tableRow.findViewById(R.id.rowtext4);
            TextView textView4 = (TextView) tableRow.findViewById(R.id.rowtext1);
            textView4.setText("基");
            //データベースからデータを取得し、表に反映
            dbAdapterSeiseki.open();
            String sql = "select * from seiseki where gameid=" + 21;
            Cursor c = dbAdapterSeiseki.db.rawQuery(sql, null);
            c.moveToFirst();
            System.out.println(c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI)));
            textView1.setText(String.valueOf(c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI))));
            c.moveToNext();
            textView2.setText(String.valueOf(c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI))));
            c.moveToNext();
            textView3.setText(String.valueOf(c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI))));
            dbAdapterSeiseki.close();

            tableLayout.addView(tableRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

    private void tip_show() {
        final TableLayout tableLayout = (TableLayout) getActivity().findViewById(R.id.tablelayout);
        final TableRow tableRow = (TableRow) getActivity().getLayoutInflater().inflate(R.layout.table_row_total, null);
        TextView textView1 = (TextView) tableRow.findViewById(R.id.rowtext2);
        TextView textView2 = (TextView) tableRow.findViewById(R.id.rowtext3);
        TextView textView3 = (TextView) tableRow.findViewById(R.id.rowtext4);
        TextView textView4 = (TextView) tableRow.findViewById(R.id.rowtext1);
        textView4.setText("チ");
        //データベースからデータを取得し、表に反映
        dbAdapterSeiseki.open();
        String sql = "select * from seiseki where gameid=" + 22;
        Cursor c = dbAdapterSeiseki.db.rawQuery(sql, null);
        c.moveToFirst();
        System.out.println(c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI)));
        textView1.setText(String.valueOf(c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI))));
        c.moveToNext();
        textView2.setText(String.valueOf(c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI))));
        c.moveToNext();
        textView3.setText(String.valueOf(c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI))));
        dbAdapterSeiseki.close();


        tableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // カスタムビューを設定
                final View layout = getActivity().getLayoutInflater().inflate(R.layout.input_syuusi, (ViewGroup) getActivity().findViewById(R.id.alertdialog_layout));
                TextView textView1 = (TextView) layout.findViewById(R.id.name1);
                TextView textView2 = (TextView) layout.findViewById(R.id.name2);
                TextView textView3 = (TextView) layout.findViewById(R.id.name3);
                textView1.setText(name1);
                textView2.setText(name2);
                textView3.setText(name3);

                // アラーとダイアログ を生成
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("結果を入力してください");
                builder.setView(layout);
                EditText editText1 = (EditText) layout.findViewById(R.id.column1);
                EditText editText2 = (EditText) layout.findViewById(R.id.column2);
                EditText editText3 = (EditText) layout.findViewById(R.id.column3);
                // rawQueryでSELECTを実行
                //データベースからデータを取得し、成績詳細表に反映
                dbAdapterSeiseki.open();
                String sql = "select * from seiseki where gameid=" + 22;
                Cursor c = dbAdapterSeiseki.db.rawQuery(sql, null);
                c.moveToFirst();
                System.out.println(c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI)));
                editText1.setText(String.valueOf(c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI))));
                c.moveToNext();
                editText2.setText(String.valueOf(c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI))));
                c.moveToNext();
                editText3.setText(String.valueOf(c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI))));
                dbAdapterSeiseki.close();
                builder.setNegativeButton("保存", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 保存 ボタンクリック処理
                        TextView textView1 = (TextView) tableRow.findViewById(R.id.rowtext2);
                        TextView textView2 = (TextView) tableRow.findViewById(R.id.rowtext3);
                        TextView textView3 = (TextView) tableRow.findViewById(R.id.rowtext4);
                        EditText editText1 = (EditText) layout.findViewById(R.id.column1);
                        EditText editText2 = (EditText) layout.findViewById(R.id.column2);
                        EditText editText3 = (EditText) layout.findViewById(R.id.column3);

                        int syuusi1 = Integer.parseInt(editText1.getText().toString());
                        int syuusi2 = Integer.parseInt(editText2.getText().toString());
                        int syuusi3 = Integer.parseInt(editText3.getText().toString());
                        int syuusi = syuusi1 + syuusi2 + syuusi3;
                        String invalid = "合計が0になっていないので保存できません。";
                        if (syuusi != 0) {
                            Toast.makeText(getActivity(), invalid, Toast.LENGTH_LONG).show();
                        } else {
                            dbAdapterSeiseki.open();
                            dbAdapterSeiseki.deleteSeiseki(22);
                            dbAdapterSeiseki.saveSeiseki(22, name1, syuusi1);
                            dbAdapterSeiseki.saveSeiseki(22, name2, syuusi2);
                            dbAdapterSeiseki.saveSeiseki(22, name3, syuusi3);
                            dbAdapterSeiseki.close();

                            textView1.setText(editText1.getText().toString());
                            textView2.setText(editText2.getText().toString());
                            textView3.setText(editText3.getText().toString());
                            //データ管理できれば、totalを計算できるはず！！
                            getActivity().setContentView(R.layout.fragment_win_money);
                            //成績表の名前を更新
                            final TableLayout tableLayout = (TableLayout) getActivity().findViewById(R.id.tablelayout);
                            TextView textView_name1 = (TextView) tableLayout.findViewById(R.id.name1);
                            TextView textView_name2 = (TextView) tableLayout.findViewById(R.id.name2);
                            TextView textView_name3 = (TextView) tableLayout.findViewById(R.id.name3);
                            dbAdapterSeiseki.open();
                            String sql = "select * from seiseki where gameid=1";
                            Cursor c = dbAdapterSeiseki.db.rawQuery(sql, null);
                            c.moveToFirst();
                            name1 = c.getString(c.getColumnIndex(DBAdapter_Seiseki.COL_MEMBER));
                            textView_name1.setText(name1);
                            c.moveToNext();
                            name2 = c.getString(c.getColumnIndex(DBAdapter_Seiseki.COL_MEMBER));
                            textView_name2.setText(name2);
                            c.moveToNext();
                            name3 = c.getString(c.getColumnIndex(DBAdapter_Seiseki.COL_MEMBER));
                            textView_name3.setText(name3);
                            dbAdapterSeiseki.close();
                            syuushi_show();
                            tip_show();
                            total_show();
                            culc_rate_and_tip();
                            //レート、チップを現在の値に更新
                            EditText rate_input_edittext = (EditText) getActivity().findViewById(R.id.rate_input);
                            EditText tip_input_edittext = (EditText) getActivity().findViewById(R.id.tip_input);
                            rate_input_edittext.setText(String.valueOf(rate_input));
                            tip_input_edittext.setText(String.valueOf(tip_input));

                        }
                    }
                });
                builder.setPositiveButton("キャンセル", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // きゃんせる ボタンクリック処理
                    }
                });


                builder.create().show();
            }
        });
        tableLayout.addView(tableRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

    }
    private void total_show() {
        TableLayout tableLayout = (TableLayout) getActivity().findViewById(R.id.tablelayout);
        TableRow tableRow = (TableRow) getActivity().getLayoutInflater().inflate(R.layout.table_row_total, null);
        total1_textview= (TextView) tableRow.findViewById(R.id.rowtext2);
        total2_textview = (TextView) tableRow.findViewById(R.id.rowtext3);
        total3_textview = (TextView) tableRow.findViewById(R.id.rowtext4);
        TextView textView1 = (TextView) tableRow.findViewById(R.id.rowtext1);
        textView1.setText("計");

        int total1;
        int total2;
        int total3;
        int syuushi1;
        int syuushi2;
        int syuushi3;
        int tip1;
        int tip2;
        int tip3;


        //データベースから収支のデータを取得
        dbAdapterSeiseki.open();
        String sql = "select * from seiseki where gameid=" + 21;
        Cursor c = dbAdapterSeiseki.db.rawQuery(sql, null);
        c.moveToFirst();
        System.out.println(c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI)));
        syuushi1 = c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI));
        c.moveToNext();
        syuushi2 = c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI));
        c.moveToNext();
        syuushi3 = c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI));
        dbAdapterSeiseki.close();

        //データベースからチップのデータを取得
        dbAdapterSeiseki.open();
        String sql2 = "select * from seiseki where gameid=" + 22;
        Cursor c2 = dbAdapterSeiseki.db.rawQuery(sql2, null);
        c2.moveToFirst();
        System.out.println(c2.getInt(c2.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI)));
        tip1 = c2.getInt(c2.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI));
        c2.moveToNext();
        tip2 = c2.getInt(c2.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI));
        c2.moveToNext();
        tip3 = c2.getInt(c2.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI));
        dbAdapterSeiseki.close();




        total1 = syuushi1 * rate_input * 10 + tip1 * tip_input;
        total2 = syuushi2 * rate_input * 10 + tip2 * tip_input;
        total3 = syuushi3 * rate_input * 10 + tip3 * tip_input;

        total1_textview.setText(String.valueOf(total1));
        total2_textview.setText(String.valueOf(total2));
        total3_textview.setText(String.valueOf(total3));
        tableLayout.addView(tableRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void culc_rate_and_tip() {
        Button button = (Button) getActivity().findViewById(R.id.calculate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText rate_input_edittext = (EditText) getActivity().findViewById(R.id.rate_input);
                EditText tip_input_edittext = (EditText) getActivity().findViewById(R.id.tip_input);
                rate_input = Integer.valueOf(rate_input_edittext.getText().toString());
                tip_input = Integer.valueOf(tip_input_edittext.getText().toString());
                Set_Save("rate", String.valueOf(rate_input), getActivity());
                Set_Save("tip",String.valueOf(tip_input),getActivity());
                rate_input_edittext.setText(String.valueOf(rate_input));
                tip_input_edittext.setText(String.valueOf(tip_input));
                int total1;
                int total2;
                int total3;
                int syuushi1;
                int syuushi2;
                int syuushi3;
                int tip1;
                int tip2;
                int tip3;


                //データベースから収支のデータを取得
                dbAdapterSeiseki.open();
                String sql = "select * from seiseki where gameid=" + 21;
                Cursor c = dbAdapterSeiseki.db.rawQuery(sql, null);
                c.moveToFirst();
                System.out.println(c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI)));
                syuushi1 = c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI));
                c.moveToNext();
                syuushi2 = c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI));
                c.moveToNext();
                syuushi3 = c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI));
                dbAdapterSeiseki.close();

                //データベースからチップのデータを取得
                dbAdapterSeiseki.open();
                String sql2 = "select * from seiseki where gameid=" + 22;
                Cursor c2 = dbAdapterSeiseki.db.rawQuery(sql2, null);
                c2.moveToFirst();
                System.out.println(c2.getInt(c2.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI)));
                tip1 = c2.getInt(c2.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI));
                c2.moveToNext();
                tip2 = c2.getInt(c2.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI));
                c2.moveToNext();
                tip3 = c2.getInt(c2.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI));
                dbAdapterSeiseki.close();

                total1 = syuushi1 * rate_input * 10 + tip1 * tip_input;
                total2 = syuushi2 * rate_input * 10 + tip2 * tip_input;
                total3 = syuushi3 * rate_input * 10 + tip3 * tip_input;

                total1_textview.setText(String.valueOf(total1));
                total2_textview.setText(String.valueOf(total2));
                total3_textview.setText(String.valueOf(total3));

            }
        });

    }
    public static void Set_Save(String sKey,String value ,Context mCon){
        SharedPreferences shPref = PreferenceManager.getDefaultSharedPreferences(mCon);
        SharedPreferences.Editor ed = shPref.edit();
        ed.putString(sKey, value);
        ed.commit();
    }
    public static String Get_Save(String sKey,Context mCon){
        SharedPreferences shPref = PreferenceManager.getDefaultSharedPreferences(mCon);
        return shPref.getString(sKey, "");
    }




    //メニューを追加
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        getActivity().getMenuInflater().inflate(R.menu.seiseki_syousai, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    protected void loadSeiseki(){
        seisekiList.clear();

        // Read
        dbAdapterSeiseki.open();
        Cursor c = dbAdapterSeiseki.getAllSeiseki();

        //startManagingCursor(c);
        if(c.moveToFirst()){
            do {
                Majong_seiseki seiseki = new Majong_seiseki(
                        c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_GAMEID)),
                        c.getString(c.getColumnIndex(DBAdapter_Seiseki.COL_MEMBER)),
                        c.getInt(c.getColumnIndex(DBAdapter_Seiseki.COL_SEISEKI))
                );
                seisekiList.add(seiseki);
                System.out.println(seiseki.getGameid());
                System.out.println(seiseki.getMember());
                System.out.println(seiseki.getSeiseki());

            } while(c.moveToNext());
        }

        //stopManagingCursor(c);
        dbAdapterSeiseki.close();


    }

}