package com.a_23_59.nikeproject.data.repo.comment

import io.reactivex.rxjava3.core.Single

interface CommentRepository {

    fun getAllComments(productId:Int):Single<List<Comment>>

    fun insert():Single<Comment>
}