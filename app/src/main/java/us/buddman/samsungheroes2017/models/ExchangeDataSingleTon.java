package us.buddman.samsungheroes2017.models;

import android.os.Handler;
import android.support.v4.util.Pair;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.buddman.samsungheroes2017.utils.NetworkHelper;

/**
 * Created by Junseok Oh on 2017-07-10.
 */

public class ExchangeDataSingleTon {
    public static final String
            KRAKEN = "Kraken",
            GEMINI = "Gemini",
            KORBIT = "Korbit",
            OKCOINCN = "Okcoincn",
            BITFLYER = "Bitflyer",
            POLONIEX = "Poloniex",
            COINONE = "Coinone",
            BITHUMB = "Bithumb",
            COINBASE = "Coinbase",
            BITSTAMP = "Bitstamp",
            YUNBI = "Yunbi";
    public static final ArrayList<String> exchangeCompanyList = new ArrayList<>();

    private static ExchangeDataSingleTon instance;
    private HashMap<String, ArrayList<Coin>> exchangeList;

    public static ExchangeDataSingleTon getInstance() {
        if (instance == null) instance = new ExchangeDataSingleTon();
        return instance;
    }

    private ExchangeDataSingleTon() {
        Collections.addAll(exchangeCompanyList, KRAKEN, GEMINI, KORBIT, OKCOINCN, BITFLYER, POLONIEX, COINONE, BITHUMB, COINBASE, BITSTAMP, YUNBI);
        exchangeList = new HashMap<>();
        // Update
        for (String companyName : exchangeCompanyList) {
            updateCompanyCoinList(companyName);
        }
    }

    /* Company name must be lowercase */
    public void updateCompanyCoinList(final String companyName) {
        Log.e("asdf", "updateCompanyCoinList " + companyName);
        NetworkHelper.getInstance().getCoinListByName(companyName.toLowerCase()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                switch (response.code()) {
                    case 200:
                        ArrayList<Coin> coinCompanyList = removeUnsupportedData(response.body());
                        for (int i = 0; i < coinCompanyList.size(); i++) {
                            coinCompanyList.get(i).setPosition(i);
                        }
                        exchangeList.put(companyName, coinCompanyList);
//                        dataSyncListener.onDataSyncComplete(companyName, getCoinArray(companyName));
                        break;
                    default:
                        Log.e("updateCompanyCoinList:" + companyName, response.code() + " HTTP");
                }
            }

            private ArrayList<Coin> removeUnsupportedData(ResponseBody body) {
                ArrayList<Coin> resultArr = new ArrayList<>();
                try {
                    JSONArray array = new JSONArray(body.string().replace("Not Supported", "-1.0"));
                    for (int i = 0; i < array.length(); i++) {
                        resultArr.add(new Gson().fromJson(String.valueOf(array.getJSONObject(i)), Coin.class));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return resultArr;
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("onFailure:" + companyName, t.getLocalizedMessage());

            }
        });
    }

    public ArrayList<Coin> getCoinArray(String exchangeName) {
        if (exchangeList.containsKey(exchangeName)) {
            Log.e("asdf", "Returning " + exchangeName + " 's Array");
            return exchangeList.get(exchangeName);
        } else Log.e("asdf", "Returning null, no " + exchangeName);
        return null;
    }
}
