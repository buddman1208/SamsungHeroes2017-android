package us.buddman.samsungheroes2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.buddman.samsungheroes2017.databinding.ActivityAuthBinding;
import us.buddman.samsungheroes2017.models.User;
import us.buddman.samsungheroes2017.utils.DataManager;
import us.buddman.samsungheroes2017.utils.NetworkHelper;

public class AuthActivity extends BaseActivity {


    ActivityAuthBinding binding;

    @Override
    protected void setDefault() {
        binding = (ActivityAuthBinding) baseBinding;
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkHelper.getInstance().loginByLocal(
                        binding.id.getText().toString().trim(),
                        binding.pw.getText().toString().trim()
                ).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        switch (response.code()) {
                            case 200:
                                Toast.makeText(AuthActivity.this, response.body().name + "님 환영합니다", Toast.LENGTH_SHORT).show();
                                DataManager.getInstance().saveUserInfo(response.body());
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                                break;
                            default:
                                Toast.makeText(AuthActivity.this, "아이디 혹은 비번이 올바르지 않습니다", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_auth;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return 0;
    }
}
