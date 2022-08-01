package com.vikas.flowpractice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FlowPracticeViewModel:ViewModel() {

    private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()

    private val _sharedFlow = MutableSharedFlow<Int>(replay = 5)
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun incrementCounter(){
        _stateFlow.value +=1
    }
     val countDownFlow = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue
        emit(startingValue)
        while (currentValue > 0){
            delay(1000)
            currentValue--
            emit(currentValue)
        }
    }
         .filter { time ->
         time %2 == 0
     }
         .map { time ->
             time * 2
         }

    init {
        collectFlow()
        viewModelScope.launch {
            sharedFlow.collect{
                //delay(2000L)
                println("Flow first: the received number is $it")
            }
        }

        viewModelScope.launch {
            sharedFlow.collect{
                delay(3000L)
                println("Flow second: the received number is $it")
            }
        }

        squareNumber(4)

    }

    fun squareNumber(number: Int){
        viewModelScope.launch {
            _sharedFlow.emit(number * number)
        }
    }

    private fun collectFlow(){
       val flow = flow<String> {
           delay(250L)
           emit("Appetizer")
           delay(1000L)
           emit("Main Dish")
           delay(100L)
           emit("Dessert")
       }

        viewModelScope.launch {
            flow.onEach {
                println("Flow: $it is delivered")
            }
                .conflate()
                .collect {
                    println("Flow: Now eating: $it")
                    delay(1500L)
                    println("Flow: Eating $it finished")
                }
        }
    }
}