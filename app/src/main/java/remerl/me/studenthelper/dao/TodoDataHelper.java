package remerl.me.studenthelper.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.content.CursorLoader;

import java.util.ArrayList;
import java.util.List;

import remerl.me.studenthelper.model.Todo;
import remerl.me.studenthelper.utils.database.Column;
import remerl.me.studenthelper.utils.database.SQLiteTable;

/**
 * Created by qiugang on 14/11/30.
 */
public class TodoDataHelper extends BaseDataHelper {

    public TodoDataHelper(Context context) {
        super(context);
    }

    @Override
    public Uri getContentUri() {
        return TodoProvider.TODO_CONTENT_URI;
    }

    private ContentValues getContentValues(Todo todo) {
        ContentValues values = new ContentValues();
        values.put(TodoDBInfo._ID, todo._id);
        values.put(TodoDBInfo.CONTENT, todo.content);
        values.put(TodoDBInfo.CREATE_AT, todo.create_at);
        values.put(TodoDBInfo.COMPLETE, todo.complete);
        return values;
    }

    public List<Todo> queryAll() {
        Todo todo = null;
        List<Todo> todos = new ArrayList<Todo>();
        Cursor cursor = query(null, null, null, null);
        if (cursor.moveToNext()) {
            todo = Todo.fromCursor(cursor);
            todos.add(todo);
        }
        cursor.close();
        return todos;
    }

    public Todo query(long _id) {
        Todo todo = null;
        Cursor cursor = query(null, TodoDBInfo._ID + "=?", new String[]{
                String.valueOf(_id)
        },TodoDBInfo._ID);
        if (cursor.moveToNext()) {
            todo = Todo.fromCursor(cursor);
        }
        cursor.close();
        return todo;
    }

    public void insert(Todo todo) {
        insert(getContentValues(todo));
    }

    public void delete(Todo todo) {
        delete(getContentUri(), TodoDBInfo._ID + "=?",new String[]{
                String.valueOf(todo._id)
        });
    }

    public void update(Todo todo) {
        update(getContentValues(todo), TodoDBInfo._ID + "=?", new String[]{
                String.valueOf(todo._id)
        });
    }

    public CursorLoader getTodoCursorLoader() {
        return new CursorLoader(getContext(), getContentUri(), null, null,null, TodoDBInfo._ID + " ASC");
    }

    public CursorLoader getDoneTodoCusorLoader() {
        return new CursorLoader(getContext(), getContentUri(), null, TodoDBInfo.COMPLETE + "=?", new String[]{
                String.valueOf(1)
        }, TodoDBInfo._ID + " ASC");
    }


    public static final class TodoDBInfo implements BaseColumns {
        private TodoDBInfo() {}

        public static final String TABLE_NAME = "todo";

        public static final String CONTENT = "content";

        public static final String CREATE_AT = "create_at";

        public static final String COMPLETE = "complete";

        public static final SQLiteTable TABLE = new SQLiteTable(TABLE_NAME)
                .addColumn(CONTENT, Column.DataType.TEXT)
                .addColumn(CREATE_AT, Column.DataType.TEXT)
                .addColumn(COMPLETE, Column.DataType.INTEGER);
    }
}
