package sharma.pankaj.newsnow.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import sharma.pankaj.newsnow.R
import sharma.pankaj.newsnow.comman.NewsItemClickListener
import sharma.pankaj.newsnow.data.model.Article
import sharma.pankaj.newsnow.databinding.NewsItemLayoutBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsAdapter @Inject constructor() : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var list: MutableList<Article> = mutableListOf()
    private var listener: NewsItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val newsBinding: NewsItemLayoutBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.news_item_layout, parent, false)
        return ViewHolder(newsBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(list[holder.adapterPosition])
        holder.itemView.setOnClickListener { listener?.onItemLister(list[holder.adapterPosition].url) }
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(private val binding: NewsItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(data: Article) {
            binding.model = data
        }
    }

    fun updateNews(list: MutableList<Article>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun addListener(listener: NewsItemClickListener) {
        this.listener = listener
    }
}