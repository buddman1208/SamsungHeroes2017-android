package us.buddman.samsungheroes2017.fragment;

/**
 * Created by Administrator on 2017-09-16.
 */

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.nitrico.lastadapter.Holder;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.buddman.samsungheroes2017.BR;
import us.buddman.samsungheroes2017.MainActivity;
import us.buddman.samsungheroes2017.R;
import us.buddman.samsungheroes2017.databinding.FragmentPinBinding;
import us.buddman.samsungheroes2017.databinding.FragmentTopicBinding;
import us.buddman.samsungheroes2017.models.TopicComment;
import us.buddman.samsungheroes2017.utils.DataManager;
import us.buddman.samsungheroes2017.utils.NetworkHelper;

public class TopicFragment extends Fragment {
    public String title;
    public FragmentTopicBinding binding;

    LastAdapter adapter;
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
        setData();
        binding.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                NetworkHelper.getInstance().newComment(
                        new Date(System.currentTimeMillis()),
                        DataManager.getInstance().getActiveUser().second.name,
                        binding.commentInputText.getText().toString().trim()
                ).enqueue(new Callback<TopicComment>() {
                    @Override
                    public void onResponse(Call<TopicComment> call, Response<TopicComment> response) {
                        switch (response.code()) {
                            case 200:
                                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                arrayList.add(response.body());
                                adapter.notifyItemInserted(arrayList.size());
                                binding.commentInputText.setText("");
                                break;
                            default:
                                Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<TopicComment> call, Throwable t) {
                        Log.e("asDf", t.getLocalizedMessage());
                    }
                });
            }
        });

    }

    public void setData() {
        arrayList.add("");
        NetworkHelper.getInstance().getCommentList().enqueue(new Callback<ArrayList<TopicComment>>() {
            @Override
            public void onResponse(Call<ArrayList<TopicComment>> call, Response<ArrayList<TopicComment>> response) {
                switch (response.code()) {
                    case 200:
                        for (TopicComment c : response.body()) {
                            arrayList.add(c);
                        }
                        adapter = new LastAdapter(arrayList, BR.content)
                                .map(String.class, new ItemType<>(R.layout.comment_header))
                                .map(TopicComment.class, new ItemType<ViewDataBinding>(R.layout.comment_layout) {
                                    @Override
                                    public void onBind(Holder<ViewDataBinding> holder) {
                                        super.onBind(holder);
                                    }
                                })
                                .into(recyclerView);
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TopicComment>> call, Throwable t) {

            }
        });
    }


}