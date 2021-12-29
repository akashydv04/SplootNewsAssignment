package shyam.brekki.splootnewsassignment.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import shyam.brekki.splootnewsassignment.databinding.ItemTopHeadlinesCardBinding
import shyam.brekki.splootnewsassignment.model.TopHeadlinesResponse

class TopHeadlinesAdapter(
    private val context: Context,
    private val list: ArrayList<TopHeadlinesResponse.Article>
) : RecyclerView.Adapter<TopHeadlinesAdapter.ViewHolder> (){

    lateinit var mListener: OnItemClickListener

    fun setOnITemClickListener(listener: OnItemClickListener){
        mListener = listener
    }

    inner class ViewHolder(private val binding: ItemTopHeadlinesCardBinding):
        RecyclerView.ViewHolder(binding.root) {

            fun setData(article: TopHeadlinesResponse.Article) {
                binding.headlineTxt.text = article.title
                Glide.with(context).load(article.urlToImage).into(binding.headlineImageView)

                binding.viewNewsDetail.setOnClickListener {
                    mListener.onItemClick(article.url)
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTopHeadlinesCardBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.setData(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener{
        fun onItemClick(url : String)
    }
}