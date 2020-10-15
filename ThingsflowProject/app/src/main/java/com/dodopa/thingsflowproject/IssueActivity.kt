package com.dodopa.thingsflowproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dodopa.thingsflowproject.databinding.ActivityIssueBinding

class IssueActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_ORG = "EXTRA_ORG"
        private const val EXTRA_REPOSITORY = "EXTRA_REPOSITORY"
        private const val EXTRA_ISSUE_ID = "EXTRA_ISSUE_ID"

        fun startActivitySelf(context: Context, org: String, repoName: String, issueId: Int) =
            Intent(context, IssueActivity::class.java).apply {
                putExtra(EXTRA_ORG, org)
                putExtra(EXTRA_REPOSITORY, repoName)
                putExtra(EXTRA_ISSUE_ID, issueId)
                context.startActivity(this)
            }
    }

    private lateinit var binding: ActivityIssueBinding
    private lateinit var gitApiViewModel: GitApiViewModel
    private lateinit var org: String
    private lateinit var repoName: String

    private var issueNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIssueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gitApiViewModel = ViewModelProvider(this).get(GitApiViewModel::class.java)
        org = intent.getStringExtra(EXTRA_ORG) ?: ""
        repoName = intent.getStringExtra(EXTRA_REPOSITORY) ?: ""
        issueNumber = intent.getIntExtra(EXTRA_ISSUE_ID, 0)

        supportActionBar?.apply {
            title = "#$issueNumber"
        }

        gitApiViewModel.searchIssueByIssueNumber(org, repoName, issueNumber)

        initObserve()
    }

    private fun initObserve() {
        gitApiViewModel.searchedIssue.observe(this, Observer {
            binding.ivAvatarIssue.setAvatar(it.user.avatarUrl)
            binding.tvNicknameIssue.text = it.user.nickname
            binding.tvBodyIssue.text = it.body
        })
    }
}