package com.adel.currencyexchange2;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EndPoints {



    @GET ("core/")
    Call<MoneyDataModel> getcurrency(
            @Query("Token") String token,
            @Query("what") String what,
            @Query("filter") String filter
    );


}
