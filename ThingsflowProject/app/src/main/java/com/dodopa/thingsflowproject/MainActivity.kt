package com.dodopa.thingsflowproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dodopa.thingsflowproject.adapter.SearchIssuesAdapter
import com.dodopa.thingsflowproject.databinding.ActivityMainBinding
import com.dodopa.thingsflowproject.model.entity.Issue

class MainActivity : AppCompatActivity() {

    companion object {
        const val ORGANIZATION_NAME = "dodopa"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var gitApiViewModel: GitApiViewModel
    private lateinit var searchIssuesAdapter: SearchIssuesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gitApiViewModel = ViewModelProvider(this).get(GitApiViewModel::class.java)

        supportActionBar?.apply {
            title = ORGANIZATION_NAME
        }

        initSearchUI()
        initRecycler()
        initObserve()

        // test 코드
        gitApiViewModel.getIssueListFromRepo(ORGANIZATION_NAME, "thigsflow_project")
    }

    private fun initSearchUI() {
        binding.tvBtnRepoInputMain.setOnClickListener { }
    }

    private fun initRecycler() {
        binding.rvMain.apply {
            searchIssuesAdapter = SearchIssuesAdapter(onClicked = {
                IssueActivity.startActivitySelf(
                    this@MainActivity,
                    ORGANIZATION_NAME,
                    it.repositoryName,
                    it.number
                )
            })
            adapter = searchIssuesAdapter
        }
    }

    private fun initObserve() {
        gitApiViewModel.fetchedIssueList.observe(this, Observer {
            searchIssuesAdapter.addItems(it)
        })
    }
}