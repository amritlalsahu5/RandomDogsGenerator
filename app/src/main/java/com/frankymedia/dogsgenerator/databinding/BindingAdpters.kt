package com.frankymedia.dogsgenerator.databinding

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.frankymedia.dogsgenerator.glide.GlideApp
import com.frankymedia.dogsgenerator.viewmodel.GenerateDogsApiStatus


@BindingAdapter("imageUrl")
fun ImageView.setImg (imgUrl : String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).signature(Key{imgUrl}).fitCenter()
        GlideApp.with(context)
            .load(imgUri).apply(requestOptions)
            .into(this)

    }

}

@BindingAdapter("ShowProgressBar")
fun ProgressBar.show(status : GenerateDogsApiStatus?)
{
    when(status) {
        GenerateDogsApiStatus.ERROR -> { visibility = View.GONE}
        GenerateDogsApiStatus.DONE -> { visibility = View.GONE}
        GenerateDogsApiStatus.LOADING-> {visibility = View.VISIBLE}
    }
}

@BindingAdapter("NetworkError")
fun ProgressBar.networkError(isError : Boolean?) {

    if(isError == true){
        visibility = View.GONE
    }
}
