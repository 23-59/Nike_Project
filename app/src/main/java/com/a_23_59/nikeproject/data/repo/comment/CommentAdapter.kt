package com.a_23_59.nikeproject.data.repo.comment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.a_23_59.nikeproject.R

class CommentAdapter(val show_All_Items: Boolean = false) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    var comments = ArrayList<Comment>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.comments_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindComments(comments[position])
    }

    override fun getItemCount(): Int {
        return if (comments.size > 3 && !show_All_Items) 3 else comments.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvTitle: TextView = itemView.findViewById(R.id.tv_comment_title)

        private val tvDescription: TextView = itemView.findViewById(R.id.tv_comment_description)

        private val tvEmail: TextView = itemView.findViewById(R.id.tv_comment_email)

        private val tvDate: TextView = itemView.findViewById(R.id.tv_comment_date)

        private val divider: View = itemView.findViewById(R.id.divider)

        fun bindComments(comment: Comment) {

            tvTitle.text = comment.title

            tvEmail.text = comment.author.email

            tvDescription.text = comment.content

            tvDate.text = comment.date

            divider.visibility =
                if (adapterPosition == 2 && !show_All_Items) View.GONE else View.VISIBLE

        }
    }

}


