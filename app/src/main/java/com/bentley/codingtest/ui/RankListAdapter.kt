package com.bentley.codingtest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.bentley.codingtest.data.model.Rank
import com.bentley.codingtest.databinding.ItemRankBinding

class RankListAdapter(private val rankList: List<Rank>) :
    RecyclerView.Adapter<RankListAdapter.RankListViewHolder>() {

    private lateinit var bindingItem: ItemRankBinding

    inner class RankListViewHolder(private val binding: ItemRankBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Rank) {
            binding.apply {
                tvRank.text = item.rank.toString()
                ivProfileImg.load(item.profileImg) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
                tvNickname.text = item.nickName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        bindingItem = ItemRankBinding.inflate(layoutInflater)
        return RankListViewHolder(bindingItem)
    }

    override fun onBindViewHolder(holder: RankListViewHolder, position: Int) {
        val item = rankList[position]
        holder.bind(item)
    }

    override fun getItemCount() = rankList.size
}
