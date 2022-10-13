package com.a_23_59.nikeproject.data.repo.comment.data_source

import com.a_23_59.nikeproject.data.repo.comment.Comment
import com.a_23_59.nikeproject.services.http.ApiService
import io.reactivex.rxjava3.core.Single

interface CommentDataSource {

    fun getAllComments(productId:Int): Single<List<Comment>>

    fun insert(): Single<Comment>
}