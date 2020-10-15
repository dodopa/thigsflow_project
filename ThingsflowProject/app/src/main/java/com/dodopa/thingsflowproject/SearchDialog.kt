package com.dodopa.thingsflowproject

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.dodopa.thingsflowproject.databinding.LayoutSearchDialogBinding

class SearchDialog(
    context: Context,
    private val searchDialogFunc: SearchDialogFunc
) : Dialog(context) {

    private lateinit var binding: LayoutSearchDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutSearchDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 배경 투명처리
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 화면 밖 터치 시 사라지지 않게 설정
        setCancelable(false)

        binding.tvBtnCancelSearchDialog.setOnClickListener { hide() }
        binding.tvBtnOkSearchDialog.setOnClickListener {
            searchDialogFunc.onClickedOkSearchDialog(binding.etSearchDialog.text.toString())
        }
    }

    override fun hide() {
        binding.etSearchDialog.text.clear()
        super.hide()
    }
}