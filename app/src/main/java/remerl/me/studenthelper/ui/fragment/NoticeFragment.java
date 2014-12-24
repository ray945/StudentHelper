package remerl.me.studenthelper.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;

import remerl.me.studenthelper.R;
import remerl.me.studenthelper.adapter.NoticesAdapter;
import remerl.me.studenthelper.model.Notice;
import remerl.me.studenthelper.utils.TaskUtils;
import remerl.me.studenthelper.view.LoadingFooter;

/**
 * Created by qiugang on 14/11/13.
 */
public class NoticeFragment extends BaseFragment {
    private ListView mListView;

    private NoticesAdapter mNoticesAdapter;

    private LoadingFooter mLoadingFooter;

    private boolean hasMore = true;

    private int page = 1;

    private ArrayList<Notice> list = new ArrayList<Notice>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_notice, container, false);
        mListView = (ListView) rootView.findViewById(R.id.notice_listView);
        mLoadingFooter = new LoadingFooter(getActivity());
        mNoticesAdapter = new NoticesAdapter(getActivity(), list);
        mListView.addFooterView(mLoadingFooter.getView());
        mListView.setAdapter(mNoticesAdapter);
        bindView();
        loadFirstPage();
        return rootView;
    }

    private void bindView() {
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (mLoadingFooter.getState() == LoadingFooter.State.Loading || mLoadingFooter.getState() == LoadingFooter.State.TheEnd) {
                    return;
                }
                if (firstVisibleItem + visibleItemCount >= totalItemCount
                        && totalItemCount != 0
                        && totalItemCount != mListView.getHeaderViewsCount()
                        + mListView.getFooterViewsCount() && mNoticesAdapter.getCount() > 0
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
                mLoadingFooter.setState(LoadingFooter.State.Idle, 3000);
                if (page == 1) {
                    for (int i = 0; i < 10; i++) {
                        Notice notice = new Notice();
                        notice.setNoticiTitle("Notice Title");
                        notice.setNoticeContent("Adobe以8亿美元现金收购图库网站Fotolia ,会将其与Creative Cloud整合 | 此次收购对 Adobe 来说意义重大, 因为Adobe在服务的正是不计其数的摄影师和设计师.买下一个图库网站将能加深 Adobe 与其用户业已存在的纽带.Fotolia将“继续作为一款独立的图库网站提供服务");
                        list.add(notice);
                    }

                }
                for (int i = 0; i < 5; i++) {
                    Notice notice = new Notice();
                    notice.setNoticiTitle("Notice Title");
                    notice.setNoticeContent("Adobe以8亿美元现金收购图库网站Fotolia ,会将其与Creative Cloud整合 | 此次收购对 Adobe 来说意义重大, 因为Adobe在服务的正是不计其数的摄影师和设计师.买下一个图库网站将能加深 Adobe 与其用户业已存在的纽带.Fotolia将“继续作为一款独立的图库网站提供服务");
                    list.add(notice);
                }
                mNoticesAdapter.notifyDataSetChanged();
            }
        });
    }


}
