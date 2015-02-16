package remerl.me.studenthelper.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import remerl.me.studenthelper.R;
import remerl.me.studenthelper.adapter.SettingListAdapter;

/**
 * Created by ray on 15-2-9.
 */
public class SettingActivity extends ActionBarActivity {

    private Toolbar toolbar;

    private ListView listView1;

    private ListView listView2;

    private SettingListAdapter adapter;

    private List<Map<String, Object>> listItems1;

    private List<Map<String, Object>> listItems2;

    private String[] key1 = {"用户名", "修改密码", "修改信息"};

    private String[] values1 = {"ray945", ">>", ">>"};

    private String[] key2 = {"版本号", "意见反馈", "关于"};

    private String[] values2 = {"v1.0", ">>", ">>"};

    private ScrollView editInfo;

    private LinearLayout editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        listView1 = (ListView) findViewById(R.id.list1);
        listView2 = (ListView) findViewById(R.id.list2);

        listItems1 = getListItems1();
        adapter = new SettingListAdapter(this, listItems1);
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (listItems1.get(position).get("key").toString()) {
                    case "修改密码":
                        editPassword = (LinearLayout) getLayoutInflater()
                                .inflate(R.layout.dialog_password, null);
                        new AlertDialog.Builder(SettingActivity.this)
                                .setTitle("设置您的密码")
                                .setView(editPassword)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //确认修改密码
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

                        break;
                    case "修改信息":
                        editInfo = (ScrollView) getLayoutInflater()
                                .inflate(R.layout.dialog_edit_info, null);
                        new AlertDialog.Builder(SettingActivity.this)
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

                        break;

                    default:
                        break;
                }
            }
        });

        listItems2 = getListItems2();
        adapter = new SettingListAdapter(this, listItems2);
        listView2.setAdapter(adapter);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //待完成
            }
        });
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private List<Map<String, Object>> getListItems1() {
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0 ; i < key1.length ; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("key", key1[i]);
            listItem.put("values", values1[i]);
            listItems.add(listItem);
        }
        return listItems;
    }

    private List<Map<String, Object>> getListItems2() {
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0 ; i < key2.length ; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("key", key2[i]);
            listItem.put("values", values2[i]);
            listItems.add(listItem);
        }
        return listItems;
    }

}
