package com.outerspace.kmoviedb.viewmodel

import android.widget.ProgressBar
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

class ResponseViewModelFactory private constructor(): ViewModelProvider.Factory {
    private lateinit var owner: LifecycleOwner
    private lateinit var recycler: RecyclerView
    private lateinit var progress: ProgressBar
    private lateinit var adapter: ResponseAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    class Builder {
        val instance: ResponseViewModelFactory = ResponseViewModelFactory()
        fun owner(owner: LifecycleOwner): Builder {
            instance.owner = owner
            return this
        }
        fun adapter(adapter: ResponseAdapter): Builder {
            instance.adapter = adapter
            return this
        }
        fun layoutManager(layoutManager : RecyclerView.LayoutManager): Builder {
            instance.layoutManager = layoutManager
            return this
        }
        fun recycler(recycler: RecyclerView): Builder {
            instance.recycler = recycler
            return this
        }
        fun progress(progress: ProgressBar): Builder {
            instance.progress = progress
            return this
        }

        fun build(): ResponseViewModelFactory {
            instance.recycler.layoutManager = instance.layoutManager
            instance.recycler.adapter = instance.adapter
            return instance
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ResponseViewModel.Builder()
            .owner(owner)
            .adapter(adapter)
            .recycler(recycler)
            .progress(progress)
            .build() as T
    }
}
