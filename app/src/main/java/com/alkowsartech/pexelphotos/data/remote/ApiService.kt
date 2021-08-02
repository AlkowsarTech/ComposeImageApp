package com.alkowsartech.pexelphotos.data.remote

import com.alkowsartech.pexelphotos.data.remote.model.Pexel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("curated")
   suspend fun getPhotos(
        @Query("page")  page : Int = 1,
        @Query("per_page") perPage: Int = 10
    ) : Response<Pexel>
}