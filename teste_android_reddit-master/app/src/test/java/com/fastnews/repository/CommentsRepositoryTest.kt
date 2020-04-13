package com.fastnews.repository

import android.app.Application
import com.fastnews.service.model.CommentChildren
import com.fastnews.service.model.CommentData
import com.fastnews.viewmodel.CommentViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class CommentsRepositoryTest {
    @Mock
    lateinit var commentRepository: CommentRepository

    @Mock
    lateinit var appMock: Application

    @Mock
    lateinit var deferred: Deferred<List<CommentData>>
    val listData = List(1) { data }
    val data = CommentData("123", "John", "thumb", "PostTest", 0, 1,  1586732936)
    val comment = CommentChildren("", data)
    val result = comment.data

    @Before
    fun initTest() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `Loading Comments Success`(): Unit = runBlocking {
        `when`(CommentRepository.getComments("123")).thenReturn(listData)
        `when`(deferred.await()).thenReturn(listData)
        val commentViewModel = CommentViewModel()
        delay(10000L)
        Assert.assertEquals(result, commentViewModel.getComments("123"))
    }
}