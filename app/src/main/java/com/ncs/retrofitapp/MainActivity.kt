package com.ncs.retrofitapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.ncs.retrofitapp.ui.theme.RetrofitAppTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException

class MainActivity : ComponentActivity() {

    var loading=true
    var items by mutableStateOf(emptyList<TODO>())
    val Tag="Main"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            val response=try{
                RetroFitInstance.api.getTODOS()
            }catch (e: IOException){
                setContent {
                    AlertDialogSample()
                }
                Log.d(Tag,"Internet Error")
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body()!=null){
                loading=false
                Log.d(Tag,"Successfull")
                items= response.body()!!
            }
        }
        setContent {
            RetrofitAppTheme {

                Box {

                    Column {
                        androidx.compose.foundation.lazy.LazyColumn {
                            items(items) { todo ->
                                banner(items = items)
                            }
                        }
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            SimpleCircularProgressComponent()

                        }
                    }


                }

            }
        }

    }

}
@Composable
fun SimpleCircularProgressComponent() {

    CircularProgressIndicator(
        modifier = Modifier.padding(16.dp),
        color = Color.Blue,
        strokeWidth = Dp(value = 4F)
    )
}

@Composable
fun AlertDialogSample() {

    val openDialog = remember { mutableStateOf(true)  }
    if (openDialog.value){
        AlertDialog(onDismissRequest = { openDialog.value = false}, title = { Text(text = "Internet Error") }, text = { Text("Please turn your internet On") },
            confirmButton = { Button(
                onClick = { openDialog.value = false }) { Text("OK") }},
        )
    }


}