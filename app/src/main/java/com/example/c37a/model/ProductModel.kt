package com.example.c37a.model

data class ProductModel(
    var productId: String = "",
    var name: String = "",
    var desc: String = "",
    var price: Double = 0.0,
    var image: String = "",
) {
    fun toMap() : Map<String,Any?>{
        return mapOf(
            "productId" to productId,
            "name" to name,
            "desc" to desc,
            "price" to price
        )
    }
}