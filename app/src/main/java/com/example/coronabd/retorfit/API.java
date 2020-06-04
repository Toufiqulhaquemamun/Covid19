package com.example.coronabd.retorfit;



import com.example.coronabd.model.CountryReport;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface API {

    @Multipart
    @POST("Api/SymptomApi/AddSymptom")
    Call<ResponseBody> uploadProduct(
            @Part("quantity") RequestBody Quantity,
            @Part("price") RequestBody Price,
            @Part("unitId") RequestBody UnitId,
            @Part("SMEAssaignedCommodityId") RequestBody ProductId,
            @Part("sellerId") RequestBody SellerId,
            @Part("features") RequestBody Features,
            @Part List<MultipartBody.Part> files);

    @FormUrlEncoded
    @POST("api/sme/product/ChangeBidStatus")
    Call<Boolean> shippingChange(@Field("bidId") int bidId, @Field("status") String status);
    @GET("https://covid19-server.chrismichael.now.sh/api/v1/ReportsByCountries/bangladesh")
    Call<CountryReport> getCountryReport();

}