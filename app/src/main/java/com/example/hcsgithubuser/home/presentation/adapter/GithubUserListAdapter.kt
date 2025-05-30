package com.example.hcsgithubuser.home.presentation.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.core.common.presentation.util.Const
import com.example.core.network.response.GithubUserDto
import com.example.hcsgithubuser.databinding.ItemGithubUserBinding

class GithubUserListAdapter() : RecyclerView.Adapter<GithubUserListAdapter.GithubUserViewHolder>() {
    private var list: MutableList<GithubUserDto> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun refreshList(newList: List<GithubUserDto>) {
        list = newList.toMutableList()
        notifyDataSetChanged()
    }

    fun addItems(newList: List<GithubUserDto>) {
        list.addAll(newList)
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

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
        holder.bind(list[position])
    }


    inner class GithubUserViewHolder(private val binding: ItemGithubUserBinding) :
        ViewHolder(binding.root) {
        fun bind(githubUser: GithubUserDto) {
            Glide
                .with(itemView.context)
                .load(githubUser.avatarUrl)
                .circleCrop()
                .into(binding.imageView)
            binding.tvUsername.text = githubUser.login
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, Class.forName("com.example.detail.presentation.DetailActivity"))
                intent.putExtra(Const.USERNAME_EXTRA, githubUser.login.orEmpty())
                intent.putExtra(Const.USER_ID_EXTRA, githubUser.id ?: 1)
                binding.root.context.startActivity(intent)
            }
        }
    }
}