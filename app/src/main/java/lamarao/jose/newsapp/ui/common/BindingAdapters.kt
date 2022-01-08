@file:Suppress("unused")

package lamarao.jose.newsapp.ui.common

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat
import java.util.Locale
import lamarao.jose.newsapp.BuildConfig
import lamarao.jose.newsapp.R

@BindingAdapter("LoadImg")
fun ImageView.bindImage(imgUrl: String?) {
  imgUrl?.let {
    val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
    Glide.with(this.context)
        .load(imgUri)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_no_image_available))
        .into(this)
  }
}

@BindingAdapter("Date")
fun TextView.handleDate(date: String) {
  val inputFormat: SimpleDateFormat =
      if (BuildConfig.sources == "cnn") {
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
      } else {
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
      }

  val outputFormat = SimpleDateFormat("dd-MM-yyyy • HH:mm", Locale.getDefault())
  val isoDate = inputFormat.parse(date)
  text = isoDate?.let { context.getString(R.string.published_at, outputFormat.format(isoDate)) }
}

@BindingAdapter("ArticleText")
fun TextView.handleArticleText(content: String?) {
  text =
      when {
        content.isNullOrEmpty() -> context.getString(R.string.not_available)
        else -> removeCopyrightAndCaption(content)
      }
}

fun removeCopyrightAndCaption(content: String): String {
  var count = 0
  return when {
    checkCondition(content) -> {
      val splitString = content.split("\n")
      splitString.forEach { if (checkCondition(it)) count++ }
      splitString.drop(count).joinToString("")
    }
    else -> content
  }
}

fun checkCondition(content: String): Boolean {
  return (content.contains("image caption", ignoreCase = true) ||
      content.contains("media caption", ignoreCase = true) ||
      content.contains("image copyright", ignoreCase = true) ||
      content.contains("image source", ignoreCase = true) ||
      content.contains("By"))
}
