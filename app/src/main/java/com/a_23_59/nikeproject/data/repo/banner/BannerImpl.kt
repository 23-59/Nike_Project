package com.a_23_59.nikeproject.data.repo.banner

import com.a_23_59.nikeproject.data.repo.banner.data_source.RemoteSourceBanner
import io.reactivex.rxjava3.core.Single

class BannerImpl(private val remoteBanner: RemoteSourceBanner):BannerRepository {

    override fun getBanners(): Single<List<Banner>> {
       return remoteBanner.getBanners()
    }


}