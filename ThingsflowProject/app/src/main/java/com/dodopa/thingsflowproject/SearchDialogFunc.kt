package com.dodopa.thingsflowproject

interface SearchDialogFunc {

    var searchDialog: SearchDialog?

    fun onClickedOkSearchDialog(text: String)
    fun showSearchDialog()
    fun hideSearchDialog()
}