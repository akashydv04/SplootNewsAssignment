package shyam.brekki.splootnewsassignment.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import shyam.brekki.splootnewsassignment.R
import shyam.brekki.splootnewsassignment.adapter.TopHeadlinesAdapter
import shyam.brekki.splootnewsassignment.databinding.FragmentTopHeadLinesBinding
import shyam.brekki.splootnewsassignment.factory.ViewModelFactory
import shyam.brekki.splootnewsassignment.model.TopHeadlinesResponse
import shyam.brekki.splootnewsassignment.network.ApiClient
import shyam.brekki.splootnewsassignment.network.ApiInterface
import shyam.brekki.splootnewsassignment.repository.TopHeadlinesRepository
import shyam.brekki.splootnewsassignment.viewmodel.TopHeadlinesViewModel

class TopHeadLines : Fragment(), TopHeadlinesAdapter.OnItemClickListener {

    lateinit var binding: FragmentTopHeadLinesBinding
    lateinit var viewModel : TopHeadlinesViewModel
    lateinit var adapter: TopHeadlinesAdapter
    var headlinesList: ArrayList<TopHeadlinesResponse.Article> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTopHeadLinesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val apiInterface = ApiInterface.getInstance()
        val repository = TopHeadlinesRepository(apiInterface)
        viewModel = ViewModelProvider(requireActivity(), ViewModelFactory(repository)).get(TopHeadlinesViewModel::class.java)
        viewModel.getTopHeadlines()
        observeResponse()
    }

    private fun observeResponse() {
        viewModel.articlesList.observe(viewLifecycleOwner,{
            if (it.articles.isNotEmpty()){
                binding.progress.visibility = View.GONE
                for (i in it.articles){
                    headlinesList.add(i)
                }
                adapter = TopHeadlinesAdapter(requireActivity(),headlinesList)
                val layoutManager = LinearLayoutManager(requireActivity())
                binding.headlinesRecycler.layoutManager = layoutManager
                binding.headlinesRecycler.adapter = adapter

                adapter.setOnITemClickListener(this)
            }
        })
    }

    companion object {
        private const val API_KEY = "47dadc6821f043d5983c9e34e1d0f6f7"
        private const val TAG = "headlines"
    }

    override fun onItemClick(url: String) {
        val bundle = Bundle()
        bundle.putString("url", url)
        findNavController().navigate(R.id.viewFullNewsFragment, bundle)
    }

}