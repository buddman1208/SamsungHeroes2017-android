package us.buddman.samsungheroes2017;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.buddman.samsungheroes2017.databinding.ActivityRegisterBinding;
import us.buddman.samsungheroes2017.utils.NetworkHelper;

public class RegisterActivity extends BaseActivity {

    ActivityRegisterBinding binding;
    @Override
    protected void setDefault() {
        binding = (ActivityRegisterBinding) baseBinding;

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkHelper.getInstance().registerLocal(
                        binding.id.getText().toString().trim(),
                        binding.pw.getText().toString().trim(),
                        binding.name.getText().toString().trim(),
                        binding.school.getText().toString().trim(),
                        Integer.parseInt(binding.grade.getText().toString().trim()),
                        Integer.parseInt(binding.classNum.getText().toString().trim())
                ).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        switch (response.code()){
                            case 200:
                                Toast.makeText(RegisterActivity.this, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), AuthActivity.class));
                                finish();
                                break;
                            default:
                                Toast.makeText(RegisterActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return 0;
    }
}
