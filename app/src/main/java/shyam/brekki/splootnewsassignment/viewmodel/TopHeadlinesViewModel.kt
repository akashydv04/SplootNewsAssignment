package shyam.brekki.splootnewsassignment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import shyam.brekki.splootnewsassignment.model.TopHeadlinesResponse
import shyam.brekki.splootnewsassignment.repository.TopHeadlinesRepository

class TopHeadlinesViewModel(private val topHeadlinesRepository: TopHeadlinesRepository): ViewModel() {

    var articlesList: MutableLiveData<TopHeadlinesResponse> = MutableLiveData()

    fun getTopHeadlines() {
        viewModelScope.launch {
            val result = topHeadlinesRepository.getTopHeadlines()
            if (result != null) {
                if (result.isSuccessful){
                    if (result.code() == 200){
                        articlesList.postValue(result.body())
                    }
                }

            }
        }
    }
}