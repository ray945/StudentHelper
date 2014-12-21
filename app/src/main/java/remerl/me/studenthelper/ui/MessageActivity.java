package remerl.me.studenthelper.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import remerl.me.studenthelper.R;

/**
 * Created by hu on 2014/12/21.
 */
public class MessageActivity extends BaseActivity {
    private Toolbar messageToolbar;
    private EditText message_et;
    private CheckBox message_cb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        messageToolbar = (Toolbar) findViewById(R.id.response_actionbar);
        setSupportActionBar(messageToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        message_et = (EditText) findViewById(R.id.content_et);
        message_cb = (CheckBox) findViewById(R.id.addresponse_checkbox);
        message_et.setText("请留言...");
        message_cb.setText("匿名留言");


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
