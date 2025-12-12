package com.example.c37a.repository

import com.example.c37a.model.ProductModel

interface ProductRepo {
    fun addProduct(
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    )

    fun deleteProduct(productId: String, callback: (Boolean, String) -> Unit)

    fun editProduct(
        productId: String,
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    )

    fun getAllProduct(callback: (Boolean, String, List<ProductModel>?) -> Unit)

    fun getProductById(productId: String, callback: (Boolean, String, ProductModel?) -> Unit)
}