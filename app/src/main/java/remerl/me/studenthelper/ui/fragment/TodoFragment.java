package remerl.me.studenthelper.ui.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import remerl.me.studenthelper.App;
import remerl.me.studenthelper.R;
import remerl.me.studenthelper.adapter.TodoListAdapter;
import remerl.me.studenthelper.dao.TodoDataHelper;

/**
 * Created by qiugang on 14/11/13.
 */
public class TodoFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String TAG = TodoFragment.class.getSimpleName();

    private ListView mListView;

    private LoaderManager mLoderManager;

    private TodoDataHelper mHelper;

    private TodoListAdapter mTodoListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View todoView = inflater.inflate(R.layout.fragment_todo, container, false);
        mListView = (ListView) todoView.findViewById(R.id.todo_listView);

        mTodoListAdapter = new TodoListAdapter(getActivity(), null);
        TextView textView = new TextView(getActivity());
        textView.setText("this is empty");
        mListView.setEmptyView(textView);
        mListView.setAdapter(mTodoListAdapter);
        getLoaderManager().initLoader(0, null, this);
        mHelper = new TodoDataHelper(getActivity());
//        mHelper.insert(new Todo(1,"This is just for test", "", 0));
        return todoView;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new TodoDataHelper(App.getContext()).getTodoCursorLoader();
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mTodoListAdapter.changeCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        getLoaderManager().restartLoader(0, null, this);
    }
}
