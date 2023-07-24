package com.ncs.retrofitapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
    fun banner(items:List<TODO>, modifier: androidx.compose.ui.Modifier= androidx.compose.ui.Modifier
    .padding(16.dp)
    .background(
        Color.Green
    ), intitalSelectedItem: Int=0){
        var clicked by remember {
            mutableStateOf(intitalSelectedItem)
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            items.forEachIndexed { index, bannerContent ->
                item(todo = bannerContent,state=index==clicked, onItemClick = { clicked = index })
            }
        }
    }

    @Composable
    fun item(todo: TODO,
             state:Boolean=false,
             onItemClick:()-> Unit,
    ){
        val checkedState = remember { mutableStateOf(todo.completed) }
        Box(modifier = Modifier
            .height(95.dp)
            .padding(start = 5.dp, end = 5.dp, top = 2.dp)
            .background(Color.Green)
            .fillMaxWidth()) {
            Box(modifier = Modifier.padding(35.dp)) {
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()  ) {
                    Text(text = todo.title, modifier = Modifier.fillMaxSize())
                    Spacer(modifier = Modifier.width(30.dp))
                    Checkbox(checked = checkedState.value, onCheckedChange = {checkedState.value=it} )
                }
            }

        }
    }
