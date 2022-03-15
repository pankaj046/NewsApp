package sharma.pankaj.newsnow.comman

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import sharma.pankaj.newsnow.R

@BindingAdapter("urlToImage")
fun urlToImage(view: ImageView, s: String?) {
    val options = RequestOptions
        .placeholderOf(R.drawable.ic_placeholder)
        .error(R.drawable.ic_placeholder)
    Glide.with(view).setDefaultRequestOptions(options)
        .load(s ?: "").into(view)
}