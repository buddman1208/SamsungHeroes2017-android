package us.buddman.samsungheroes2017.fragment;

/**
 * Created by Administrator on 2017-09-16.
 */

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.rey.material.widget.FloatingActionButton;

import us.buddman.samsungheroes2017.R;
import us.buddman.samsungheroes2017.databinding.FragmentPinBinding;

public class PIMFragment extends Fragment {
    private int mPageNumber;
    public String title;
    public FragmentPinBinding binding;
    public int currentSortStatus = 0;

    EditText inputText;
    TextView sendTarget;
    FloatingActionButton send;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pin, container, false);
        inputText = binding.inputText;
        sendTarget = binding.sendTargetSelect;
        send = binding.sendFab;

        return binding.getRoot();
    }


}