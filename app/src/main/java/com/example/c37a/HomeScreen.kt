package com.example.c37a

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.c37a.model.ProductModel
import com.example.c37a.repository.ProductRepoImpl
import com.example.c37a.ui.theme.Blue
import com.example.c37a.ui.theme.PurpleGrey80
import com.example.c37a.viewmodel.ProductViewModel

@Composable
fun HomeScreen() {


    val productViewModel = remember { ProductViewModel(ProductRepoImpl()) }

    val context = LocalContext.current

    val products = productViewModel.products.observeAsState(initial = null)
    var pName by remember { mutableStateOf("") }
    var pPrice by remember { mutableStateOf("") }
    var pDesc by remember { mutableStateOf("") }

    LaunchedEffect(products.value) {
        productViewModel.getAllProduct()
        products.value?.let {
            pName = it.name
            pDesc = it.desc
            pPrice = it.price.toString()
        }
    }

    val loading = productViewModel.loading.observeAsState(initial = false)
    val allProducts = productViewModel.allProducts.observeAsState(initial = emptyList())

    var showDialog by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = {
                        showDialog = false
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            var model = ProductModel(
                                products.value!!.productId,
                                pName,
                                pDesc,
                                pPrice.toDouble()
                            )
                            productViewModel.editProduct(model)
                            { success, message ->

                                if (success) {
                                    showDialog = false
                                }

                            }
                        }) {
                            Text("Update")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            showDialog = false
                        }) {
                            Text("Cancel")
                        }
                    },
                    title = { Text("Update product") },
                    text = {
                        Column {
                            Spacer(modifier = Modifier.height(50.dp))
                            OutlinedTextField(
                                value = pName,
                                onValueChange = { data ->
                                    pName = data
                                },
                                shape = RoundedCornerShape(15.dp),
                                placeholder = {
                                    Text("Product name")
                                },

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 15.dp),
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = PurpleGrey80,
                                    unfocusedContainerColor = PurpleGrey80,
                                    focusedIndicatorColor = Blue,
                                    unfocusedIndicatorColor = Color.Transparent
                                )

                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            OutlinedTextField(
                                value = pPrice,
                                onValueChange = { data ->
                                    pPrice = data
                                },
                                shape = RoundedCornerShape(15.dp),
                                placeholder = {
                                    Text("Product Price")
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 15.dp),
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = PurpleGrey80,
                                    unfocusedContainerColor = PurpleGrey80,
                                    focusedIndicatorColor = Blue,
                                    unfocusedIndicatorColor = Color.Transparent
                                )

                            )

                            Spacer(modifier = Modifier.height(15.dp))
                            OutlinedTextField(
                                value = pDesc,
                                onValueChange = { data ->
                                    pDesc = data
                                },
                                shape = RoundedCornerShape(15.dp),
                                placeholder = {
                                    Text("Product Description")
                                },
                                maxLines = 5,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 15.dp),
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = PurpleGrey80,
                                    unfocusedContainerColor = PurpleGrey80,
                                    focusedIndicatorColor = Blue,
                                    unfocusedIndicatorColor = Color.Transparent
                                )

                            )

                        }
                    }
                )
            }
        }
        if(loading.value){
            item {
                CircularProgressIndicator()
            }
        }else{
            items(allProducts.value!!.size) { index ->
                val data = allProducts.value!![index]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
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
                                productViewModel.getProductById(data.productId)
                            }) {
                                Icon(Icons.Default.Edit, contentDescription = null, tint = Color.Green)
                            }
                            IconButton(onClick = {
                                productViewModel.deleteProduct(data.productId) { succes, msg ->
                                    if (succes) {
                                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                    } else {
                                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()

                                    }
                                }
                            }) {
                                Icon(Icons.Default.Delete, contentDescription = null, tint = Color.Red)
                            }
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