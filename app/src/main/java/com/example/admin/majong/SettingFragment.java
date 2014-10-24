package com.example.admin.majong;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link android.app.Fragment} subclass.
 * Use the {@link com.example.admin.majong.SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class SettingFragment extends Fragment {
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
    public static SettingFragment newInstance(int resourceId, int sectionNumber) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM_RESOURCE_ID, resourceId);
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public SettingFragment() {
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
        //inflater.inflate(R.menu.seiseki_table,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    //フラグメントがActivityに追加される時点で呼ばれるコールバックメソッド
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        ((Majong_Activity)activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_setting,container,false);

        //ImageView imageView = (ImageView)rootView.findViewById(R.id.image_view);
        //imageView.setImageResource(mResourceId);
        return rootView;
    }


}
