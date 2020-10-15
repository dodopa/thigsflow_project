package com.dodopa.thingsflowproject.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dodopa.thingsflowproject.model.entity.Issue
import com.dodopa.thingsflowproject.viewholder.IssueListItemViewHolder

class SearchIssuesAdapter(
    private val items: ArrayList<Issue> = arrayListOf(),
    private val onClicked: ((issue: Issue) -> Unit)? = null
) : RecyclerView.Adapter<IssueListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueListItemViewHolder =
        IssueListItemViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: IssueListItemViewHolder, position: Int) =
        holder.bindTo(position, items[position], onClicked)

    fun addItems(others: List<Issue>) {
        val startPos = items.size
        items.addAll(others)
        notifyItemRangeInserted(startPos, others.size)
    }

    fun clear() {
        notifyItemRangeRemoved(0, items.size)
        items.clear()
    }
}