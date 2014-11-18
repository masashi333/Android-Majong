package com.example.admin.majong;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
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
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 2014/10/30.
 */
public class Seiseki_Syousai_Fragment extends Fragment {
    static int column1_total;
    static int column2_total;
    static int column3_total;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_seiseki_syousai, container, false);
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
            //totalを計算する
            TextView textView1 = (TextView) tableRow.findViewById(R.id.rowtext2);
            TextView textView2 = (TextView) tableRow.findViewById(R.id.rowtext3);
            TextView textView3 = (TextView) tableRow.findViewById(R.id.rowtext4);
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
                                Toast.makeText(getActivity(),invalid, Toast.LENGTH_LONG).show();
                            }else{
                                TextView textView1 = (TextView) tableRow.findViewById(R.id.rowtext2);
                                TextView textView2 = (TextView) tableRow.findViewById(R.id.rowtext3);
                                TextView textView3 = (TextView) tableRow.findViewById(R.id.rowtext4);
                                textView1.setText(editText1.getText().toString());
                                textView2.setText(editText2.getText().toString());
                                textView3.setText(editText3.getText().toString());
                                //データ管理できれば、totalを計算できるはず！！
                            /*setContentView(R.layout.activity_second_majong);
                            for (int i = 0; i < 20; i++) {
                                int row_number = i + 1;

                                initTableLayout(row_number);
                            }
                            totalTableLayout();*/



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
            tableLayout.addView(tableRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        //メニューを追加
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        getActivity().getMenuInflater().inflate(R.menu.seiseki_syousai, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}