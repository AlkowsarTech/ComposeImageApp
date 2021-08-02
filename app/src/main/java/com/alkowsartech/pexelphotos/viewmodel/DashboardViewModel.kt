package com.alkowsartech.pexelphotos.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alkowsartech.pexelphotos.data.remote.model.Photo
import com.alkowsartech.pexelphotos.data.remote.pagination.PhotoSource
import com.alkowsartech.pexelphotos.repository.RepositoryService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repositoryService: RepositoryService
) : ViewModel() {

    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    var photoList = mutableStateOf<List<Photo>>(listOf())
    private var currentPage = 1


    fun fetchPhotos() {

//        viewModelScope.launch {
//            when (val state = repositoryService.fetchAll(currentPage)) {
//                is Resource.Loading -> {
//                    Timber.d("Loading")
//                    loadError.value = ""
//                    isLoading.value = true
//                }
//                is Resource.Success -> {
//                    endReached.value = currentPage * 1 >= state.data!!.page
//                    photoList.value = state.data.photos
//                    loadError.value = ""
//                    isLoading.value = false
//                    currentPage++
//                }
//                is Resource.Error -> {
//                    loadError.value = "An error occurred"
//                    isLoading.value = false
//                    Timber.tag("L").d("ERROR")
//                }
//            }
//        }
    }

    fun getPhotoPagination(): Flow<PagingData<Photo>> {
        return Pager(PagingConfig(pageSize = 10)) {
            PhotoSource(repositoryService = repositoryService)
        }.flow
    }
}