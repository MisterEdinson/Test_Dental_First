package com.app.dental_first.core

import com.app.dental_first.data.local.entity.CatalogEntity
import com.app.dental_first.data.local.entity.ProductEntity
import com.app.dental_first.data.local.entity.SubCategoryEntity
import com.app.dental_first.data.network.models.ProductsResponse


object CatalogMapper {

    fun mapToEntity(response: ProductsResponse): Triple<List<CatalogEntity>, List<SubCategoryEntity>, List<ProductEntity>> {

        val catalogs = mutableListOf<CatalogEntity>()
        val subCategories = mutableListOf<SubCategoryEntity>()
        val products = mutableListOf<ProductEntity>()

        response.catalog.forEach { catalog ->
            catalogs.add(
                CatalogEntity(
                    id = catalog.id,
                    name = catalog.name,
                    image = catalog.image,
                    description = catalog.description
                )
            )
            catalog.items.forEach { sub ->
                subCategories.add(
                    SubCategoryEntity(
                        id = sub.id,
                        catalogId = catalog.id,
                        name = sub.name,
                        image = sub.image,
                        description = sub.description
                    )
                )
                sub.items.forEach { product ->
                    products.add(
                        ProductEntity(
                            id = product.id,
                            catalogId = catalog.id,
                            subCategoryId = sub.id,
                            name = product.name,
                            image = product.image,
                            price = product.price,
                            description = product.description
                        )
                    )
                }
            }
        }
        response.products.forEach {
            products.add(
                ProductEntity(
                    id = it.id,
                    catalogId = -1,
                    subCategoryId = -1,
                    name = it.name,
                    image = it.image,
                    price = it.price,
                    description = it.description
                )
            )
        }
        return Triple(catalogs, subCategories, products)
    }
}