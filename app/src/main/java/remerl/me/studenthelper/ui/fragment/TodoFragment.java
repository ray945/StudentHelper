package remerl.me.studenthelper.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import remerl.me.studenthelper.R;
import remerl.me.studenthelper.adapter.TodosAdapter;

/**
 * Created by qiugang on 14/11/13.
 */
public class TodoFragment extends BaseFragment {

    private ListView mListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View todoView = inflater.inflate(R.layout.fragment_todo, container, false);
        mListView = (ListView) todoView.findViewById(R.id.todo_listView);
        mListView.setAdapter(new TodosAdapter());
        return todoView;
    }
}
