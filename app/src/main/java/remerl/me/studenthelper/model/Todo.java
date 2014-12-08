package remerl.me.studenthelper.model;

import android.database.Cursor;

import java.util.HashMap;

import remerl.me.studenthelper.dao.TodoDataHelper;

/**
 * Created by qiugang on 14/11/30.
 */
public class Todo {
    private static HashMap<Long, Todo> CACHE = new HashMap<Long, Todo>();

    public long _id;

    public String content;

    public String create_at;

    public int complete;

    public Todo(long _id, String content, String create_at, int complete) {
        this._id = _id;
        this.content = content;
        this.create_at = create_at;
        this.complete = complete;
    }

    private static void addToCache(Todo todo) {
        CACHE.put(todo._id, todo);
    }

    private static Todo getFromCache(long _id) {
        return CACHE.get(_id);
    }

    public static Todo fromCursor(Cursor cursor) {
        Todo todo = null;
        long _id = cursor.getLong(cursor.getColumnIndex(TodoDataHelper.TodoDBInfo._ID));
        String content = cursor.getString(cursor.getColumnIndex(TodoDataHelper.TodoDBInfo.CONTENT));
        String create_at = cursor.getString(cursor.getColumnIndex(TodoDataHelper.TodoDBInfo.CREATE_AT));
        int complete = cursor.getInt(cursor.getColumnIndex(TodoDataHelper.TodoDBInfo.COMPLETE));
        todo = getFromCache(_id);
        if (todo != null) {
            return todo;
        }
        todo = new Todo(_id, content, create_at, complete);
        addToCache(todo);
        return todo;
    }


}
