package com.turker.ecommerce.data.model.request

import com.turker.ecommerce.data.model.Product

data class GetProductResponse(
    val message: String?,
    val products: List<Product>?,
    val status: Int?
)