package com.fastnews.repository

import android.app.Application
import com.fastnews.service.model.PostChildren
import com.fastnews.service.model.PostData
import com.fastnews.viewmodel.PostViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class PostsRepositoryTest {
    @Mock
    lateinit var postRepository: PostRepository

    @Mock
    lateinit var appMock: Application

    @Mock
    lateinit var deferred: Deferred<List<PostData>>
    val listData = List(1) { data }
    val data = PostData("123", "John", "thumb", "PostTest", 0, 1, "TitleTest", 1586732936, "www.google.com", null)
    val post = PostChildren(data)
    val result = post.data

    @Before
    fun initTest() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `Loading Posts Success`(): Unit = runBlocking {
        `when`(PostRepository.getPosts("", 1)).thenReturn(listData)
        `when`(deferred.await()).thenReturn(listData)
        val postViewModel = PostViewModel(appMock)
        delay(10000L)
        Assert.assertEquals(result, postViewModel.getPosts("", 0))
    }
}