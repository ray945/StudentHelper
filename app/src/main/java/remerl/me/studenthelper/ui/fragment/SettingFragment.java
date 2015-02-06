package remerl.me.studenthelper.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import remerl.me.studenthelper.R;
import remerl.me.studenthelper.adapter.InfoListViewAdapter;

/**
 * Created by Rui on 2015/2/5 0005.
 */
public class SettingFragment extends BaseFragment {

    private ListView listView;

    private InfoListViewAdapter adapter;

    private List<Map<String, Object>> listItems;

    private FloatingActionButton editButton;

    private ScrollView editInfo;

    private String[] key = {"性别", "生日", "学号", "班级", "电话", "邮箱", "QQ"};

    private String[] values = {"男", "1994.07.02", "12101020426", "软件124", "18508130991",
            "zhaorui007@qq.com", "610852132"};

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        listView = (ListView) view.findViewById(R.id.list_view_info);
        listItems = getListItems();
        adapter = new InfoListViewAdapter(getActivity(), listItems);
        listView.setAdapter(adapter);

        editButton = (FloatingActionButton) view.findViewById(R.id.info_edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editInfo = (ScrollView) getActivity().getLayoutInflater()
                        .inflate(R.layout.dialog_edit_info, null);
                new AlertDialog.Builder(getActivity())
                        .setTitle("修改您的信息")
                        .setView(editInfo)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //确认
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //取消
                            }
                        })
                        .create()
                        .show();
            }
        });
        return view;
    }

    private List<Map<String, Object>> getListItems() {
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0 ; i < key.length; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("key", key[i]);
            listItem.put("values", values[i]);
            listItems.add(listItem);
        }
        return listItems;
    }
}
