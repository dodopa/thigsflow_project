package com.dodopa.thingsflowproject

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dodopa.thingsflowproject.adapter.SearchIssuesAdapter
import com.dodopa.thingsflowproject.databinding.ActivityMainBinding
import com.dodopa.thingsflowproject.model.entity.Issue

class MainActivity : AppCompatActivity(), SearchDialogFunc {

    companion object {
        const val ORGANIZATION_NAME = "dodopa"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var gitApiViewModel: GitApiViewModel
    private lateinit var searchIssuesAdapter: SearchIssuesAdapter

    override var searchDialog: SearchDialog? = null

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
        binding.tvBtnRepoInputMain.setOnClickListener { showSearchDialog() }
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

    override fun onClickedOkSearchDialog(text: String) {
        searchIssuesAdapter.clear()
        gitApiViewModel.getIssueListFromRepo(ORGANIZATION_NAME, text, succeedCallback = { isEmpty ->
            if (isEmpty) {
                showAlertDialog("결과가 없습니다.")
            }
            hideSearchDialog()
        }, errorCallback = {
            showAlertDialog("조회할 수 없습니다.")
        })
    }

    override fun showSearchDialog() {
        if (searchDialog == null) {
            searchDialog = SearchDialog(this, this)
        }
        searchDialog!!.show()
    }

    override fun hideSearchDialog() {
        searchDialog?.hide()
    }

    private fun showAlertDialog(message: String) = AlertDialog.Builder(this)
        .setMessage(message)
        .setPositiveButton("확인") { _, _ -> }
        .create()
        .show()
}