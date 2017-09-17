package us.buddman.samsungheroes2017;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.buddman.samsungheroes2017.databinding.ActivityReceivedMessageBinding;
import us.buddman.samsungheroes2017.models.PIFMessage;
import us.buddman.samsungheroes2017.utils.DataManager;
import us.buddman.samsungheroes2017.utils.NetworkHelper;

public class ReceivedMessageActivity extends BaseActivity {

    ActivityReceivedMessageBinding binding;

    ArrayList<PIFMessage> arrayList = new ArrayList<>();

    @Override
    protected void setDefault() {
        binding = (ActivityReceivedMessageBinding) baseBinding;
        setToolbarTitle("내가 받은 Pay it forward");
        binding.receivedRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get from Server
        NetworkHelper.getInstance().getMyMessageList(DataManager.getInstance().getActiveUser().second.userid).enqueue(new Callback<ArrayList<PIFMessage>>() {
            @Override
            public void onResponse(Call<ArrayList<PIFMessage>> call, Response<ArrayList<PIFMessage>> response) {
                switch (response.code()) {
                    case 200:
                        arrayList = response.body();
                        new LastAdapter(arrayList, us.buddman.samsungheroes2017.BR.content)
                                .map(PIFMessage.class, new ItemType<ViewDataBinding>(R.layout.received_message_content))
                                .into(binding.receivedRecyclerView);

                        break;
                    default:
                        Toast.makeText(ReceivedMessageActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PIFMessage>> call, Throwable t) {
                Log.e("asdf", t.getLocalizedMessage());
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_received_message;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }
}
