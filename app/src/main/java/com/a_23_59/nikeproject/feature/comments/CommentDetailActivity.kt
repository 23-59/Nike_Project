package com.a_23_59.nikeproject.feature.comments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.a_23_59.nikeproject.R
import com.a_23_59.nikeproject.common.EXTRA_KEY_ID
import com.a_23_59.nikeproject.common.NikeActivity
import com.a_23_59.nikeproject.common.view.CustomToolbar
import com.a_23_59.nikeproject.data.repo.comment.Comment
import com.a_23_59.nikeproject.data.repo.comment.CommentAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CommentDetailActivity : NikeActivity() {

    /*
    TASK:send product id to ViewModel of this activity
    DATA ORIGIN: ProductDetailActivity
    DESTINATION: CommentDetailViewModel
     */
    private val commentDetailViewModel:CommentDetailViewModel by viewModel { parametersOf(
        intent.extras!!.getInt(
        EXTRA_KEY_ID)) }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_detail)

        val commentDetailRecyclerView:RecyclerView=findViewById(R.id.rv_comments_detail)


        commentDetailViewModel.commentsList.observe(this){

            val commentAdapter=CommentAdapter(true)

            commentAdapter.comments= it as ArrayList<Comment>

            commentDetailRecyclerView.layoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false)

            commentDetailRecyclerView.adapter=commentAdapter
        }

        commentDetailViewModel.progressBarLiveData.observe(this){

            mSetProgressIndicator(it)
        }

        findViewById<CustomToolbar>(R.id.commentListToolbar)
            .onBackButtonClickListener= View.OnClickListener {
                finish()
        }


    }


}