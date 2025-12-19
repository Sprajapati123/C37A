package com.example.c37a

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.c37a.repository.ProductRepoImpl
import com.example.c37a.viewmodel.ProductViewModel

@Composable
fun HomeScreen() {

    val productViewModel = remember { ProductViewModel(ProductRepoImpl()) }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        productViewModel.getAllProduct()
    }

    val allProducts = productViewModel.allProducts.observeAsState(initial = emptyList())

    var showDialog by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            if(showDialog){
                AlertDialog(
                    onDismissRequest = {
                        showDialog = false
                    },
                    confirmButton = {
                        TextButton(onClick = {}) { Text("Update")}
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            showDialog = false
                        }) {Text("Cancel") }
                    },
                    title = {Text("Update Product")}
                )
            }
        }
        items(allProducts.value!!.size){index->
            val data = allProducts.value!![index]
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)){

                Row(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(data.name)
                        Text(data.price.toString())
                        Text(data.desc)

                    }
                    Column(
                        verticalArrangement = Arrangement.Top
                    ) {
                        IconButton(onClick = {
                            showDialog = true
                        }) {
                            Icon(Icons.Default.Edit,contentDescription = null, tint = Color.Green)
                        }
                        IconButton(onClick = {
                            productViewModel.deleteProduct(data.productId){
                                    succes,msg->
                                if(succes){
                                    Toast.makeText(context,msg, Toast.LENGTH_SHORT).show()
                                }else{
                                    Toast.makeText(context,msg, Toast.LENGTH_SHORT).show()

                                }
                            }
                        }) {
                            Icon(Icons.Default.Delete,contentDescription = null, tint = Color.Red)
                        }
                    }
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