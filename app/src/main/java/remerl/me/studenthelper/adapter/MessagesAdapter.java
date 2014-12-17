package remerl.me.studenthelper.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import remerl.me.studenthelper.App;
import remerl.me.studenthelper.R;
import remerl.me.studenthelper.ui.CommentActivity;

/**
 * Created by qiugang on 14/11/16.
 */
public class MessagesAdapter extends BaseAdapter {
    private Context mContext;

    public MessagesAdapter(Context context){
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
        Holder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(App.getContext()).inflate(R.layout.view_message, null);
            viewHolder = getHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Holder) convertView.getTag();
        }
        viewHolder.commentMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CommentActivity.class);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    private Holder getHolder(final View view) {
        Holder holder = (Holder) view.getTag();
        if (holder == null) {
            return new Holder(view);
        }
        return holder;
    }

    static class Holder {
        private ImageButton buttonLikes;

        private ImageButton buttonComments;

        private TextView commentMessage;

        public Holder(View view) {
            buttonLikes = (ImageButton) view.findViewById(R.id.message_like_button);
            buttonComments = (ImageButton) view.findViewById(R.id.message_comments_button);
            commentMessage = (TextView) view.findViewById(R.id.message);
        }

    }
}
