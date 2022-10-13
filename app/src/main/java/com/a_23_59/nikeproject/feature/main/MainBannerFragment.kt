package com.a_23_59.nikeproject.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.a_23_59.nikeproject.R
import com.a_23_59.nikeproject.common.EXTRA_KEY_DATA
import com.a_23_59.nikeproject.data.repo.banner.Banner
import com.a_23_59.nikeproject.services.imageloading.ImageLoadingService
import org.koin.android.ext.android.inject

class MainBannerFragment : Fragment() {

    private val imageLoadingService: ImageLoadingService by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val imageView = inflater.inflate(R.layout.banner_fragment, container, false) as ImageView

        val currentBanner: Banner? = requireArguments().getParcelable(EXTRA_KEY_DATA)

        context?.let {nonNullContext -> imageLoadingService.load(nonNullContext, currentBanner!!.image,imageView) }

        return imageView
    }

    companion object {

        fun newInstance(banner: Banner): MainBannerFragment {

            val bundle = Bundle()

            val bannerFragment = MainBannerFragment()

            bundle.putParcelable(EXTRA_KEY_DATA, banner)

            bannerFragment.arguments = bundle

            return bannerFragment
        }
    }
}