package com.outerspace.kmoviedb.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding.view.RxView
import com.outerspace.dubsmashchallenge.R
import com.outerspace.dubsmashchallenge.api.Result
import com.outerspace.dubsmashchallenge.databinding.ResponseItemBinding

class ResponseAdapter: RecyclerView.Adapter<ResponseAdapter.ResponseViewHolder>() {
    private val resultList: MutableList<Result> = mutableListOf()

    lateinit var playSound: (String?) -> Unit

    fun loadResults(resultList: List<Result>) {
        this.resultList.clear()
        this.resultList.addAll(resultList)
        notifyDataSetChanged()
    }

    class ResponseViewHolder(val binder: ResponseItemBinding, val itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResponseViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binder: ResponseItemBinding = DataBindingUtil.inflate(inflater, R.layout.response_item, parent,false)
        return ResponseViewHolder(binder, binder.root)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: ResponseViewHolder, position: Int) {
        val result = resultList[position]
        holder.binder.result = result
        val url = result.data?.url
        RxView.clicks(holder.binder.playback).subscribe() {_ -> playSound(url)}
    }
}