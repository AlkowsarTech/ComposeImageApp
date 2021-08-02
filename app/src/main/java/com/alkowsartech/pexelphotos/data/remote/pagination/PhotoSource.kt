package com.alkowsartech.pexelphotos.data.remote.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alkowsartech.pexelphotos.data.remote.model.Photo
import com.alkowsartech.pexelphotos.repository.RepositoryService

class PhotoSource (private val repositoryService: RepositoryService) : PagingSource<Int, Photo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val page = params.key ?: 1
            val photoResponse = repositoryService.getPhotos(page)

            LoadResult.Page(
                data = photoResponse.photos,
                prevKey = if (page == 1) null else page - 1,
                nextKey = page.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { state.closestItemToPosition(it)?.id }
    }
}