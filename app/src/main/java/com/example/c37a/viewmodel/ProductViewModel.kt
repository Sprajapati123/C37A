package com.example.c37a.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.c37a.model.ProductModel
import com.example.c37a.repository.ProductRepo

class ProductViewModel(val repo: ProductRepo) : ViewModel() {
    fun addProduct(
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        repo.addProduct(model, callback)
    }

    fun deleteProduct(productId: String, callback: (Boolean, String) -> Unit) {
        repo.deleteProduct(productId, callback)
    }

    fun editProduct(
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        repo.editProduct(model, callback)
    }

    private val _products = MutableLiveData<ProductModel?>()
    val products: MutableLiveData<ProductModel?> get() = _products

    private val _allProducts = MutableLiveData<List<ProductModel>?>()
    val allProducts: MutableLiveData<List<ProductModel>?> get() = _allProducts


    private val _loading = MutableLiveData<Boolean>()
    val loading: MutableLiveData<Boolean> get() = _loading

    fun getAllProduct() {
        _loading.postValue(true)
        repo.getAllProduct { success, message, data ->
            if (success) {

                _loading.postValue(false)
                _allProducts.value = data
            } else {
                _loading.postValue(false)
                _allProducts.value = emptyList()
            }
        }
    }

    fun getProductById(productId: String) {
        repo.getProductById(productId) { success, message, data ->
            if (success) {
                _products.value = data
            } else {
                _products.value = null
            }
        }
    }

    fun uploadImage(context: Context, imageUri: Uri, callback: (String?) -> Unit){
        repo.uploadImage(context,imageUri,callback)
    }
}