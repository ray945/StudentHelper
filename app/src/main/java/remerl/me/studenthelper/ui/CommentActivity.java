package remerl.me.studenthelper.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import remerl.me.studenthelper.R;
import remerl.me.studenthelper.adapter.CommentAdapter;
import remerl.me.studenthelper.model.Message;
import remerl.me.studenthelper.utils.TaskUtils;
import remerl.me.studenthelper.view.LoadingFooter;

/**
 * Created by hu on 2014/12/16.
 */
public class CommentActivity extends BaseActivity {
    private ListView commentListView;

    private CommentAdapter mAdapter;

    private View commentHeader;

    private ImageView comment;

    private Toolbar commentToolbar;

    private boolean hasMore = true;
    private int page = 1;
    private ArrayList<Message> list = new ArrayList<Message>();
    private LoadingFooter mLoadingFooter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commentListView = (ListView) findViewById(R.id.commentListView);
        commentToolbar = (Toolbar) findViewById(R.id.comment_actionbar);
        setSupportActionBar(commentToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mLoadingFooter = new LoadingFooter(this);

        LayoutInflater inflator = LayoutInflater.from(this);
        commentHeader = inflator.inflate(R.layout.view_message_content, commentListView, false);
        commentListView.addHeaderView(commentHeader, null, false);
        mAdapter = new CommentAdapter(this, list);
        commentListView.addFooterView(mLoadingFooter.getView());
        commentListView.setAdapter(mAdapter);
        comment = (ImageView) findViewById(R.id.message_comments_button);
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommentActivity.this, PostCommentActivity.class);
                startActivity(intent);
            }
        });
        bindListView();
        loadFirstPage();

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

    private void bindListView() {
        commentListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {

                //如果footer是正在加载或者是已经加载到最后一页，屏蔽分页加载事件
                if (mLoadingFooter.getState() == LoadingFooter.State.Loading
                        || mLoadingFooter.getState() == LoadingFooter.State.TheEnd) {
                    return;
                }
                /**
                 * 第一个判断是判断是否滑动到底部
                 * 原本应该是totalItemCount = firstVisibleItem + visibleItemCount
                 * 因为我们加了footerView(还可以添加headerView),所以应该是firstVisibleItem + visibleItemCount >= totalItemCount
                 *
                 * 然后接下来的判断就是判空
                 *
                 * 最后加一个参数hasMore是给加载数据时，根据获得数据的条数来判断是否加载到最后一页
                 * 等于规定的条数, hasMore = true;
                 * 小于规定的条数, hasMore = false;
                 */
                if (firstVisibleItem + visibleItemCount >= totalItemCount
                        && totalItemCount != 0
                        && totalItemCount != commentListView.getHeaderViewsCount()
                        + commentListView.getFooterViewsCount() && mAdapter.getCount() > 0
                        && hasMore) {
                    loadNextPage();
                }
            }
        });
    }

    private void loadFirstPage() {
        page = 1;
        loadData(page);
    }

    private void loadNextPage() {
        //开始加载数据设置footer的状态为loading
        mLoadingFooter.setState(LoadingFooter.State.Loading);
        page++;
        loadData(page);
    }

    private void loadData(final int page) {
        TaskUtils.executeAsyncTask(new AsyncTask<Object, Object, Object>() {
                                       @Override
                                       protected Object doInBackground(Object... params) {
                                           return null;
                                       }

                                       @Override
                                       protected void onPostExecute(Object o) {
                                           super.onPostExecute(o);
                                           //推迟3秒设置footer消失，保证footer显示的一致性
                                           mLoadingFooter.setState(LoadingFooter.State.Idle, 3000);
                                           if (page == 1) {
                                               for (int i = 0; i < 10; i++) {
                                                   Message message = new Message();
                                                   message.setUsername("gadget");
                                                   message.setDate("16小时前");
                                                   message.setMessage("机会只给有准备的人机会只给有准备的人机会只给有准备的人机会只给有准备的人");
                                                   list.add(message);
                                               }
                                           }
                                           //添加数据
                                           for (int i = 0; i < 5; i++) {
                                               Message message = new Message();
                                               message.setUsername("gadget");
                                               message.setDate("16小时前");
                                               message.setMessage("机会只给有准备的人机会只给有准备的人机会只给有准备的人机会只给有准备的人");
                                               list.add(message);
                                           }
                                           mAdapter.onDateChange(list);
                                       }
                                   }
        );
    }
}
