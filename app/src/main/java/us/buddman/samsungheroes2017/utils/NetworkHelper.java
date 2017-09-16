package us.buddman.samsungheroes2017.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Junseok on 2017-09-02.
 */

public class NetworkHelper {
    private static String url = "http://45.32.44.227:8867/";


    private static Retrofit retrofit = null;
    public static NetworkAPI getInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(NetworkAPI.class);
    }
}
