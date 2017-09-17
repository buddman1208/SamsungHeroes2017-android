package us.buddman.samsungheroes2017.fragment;

/**
 * Created by Administrator on 2017-09-16.
 */

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.buddman.samsungheroes2017.R;
import us.buddman.samsungheroes2017.databinding.FragmentDailymissionBinding;
import us.buddman.samsungheroes2017.databinding.FragmentPinBinding;
import us.buddman.samsungheroes2017.utils.DataManager;
import us.buddman.samsungheroes2017.utils.NetworkHelper;

public class DailyMissionFragment extends Fragment {
    private int mPageNumber;
    public String title;
    public FragmentDailymissionBinding binding;
    public int currentSortStatus = 0;

    int currentCount = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dailymission, container, false);
        NetworkHelper.getInstance().getTamagoCount(
                DataManager.getInstance().getActiveUser().second.school,
                DataManager.getInstance().getActiveUser().second.grade,
                DataManager.getInstance().getActiveUser().second.classNum
        ).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                switch (response.code()) {
                    case 200:
                        currentCount = response.body();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
        binding.countText.setText(DataManager.getInstance().getActiveUser().second.school + "학교 "+ DataManager.getInstance().getActiveUser().second.grade + "학년 "+ DataManager.getInstance().getActiveUser().second.classNum + "반\n누적횟수 " + currentCount + "회");
        binding.tamago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentCount++;
                binding.countText.setText(DataManager.getInstance().getActiveUser().second.school + "학교 "+ DataManager.getInstance().getActiveUser().second.grade + "학년 "+ DataManager.getInstance().getActiveUser().second.classNum + "반\n누적횟수 " + currentCount + "회");
                NetworkHelper.getInstance().tamagoUp(DataManager.getInstance().getActiveUser().second.school, DataManager.getInstance().getActiveUser().second.grade, DataManager.getInstance().getActiveUser().second.classNum).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.e("asdf", response.code() + " HTTP");
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("asdf", t.getLocalizedMessage());

                    }
                });
            }
        });
        return binding.getRoot();
    }


}