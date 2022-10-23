package io.bibuti.news.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val modifiedRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ")
//        if (appStore.encryptedAccessToken.isEmpty()) {
//            modifiedRequest.removeHeader("Authorization")
//        }
        return chain.proceed(modifiedRequest.build())
    }
}
