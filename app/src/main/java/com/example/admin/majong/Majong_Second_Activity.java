package com.example.admin.majong;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by admin on 2014/10/30.
 */
public class Majong_Second_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_majong__second__activity);
        //ActionBarをGetしてTabModeをセット
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setDisplayShowHomeEnabled(false);

        actionBar.addTab(actionBar.newTab()
                .setText("成績詳細")
                .setTabListener(new TabListener<Seiseki_Syousai_Fragment>(
                        this, "tag1", Seiseki_Syousai_Fragment.class)));
    /*actionBar.addTab(actionBar.newTab()
            .setText("日付⇨日数")
    .setTabListener(new TabListener<SecondTabFragment>(
            this, "tag2", SecondTabFragment.class)));

    actionBar.addTab(actionBar.newTab()
            .setText("記念日")
    .setTabListener(new TabListener<ThirdTabFragment>(
            this, "tag3", ThirdTabFragment.class)));
*/
    }
}






