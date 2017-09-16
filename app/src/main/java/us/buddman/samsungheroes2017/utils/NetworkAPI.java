package us.buddman.samsungheroes2017.utils;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import us.buddman.samsungheroes2017.models.User;

/**
 * Created by Junseok Oh on 2017-07-18.
 */

public interface NetworkAPI {

    @POST("/auth/local/register")
    @FormUrlEncoded
    Call<ResponseBody> registerLocal(
            @Field("email") String email,
            @Field("nickname") String nickname,
            @Field("password") String password);

    @POST("/auth/local/authenticate")
    @FormUrlEncoded
    Call<User> authenticateByToken(
            @Field("token") String token);

    @POST("/auth/local/login")
    @FormUrlEncoded
    Call<User> loginByLocal(
            @Field("email") String email,
            @Field("password") String password);

    @POST("/coin/find/{companyName}")
    Call<ResponseBody> getCoinListByName(@Path("companyName") String companyName);


}
