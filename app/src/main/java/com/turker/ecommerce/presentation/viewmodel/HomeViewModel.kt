package com.turker.ecommerce.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turker.ecommerce.data.model.ProductUI
import com.turker.ecommerce.data.model.response.CRUDResponse
import com.turker.ecommerce.data.repo.ProductRepository
import com.turker.ecommerce.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    private var _homeState = MutableLiveData<HomeState>()
    val homeState: LiveData<HomeState>
        get() = _homeState

    fun getAllProducts() {
        viewModelScope.launch {

            _homeState.value = HomeState.Loading

            val result = productRepository.getAllProducts()

            if (result is Resource.Success) {
                _homeState.value = HomeState.ProductList(result.data)
            } else if (result is Resource.Error) {
                _homeState.value = HomeState.Error(result.throwable)
            }

        }
    }

}

sealed interface HomeState {
    object Loading : HomeState
    data class ProductList(val products: List<ProductUI>) : HomeState
    data class SaleProductList(val saleProducts: List<ProductUI>) : HomeState
    data class PostResponse(val crud: CRUDResponse) : HomeState
    data class Error(val throwable: Throwable) : HomeState
}