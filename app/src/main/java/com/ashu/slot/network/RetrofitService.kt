package com.ashu.slot.network

import com.ashu.slot.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService private constructor() {

    val retrofit: Retrofit

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val builder = Retrofit.Builder()
            .baseUrl(Constants.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        retrofit = builder.client(httpClient).build()
    }

    companion object {
        private var self: RetrofitService? = null

        val instance: RetrofitService
            get() {
                if (self == null) {
                    synchronized(RetrofitService::class.java) {
                        if (self == null) {
                            self =
                                RetrofitService()
                        }
                    }
                }
                return self!!
            }
    }

}