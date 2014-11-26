package remerl.me.studenthelper.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

/**
 * Created by qiugang on 14/11/23.
 */
public abstract class BaseDataHelper {
    private Context mContext;

    public BaseDataHelper(Context context) {
        this.mContext = context;
    }

    public abstract Uri getContentUri();

    public void notifyChange() {
        mContext.getContentResolver().notifyChange(getContentUri(), null);
    }

    protected final Cursor query(Uri uri, String[] projection, String selection,
                                 String[] slectionArgs, String sortOrder) {

        return mContext.getContentResolver().query(uri, projection, selection,
                slectionArgs, sortOrder);
    }

    protected final Cursor query( String[] projection, String selection,
                                 String[] slectionArgs, String sortOrder) {

        return mContext.getContentResolver().query(getContentUri(), projection, selection,
                slectionArgs, sortOrder);
    }

    protected final Uri insert(ContentValues values) {
        return mContext.getContentResolver().insert(getContentUri(), values);
    }

    protected final int update(ContentValues values, String where, String[] whereArgs) {
        return mContext.getContentResolver().update(getContentUri(), values, where, whereArgs);
    }

    protected final int delete(Uri uri,String selection, String[] selectionArgs) {
        return mContext.getContentResolver().delete(uri, selection, selectionArgs);
    }

    protected final Cursor getList(String[] projection, String selection,
                                   String[] slectionArgs, String sortOrder) {
        return mContext.getContentResolver().query(getContentUri(), projection, selection,
                slectionArgs,sortOrder);
    }

    protected final CursorLoader getCursorLoader(Context context,String[] projection, String selection,
                                                 String[] slectionArgs, String sortOrder) {
        return new CursorLoader(context, getContentUri(), projection, selection,slectionArgs,sortOrder);
    }

    protected final CursorLoader getCursorLoader(Context context) {
        return getCursorLoader(context, null, null, null, null);
    }
}
