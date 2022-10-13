package com.a_23_59.nikeproject.data.repo.banner

import io.reactivex.rxjava3.core.Single

interface BannerRepository {

    fun getBanners():Single<List<Banner>>
}