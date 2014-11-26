package remerl.me.studenthelper.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.daimajia.swipe.SwipeLayout;

import remerl.me.studenthelper.App;
import remerl.me.studenthelper.R;

/**
 * Created by qiugang on 14/11/17.
 */
public class TodosAdapter extends BaseAdapter {
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
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(App.getContext()).inflate(R.layout.view_todo, null);
            viewHolder.todoLayout = (SwipeLayout) convertView.findViewById(R.id.todo_layout);
            viewHolder.todoLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
            viewHolder.todoLayout.setDragEdge(SwipeLayout.DragEdge.Right);
            viewHolder.todoLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
                @Override
                public void onStartOpen(SwipeLayout swipeLayout) {

                }

                @Override
                public void onOpen(SwipeLayout swipeLayout) {

                }

                @Override
                public void onStartClose(SwipeLayout swipeLayout) {

                }

                @Override
                public void onClose(SwipeLayout swipeLayout) {

                }

                @Override
                public void onUpdate(SwipeLayout swipeLayout, int i, int i2) {

                }

                @Override
                public void onHandRelease(SwipeLayout swipeLayout, float v, float v2) {

                }
            });
            viewHolder.todoDel = (Button) convertView.findViewById(R.id.todo_del);
            viewHolder.todoDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        return convertView;
    }
    static class ViewHolder {
        private SwipeLayout todoLayout;
        private Button todoDel;
    }
}
