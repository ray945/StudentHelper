package remerl.me.studenthelper.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import remerl.me.studenthelper.App;
import remerl.me.studenthelper.R;
import remerl.me.studenthelper.model.Message;
import remerl.me.studenthelper.ui.CommentActivity;

/**
 * Created by qiugang on 14/11/16.
 */
public class MessagesAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Message> list ;

    public MessagesAdapter(Context context, ArrayList<Message> list) {
        this.list = list;
        this.mContext = context;
    }

    public void onDateChange(ArrayList<Message> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder viewHolder;
        Message message = list.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(App.getContext()).inflate(R.layout.view_message, null);
            viewHolder = getHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Holder) convertView.getTag();
        }
//        viewHolder.buttonLikes.setImageResource(R.drawable.profile);
        viewHolder.username.setText(message.getUsername());
        viewHolder.date.setText(message.getDate());
        viewHolder.commentMessage.setText(message.getMessage());
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

        private TextView date;

        private TextView username;

        public Holder(View view) {
            username = (TextView) view.findViewById(R.id.comment_UserNames);
            date = (TextView) view.findViewById(R.id.comment_Dates);
            buttonLikes = (ImageButton) view.findViewById(R.id.message_like_button);
            buttonComments = (ImageButton) view.findViewById(R.id.message_comments_button);
            commentMessage = (TextView) view.findViewById(R.id.comment_message);
        }

    }
}
