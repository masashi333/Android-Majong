package com.example.admin.majong;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 2014/10/30.
 */
public class Seiseki_Syousai_Fragment extends Fragment {
    static int column1_total;
    static int column2_total;
    static int column3_total;
    static DBAdapter dbAdapter;
    static List<Majong_seiseki> seisekiList = new ArrayList<Majong_seiseki>();
    static String title;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        /*// ここで値を受け取ってる
        title = getArguments().getString("title");
        dbAdapter = new DBAdapter(getActivity(),title);*/
        //loadSeiseki();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_seiseki_syousai, container, false);
        // ここで値を受け取ってる
        title = getArguments().getString("title");
        dbAdapter = new DBAdapter(getActivity(),title);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        /**
         * Called when the activity is first created.
         */

            for (int i = 0; i < 20; i++) {
                int row_number = i + 1;

                initTableLayout(row_number);
            }
            totalTableLayout();
        }

        private void initTableLayout(final int row_number) {
            final TableLayout tableLayout = (TableLayout) getActivity().findViewById(R.id.tablelayout);
            final TableRow tableRow = (TableRow) getActivity().getLayoutInflater().inflate(R.layout.table_row, null);
            TextView textView1 = (TextView) tableRow.findViewById(R.id.rowtext2);
            TextView textView2 = (TextView) tableRow.findViewById(R.id.rowtext3);
            TextView textView3 = (TextView) tableRow.findViewById(R.id.rowtext4);
            // rawQueryでSELECTを実行
            //データベースからデータを取得し、表に反映
            dbAdapter.open();
            String sql = "select * from seiseki" +title+ " where gameid=" + row_number;
            Cursor c = dbAdapter.db.rawQuery(sql, null);
            c.moveToFirst();
            System.out.println(c.getInt(c.getColumnIndex(DBAdapter.COL_SEISEKI)));
            textView1.setText(String.valueOf(c.getInt(c.getColumnIndex(DBAdapter.COL_SEISEKI))));
            c.moveToNext();
            textView2.setText(String.valueOf(c.getInt(c.getColumnIndex(DBAdapter.COL_SEISEKI))));
            c.moveToNext();
            textView3.setText(String.valueOf(c.getInt(c.getColumnIndex(DBAdapter.COL_SEISEKI))));
            dbAdapter.close();
            //totalを計算する
            int syuusi1 = Integer.parseInt(textView1.getText().toString());
            int syuusi2 = Integer.parseInt(textView2.getText().toString());
            int syuusi3 = Integer.parseInt(textView3.getText().toString());
            column1_total += syuusi1;
            column2_total += syuusi2;
            column3_total += syuusi3;

            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // カスタムビューを設定
                    final View layout = getActivity().getLayoutInflater().inflate(R.layout.input_syuusi, (ViewGroup) getActivity().findViewById(R.id.alertdialog_layout));

                    // アラーとダイアログ を生成
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("結果を入力してください");
                    builder.setView(layout);
                    EditText editText1 = (EditText) layout.findViewById(R.id.column1);
                    EditText editText2 = (EditText) layout.findViewById(R.id.column2);
                    EditText editText3 = (EditText) layout.findViewById(R.id.column3);
                    // rawQueryでSELECTを実行
                    //データベースからデータを取得し、成績詳細表に反映
                    dbAdapter.open();
                    String sql = "select * from seiseki" +title+  " where gameid=" + row_number;
                    Cursor c = dbAdapter.db.rawQuery(sql, null);
                    c.moveToFirst();
                    System.out.println(c.getInt(c.getColumnIndex(DBAdapter.COL_SEISEKI)));
                    editText1.setText(String.valueOf(c.getInt(c.getColumnIndex(DBAdapter.COL_SEISEKI))));
                    c.moveToNext();
                    editText2.setText(String.valueOf(c.getInt(c.getColumnIndex(DBAdapter.COL_SEISEKI))));
                    c.moveToNext();
                    editText3.setText(String.valueOf(c.getInt(c.getColumnIndex(DBAdapter.COL_SEISEKI))));
                    dbAdapter.close();
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
                            if(syuusi!=0){
                                Toast.makeText(getActivity(),invalid, Toast.LENGTH_LONG).show();
                            }else{
                                dbAdapter.open();
                                dbAdapter.deleteSeiseki(row_number);
                                dbAdapter.saveSeiseki(row_number,1,syuusi1);
                                dbAdapter.saveSeiseki(row_number,2,syuusi2);
                                dbAdapter.saveSeiseki(row_number,3,syuusi3);
                                dbAdapter.close();
                               /* // rawQueryでSELECTを実行
                                //データベースからデータを取得し、表に反映
                                dbAdapter.open();
                                String sql = "select * from seiseki where gameid=" + row_number;
                                Cursor c = dbAdapter.db.rawQuery(sql, null);
                                c.moveToFirst();
                                System.out.println(c.getInt(c.getColumnIndex(DBAdapter.COL_SEISEKI)));
                                textView1.setText(String.valueOf(c.getInt(c.getColumnIndex(DBAdapter.COL_SEISEKI))));
                                c.moveToNext();
                                textView2.setText(String.valueOf(c.getInt(c.getColumnIndex(DBAdapter.COL_SEISEKI))));
                                c.moveToNext();
                                textView3.setText(String.valueOf(c.getInt(c.getColumnIndex(DBAdapter.COL_SEISEKI))));
*/
                                loadSeiseki();

                               /* TextView textView1 = (TextView) tableRow.findViewById(R.id.rowtext2);
                                TextView textView2 = (TextView) tableRow.findViewById(R.id.rowtext3);
                                TextView textView3 = (TextView) tableRow.findViewById(R.id.rowtext4);*/
                                textView1.setText(editText1.getText().toString());
                                textView2.setText(editText2.getText().toString());
                                textView3.setText(editText3.getText().toString());
                                //データ管理できれば、totalを計算できるはず！！
                                getActivity().setContentView(R.layout.fragment_seiseki_syousai);
                                for (int i = 0; i < 20; i++) {
                                    int row_number = i + 1;

                                    initTableLayout(row_number);
                                }
                                totalTableLayout();



                            }
                      /*  EditText title = (EditText) layout.findViewById(R.id.edittext);
                        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());

                        // Preferences に書き込むための Editor クラスを取得する
                        SharedPreferences.Editor editor = sp.edit();

                        for (int i = 0; i < 30; i++) {
                            String s = String.valueOf(i);
                            String title_list = "title" + s;
                            if (sp.getString(title_list, "").equals("")) {
                                // putXxxx("キー",データ) にて書き込むデータを登録する
                                editor.putString(title_list, title.getText().toString());
                                // 書き込みを確定する
                                editor.commit();
                                Toast.makeText(getActivity(), title.getText().toString(), Toast.LENGTH_LONG).show();
                                refreshView();
                                break;
                            }
                        }*/
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

            TextView row_number_string = (TextView) tableRow.findViewById(R.id.rowtext1);
            row_number_string.setText(String.valueOf(row_number));
            tableLayout.addView(tableRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    /*private void total_culc(int column_number){
        for(int i=0;i < 20;i++){
            TextView textView = (TextView)
        }
        TextView textView
    }*/

        private void addTableLayout(int row_number) {
            TableLayout tableLayout = (TableLayout) getActivity().findViewById(R.id.tablelayout);
            TableRow tableRow = (TableRow) getActivity().getLayoutInflater().inflate(R.layout.table_row, null);
            TextView row_number_string = (TextView) tableRow.findViewById(R.id.rowtext1);
            row_number_string.setText(String.valueOf(row_number));
            tableLayout.addView(tableRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        private void totalTableLayout() {
            TableLayout tableLayout = (TableLayout) getActivity().findViewById(R.id.tablelayout);
            TableRow tableRow = (TableRow) getActivity().getLayoutInflater().inflate(R.layout.table_row_total, null);
            TextView textView1 = (TextView) tableRow.findViewById(R.id.rowtext2);
            TextView textView2 = (TextView) tableRow.findViewById(R.id.rowtext3);
            TextView textView3 = (TextView) tableRow.findViewById(R.id.rowtext4);
            textView1.setText(String.valueOf(column1_total));
            textView2.setText(String.valueOf(column2_total));
            textView3.setText(String.valueOf(column3_total));
            //各列の合計を初期化
            column1_total=0;
            column2_total=0;
            column3_total=0;

            tableLayout.addView(tableRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
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
        dbAdapter.open();
        Cursor c = dbAdapter.getAllSeiseki();

        //startManagingCursor(c);
        if(c.moveToFirst()){
            do {
                Majong_seiseki seiseki = new Majong_seiseki(
                        c.getInt(c.getColumnIndex(DBAdapter.COL_GAMEID)),
                        c.getInt(c.getColumnIndex(DBAdapter.COL_MEMBERID)),
                        c.getInt(c.getColumnIndex(DBAdapter.COL_SEISEKI))
                );
                seisekiList.add(seiseki);
                System.out.println(seiseki.getGameid());
                System.out.println(seiseki.getMemberid());
                System.out.println(seiseki.getSeiseki());

            } while(c.moveToNext());
        }

        //stopManagingCursor(c);
        dbAdapter.close();


    }

}