package remerl.me.studenthelper.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import remerl.me.studenthelper.R;


/**
 * Created by hu on 2014/12/17.
 */
public class ResonseActivity extends BaseActivity {

    private Toolbar responseBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        responseBar = (Toolbar) findViewById(R.id.response_actionbar);
        setSupportActionBar(responseBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_addresponse;
    }
}
