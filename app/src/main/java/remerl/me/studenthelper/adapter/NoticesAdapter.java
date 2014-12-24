package remerl.me.studenthelper.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;

import remerl.me.studenthelper.App;
import remerl.me.studenthelper.R;
import remerl.me.studenthelper.model.Notice;

/**
 * Created by qiugang on 14/12/12.
 */
public class NoticesAdapter extends BaseAdapter {
    private Context mContext;
    private final SparseBooleanArray mCollapsedStatus;
    private ArrayList<Notice> list ;

    public NoticesAdapter(Context context,ArrayList<Notice> list) {
        this.mContext = context;
        mCollapsedStatus = new SparseBooleanArray();
        this.list = list;
    }



    @Override
    public int getCount() {
        return list.size();
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
        Notice notice = list.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(App.getContext()).inflate(R.layout.view_notice, null);
        }
        Holder holder = getHolder(convertView);
        holder.mExpandableTextView.setText(notice.getNoticeContent(),mCollapsedStatus,position);

        return convertView;
    }

    private Holder getHolder(final View view) {
        Holder holder = (Holder) view.getTag();
        if (holder != null) {
            return holder;
        }
        return new Holder(view);
    }


    class Holder {
        TextView noticeTitle;
        ExpandableTextView mExpandableTextView;

        public Holder(View view) {
            noticeTitle = (TextView) view.findViewById(R.id.view_notice_title_textView);
            mExpandableTextView = (ExpandableTextView) view.findViewById(R.id.view_notice_expand_textView);
        }
    }

}
