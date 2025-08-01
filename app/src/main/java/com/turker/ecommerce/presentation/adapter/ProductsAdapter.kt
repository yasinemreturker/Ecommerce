package com.turker.ecommerce.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.turker.ecommerce.R
import com.turker.ecommerce.data.model.ProductUI
import com.turker.ecommerce.databinding.ProductItemBinding
import com.turker.ecommerce.util.extension.loadImage

class ProductsAdapter(
    private val productListener: ProductListener
) : ListAdapter<ProductUI, ProductsAdapter.ProductViewHolder>(ProductDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            productListener
        )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ProductViewHolder(
        private val binding: ProductItemBinding,
        private val productListener: ProductListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductUI) = with(binding) {
            tvProductPrice.text = product.price.toString()
            tvProductTitle.text = product.title
            ivProduct.loadImage(product.imageOne)

            var isFavorite = product.isFavorite

            if (isFavorite) {
                ivFavorite.setImageResource(R.drawable.ic_favorite_enable)
            }

            ivProduct.setOnClickListener {
                productListener.onProductClick(product.id)
            }

            buttonAddToCart.setOnClickListener {
                productListener.onAddToCartButtonClick(product.id)
            }

            ivFavorite.setOnClickListener {
                isFavorite = !isFavorite
                ivFavorite.apply {
                    if (isFavorite) {
                        ivFavorite.setImageResource(R.drawable.ic_favorite_enable)
                    } else {
                        ivFavorite.setImageResource(R.drawable.ic_favorite_disable)
                    }
                }
                productListener.onFavoriteButtonClick(product)
            }
        }
    }

    class ProductDiffCallBack : DiffUtil.ItemCallback<ProductUI>() {
        override fun areContentsTheSame(oldItem: ProductUI, newItem: ProductUI): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: ProductUI, newItem: ProductUI): Boolean {
            return oldItem.id == newItem.id
        }
    }

    interface ProductListener {
        fun onProductClick(id: Int)
        fun onAddToCartButtonClick(id: Int)
        fun onFavoriteButtonClick(product: ProductUI)
    }

}