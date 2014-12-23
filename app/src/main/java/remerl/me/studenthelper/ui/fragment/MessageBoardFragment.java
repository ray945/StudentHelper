package remerl.me.studenthelper.ui.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

import remerl.me.studenthelper.R;
import remerl.me.studenthelper.adapter.MessagesAdapter;
import remerl.me.studenthelper.model.Message;
import remerl.me.studenthelper.ui.PostMessageActivity;
import remerl.me.studenthelper.utils.TaskUtils;
import remerl.me.studenthelper.view.LoadingFooter;

/**
 * Created by qiugang on 14/11/13.
 */
public class MessageBoardFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    private SwipeRefreshLayout mSwipeRefrshLayout;

    private ListView mListView;

    private boolean hasMore = true;

    private FloatingActionButton mFloatingActionButton;

    private ArrayList<Message> list = new ArrayList<Message>();

    private int page = 1;

    private LoadingFooter mLoadingFooter;

    private MessagesAdapter messagesAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_message_board, container, false);
        mSwipeRefrshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.message_swipeRefreshLayout);
        mListView = (ListView) rootView.findViewById(R.id.message_listView);
        mFloatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.message_fb);
        mLoadingFooter = new LoadingFooter(getActivity());
        messagesAdapter = new MessagesAdapter(getActivity(), list);
        mListView.addFooterView(mLoadingFooter.getView());
        mListView.setAdapter(messagesAdapter);
        mFloatingActionButton.attachToListView(mListView);
        mFloatingActionButton.show();
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PostMessageActivity.class);
                startActivity(intent);
            }
        });
        bindListView();
        loadFirstPage();
        return rootView;
    }

    private void bindListView() {
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
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
                        && totalItemCount != mListView.getHeaderViewsCount()
                        + mListView.getFooterViewsCount() && messagesAdapter.getCount() > 0
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
                                                   message.setUsername("皮卡丘");
                                                   message.setDate("16小时前");
                                                   message.setMessage("Priority mode on AndroidLollipop is like a reception for your notifications.You tell it what to let through,and what to keep ....");
                                                   list.add(message);
                                               }
                                           }
                                           //添加数据
                                           for (int i = 0; i < 5; i++) {
                                               Message message = new Message();
                                               message.setUsername("皮卡丘");
                                               message.setDate("16小时前");
                                               message.setMessage("Priority mode on AndroidLollipop is like a reception for your notifications.You tell it what to let through,and what to keep ....");
                                               list.add(message);
                                           }
                                           messagesAdapter.onDateChange(list);
                                       }
                                   }
        );
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
