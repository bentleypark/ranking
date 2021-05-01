package com.bentley.codingtest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
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
        }
    }
}