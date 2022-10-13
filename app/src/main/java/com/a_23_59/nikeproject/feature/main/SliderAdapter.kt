package com.a_23_59.nikeproject.feature.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.a_23_59.nikeproject.data.repo.banner.Banner

class SliderAdapter(fragment: Fragment, private val banners: List<Banner>) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = banners.size

    override fun createFragment(position: Int): Fragment = MainBannerFragment.newInstance(banners[position])


}