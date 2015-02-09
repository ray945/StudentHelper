package remerl.me.studenthelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import remerl.me.studenthelper.R;
import remerl.me.studenthelper.model.InfoItem;

/**
 * Created by ray on 15-2-9.
 */
public class SettingListAdapter extends BaseAdapter {

    private Context context;

    private List<Map<String, Object>> listItems;

    private LayoutInflater listContainer;

    public SettingListAdapter(Context context, List<Map<String, Object>> listItems) {
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
            convertView = listContainer.inflate(R.layout.listitem_setting, null);
            infoItem.key = (TextView)convertView.findViewById(R.id.setting_key);
            infoItem.value = (TextView) convertView.findViewById(R.id.setting_value);
            convertView.setTag(infoItem);
        } else {
            infoItem = (InfoItem) convertView.getTag();
        }

        infoItem.key.setText((String) listItems.get(position).get("key"));
        infoItem.value.setText((String) listItems.get(position).get("values"));
        return convertView;
    }
}
