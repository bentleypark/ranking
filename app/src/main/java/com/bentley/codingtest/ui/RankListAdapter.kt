package com.bentley.codingtest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.bentley.codingtest.R
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

                if (item.isMe) {
                    highlightBackgroundView.isVisible = true
                    highlightBackgroundView.setBackgroundResource(R.drawable.my_profile_highlighted)
                    tvRank.setTextColor(tvRank.context.resources.getColor(R.color.white, null))
                    tvNickname.setTextColor(
                        tvNickname.context.resources.getColor(
                            R.color.white,
                            null
                        )
                    )
                    tvDiffRank.apply {
                        text = item.diffRank.toString()
                        isVisible = true
                    }
                    ivRankArrow.isVisible = true
                    tvNickname.text = "나, ${item.age}"
                } else {
                    tvNickname.text = "${item.nickName}, ${item.age}"
                    highlightBackgroundView.setBackgroundColor(
                        highlightBackgroundView.context.resources.getColor(
                            android.R.color.transparent,
                            null
                        )
                    )
                    tvRank.setTextColor(tvRank.context.resources.getColor(R.color.rank_text, null))
                    tvNickname.setTextColor(
                        tvNickname.context.resources.getColor(
                            R.color.nickname_text,
                            null
                        )
                    )
                }
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
