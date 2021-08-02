package com.alkowsartech.pexelphotos.repository

import com.alkowsartech.pexelphotos.data.remote.ApiService
import com.alkowsartech.pexelphotos.data.remote.model.Pexel
import com.alkowsartech.pexelphotos.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import timber.log.Timber
import javax.inject.Inject

@ActivityScoped
class RepositoryService @Inject constructor(private val apiService: ApiService) {

//    suspend fun fetchAll(pageNo: Int): Resource<Pexel> {
//        Resource.Loading("Loading", null)
//        return try {
//            val res = apiService.getPhotos(pageNo)
//            val result = res.body()
//            if (res.isSuccessful && result != null) {
//
//                Resource.Success(result)
//            } else {
//                Resource.Error("An error occurred", null)
//            }
//        } catch (e: Exception) {
//            Timber.d("Something went wrong")
//            Resource.Error(e.message.toString(), null)
//        }
//    }

    suspend fun getPhotos(pageNo: Int): Pexel {

        val res = apiService.getPhotos(pageNo)
        val result = res.body()
        return result!!
    }
}