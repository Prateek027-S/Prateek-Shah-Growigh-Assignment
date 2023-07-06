package com.prateekshah.prateekshahgrowghassignment.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://picsum.photos"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ImagesApiService {
    @GET ("v2/list")
    suspend fun getPhotos(@Query("page") pageNum: Int,
                          @Query("limit") amount: Int): List<ImageResponse>
}

object ImageApi{
    val retrofitService: ImagesApiService by lazy {
        retrofit.create(ImagesApiService::class.java)
    }
}