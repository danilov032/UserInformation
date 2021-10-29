package com.example.userinformation.data.api

import com.example.userinformation.data.responses.UserResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET(GET_QUERY)
    fun getListUsers(): Single<List<UserResponse>>

    companion object Factory {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://firebasestorage.googleapis.com")
                .build()

            return retrofit.create(ApiService::class.java);
        }

        private const val GET_QUERY = "/v0/b/candidates--questionnaire.appspot.com/o/users.json?alt=media&token=e3672c23-b1a5-4ca7-bb77-b6580d75810c"
    }
}