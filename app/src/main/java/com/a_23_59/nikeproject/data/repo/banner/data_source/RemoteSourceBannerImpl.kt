package com.a_23_59.nikeproject.data.repo.banner.data_source

import com.a_23_59.nikeproject.data.repo.banner.Banner
import com.a_23_59.nikeproject.services.http.ApiService
import io.reactivex.rxjava3.core.Single

class RemoteSourceBannerImpl(private val apiService: ApiService) : RemoteSourceBanner {
    override fun getBanners(): Single<List<Banner>> {

        return apiService.getBanners()
    }

}