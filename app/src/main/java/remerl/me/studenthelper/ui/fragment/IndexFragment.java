package remerl.me.studenthelper.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import remerl.me.studenthelper.R;
import remerl.me.studenthelper.model.Category;
import remerl.me.studenthelper.ui.MainActivity;

/**
 * Created by ray on 14-12-15.
 */
public class IndexFragment extends BaseFragment implements View.OnClickListener {

    private Button intoTodo;

    private Button intoNotice;

    private Button intoMessage;

    private Button intoInfo;

    private Button intoSetting;

    private Button intoQuit;

    private FragmentManager fragmentManager;

    private FragmentTransaction transaction;

    private MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        intoTodo = (Button) view.findViewById(R.id.into_todo);
        intoNotice = (Button) view.findViewById(R.id.into_notice);
        intoMessage = (Button) view.findViewById(R.id.into_message);
        intoInfo = (Button) view.findViewById(R.id.into_info);
        intoSetting = (Button) view.findViewById(R.id.into_setting);
        intoQuit = (Button) view.findViewById(R.id.into_quit);
        intoTodo.setOnClickListener(this);
        intoNotice.setOnClickListener(this);
        intoMessage.setOnClickListener(this);
        intoInfo.setOnClickListener(this);
        intoSetting.setOnClickListener(this);
        intoQuit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        activity = (MainActivity) getActivity();
        switch (v.getId()) {
            case R.id.into_todo:
                activity.setCategory(Category.TodoList);
                break;
            case R.id.into_notice:
                activity.setCategory(Category.ClassInformation);
                break;
            case R.id.into_message:
                activity.setCategory(Category.MessageBoard);
                break;
            case R.id.into_info:
                activity.setCategory(Category.Settings);
                break;
            case R.id.into_setting:
                break;
            case R.id.into_quit:
                break;
            default:
                break;
        }
    }
}
