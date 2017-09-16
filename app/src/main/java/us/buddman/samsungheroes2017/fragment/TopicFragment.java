package us.buddman.samsungheroes2017.fragment;

/**
 * Created by Administrator on 2017-09-16.
 */

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.github.nitrico.lastadapter.Holder;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import us.buddman.samsungheroes2017.BR;
import us.buddman.samsungheroes2017.R;
import us.buddman.samsungheroes2017.databinding.FragmentPinBinding;
import us.buddman.samsungheroes2017.databinding.FragmentTopicBinding;
import us.buddman.samsungheroes2017.models.TopicComment;

public class TopicFragment extends Fragment {
    public String title;
    public FragmentTopicBinding binding;

    ArrayList<Object> arrayList = new ArrayList<>();
    RecyclerView recyclerView;
    EditText inputText;
    Button commentButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_topic, container, false);
        recyclerView = binding.topicRecyclerView;
        inputText = binding.commentInputText;
        commentButton = binding.sendButton;
        setView();
        return binding.getRoot();
    }

    private void setView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayList.add("");
        arrayList.add(new TopicComment(new Date(System.currentTimeMillis()),"오준석", "댓글입니다" ));
        arrayList.add(new TopicComment(new Date(System.currentTimeMillis()),"오준석", "댓글입니다" ));
        arrayList.add(new TopicComment(new Date(System.currentTimeMillis()),"오준석", "댓글입니다" ));
        arrayList.add(new TopicComment(new Date(System.currentTimeMillis()),"오준석", "댓글입니다" ));
        new LastAdapter(arrayList, BR.content)
                .map(String.class, new ItemType<>(R.layout.comment_header))
                .map(TopicComment.class, new ItemType<ViewDataBinding>(R.layout.comment_layout){
                    @Override
                    public void onBind(Holder<ViewDataBinding> holder) {
                        super.onBind(holder);
                    }
                })
                .into(recyclerView);
    }


}