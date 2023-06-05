package com.example.backwithresulttest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.backwithresulttest.ui.theme.BackWithResultTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BackWithResultTestTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "screen1") {
                    composable("screen1") { entry ->
                        val text = entry.savedStateHandle.get<String>("text")
                        Column(modifier = Modifier.fillMaxSize()) {
                            text?.let {
                                Text(text = text)
                            }
                            Button(onClick = {
                                navController.navigate("screen2")
                            }) {
                                Text("Go to Screen2")
                            }
                        }
                    }

                    composable("screen2") {
                        Column(modifier = Modifier.fillMaxSize()) {
                            var text by remember {
                                mutableStateOf("")
                            }
                            OutlinedTextField(
                                value = text,
                                onValueChange = { text = it },
                                modifier = Modifier.width(300.dp)
                            )
                            Button(onClick = {
                                navController.previousBackStackEntry?.savedStateHandle?.set("text", text)
                                navController.popBackStack()
                            }) {
                                Text(text = "Apply")
                            }
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
    BackWithResultTestTheme {
        Greeting("Android")
    }
}