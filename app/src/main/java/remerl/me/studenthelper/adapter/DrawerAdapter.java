package remerl.me.studenthelper.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import remerl.me.studenthelper.App;
import remerl.me.studenthelper.R;
import remerl.me.studenthelper.dao.Category;


/**
 * Created by qiugang on 2014/9/21.
 */
public class DrawerAdapter extends BaseAdapter {

    private ListView mListView;

    public DrawerAdapter(ListView listView) {
        mListView = listView;
    }

    @Override
    public int getCount() {
        return Category.values().length;
    }

    @Override
    public Category getItem(int position) {
        return Category.values()[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(App.getContext()).inflate(R.layout.listitem_drawer, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.textView);
        textView.setText(getItem(position).getDisplayName());
        textView.setSelected(mListView.isItemChecked(position));
        return convertView;
    }
}
