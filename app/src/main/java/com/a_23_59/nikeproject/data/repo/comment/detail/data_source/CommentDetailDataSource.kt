package com.a_23_59.nikeproject.data.repo.comment.detail.data_source

import com.a_23_59.nikeproject.data.repo.comment.Comment
import io.reactivex.rxjava3.core.Single

interface CommentDetailDataSource {

    fun getAllComments(productId:Int): Single<List<Comment>>

    fun insert(): Single<Comment>
}