package com.fastnews.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fastnews.R
import com.fastnews.ui.timeline.TimelineFragment
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.robolectric.Robolectric


@RunWith(JUnit4::class)
class TimelineFragmentTest {
    private val activity = Robolectric.setupActivity(SchemeActivity::class.java)
    private var fragment: TimelineFragment? = null

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        fragment = activity.supportFragmentManager.findFragmentById(R.layout.fragment_timeline) as TimelineFragment?
    }

    @Test
    fun fragmentIsNotNull() {
        assertNotNull(fragment)
    }
}