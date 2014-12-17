package remerl.me.studenthelper.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import remerl.me.studenthelper.R;
import remerl.me.studenthelper.adapter.CommentAdapter;

/**
 * Created by hu on 2014/12/16.
 */
public class CommentActivity extends BaseActivity {
    private ListView commentListView;
    private CommentAdapter mAdapter;
    private View commentHeader;
    private ImageView comment;
    private Toolbar commentToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commentListView = (ListView) findViewById(R.id.commentListView);
        commentToolbar = (Toolbar) findViewById(R.id.comment_actionbar);
        setSupportActionBar(commentToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LayoutInflater inflator = LayoutInflater.from(this);
        commentHeader = inflator.inflate(R.layout.view_message_content, commentListView, false);
        commentListView.addHeaderView(commentHeader, null, false);
        mAdapter = new CommentAdapter(this);
        commentListView.setAdapter(mAdapter);
        comment = (ImageView) findViewById(R.id.message_comments_button);
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommentActivity.this, ResonseActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_comment;
    }

    @Override
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
}
