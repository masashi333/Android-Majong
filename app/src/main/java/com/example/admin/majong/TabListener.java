package com.example.admin.majong;

/**
 * Created by admin on 2014/08/26.
 */

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class TabListener<T extends Fragment> implements ActionBar.TabListener {
    private Fragment mFragment;
    private final Activity mActivity;
    private final String mTag;
    private final Class<T> mClass;

    //コンストラクタ
    public TabListener(Activity activity, String tag, Class<T> clz) {
        mActivity = activity;
        mTag = tag;
        mClass = clz;
        mFragment = mActivity.getFragmentManager().findFragmentByTag(mTag);
    }

    //タブが選択されたとき
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

        if (mFragment == null) {
            mFragment = Fragment.instantiate(mActivity, mClass.getName());
            FragmentManager fm = mActivity.getFragmentManager();
            fm.beginTransaction().add(R.id.container, mFragment, mTag).commit();
        } else {
            if (mFragment.isDetached()) {
                // isDetached(): Return true if the fragment has been explicitly detached from the UI.
                FragmentManager fm = mActivity.getFragmentManager();
               //attach: Re-attach a fragment after it had previously been deatched from the UI with detach(Fragment).
                fm.beginTransaction().attach(mFragment).commit();
            }

        }
    }

    //タブの選択が解除されたとき
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (mFragment != null) {
            FragmentManager fm = mActivity.getFragmentManager();
            fm.beginTransaction().detach(mFragment).commit();
        }
    }

    //選択されたタブが選択されたとき
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }
}