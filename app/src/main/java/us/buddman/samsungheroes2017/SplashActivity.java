package us.buddman.samsungheroes2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.buddman.samsungheroes2017.models.User;
import us.buddman.samsungheroes2017.utils.DataManager;
import us.buddman.samsungheroes2017.utils.NetworkHelper;

public class SplashActivity extends AppCompatActivity {

    DataManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        manager = DataManager.getInstance();
        if (manager.getActiveUser().first) {
            NetworkHelper.getInstance().authenticateByToken(manager.getActiveUser().second.token).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    switch (response.code()) {
                        case 200:
                            Toast.makeText(SplashActivity.this, response.body().name + "님 환영합니다", Toast.LENGTH_SHORT).show();
                            DataManager.getInstance().saveUserInfo(response.body());
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                            break;
                        default:
                            findViewById(R.id.container).setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        } else findViewById(R.id.container).setVisibility(View.VISIBLE);


        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AuthActivity.class));
                finish();
            }
        });
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                finish();

            }
        });
    }
}
