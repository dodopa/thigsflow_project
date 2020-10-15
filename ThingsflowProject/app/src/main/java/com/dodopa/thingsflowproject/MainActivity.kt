package com.dodopa.thingsflowproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dodopa.thingsflowproject.adapter.SearchIssuesAdapter
import com.dodopa.thingsflowproject.databinding.ActivityMainBinding
import com.dodopa.thingsflowproject.viewholder.IssueListItemViewHolder

class MainActivity : AppCompatActivity(), SearchDialogFunc, PagingScrollListener.Fetch {

    companion object {
        const val ORGANIZATION_NAME = "dodopa"
        const val PAGING_SIZE = 10
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var searchIssuesAdapter: SearchIssuesAdapter
    private lateinit var pagingScrollListener: PagingScrollListener<IssueListItemViewHolder>

    override var searchDialog: SearchDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        supportActionBar?.apply {
            title = ORGANIZATION_NAME
        }

        initSearchUI()
        initSwipeRefreshLayout()
        initRecycler()
        initObserve()
    }

    private fun initSearchUI() {
        binding.tvBtnRepoInputMain.setOnClickListener { showSearchDialog() }
    }

    private fun initSwipeRefreshLayout() {
        binding.srlMain.setOnRefreshListener {
            if (mainViewModel.searchRepoName.value == null) {
                binding.srlMain.isRefreshing = false
                return@setOnRefreshListener
            }
            pagingScrollListener.refresh()
        }
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

            pagingScrollListener = PagingScrollListener(
                linearLayoutManager = layoutManager as LinearLayoutManager,
                adapter = searchIssuesAdapter,
                fetchCallback = this@MainActivity,
                pagingSize = PAGING_SIZE
            )
            addOnScrollListener(pagingScrollListener)
        }
    }

    override fun fetch(
        nextPage: Int,
        pagingSize: Int,
        successCallback: (isResultNullOrEmpty: Boolean) -> Unit
    ) {
        val repoName = mainViewModel.searchRepoName.value ?: return

        mainViewModel.getIssueListFromRepo(
            ORGANIZATION_NAME,
            repoName,
            nextPage,
            pagingSize,
            succeedCallback = { isEmpty ->
                if (isEmpty && nextPage == 1) {
                    showAlertDialog("결과가 없습니다.")
                }
                hideSearchDialog()
                successCallback(isEmpty)
            },
            errorCallback = {
                showAlertDialog("조회할 수 없습니다.")
            })
    }

    private fun initObserve() {
        mainViewModel.fetchedIssueList.observe(this, Observer {
            if (binding.srlMain.isRefreshing) {
                searchIssuesAdapter.setItems(it)
            } else {
                searchIssuesAdapter.addItems(it)
            }

            binding.srlMain.isRefreshing = false
        })
        mainViewModel.searchRepoName.observe(this, Observer {
            supportActionBar?.apply {
                title = "$ORGANIZATION_NAME / $it"
            }
        })
    }

    override fun onClickedOkSearchDialog(text: String) {
        searchIssuesAdapter.clear()
        mainViewModel.setSearchRepoName(text)
        pagingScrollListener.refresh()
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