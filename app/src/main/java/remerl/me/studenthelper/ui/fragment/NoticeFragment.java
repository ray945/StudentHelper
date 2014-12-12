package remerl.me.studenthelper.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import remerl.me.studenthelper.R;
import remerl.me.studenthelper.adapter.NoticesAdapter;

/**
 * Created by qiugang on 14/11/13.
 */
public class NoticeFragment extends BaseFragment {
    private ListView mListView;

    private NoticesAdapter mNoticesAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_notice, container, false);
        mListView = (ListView) rootView.findViewById(R.id.notice_listView);
        mNoticesAdapter = new NoticesAdapter(getActivity());
        mListView.setAdapter(mNoticesAdapter);
        return rootView;
    }
}
