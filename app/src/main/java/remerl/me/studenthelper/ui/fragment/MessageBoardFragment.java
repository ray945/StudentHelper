package remerl.me.studenthelper.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;

import remerl.me.studenthelper.R;
import remerl.me.studenthelper.adapter.MessagesAdapter;

/**
 * Created by qiugang on 14/11/13.
 */
public class MessageBoardFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener{
    private SwipeRefreshLayout mSwipeRefrshLayout;

    private ListView mListView;

    private FloatingActionButton mFAB;

    private LinearLayout viewMessage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_message_board, container, false);
        mSwipeRefrshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.message_swipeRefreshLayout);
        mListView = (ListView) rootView.findViewById(R.id.message_listView);
        mFAB = (FloatingActionButton) rootView.findViewById(R.id.message_fb);
        mFAB.attachToListView(mListView);
        mFAB.show();
        mListView.setAdapter(new MessagesAdapter(getActivity()));
        return rootView;
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
