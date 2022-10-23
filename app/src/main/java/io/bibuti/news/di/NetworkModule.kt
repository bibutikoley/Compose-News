package io.bibuti.news.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.bibuti.news.data.remote.NetworkInterceptor
import io.bibuti.news.data.remote.NetworkService
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

@Module(includes = [AppModule::class])
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesBaseURL(): String = "" // BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun providesConverterFactory(gson: Gson?): Converter.Factory =
        GsonConverterFactory.create(gson ?: Gson())

    @Provides
    @Singleton
    fun providesDataRequestInterceptor() = HttpLoggingInterceptor { message ->
        Timber.i(message)
    }.apply {
//        level = if (BuildConfig.DEBUG)
//            HttpLoggingInterceptor.Level.BODY
//        else
        HttpLoggingInterceptor.Level.BASIC
    }

    @Provides
    @Singleton
    fun providesSupportClient(
        networkInterceptor: NetworkInterceptor,
        dataRequestInterceptor: HttpLoggingInterceptor,
        // tokenAuthenticator: TokenAuthenticator,
    ) = OkHttpClient()
        .newBuilder()
        .addInterceptor(networkInterceptor)
        .addInterceptor(dataRequestInterceptor)
        // .authenticator(tokenAuthenticator)
        .connectTimeout(1, TimeUnit.MINUTES)
        .callTimeout(1, TimeUnit.MINUTES)
        .build()

    @Provides
    @Singleton
    fun providesRetrofitInstance(
        baseUrl: String,
        supportClient: OkHttpClient,
        converter: Converter.Factory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(converter)
        .client(supportClient)
        .build()

    @Provides
    @Singleton
    fun providesNetworkService(networkBuilder: Retrofit): NetworkService =
        networkBuilder.create(NetworkService::class.java)
}
