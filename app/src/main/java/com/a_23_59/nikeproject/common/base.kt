package com.a_23_59.nikeproject.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a_23_59.nikeproject.R
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.lang.IllegalStateException

abstract class NikeFragment : Fragment(), NikeView {

    override val rootView: CoordinatorLayout?
        get() = view as CoordinatorLayout

    override val mContext: Context?
        get() = context
}

abstract class NikeActivity : AppCompatActivity(), NikeView {

    override val rootView: CoordinatorLayout?
        get(){
            val viewGroup=window.decorView.findViewById(android.R.id.content) as ViewGroup

            if (viewGroup !is CoordinatorLayout){
              viewGroup.children.forEach {
                  if (it is CoordinatorLayout)
                      return it

              }
                throw IllegalStateException("item must be instance of CoordinatorLayout")
            }
            else
                return viewGroup

        }

    override val mContext: Context?
        get() = this

}

abstract class CustomViewModel : ViewModel(){

    val compositeDisposable=CompositeDisposable()

    val progressBarLiveData=MutableLiveData<Boolean>()

    val errorLiveData=MutableLiveData<Throwable>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

interface NikeView {

    val rootView:CoordinatorLayout?

    val mContext:Context?

    fun mSetProgressIndicator(mustShow: Boolean){

        rootView?.let { root ->

            mContext?.let { context ->

                var loadingView=root.findViewById<View>(R.id.progressBarLayout)

                if (loadingView==null && mustShow){

                    loadingView=LayoutInflater.from(context).inflate(R.layout.loading_layout,root,false)

                    root.addView(loadingView)

                }

                loadingView?.visibility= if (mustShow) View.VISIBLE else View.INVISIBLE

            }
        }

    }
}