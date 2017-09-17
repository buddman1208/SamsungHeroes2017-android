package us.buddman.samsungheroes2017.fragment;

/**
 * Created by Administrator on 2017-09-16.
 */

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.net.Network;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rey.material.widget.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.buddman.samsungheroes2017.R;
import us.buddman.samsungheroes2017.databinding.FragmentPinBinding;
import us.buddman.samsungheroes2017.models.PIFMessage;
import us.buddman.samsungheroes2017.models.User;
import us.buddman.samsungheroes2017.utils.DataManager;
import us.buddman.samsungheroes2017.utils.DialogUtils;
import us.buddman.samsungheroes2017.utils.NetworkHelper;

public class PIMFragment extends Fragment {
    private int mPageNumber;
    public String title;
    DataManager manager;
    public FragmentPinBinding binding;
    public int currentSortStatus = 0;
    User selectedUser = null;

    ArrayList<User> friendList = new ArrayList<>();
    ArrayList<String> friendNameList = new ArrayList<>();

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
        manager = DataManager.getInstance();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pin, container, false);
        inputText = binding.inputText;
        sendTarget = binding.sendTargetSelect;
        send = binding.sendFab;

        NetworkHelper.getInstance().getFriendList(manager.getActiveUser().second.school, manager.getActiveUser().second.grade, manager.getActiveUser().second.classNum).enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                switch (response.code()) {
                    case 200:
                        friendList = response.body();
                        convertToNameArr();
                        binding.sendTargetSelect.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                new DialogUtils().showListDialog(getActivity(), "보낼 대상 선택", new MaterialDialog.ListCallback() {
                                    @Override
                                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                        selectedUser = friendList.get(position);
                                        binding.sendTargetSelect.setText(selectedUser.name);
                                    }
                                }, friendNameList);
                            }
                        });

                        break;
                    default:
                        Toast.makeText(getContext(), "친구 목록을 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });

        binding.sendFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedUser == null)
                    Toast.makeText(getContext(), "유저를 선택해주세요!!", Toast.LENGTH_SHORT).show();
                else {
                    NetworkHelper.getInstance().newMessage(
                            binding.inputText.getText().toString(),
                            new Date(System.currentTimeMillis()),
                            selectedUser.userid,
                            manager.getActiveUser().second.name
                    ).enqueue(new Callback<PIFMessage>() {
                        @Override
                        public void onResponse(Call<PIFMessage> call, Response<PIFMessage> response) {
                            switch (response.code()) {
                                case 200:
                                    Toast.makeText(getContext(), "성공적으로 전송되었습니다.", Toast.LENGTH_SHORT).show();
                                    selectedUser = null;
                                    binding.sendTargetSelect.setText("보낼 사람을 선택해주세요");
                                    binding.inputText.setText("");
                                    break;
                                default:
                                    Log.e("aSdf", response.message());

                            }
                        }

                        @Override
                        public void onFailure(Call<PIFMessage> call, Throwable t) {
                            Log.e("aSdf", t.getLocalizedMessage());
                        }
                    });
                }
            }
        });

        return binding.getRoot();
    }

    public void convertToNameArr() {
        for (User u : friendList) {
            friendNameList.add(u.name);

        }
    }


}