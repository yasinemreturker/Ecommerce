package com.turker.ecommerce.data.repo

import com.turker.ecommerce.data.datasource.local.ProductDao
import com.turker.ecommerce.data.datasource.remote.ProductService
import com.turker.ecommerce.data.model.ProductUI
import com.turker.ecommerce.util.Resource

class ProductRepository(
    private val productService: ProductService,
    private val productDao: ProductDao
) {

    suspend fun getAllProducts(): Resource<List<ProductUI>> {
        return try {
            val getFavoriteIds = getFavoriteIds()
            val result = productService.getAllProducts().products.orEmpty()

            if (result.isEmpty()) {
                Resource.Error(Exception("Products not found"))
            } else {
                Resource.Success(result.map {
                    it.mapToProductUI(isFavorite = getFavoriteIds.contains(it.id))
                })
            }

        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    suspend fun getFavoriteIds() = productDao.getFavoriteIds()

}