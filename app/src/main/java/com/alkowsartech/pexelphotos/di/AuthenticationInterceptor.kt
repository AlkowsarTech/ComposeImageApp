package com.alkowsartech.pexelphotos.di

import com.alkowsartech.pexelphotos.utils.API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Singleton

@Singleton
class AuthenticationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
            .method(request.method, request.body)
            .header("Authorization", API_KEY)
        return chain.proceed(builder.build())
    }

}