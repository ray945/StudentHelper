package remerl.me.studenthelper.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.CursorSwipeAdapter;

import remerl.me.studenthelper.R;
import remerl.me.studenthelper.model.Todo;

/**
 * Created by qiugang on 14/11/17.
 */
public class TodoListAdapter extends CursorSwipeAdapter {
    private static final String TAG = TodoListAdapter.class.getSimpleName();

    private Context mContext;

    private Todo todo;

    private LayoutInflater mLayoutInflater;

    public TodoListAdapter(Context context, Cursor cursor) {
        super(context, cursor, false);
        this.mContext = context;
        mLayoutInflater = ((Activity)context).getLayoutInflater();
    }

    @Override
    public Todo getItem(int position) {
        getCursor().moveToPosition(position);
        Log.d(TAG, getCursor().getCount() + "");
        if (getCursor() != null) {
            return Todo.fromCursor(getCursor());
        } else {
            return null;
        }
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return mLayoutInflater.inflate(R.layout.view_todo, null);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Holder holder = getHolder(view);
        todo = Todo.fromCursor(cursor);

        TextView contentView = (TextView) view.findViewById(R.id.todo_content_textView);
        contentView.setText(todo._id +"/" + todo.content + "/" + todo.create_at  + "/"+ todo.complete );
        Log.d("this is content", todo.content);
    }

    private Holder getHolder(final View view) {
        Holder holder = (Holder) view.getTag();
        if (holder == null) {
            holder = new Holder(view);
            view.setTag(holder);
        }
        return holder;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.todo_layout;
    }

    private class Holder {
        private SwipeLayout todoLayout;

        private TextView todoContent;

        private Button todoDel;

        public Holder(View view) {
            todoLayout = (SwipeLayout) view.findViewById(R.id.todo_layout);
            todoDel = (Button) view.findViewById(R.id.todo_del_button);
            this.todoContent = (TextView)view.findViewById(R.id.todo_content_textView);
        }
    }
}
