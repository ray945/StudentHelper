package remerl.me.studenthelper.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import remerl.me.studenthelper.R;

/**
 * Created by hu on 2014/12/17.
 */
public class ResonseActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_addresponse, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_addresponse;
    }
}
