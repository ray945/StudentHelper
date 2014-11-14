package remerl.me.studenthelper.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import remerl.me.studenthelper.R;
import remerl.me.studenthelper.adapter.DrawerAdapter;
import remerl.me.studenthelper.dao.Category;
import remerl.me.studenthelper.ui.MainActivity;


/**
 * Created by qiugang on 2014/9/21.
 */
public class DrawerFragment extends Fragment {

    private ListView mListView;

    private TextView mTextView;

    private DrawerAdapter mAdapter;

    private MainActivity mainActivity;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_drawer, null);
        mainActivity = (MainActivity)getActivity();
        mListView = (ListView) rootView.findViewById(R.id.drawer_listView);
        mAdapter = new DrawerAdapter(mListView);
        mListView.setAdapter(mAdapter);
        mListView.setItemChecked(0, true);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListView.setItemChecked(position, true);
                mainActivity.setCategory(Category.values()[position]);

                switch (Category.values()[position]) {
                    case Home:

                        break;
                    case Personal:
                        TodoFragment todoFragment = new TodoFragment();
                        getFragmentManager().beginTransaction().replace(R.id.container, todoFragment)
                            .commit();
                        break;
                    case MyClass:

                        break;
                    case MessageBoard:
                        
                        break;
                    case Settings:

                        break;
                    default:
                        break;
                }
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }
}
