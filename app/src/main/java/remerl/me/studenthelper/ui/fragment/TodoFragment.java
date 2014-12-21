package remerl.me.studenthelper.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import remerl.me.studenthelper.App;
import remerl.me.studenthelper.R;
import remerl.me.studenthelper.adapter.TodoListAdapter;
import remerl.me.studenthelper.dao.TodoDataHelper;
import remerl.me.studenthelper.model.Todo;

/**
 * Created by qiugang on 14/11/13.
 */
public class TodoFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> ,
        TodoListAdapter.DataChangedListener{

    public static final String TAG = TodoFragment.class.getSimpleName();

    private ListView mListView;

    private LoaderManager mLoderManager;

    private TodoDataHelper mHelper;

    private TodoListAdapter mTodoListAdapter;

    private LinearLayout createTodo;

    private int i = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View todoView = inflater.inflate(R.layout.fragment_todo, container, false);
        mListView = (ListView) todoView.findViewById(R.id.todo_listView);

        mTodoListAdapter = new TodoListAdapter(getActivity(), null, this);
        TextView textView = new TextView(getActivity());
        textView.setText("this is empty");
        mListView.setEmptyView(textView);
        mListView.setAdapter(mTodoListAdapter);
        getLoaderManager().initLoader(0, null, this);
        mHelper = new TodoDataHelper(getActivity());
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_todo, menu);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_todo:
                createTodo = (LinearLayout) getActivity().getLayoutInflater()
                        .inflate(R.layout.dialog_create_todo, null);
                new AlertDialog.Builder(getActivity())
                        .setTitle("添加Todo事件")
                        .setView(createTodo)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String text = ((EditText) createTodo.findViewById(R.id.set_todo))
                                        .getText().toString();
                                mHelper.insert(new Todo(1, text, "", 0));
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //取消
                            }
                        })
                        .create()
                        .show();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void dataChanged() {
        getLoaderManager().restartLoader(0, null, this);
    }
}
