package com.dodopa.thingsflowproject.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dodopa.thingsflowproject.model.data.IssueListData
import com.dodopa.thingsflowproject.model.entity.Issue
import com.dodopa.thingsflowproject.viewholder.EmptyViewHolder
import com.dodopa.thingsflowproject.viewholder.IssueListImageItemViewHolder
import com.dodopa.thingsflowproject.viewholder.IssueListItemViewHolder
import com.dodopa.thingsflowproject.viewholder.SmartViewHolder

class SearchIssuesAdapter(
    private val items: ArrayList<IssueListData> = arrayListOf(),
    private val onClickedIssue: ((issue: Issue) -> Unit)? = null,
    private val onClickedImage: ((url: String) -> Unit)? = null
) : RecyclerView.Adapter<SmartViewHolder>() {

    enum class ViewType(val num: Int) {
        ISSUE(0), IMAGE(1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmartViewHolder =
        when (viewType) {
            ViewType.ISSUE.num -> IssueListItemViewHolder(parent)
            ViewType.IMAGE.num -> IssueListImageItemViewHolder(parent)
            else -> EmptyViewHolder(parent)
        }

    override fun getItemViewType(position: Int): Int = items[position].viewType.num

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SmartViewHolder, position: Int) =
        when (getItemViewType(position)) {
            ViewType.ISSUE.num -> (holder as IssueListItemViewHolder).bindTo(
                items[position].issue!!,
                onClickedIssue
            )
            ViewType.IMAGE.num -> (holder as IssueListImageItemViewHolder).bindTo(
                items[position].imageUrl,
                onClickedImage
            )
            else -> {
                // EMPTY
            }
        }

    fun setItems(others: List<IssueListData>) {
        items.clear()
        items.addAll(others)
        notifyDataSetChanged()
    }

    fun addItems(others: List<IssueListData>) {
        val startPos = items.size
        items.addAll(others)
        notifyItemRangeInserted(startPos, others.size)
    }

    fun clear() {
        notifyItemRangeRemoved(0, items.size)
        items.clear()
    }
}