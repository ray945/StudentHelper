package remerl.me.studenthelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import remerl.me.studenthelper.R;
import remerl.me.studenthelper.model.InfoItem;

/**
 * Created by Rui on 2015/2/6 0006.
 */
public class InfoListViewAdapter extends BaseAdapter {

    private Context context;

    private List<Map<String, Object>> listItems;

    private LayoutInflater listContainer;

    public InfoListViewAdapter(Context context, List<Map<String, Object>> listItems) {

        this.context = context;
        listContainer = LayoutInflater.from(context);
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
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

        InfoItem infoItem = null;
        if (convertView == null) {
            infoItem = new InfoItem();
            convertView = listContainer.inflate(R.layout.info_item, null);
            infoItem.key = (TextView)convertView.findViewById(R.id.inro_item_key);
            infoItem.value = (TextView) convertView.findViewById(R.id.info_item_value);
            convertView.setTag(infoItem);
        } else {
            infoItem = (InfoItem) convertView.getTag();
        }

        infoItem.key.setText((String) listItems.get(position).get("key"));
        infoItem.value.setText((String) listItems.get(position).get("values"));
        return convertView;
    }
}
