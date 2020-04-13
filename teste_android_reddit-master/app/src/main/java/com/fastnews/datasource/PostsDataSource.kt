package com.fastnews.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.fastnews.service.api.RedditAPI
import com.fastnews.service.model.PostData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class PostsDataSource(private val scope: CoroutineScope) :
    PageKeyedDataSource<String, PostData>() {
    private val apiService = RedditAPI.redditService

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, PostData>) {
        scope.launch {
            try {
                val response = apiService.getPostList("")
                when{
                    response.getCompleted().isSuccessful -> {
                        val listing = response.getCompleted().body()?.data
                        val redditPosts = listing?.children?.map { it.data }
//                        callback.onResult(redditPosts ?: listOf(), before, listing?.after)
                    }
                }

            }catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }

        }

    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, PostData>) {
        scope.launch {
            try {
                val response = apiService.getPostList("")
                when{
                    response.getCompleted().isSuccessful -> {
                        val listing = response.getCompleted().body()?.data
                        val items = listing?.children?.map { it.data }
//                        callback.onResult(items ?: listOf(), after)
                    }
                }

            }catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }

    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, PostData>) {
        scope.launch {
            try {
                val response = apiService.getPostList("")
                when{
                    response.getCompleted().isSuccessful -> {
                        val listing = response.getCompleted().body()?.data
                        val items = listing?.children?.map { it.data }
//                        callback.onResult(items ?: listOf(), listing?.after)
                    }
                }

            }catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }

    }

    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }

}