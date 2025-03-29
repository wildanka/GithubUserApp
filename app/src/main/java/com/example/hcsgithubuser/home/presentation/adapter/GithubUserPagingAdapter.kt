package com.example.hcsgithubuser.home.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.hcsgithubuser.databinding.ItemGithubUserBinding
import com.example.hcsgithubuser.detail.presentation.DetailActivity
import com.example.hcsgithubuser.home.data.local.entity.GithubUserEntity

class GithubUserPagingAdapter() :
    PagingDataAdapter<GithubUserEntity, GithubUserPagingAdapter.GithubUserViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GithubUserEntity>() {
            override fun areItemsTheSame(
                oldItem: GithubUserEntity,
                newItem: GithubUserEntity
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: GithubUserEntity,
                newItem: GithubUserEntity
            ) = oldItem == newItem

        }
    }

    inner class GithubUserViewHolder(private val binding: ItemGithubUserBinding) :
        ViewHolder(binding.root) {
        fun bind(githubUser: GithubUserEntity?) {
            Glide
                .with(itemView.context)
                .load(githubUser?.avatarUrl)
                .circleCrop()
                .into(binding.imageView)
            binding.tvUsername.text = githubUser?.login

            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, DetailActivity::class.java)
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserViewHolder {
        return GithubUserViewHolder(
            ItemGithubUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
        val item = getItem(position)
        return holder.bind(item)
    }
}