package com.example.admin.majong;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SeisekiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeisekiFragment extends Fragment {
    private static final String ARG_PARAM_RESOURCE_ID = "resource_id";
    private static final String ARG_SECTION_NUMBER = "section_number";
    //private final ArrayAdapter<String> person_array = new ArrayAdapter<String>()
    private int mResourceId;
    static Spinner spinner1;
    static Spinner spinner2;
    static Spinner spinner3;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SeisekiFragment newInstance(int resourceId, int sectionNumber) {
        SeisekiFragment fragment = new SeisekiFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM_RESOURCE_ID, resourceId);
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public SeisekiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mResourceId = getArguments().getInt(ARG_PARAM_RESOURCE_ID);
        }

        setHasOptionsMenu(true); //付け忘れないように
    }


    //メニューを追加
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // If the drawer is open, show the global app actions in the action bar. See also
        // showGlobalContextActionBar, which controls the top-left area of the action bar.
        inflater.inflate(R.menu.seiseki_table, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_add) {
            // カスタムビューを設定
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View layout = inflater.inflate(R.layout.new_seiseki, (ViewGroup) getActivity().findViewById(R.id.alertdialog_layout));

            // アラーとダイアログ を生成
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("成績表の作成");
            builder.setView(layout);
            /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // アイテムを追加します
            adapter.add("red");
            adapter.add("green");
            adapter.add("blue");*/
            final Spinner spinner = (Spinner) layout.findViewById(R.id.spinner2);
            // アダプターを設定します
            /*spinner.setAdapter(adapter);*/
            // スピナーのアイテムが選択された時に呼び出されるコールバックリスナーを登録します
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    Spinner spinner = (Spinner) parent;
                    // 選択されたアイテムを取得します
                    String item = (String) spinner.getSelectedItem();
                    Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            //雀士の一覧を配列に入れる
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
            for (int i = 0; i < 50; i++) {
                String s = String.valueOf(i);
                String name_list = "name" + s;
                if (!sp.getString(name_list, "").equals("")) {
                    String name = sp.getString(name_list, "");
                    adapter.add(name);
                }
            }
           /* // アイテムを追加します
            adapter.add("ノダ");
            adapter.add("ムカミ");
            adapter.add("アカギ");*/
            // リストビューのデータの作成

            List<CustomData> objects = new ArrayList<CustomData>();
            for (int i = 0; i < 3; i++) {
                CustomData item_i = new CustomData();
                item_i.setAdapter(adapter);
                objects.add(item_i);
            }

            final CustomAdapter_seiseki_information customAdapater = new CustomAdapter_seiseki_information(getActivity(), 0, objects);
            final ListView listView = (ListView) layout.findViewById(R.id.listView);
            listView.setAdapter(customAdapater);

            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // おーけー ボタンクリック処理
                    EditText title = (EditText) layout.findViewById(R.id.edittext);
                    spinner1 = (Spinner) listView.getChildAt(0).findViewById(R.id.spinner);
                    spinner2 = (Spinner) listView.getChildAt(1).findViewById(R.id.spinner);
                    spinner3 = (Spinner) listView.getChildAt(2).findViewById(R.id.spinner);
                    System.out.println("スピナーの選択された名前：" + spinner1.getSelectedItem());
                    System.out.println("スピナーの選択された名前：" + spinner2.getSelectedItem());
                    System.out.println("スピナーの選択された名前：" + spinner3.getSelectedItem());
                    String name[] = {spinner1.getSelectedItem().toString(),spinner2.getSelectedItem().toString(),spinner3.getSelectedItem().toString()};
                    //データベースを作成
                    DBAdapter_Seiseki dbAdapter_seiseki = new DBAdapter_Seiseki(getActivity(),title.getText().toString(),name);



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
                    }
                }
            });

            builder.setPositiveButton("キャンセル", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // きゃんせる ボタンクリック処理
                }
            });

            // 表示
            builder.create().show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //フラグメントがActivityに追加される時点で呼ばれるコールバックメソッド
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((Majong_Activity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }
    //戻るボタンを押した時に成績合計を更新
    @Override
    public void onStart() {
        super.onStart();
        refreshView();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_seiseki, container, false);
        refreshView(rootView);

        //ImageView imageView = (ImageView)rootView.findViewById(R.id.image_view);
        //imageView.setImageResource(mResourceId);
        return rootView;
    }


    //Preferenceからデータを読み込んで、listviewにすべての成績表を表示させる
    private void refreshView(View rootview) {
        // アプリ標準の Preferences を取得する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        List<CustomData> objects = new ArrayList<CustomData>();
        for (int i = 0; i < 50; i++) {
            String s = String.valueOf(i);
            String title_list = "title" + s;
            if (!sp.getString(title_list, "").equals("")) {
                String title = sp.getString(title_list, "");
                CustomData item_i = new CustomData();
                item_i.setTextData(title);
                objects.add(item_i);
            }
        }
        CustomAdapter_seiseki_table customAdapater = new CustomAdapter_seiseki_table(getActivity(), 0, objects);

        ListView listView = (ListView) rootview.findViewById(R.id.listView);
        listView.setAdapter(customAdapater);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView title = (TextView) view.findViewById(R.id.title);
                // インテントのインスタンス生成
                Intent intent = new Intent(getActivity(), Majong_Second_Activity.class);
                intent.putExtra("title", title.getText().toString());
                // 次画面のアクティビティ起動
                startActivity(intent);
            }
        });

    }

    //Preferenceからデータを読み込んで、listviewにすべての成績表を表示させる
    private void refreshView() {
        // アプリ標準の Preferences を取得する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        List<CustomData> objects = new ArrayList<CustomData>();
        for (int i = 0; i < 50; i++) {
            String s = String.valueOf(i);
            String title_list = "title" + s;
            if (!sp.getString(title_list, "").equals("")) {
                String title = sp.getString(title_list, "");
                CustomData item_i = new CustomData();
                item_i.setTextData(title);
                objects.add(item_i);
            }
        }
        CustomAdapter_seiseki_table customAdapater = new CustomAdapter_seiseki_table(getActivity(), 0, objects);

        ListView listView = (ListView) getActivity().findViewById(R.id.listView);
        listView.setAdapter(customAdapater);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView title = (TextView) view.findViewById(R.id.title);
                // インテントのインスタンス生成
                Intent intent = new Intent(getActivity(), Majong_Second_Activity.class);
                //　インテントに値をセット
                intent.putExtra("title", title.getText().toString());

                // 次画面のアクティビティ起動
                startActivity(intent);
            }
        });

    }


}
