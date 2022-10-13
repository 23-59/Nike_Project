package com.a_23_59.nikeproject.data.repo.banner.data_source

import com.a_23_59.nikeproject.data.repo.banner.Banner
import io.reactivex.rxjava3.core.Single

interface RemoteSourceBanner {

    fun getBanners():Single<List<Banner>>
}