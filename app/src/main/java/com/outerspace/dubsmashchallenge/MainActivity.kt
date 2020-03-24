package com.outerspace.dubsmashchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding.widget.RxCompoundButton
import com.jakewharton.rxbinding.widget.RxTextView
import com.outerspace.kmoviedb.viewmodel.ResponseAdapter
import com.outerspace.kmoviedb.viewmodel.ResponseViewModel
import com.outerspace.kmoviedb.viewmodel.ResponseViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import rx.Subscription

class MainActivity : AppCompatActivity() {

    lateinit var termSubscription: Subscription

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter: ResponseAdapter = ResponseAdapter()

        val factory = ResponseViewModelFactory.Builder()
            .owner(this)
            .progress(progress)
            .recycler(recycler)
            .adapter(adapter)
            .layoutManager(LinearLayoutManager(this))
            .build()

        val viewModel = ViewModelProvider(this, factory).get(ResponseViewModel::class.java)

        termSubscription =
            RxTextView.textChanges(termEditText).subscribe() { inputTerm ->
                viewModel.searchTerm(termEditText, inputTerm)
            }
    }

    override fun onStart() {
        super.onStart()

    }
}
