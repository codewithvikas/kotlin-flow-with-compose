package com.vikas.flowpractice

import app.cash.turbine.test
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class FlowPracticeViewModelTest {

    private lateinit var viewModel: FlowPracticeViewModel
    @Before
    fun setUp() {
        //viewModel = FlowPracticeViewModel()
    }

    @Test
    fun `countdownflow count down properly from 5 to 0`() = runBlocking {
        viewModel.countDownFlow.test {
            for (i in 5 downTo 0) {
                val emission = awaitItem()
                assertEquals(emission,i)
            }

        }
    }
}