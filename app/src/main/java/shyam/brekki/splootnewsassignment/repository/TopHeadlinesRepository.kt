package shyam.brekki.splootnewsassignment.repository

import retrofit2.Call
import retrofit2.Response
import shyam.brekki.splootnewsassignment.model.TopHeadlinesResponse
import shyam.brekki.splootnewsassignment.network.ApiInterface

class TopHeadlinesRepository(private val apiInterface: ApiInterface) {
    suspend fun getTopHeadlines(): Response<TopHeadlinesResponse?>? =
        apiInterface.getLatestNews("us",
            "business",
            "47dadc6821f043d5983c9e34e1d0f6f7")
//    {
//        return apiInterface.getLatestNews(
//            "us",
//            "business",
//            "47dadc6821f043d5983c9e34e1d0f6f7")
//    }

}