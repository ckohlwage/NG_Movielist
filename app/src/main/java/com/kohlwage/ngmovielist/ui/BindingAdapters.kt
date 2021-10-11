package com.kohlwage.ngmovielist.ui

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.kohlwage.ngmovielist.models.ListWithNames


@BindingAdapter("name_list")
fun setList(textView: TextView, list: List<ListWithNames>?) {
    val builder = StringBuilder()
    list?.forEach {
        builder.append(it.name + "  ")
    }
    textView.text = builder.toString()
}

