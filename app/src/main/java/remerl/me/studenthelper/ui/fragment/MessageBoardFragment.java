package remerl.me.studenthelper.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.telly.floatingaction.FloatingAction;

import remerl.me.studenthelper.R;
import remerl.me.studenthelper.adapter.MessagesAdapter;

/**
 * Created by qiugang on 14/11/13.
 */
public class MessageBoardFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener{

    private SwipeRefreshLayout mSwipe;
    private ListView mListView;

    private FloatingAction mFloatingAction;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_message_board, container, false);
        mSwipe = (SwipeRefreshLayout) rootView.findViewById(R.id.message_swipeRefreshLayout);
        mListView = (ListView) rootView.findViewById(R.id.message_listView);
        mListView.setAdapter(new MessagesAdapter());
        mFloatingAction = FloatingAction.from(getActivity())
                .listenTo(mListView)
                .icon(R.drawable.add)
                .listener(this)
                .build();
        return rootView;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onClick(View v) {

    }
}
