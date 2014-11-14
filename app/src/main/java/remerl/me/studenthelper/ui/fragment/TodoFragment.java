package remerl.me.studenthelper.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daimajia.swipe.SwipeLayout;

import remerl.me.studenthelper.R;

/**
 * Created by qiugang on 14/11/13.
 */
public class TodoFragment extends Fragment {

    Button todoDel;

    SwipeLayout todoLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View todoView = inflater.inflate(R.layout.todo_layout, null);

        todoLayout = (SwipeLayout) todoView.findViewById(R.id.todo_layout);
        todoLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        todoLayout.setDragEdge(SwipeLayout.DragEdge.Right);
        todoLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
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
        todoDel = (Button) todoView.findViewById(R.id.todo_del);
        todoDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return todoView;
    }
}
