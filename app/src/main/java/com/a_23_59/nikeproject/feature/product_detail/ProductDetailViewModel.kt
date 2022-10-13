package com.a_23_59.nikeproject.feature.product_detail

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.a_23_59.nikeproject.common.CustomViewModel
import com.a_23_59.nikeproject.common.EXTRA_KEY_DATA
import com.a_23_59.nikeproject.data.repo.comment.Comment
import com.a_23_59.nikeproject.data.repo.comment.CommentRepository
import com.a_23_59.nikeproject.data.repo.product.Product
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ProductDetailViewModel(bundle: Bundle, commentRepository: CommentRepository) :
    CustomViewModel() {

    val singleProductLiveData = MutableLiveData<Product>()

    val commentsListLiveData = MutableLiveData<List<Comment>>()

    init {
        singleProductLiveData.value = bundle.getParcelable(EXTRA_KEY_DATA)

        progressBarLiveData.value = true

        commentRepository.getAllComments(singleProductLiveData.value!!.id)
            .subscribeOn(Schedulers.io())
            .doFinally { progressBarLiveData.postValue(false) }
            .subscribe(object : SingleObserver<List<Comment>> {

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<Comment>) {
                    commentsListLiveData.postValue(t)
                }

                override fun onError(e: Throwable) {
                    Log.e("error", "onError: ", e)
                }
            })


    }

}