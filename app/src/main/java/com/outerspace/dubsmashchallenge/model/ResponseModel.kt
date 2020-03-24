package com.outerspace.dubsmashchallenge.model

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.outerspace.dubsmashchallenge.api.Quote
import com.outerspace.dubsmashchallenge.api.Result
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceDubSmash {
    @GET("1/search/entertainment/quote")
    fun smashApi(@Query("term") term: String) : Observable<Quote>
}

object ResponseModel {
    private val BASE_URL = "https://search-interview.dubsmash.com/"

    fun fetchTerm(term: String, mutableResultList: MutableLiveData<List<Result>>, mutableError: MutableLiveData<Throwable>) {
        val gson: Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val service : ServiceDubSmash = retrofit.create(ServiceDubSmash::class.java)

        val observable : Observable<Quote> = service.smashApi(term)

        observable
            .subscribeOn(Schedulers.io())
            .map{Quote -> Quote.results}
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Observer<List<Result>?> {
                override fun onComplete() {}
                override fun onSubscribe(d: Disposable) {}

                override fun onNext(resutList: List<Result>) {mutableResultList.value = resutList}

                override fun onError(throwble: Throwable) {mutableError.value = throwble}
            })
    }
    // .subscribe({quoteConsumer.accept(it)}, {errorConsumer.accept(it)})

}