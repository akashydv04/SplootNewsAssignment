package shyam.brekki.splootnewsassignment.network

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import shyam.brekki.splootnewsassignment.model.TopHeadlinesResponse

interface ApiInterface {
    @GET("top-headlines")
    suspend fun getLatestNews(
        @Query("country") country: String?,
        @Query("category") category: String?,
        @Query("apiKey") apiKey: String?
    ): Response<TopHeadlinesResponse?>?

    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"
        var retrofitService: ApiInterface? = null
        fun getInstance() : ApiInterface {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ApiInterface::class.java)
            }
            return retrofitService!!
        }

    }

}