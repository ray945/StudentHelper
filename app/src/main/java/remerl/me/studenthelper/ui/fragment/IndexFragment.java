package remerl.me.studenthelper.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import remerl.me.studenthelper.R;

/**
 * Created by ray on 14-12-15.
 */
public class IndexFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        return view;
    }
}
