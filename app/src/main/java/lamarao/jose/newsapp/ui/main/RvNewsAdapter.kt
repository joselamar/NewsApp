package lamarao.jose.newsapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import lamarao.jose.newsapp.R
import lamarao.jose.newsapp.database.entities.Article
import lamarao.jose.newsapp.databinding.NewsItemBinding

class NewsAdapter(private val clickListener: NewsClickListener) :
    ListAdapter<Article, NewsViewHolder>(ArticleDiffUtil()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
      NewsViewHolder(
          DataBindingUtil.inflate(
              LayoutInflater.from(parent.context), R.layout.news_item, parent, false))

  override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
    holder
        .binding
        .also {
          it.article = getItem(position)
          it.clickListener = clickListener
        }
        .executePendingBindings()
  }
}

class NewsViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)

class NewsClickListener(val clickListener: (article: Article) -> Unit) {
  fun onClick(article: Article) = clickListener(article)
}

class ArticleDiffUtil : DiffUtil.ItemCallback<Article>() {
  override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
    return oldItem.url == newItem.url
  }

  override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
    return oldItem == newItem
  }
}
