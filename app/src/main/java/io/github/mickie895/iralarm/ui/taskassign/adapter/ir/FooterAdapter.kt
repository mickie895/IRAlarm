package io.github.mickie895.iralarm.ui.taskassign.adapter.ir

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class FooterAdapter(@LayoutRes private val resource: Int, private val context: Context, private val listener: OnClickListener): RecyclerView.Adapter<FooterAdapter.FooterViewHolder>() {
    class FooterViewHolder(view: View): ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FooterViewHolder {
        val view = LayoutInflater.from(context).inflate(resource, parent, false)
        view.setOnClickListener(listener)
        return FooterViewHolder(view)
    }

    override fun getItemCount(): Int
    =1

    override fun onBindViewHolder(holder: FooterViewHolder, position: Int) {
    }
}