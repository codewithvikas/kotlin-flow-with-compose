package com.vikas.flowpractice

import app.cash.turbine.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class FlowPracticeViewModelTest {

    private lateinit var viewModel: FlowPracticeViewModel
    private lateinit var testDispatchers: TestDispatchers
    @Before
    fun setUp() {
        testDispatchers = TestDispatchers()
        Dispatchers.setMain(testDispatchers.testDispatcher)
        viewModel = FlowPracticeViewModel(testDispatchers)
    }

    @Test
    fun `countdownFlow count down properly from 5 to 0`() = runBlocking {
        viewModel.countDownFlow.test {
            for (i in 5 downTo 0) {
                testDispatchers.testDispatcher.advanceTimeBy(1000L)
                val emission = awaitItem()
                assertEquals(emission,i)
            }
        cancelAndConsumeRemainingEvents()
        }
    }
}