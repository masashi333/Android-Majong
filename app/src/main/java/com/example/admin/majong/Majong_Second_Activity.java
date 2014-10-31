package com.example.admin.majong;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by admin on 2014/10/30.
 */
public class Majong_Second_Activity extends Activity {

    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_majong);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // 次画面のアクティビティ終了
                finish();
            }
        });

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






