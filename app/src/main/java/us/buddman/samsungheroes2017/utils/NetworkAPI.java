package us.buddman.samsungheroes2017.utils;


import java.util.ArrayList;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Query;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import us.buddman.samsungheroes2017.models.PIFMessage;
import us.buddman.samsungheroes2017.models.TopicComment;
import us.buddman.samsungheroes2017.models.User;

/**
 * Created by Junseok Oh on 2017-07-18.
 */

public interface NetworkAPI {

    @GET("/auth/local/register")
    Call<ResponseBody> registerLocal(
            @Query("userid") String userid,
            @Query("password") String password,
            @Query("name") String name,
            @Query("school") String school,
            @Query("grade") int grade,
            @Query("classNum") int classNum);

    @GET("/auth/local/authenticate")
    Call<User> authenticateByToken(
            @Query("token") String token);

    @GET("/auth/local/login")
    Call<User> loginByLocal(
            @Query("userid") String userid,
            @Query("password") String password);

    @GET("/comment/newComment")
    Call<TopicComment> newComment(
            @Query("date") Date date,
            @Query("name") String name,
            @Query("comment") String comment);

    @GET("/comment/getCommentList")
    Call<ArrayList<TopicComment>> getCommentList();

    @GET("/pif/getFriendList")
    Call<ArrayList<User>> getFriendList(
            @Query("school") String school,
            @Query("grade") int grade,
            @Query("classNum") int classNum);

    @GET("/pif/newMessage")
    Call<PIFMessage> newMessage(
            @Query("message") String msg,
            @Query("date") Date date,
            @Query("target") String targetUserid,
            @Query("author") String myname);

    @GET("/pif/getMyMessageList")
    Call<ArrayList<PIFMessage>> getMyMessageList(
            @Query("userid") String userid);

    @GET("/tamago/up")
    Call<ResponseBody> tamagoUp(
            @Query("school") String school,
            @Query("grade") int grade,
            @Query("classNum") int classNum);

    @GET("/tamago/getTamagoCount")
    Call<Integer> getTamagoCount(
            @Query("school") String school,
            @Query("grade") int grade,
            @Query("classNum") int classNum);



}
