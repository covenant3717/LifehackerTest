package com.covenant.lifehackertest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.covenant.lifehackertest.R
import com.covenant.lifehackertest.model.Post
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_post.view.*

/**
 * Created by Evgeny Kuksov 02.03.2020
 */

class RVAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listData = mutableListOf<Post>()

    //==============================================================================================

    override fun getItemCount() = listData.size

    override fun getItemViewType(position: Int) = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PostHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PostHolder -> holder.bind(listData[position])
        }
    }

    fun setData(list: MutableList<Post>){
        if (list.isNotEmpty()){
            listData = list
            notifyDataSetChanged()
        }
    }

    //==============================================================================================

    private inner class PostHolder(private val item: View) : RecyclerView.ViewHolder(item) {
        private val img = itemView.itemImg
        private val tvAuthor = itemView.itemTvAauthor
        private val tvTittle = itemView.itemTvTitle

        fun bind(current: Post) {
            // image
            Picasso.get()
                .load(current.cat_cover.sizes.mobile)
                .fit()
                .centerCrop()
                .placeholder(img.context.resources.getDrawable(R.drawable.placeholder))
                .error(img.context.resources.getDrawable(R.drawable.placeholder))
                .into(img)

            // author
            tvAuthor.text = current.author_name

            // tittle
            tvTittle.text = current.title.rendered

        }

    }

}
