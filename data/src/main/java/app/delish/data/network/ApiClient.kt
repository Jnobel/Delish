package app.delish.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import app.delish.data.BuildConfig

object ApiClient {

    private val client = OkHttpClient.Builder()
        .addInterceptor(CommonHeaderInterceptor())
        .build()

    fun create(baseUrl: String): SpoonacularApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)  // Pass the base URL as a parameter
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(SpoonacularApiService::class.java)
    }
}
