package com.example.c37a

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    val images = listOf(
        R.drawable.face,
        R.drawable.bettafish,
        R.drawable.dog,
        R.drawable.bird,
        R.drawable.hamster,
        R.drawable.goldfish,
        R.drawable.guineapig,
    )

    val names = listOf(
        "Facebook",
        "Bettafish",
        "Dog",
        "Bird",
        "Hamster",
        "Goldfish",
        "GuineaPig",
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            Text("Welcome")
            Text("Good Morning")
        }
        item {
            LazyRow {
                items(images.size) { index ->
                    Column {
                        Image(
                            painter = painterResource(images[index]),
                            contentDescription = null,
                            modifier = Modifier
                                .size(70.dp)
                                .padding(end = 10.dp)
                        )
                        Text(names[index])
                    }
                }
            }
        }



       item {
           Image(
               painter = painterResource(R.drawable.banner),
               contentDescription = null,
               modifier = Modifier.fillMaxWidth()
           )
           Text("Featured Products")
           LazyVerticalGrid(
               columns = GridCells.Fixed(3),
               modifier = Modifier.height(500.dp)
           ) {
              items(images.size){index->
                  Image(
                      painter = painterResource(images[index]),
                      contentDescription = null,
                      modifier = Modifier
                          .size(70.dp)
                          .padding(end = 10.dp)
                  )
           }
       }



    }
    }
}

@Preview
@Composable
fun HomePre() {
    HomeScreen()
}