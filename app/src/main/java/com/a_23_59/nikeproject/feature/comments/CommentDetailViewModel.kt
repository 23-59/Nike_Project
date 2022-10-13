package com.a_23_59.nikeproject.feature.comments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.a_23_59.nikeproject.common.CustomViewModel
import com.a_23_59.nikeproject.data.repo.comment.Comment
import com.a_23_59.nikeproject.data.repo.comment.detail.CommentDetailRepository
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CommentDetailViewModel(productId: Int, commentDetailRepository: CommentDetailRepository) :
    CustomViewModel() {
    val commentsList = MutableLiveData<List<Comment>>()

    init {
        progressBarLiveData.value = true

        commentDetailRepository.getAllComments(productId)
            .subscribeOn(Schedulers.io())
            .doFinally { progressBarLiveData.postValue(false) }
            .subscribe(object : SingleObserver<List<Comment>> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<Comment>) {

                    commentsList.postValue(t)


                }

                override fun onError(e: Throwable) {
                    Log.e("error", "onError: ", e)
                }
            })
    }


}