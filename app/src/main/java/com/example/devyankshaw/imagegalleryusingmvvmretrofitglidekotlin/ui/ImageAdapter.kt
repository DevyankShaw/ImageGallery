package com.example.devyankshaw.imagegalleryusingmvvmretrofitglidekotlin.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.devyankshaw.imagegalleryusingmvvmretrofitglidekotlin.R
import com.example.devyankshaw.imagegalleryusingmvvmretrofitglidekotlin.data.model.ImageUrls
import kotlinx.android.synthetic.main.recycler_layout.view.*

class ImageAdapter(private val activity: FragmentActivity?, private val imageUrlList: List<ImageUrls>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_layout,
            parent, false)

        return ImageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        if (activity != null) {
            Glide.with(activity)
                .load(imageUrlList[position].imageUrl)
                .into(holder.imageView)
        };

    }

    override fun getItemCount() = imageUrlList.size

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.image_view
    }
}