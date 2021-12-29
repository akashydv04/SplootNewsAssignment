package shyam.brekki.splootnewsassignment.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import shyam.brekki.splootnewsassignment.repository.TopHeadlinesRepository
import shyam.brekki.splootnewsassignment.viewmodel.TopHeadlinesViewModel

class ViewModelFactory(private val topHeadlinesRepository: TopHeadlinesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TopHeadlinesViewModel::class.java)) {
            TopHeadlinesViewModel(this.topHeadlinesRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}