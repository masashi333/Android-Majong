package com.example.admin.majong;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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

/**
 * Created by admin on 2014/10/30.
 */
public class Majong_Second_Activity extends Activity {

    /**
     * Called when the activity is first created.
     */
    static int row_number;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_majong);
        for(int i=0;i<10;i++) {
           row_number = i + 2;

            initTableLayout(row_number);
        }
        totalTableLayout();
        final TableLayout tableLayout = (TableLayout) findViewById(R.id.tablelayout);
        final TableRow tableRow = (TableRow) findViewById(R.id.tablerow1);
        tableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // カスタムビューを設定
                final View layout = getLayoutInflater().inflate(R.layout.input_syuusi,(ViewGroup)findViewById(R.id.alertdialog_layout));

                // アラーとダイアログ を生成
                AlertDialog.Builder builder = new AlertDialog.Builder(Majong_Second_Activity.this);
                builder.setTitle("結果を入力してください");
                builder.setView(layout);
                builder.setNegativeButton("保存", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 保存 ボタンクリック処理
                        EditText editText1 = (EditText) layout.findViewById(R.id.column1);
                        EditText editText2 = (EditText) layout.findViewById(R.id.column2);
                        EditText editText3 = (EditText) layout.findViewById(R.id.column3);
                        int syuusi1 = Integer.parseInt(editText1.getText().toString());
                        int syuusi2 = Integer.parseInt(editText2.getText().toString());
                        int syuusi3 = Integer.parseInt(editText3.getText().toString());
                        int syuusi = syuusi1 + syuusi2 + syuusi3;
                        String invalid = "合計が0になっていないので保存できません。";
                        if (syuusi != 0) {
                            Toast.makeText(Majong_Second_Activity.this, invalid, Toast.LENGTH_LONG).show();
                        } else{
                            TextView textView1 = (TextView) tableRow.findViewById(R.id.rowtext2);
                            TextView textView2 = (TextView) tableRow.findViewById(R.id.rowtext3);
                            TextView textView3 = (TextView) tableRow.findViewById(R.id.rowtext4);
                            textView1.setText(editText1.getText().toString());
                            textView2.setText(editText2.getText().toString());
                            textView3.setText(editText3.getText().toString());

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

    }

    private void initTableLayout(int row_number) {
     final TableLayout tableLayout = (TableLayout) findViewById(R.id.tablelayout);
     final TableRow tableRow = (TableRow)getLayoutInflater().inflate(R.layout.table_row, null);
        tableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // カスタムビューを設定
                final View layout = getLayoutInflater().inflate(R.layout.input_syuusi,(ViewGroup)findViewById(R.id.alertdialog_layout));

                // アラーとダイアログ を生成
                AlertDialog.Builder builder = new AlertDialog.Builder(Majong_Second_Activity.this);
                builder.setTitle("結果を入力してください");
                builder.setView(layout);
                builder.setNegativeButton("保存", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 保存 ボタンクリック処理
                        EditText editText1 = (EditText) layout.findViewById(R.id.column1);
                        EditText editText2 = (EditText) layout.findViewById(R.id.column2);
                        EditText editText3 = (EditText) layout.findViewById(R.id.column3);
                        int syuusi1 = Integer.parseInt(editText1.getText().toString());
                        int syuusi2 = Integer.parseInt(editText2.getText().toString());
                        int syuusi3 = Integer.parseInt(editText3.getText().toString());
                        int syuusi = syuusi1 + syuusi2 + syuusi3;
                        String invalid = "合計が0になっていないので保存できません。";
                        if(syuusi!=0){
                            Toast.makeText(Majong_Second_Activity.this,invalid, Toast.LENGTH_LONG).show();
                        }else{
                            TextView textView1 = (TextView) tableRow.findViewById(R.id.rowtext2);
                            TextView textView2 = (TextView) tableRow.findViewById(R.id.rowtext3);
                            TextView textView3 = (TextView) tableRow.findViewById(R.id.rowtext4);
                            textView1.setText(editText1.getText().toString());
                            textView2.setText(editText2.getText().toString());
                            textView3.setText(editText3.getText().toString());

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

    private void addTableLayout(int row_number) {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tablelayout);
        TableRow tableRow = (TableRow)getLayoutInflater().inflate(R.layout.table_row, null);
        TextView row_number_string = (TextView) tableRow.findViewById(R.id.rowtext1);
        row_number_string.setText(String.valueOf(row_number));
        tableLayout.addView(tableRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
    private void totalTableLayout() {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tablelayout);
        TableRow tableRow = (TableRow)getLayoutInflater().inflate(R.layout.table_row_total, null);
        tableLayout.addView(tableRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    //メニューを追加
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // If the drawer is open, show the global app actions in the action bar. See also
        // showGlobalContextActionBar, which controls the top-left area of the action bar.
        getMenuInflater().inflate(R.menu.seiseki_syousai, menu);
        return super.onCreateOptionsMenu(menu);
    }
}






