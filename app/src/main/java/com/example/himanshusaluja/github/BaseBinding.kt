package com.example.himanshusaluja.github

import android.databinding.BindingAdapter
import android.view.View
import android.widget.Button

class BaseBinding {
    companion object {
        @JvmStatic
        @BindingAdapter("onClick")
        fun bindClick(v: Button, function: () -> Unit) {
            v.setOnClickListener({
                function()
            })
        }

        @JvmStatic
        @BindingAdapter("visible")
        fun bindVisible(v: View, boolean: Boolean) {
           if(boolean){
               v.visibility = View.VISIBLE
           }else{
               v.visibility =View.GONE
           }
        }
    }
}