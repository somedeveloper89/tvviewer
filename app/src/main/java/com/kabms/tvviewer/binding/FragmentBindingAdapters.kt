package com.kabms.tvviewer.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class FragmentBindingAdapters(val fragment: Fragment) {
    @BindingAdapter("imageSrc")
    fun bindImage(imageView: ImageView, url: String) {
        Glide.with(fragment).load(url).into(imageView)
    }
}