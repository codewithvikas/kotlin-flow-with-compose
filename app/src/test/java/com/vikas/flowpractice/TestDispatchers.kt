package com.vikas.flowpractice

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

class TestDispatchers:DispatcherProvider {
    val testDispatcher = StandardTestDispatcher()
    override val main: CoroutineDispatcher
        get() = TODO("Not yet implemented")
    override val io: CoroutineDispatcher
        get() = TODO("Not yet implemented")
    override val default: CoroutineDispatcher
        get() = TODO("Not yet implemented")
}