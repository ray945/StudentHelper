package remerl.me.studenthelper.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import remerl.me.studenthelper.App;
import remerl.me.studenthelper.R;

/**
 * Created by qiugang on 14/12/12.
 */
public class NoticesAdapter extends BaseAdapter{
    private Context mContext;
    private final SparseBooleanArray mCollapsedStatus;

    public NoticesAdapter(Context context) {
        this.mContext = context;
        mCollapsedStatus = new SparseBooleanArray();
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(App.getContext()).inflate(R.layout.view_notice, null);
        ExpandableTextView expandableTextView = (ExpandableTextView) convertView.findViewById(R.id.expand_text_view);
        expandableTextView.setText(mContext.getString(R.string.test_string), mCollapsedStatus, position);
        return convertView;
    }
}
