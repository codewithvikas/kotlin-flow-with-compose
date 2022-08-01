package com.vikas.flowpractice

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vikas.flowpractice.ui.theme.FlowPracticeTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowPracticeTheme {
                val viewModel = viewModel<FlowPracticeViewModel>()
                //val timer = viewModel.countDownFlow.collectAsState(initial = 10)
                val count = viewModel.stateFlow.collectAsState(initial = 10)

                    LaunchedEffect(key1 = true){
                        viewModel.sharedFlow.collect{
                           Log.d("Flow:Shared","Value:$it")
                        }
                    }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        /*
                        Example for floe basic
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            fontSize = 30.sp,
                            text = timer.value.toString()
                        )*/

                        Button(
                            modifier = Modifier.align(Alignment.Center),
                            onClick = {viewModel.incrementCounter()}
                        ) {
                            Text(text = "Counter : ${count.value}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FlowPracticeTheme {
        Greeting("Android")
    }
}