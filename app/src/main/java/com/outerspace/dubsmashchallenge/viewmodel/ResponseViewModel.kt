package com.outerspace.kmoviedb.viewmodel

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.outerspace.dubsmashchallenge.R
import com.outerspace.dubsmashchallenge.api.Result
import com.outerspace.dubsmashchallenge.model.ResponseModel


class ResponseViewModel : ViewModel() {
    private lateinit var owner: LifecycleOwner
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: ResponseAdapter
    private lateinit var progress: ProgressBar
    private lateinit var appContext: Context

    private val mutableResultList: MutableLiveData<List<Result>> = MutableLiveData()
    private val mutableError: MutableLiveData<Throwable> = MutableLiveData()

    class Builder {
        private val instance = ResponseViewModel()
        fun owner(owner: LifecycleOwner): Builder {
            instance.owner = owner; return this
        }

        fun progress(progress: ProgressBar): Builder {
            instance.progress = progress; instance.appContext =
                (progress.parent as ViewGroup).context.applicationContext; return this
        }

        fun recycler(recycler: RecyclerView): Builder {
            instance.recycler = recycler; return this
        }

        fun adapter(adapter: ResponseAdapter): Builder {
            instance.adapter = adapter; instance.adapter.playSound =
                instance::playSoundUrl; return this
        }

        fun build(): ResponseViewModel {
            instance.mutableResultList.observe(instance.owner,
                Observer { resultList -> instance.showList(resultList) })

            instance.mutableError.observe(instance.owner,
                Observer { throwable -> instance.showError(throwable) })

            return instance
        }
    }


    private fun showList(resultList: List<Result>) {
        adapter.loadResults(resultList)
        if (resultList.size == 0) {
            val noRecordsMessage = appContext.getString(R.string.found_no_records)
            val toast = Toast.makeText(appContext, noRecordsMessage, Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
            toast.show()
        }
        progress.visibility = View.GONE
    }

    private fun showError(throwable: Throwable) {
        val networkErrorMessage = appContext.getString(R.string.network_error)
        val toast = Toast.makeText(appContext, networkErrorMessage, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.show()
        progress.visibility = View.GONE
    }

    // Todo: Please fix, this is overlaping
    // several MediaPlayers overlap when the user taps
    // multiple sound tracks.
    fun playSoundUrl(url: String?) {
        if (url != null) {
            val mediaPlayer: MediaPlayer = MediaPlayer()

            mediaPlayer.setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            mediaPlayer.setDataSource(url)
            mediaPlayer.prepare()
            mediaPlayer.start()
        }
    }

    fun searchTerm(inputTerm: TextView, term: CharSequence) {
        if (term.contains("\n")) {
            inputTerm.text = term.toString().replace("\n", "")
            progress.visibility = View.VISIBLE
            ResponseModel.fetchTerm(term.toString(), mutableResultList, mutableError)
        }
    }
}



