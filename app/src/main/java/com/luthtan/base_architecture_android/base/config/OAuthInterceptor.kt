package com.luthtan.base_architecture_android.base.config

import com.luthtan.base_architecture_android.base.util.SessionHelper
import okhttp3.Interceptor
import okhttp3.Response

class OAuthInterceptor(private val tokenType: String, private val sessionHelper: SessionHelper) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        return if (!sessionHelper.getString(SessionHelper.ACCESS_TOKEN_KEY).isNullOrEmpty()) {
            request =
                request.newBuilder()
                    .header("Authorization", "$tokenType ${sessionHelper.getString(SessionHelper.ACCESS_TOKEN_KEY)}")
                    .build()

            chain.proceed(request)
        } else {
            chain.proceed(request)
        }

    }

    companion object {
        const val BEARER = "Bearer"
    }
}