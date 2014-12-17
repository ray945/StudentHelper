package remerl.me.studenthelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import remerl.me.studenthelper.App;
import remerl.me.studenthelper.R;

/**
 * Created by hu on 2014/12/17.
 */
public class CommentAdapter extends BaseAdapter {
    private Context mContext;

    public CommentAdapter(Context context) {
        this.mContext = context;
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(App.getContext()).inflate(R.layout.view_comment_item, null);
            holder = getHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    private ViewHolder getHolder(final View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null) {
            return new ViewHolder(view);
        }
        return holder;
    }

    static class ViewHolder {
        private ImageView messageContentImage;
        private TextView messageContentUser;
        private TextView messageContentDate;
        private TextView messageContentWords;

        public ViewHolder(View view) {
            messageContentImage = (ImageView) view.findViewById(R.id.comment_UerPhotos);
            messageContentUser = (TextView) view.findViewById(R.id.comment_UserNames);
            messageContentDate = (TextView) view.findViewById(R.id.comment_Dates);
            messageContentWords = (TextView) view.findViewById(R.id.comment_Words);
        }


    }

}


