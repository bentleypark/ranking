package com.bentley.codingtest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.bentley.codingtest.R
import com.bentley.codingtest.data.DataState
import com.bentley.codingtest.data.model.RankingInfo
import com.bentley.codingtest.databinding.ActivityMainBinding
import com.bentley.codingtest.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel: RankViewModel by viewModels()
    private lateinit var listAdapter: RankListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupObserve()
//        viewModel.fetchInitialData()
    }

    private fun setupObserve() {
        viewModel.apply {
            rankInfo.observe(this@MainActivity, {
                when (it) {
                    is DataState.Success<RankingInfo> -> {
                        setupViews(it.data)
                        binding.progressCircular.isVisible = false
                    }
                    is DataState.Loading -> {
                        binding.progressCircular.isVisible = true
                    }
                }
            })
        }
    }

    private fun setupViews(initialData: RankingInfo) {
        binding.apply {
            listAdapter = RankListAdapter(initialData.totalRank)

            rvRankList.apply {
                setHasFixedSize(true)
                adapter = listAdapter
                addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            }

            tvLocation.text = initialData.location
            ivMyProfileImg.load(initialData.profileImg) {
                transformations(CircleCropTransformation())
            }
            tvMyRank.text = initialData.myRank.toString()
            tvRankInfo.text = "상위 ${initialData.percent}"
        }
    }
}