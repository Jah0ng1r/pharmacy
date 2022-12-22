package com.example.apteka.api;

import com.example.apteka.model.addAptekaModel.AddAptekaModel;
import com.example.apteka.model.addAptekaModel.AddAptekaResponse;
import com.example.apteka.model.aptekaModel.AptekaTokenModel;
import com.example.apteka.model.historyModel.AptekaHistoryModel;
import com.example.apteka.model.historyModel.AptekaHistoryResponseModel;
import com.example.apteka.model.aptekaModel.BaseApteka;
import com.example.apteka.model.doriModel.DorilarModel;
import com.example.apteka.model.loginModel.LoginModel;
import com.example.apteka.model.loginModel.LoginResponse;
import com.example.apteka.model.stattisticModel.StatisticListResponse;
import com.example.apteka.model.stattisticModel.StatisticModel;
import com.example.apteka.model.storeModel.StoreModel;
import com.example.apteka.model.storeModel.StoreResponse;
import com.example.apteka.model.tolovModel.TolovModel;
import com.example.apteka.model.tolovModel.TolovModelResponse;
import com.example.apteka.model.umumiyqarz.QarzModel;
import com.example.apteka.model.umumiyqarz.QarzResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {

    @POST("api/accounts/login")
    Call<LoginResponse> getLogin(@Body LoginModel model);

    @Headers("Content-Type: application/json")
    @POST("store/")
    Call<StoreResponse> senData(@Body StoreModel storeModel);

    @POST("sotuvchi/")
    Call<QarzResponse> getQarz(@Body QarzModel qarzModel);

    @POST("sotuvchi/apteka/history/")
    Call<AptekaHistoryResponseModel> getHistory(@Body AptekaHistoryModel historyModel);

    @POST("sotuvchi/qarzdorlar/tolash/")
    Call<TolovModelResponse> payQarz(@Body TolovModel tolovModel);

    @POST("get_aptekalar_list/")
    Call<BaseApteka> getAptekalar(@Body AptekaTokenModel tokenModel);

    @POST("add_apteka/")
    Call<AddAptekaResponse> addApteka(@Body AddAptekaModel addAptekaModel);

    @GET("dorilar_list")
    Call<List<DorilarModel>> getDorilar();

    @POST("sotuvchi/single_day_statistics/")
    Call<StatisticListResponse> getStatisticHistory(@Body StatisticModel statisticModel);


//    @POST("sotuvchi/qarzdorlar/history/")
//    Call<>
}
