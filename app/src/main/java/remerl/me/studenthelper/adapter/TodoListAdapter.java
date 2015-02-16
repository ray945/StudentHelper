package remerl.me.studenthelper.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.CursorSwipeAdapter;

import remerl.me.studenthelper.R;
import remerl.me.studenthelper.dao.TodoDataHelper;
import remerl.me.studenthelper.model.Todo;

/**
 * Created by qiugang on 14/11/17.
 */
public class TodoListAdapter extends CursorSwipeAdapter {
    private static final String TAG = TodoListAdapter.class.getSimpleName();

    private Context mContext;

    private Todo todo;

    private Button todoDelButton;

    private Button todoCompleteButton;

    private TodoDataHelper mHelper;

    private LayoutInflater mLayoutInflater;

    private DataChangedListener dateChangedListener;

    public TodoListAdapter(Context context, Cursor cursor, DataChangedListener dataChangedListener) {
        super(context, cursor, false);
        this.mContext = context;
        this.dateChangedListener = dataChangedListener;
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
    public void bindView(View view, final Context context,final Cursor cursor) {
        final Holder holder = getHolder(view);
        mHelper = new TodoDataHelper(context);
        todo = Todo.fromCursor(cursor);
//        Log.d("cursor position", cursor.getPosition() + "");
        final TextView contentView = (TextView) view.findViewById(R.id.todo_content_textView);

        contentView.setText(todo.content);

        if (todo.complete == 0) {
            holder.todoComplete.setBackgroundColor(Color.rgb(149,117,205));
            holder.finishFlag.setBackgroundColor(Color.rgb(5,111,0));
        } else {
            holder.todoComplete.setBackgroundColor(Color.rgb(5,111,0));
            holder.finishFlag.setBackgroundColor(Color.rgb(149,117,205));
        }

        holder.todoDel.setOnClickListener(new View.OnClickListener() {
            private Todo temple = todo;
            @Override
            public void onClick(View v) {
                mHelper.delete(temple);
                Log.d("delete todo id", temple._id + "");
                dateChangedListener.dataChanged();
//                Log.d("cursor position", cursor.getPosition() + "");
                holder.todoLayout.close();
            }
        });
        holder.todoComplete.setOnClickListener(new View.OnClickListener() {
            private Todo temple = todo;
            @Override
            public void onClick(View v) {
                if (temple.complete == 0) {
                    holder.todoComplete.setText("NOT");
                    holder.todoComplete.setBackgroundColor(Color.rgb(5,111,0));
                    holder.finishFlag.setBackgroundColor(Color.rgb(149,117,205));
                    temple.complete = 1;
                    mHelper.update(temple);
                    Toast.makeText(context, "完成"+"\""+holder.todoContent.getText().toString()+"\"", Toast.LENGTH_SHORT).show();
                } else {
                    holder.todoComplete.setText("YES");
                    holder.todoComplete.setBackgroundColor(Color.rgb(149,117,205));
                    holder.finishFlag.setBackgroundColor(Color.rgb(5,111,0));
                    temple.complete = 0;
                    mHelper.update(temple);
                    Toast.makeText(context, "\""+holder.todoContent.getText().toString()+"\""+"待完成", Toast.LENGTH_SHORT).show();
                }
            }
        });

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

        private Button todoComplete;

        private Button finishFlag;

        public Holder(View view) {
            todoLayout = (SwipeLayout) view.findViewById(R.id.todo_layout);
            todoDel = (Button) view.findViewById(R.id.todo_del_button);
            todoComplete = (Button) view.findViewById(R.id.todo_complete_button);
            finishFlag = (Button) view.findViewById(R.id.finish_flag);
            this.todoContent = (TextView)view.findViewById(R.id.todo_content_textView);
        }
    }

    public interface DataChangedListener {
        public void dataChanged();
    }
}
