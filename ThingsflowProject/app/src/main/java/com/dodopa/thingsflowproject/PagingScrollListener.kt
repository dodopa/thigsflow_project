package com.dodopa.thingsflowproject

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PagingScrollListener<T : RecyclerView.ViewHolder>(
    private val linearLayoutManager: LinearLayoutManager,
    private val adapter: RecyclerView.Adapter<T>,
    private val fetchCallback: Fetch,
    private val pagingSize: Int = 10
) : RecyclerView.OnScrollListener() {

    interface Fetch {
        fun fetch(
            nextPage: Int,
            pagingSize: Int,
            successCallback: (isResultNullOrEmpty: Boolean) -> Unit
        )
    }

    private var isLoadable = true
    private var nextPage = 0

    init {
        fetchData()
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)

        // 데이터 로딩 시 return
        if (!isLoadable) {
            return
        }

        val position = linearLayoutManager.findLastCompletelyVisibleItemPosition()
        if (position >= adapter.itemCount - 2) {
            fetchData()
        }
    }

    private fun fetchData() {
        ++nextPage

        isLoadable = false
        fetchCallback.fetch(nextPage, pagingSize) {
            // it == result array list의 isNullOrEmpty()
            // 마지막 페이지를 알아내기 위해 검사
            isLoadable = !it
        }
    }

    fun refresh() {
        isLoadable = true
        nextPage = 0
        fetchData()
    }
}