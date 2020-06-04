package com.example.coronabd.manager;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.example.coronabd.other.ApplicationContext;
import com.example.coronabd.other.InternetConnection;
import com.example.coronabd.retorfit.API;
import com.example.coronabd.retorfit.RetrofitInstance;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiDataManager extends InternetConnection {

    private static MultipartBody.Part imageBody;
    private static RequestBody bidIdBody;
    private static RequestBody remark;
    private static API api = RetrofitInstance.getApi();
    private static Context context = ApplicationContext.getContext();
}


