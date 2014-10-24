package com.example.admin.majong;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link android.app.Fragment} subclass.
 * Use the {@link com.example.admin.majong.AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class AddFragment extends Fragment {
    private static final String ARG_PARAM_RESOURCE_ID = "resource_id";
    private static final String ARG_SECTION_NUMBER = "section_number";
    private int mResourceId;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(int resourceId, int sectionNumber) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM_RESOURCE_ID, resourceId);
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public AddFragment() {
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

    //メニューバーを変更
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // If the drawer is open, show the global app actions in the action bar. See also
        // showGlobalContextActionBar, which controls the top-left area of the action bar.
        inflater.inflate(R.menu.add_person, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_add_person) {
            // カスタムビューを設定
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View layout = inflater.inflate(R.layout.new_person, (ViewGroup) getActivity().findViewById(R.id.alertdialog_layout));

            // アラーとダイアログ を生成
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("新規雀士の登録");
            builder.setView(layout);


            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // おーけー ボタンクリック処理
                    EditText editText = (EditText) layout.findViewById(R.id.editText);

                    if (editText.getText().toString().equals("")) {

                        Toast.makeText(getActivity(), "文字を入力してください", Toast.LENGTH_LONG).show();
                    } else {
                        String name = editText.getText().toString();
                        // アプリ標準の Preferences を取得する
                        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        for (int i = 0; i < 50; i++) {
                            String s = String.valueOf(i);
                            String name_list = "name" + s;
                            if (sp.getString(name_list, "").equals("")) {
                                savePerson(getActivity(), name_list, name);
                                refreshView(getActivity());
                                break;
                            }

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

    //Preferenceにデータを保存
    private void savePerson(Context context, String name_list, String name) {
        // アプリ標準の Preferences を取得する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        // Preferences に書き込むための Editor クラスを取得する
        SharedPreferences.Editor editor = sp.edit();

        // putXxxx("キー",データ) にて書き込むデータを登録する
        editor.putString(name_list, name);


        // 書き込みを確定する
        editor.commit();
    }

    //Preferenceからデータを読み込んで、listviewにすべての雀士を表示させる
    private void refreshView(Context context) {
        // アプリ標準の Preferences を取得する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        List<CustomData> objects = new ArrayList<CustomData>();
        for (int i = 0; i < 50; i++) {
            String s = String.valueOf(i);
            String name_list = "name" + s;
            if (!sp.getString(name_list, "").equals("")) {
                String name = sp.getString(name_list, "");
                CustomData item_i = new CustomData();
                item_i.setTextData(sp.getString(name_list, ""));
                objects.add(item_i);
            }
        }
        CustomAdapter_add_person customAdapater = new CustomAdapter_add_person(getActivity(), 0, objects);

        ListView listView = (ListView) getActivity().findViewById(R.id.listView);
        listView.setAdapter(customAdapater);
    }


    //フラグメントがActivityに追加される時点で呼ばれるコールバックメソッド
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((Majong_Activity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        List<CustomData> objects = new ArrayList<CustomData>();
        for (int i = 0; i < 50; i++) {
            String s = String.valueOf(i);
            String name_list = "name" + s;
            if (!sp.getString(name_list, "").equals("")) {
                String name = sp.getString(name_list, "");
                CustomData item_i = new CustomData();
                item_i.setTextData(sp.getString(name_list, ""));
                objects.add(item_i);
            }
        }
        CustomAdapter_add_person customAdapater = new CustomAdapter_add_person(getActivity(), 0, objects);

        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(customAdapater);

        //ImageView imageView = (ImageView)rootView.findViewById(R.id.image_view);
        //imageView.setImageResource(mResourceId);
        return rootView;
    }
}



