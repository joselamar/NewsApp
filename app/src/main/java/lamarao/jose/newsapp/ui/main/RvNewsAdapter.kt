package lamarao.jose.newsapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lamarao.jose.newsapp.R
import lamarao.jose.newsapp.database.Article
import lamarao.jose.newsapp.databinding.NewsItemBinding

class RvNewsAdapter (private val clickListener: NewsClickListener) : RecyclerView.Adapter<NewsViewHolder>() {

    var data: List<Article> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val withDataBinding : NewsItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            NewsViewHolder.LAYOUT,
            parent,
            false)
        return NewsViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.article = data[position]
            it.clickListener = clickListener
        }
    }

    override fun getItemCount() = data.size

}

class NewsViewHolder (val viewDataBinding: NewsItemBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.news_item
    }
}

class NewsClickListener(val clickListener: (article: Article) -> Unit) {
    fun onClick(article: Article) = clickListener(article)
}
