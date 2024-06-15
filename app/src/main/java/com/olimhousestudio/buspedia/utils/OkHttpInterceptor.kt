package com.olimhousestudio.buspedia.utils

import com.olimhousestudio.buspedia.data.source.local.KotprefLocalStorage
import okhttp3.Interceptor
import okhttp3.Response

class OkHttpInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val token =
            if (KotprefLocalStorage.accessToken.isEmpty()) "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InpxY2Z2eHZ5eXFlcmlmb2xlaXRvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTY5NDU4NzYsImV4cCI6MjAzMjUyMTg3Nn0.bsgK7HSHg9T48zVtpZhYUsxVUTzO-2yn40KdOpGXN8s"
            else KotprefLocalStorage.accessToken

        val request = chain.request().newBuilder()
            .header(
                name = "apikey",
                value = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InpxY2Z2eHZ5eXFlcmlmb2xlaXRvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTY5NDU4NzYsImV4cCI6MjAzMjUyMTg3Nn0.bsgK7HSHg9T48zVtpZhYUsxVUTzO-2yn40KdOpGXN8s"
            )
            .header(
                name = "Authorization",
                value = "Bearer $token"
            )
            .build()
        return chain.proceed(request = request)
    }
}