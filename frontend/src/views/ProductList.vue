<script setup lang="ts">
import { ref, onMounted, watch } from 'vue'
import { useRoute } from 'vue-router'
import { useProductStore } from '../stores/products'
import ProductFilters from '../components/ProductFilters.vue'
import ProductCard from '../components/ProductCard.vue'
import type { Product } from '../stores/products'

const route = useRoute()
const productStore = useProductStore()
const searchQuery = ref('')
const currentPage = ref(1)
const pageSize = ref(12)
const filters = ref<{
  categories: string[],
  brands: string[],
  priceRange: [number, number],
  minRating: number
}>({
  categories: [],
  brands: [],
  priceRange: [0, 1000],
  minRating: 0
})


const filteredProducts = ref<Product[]>([])

const updateProducts = () => {
  filteredProducts.value = productStore.searchProducts(
      searchQuery.value,
      {
        category: filters.value.categories.length > 0 ? filters.value.categories : undefined,
        brand: filters.value.brands.length > 0 ? filters.value.brands : undefined,
        priceRange: filters.value.priceRange,
        minRating: filters.value.minRating || undefined
      }
  )
}

// 监听搜索条件变化
watch(() => route.query.search, (newQuery) => {
  searchQuery.value = newQuery as string || ''
  updateProducts()
})

const handleFiltersChange = (newFilters: any) => {
  filters.value = newFilters
  updateProducts()
}

onMounted(async () => {
  // 从后端加载数据
  // await productStore.fetchProducts()

  // 如果路由中存在搜索条件，更新搜索查询
  if (route.query.search) {
    searchQuery.value = route.query.search as string
  }

  // 更新产品列表
  updateProducts()
})
</script>

<template>
  <div class="max-w-7xl mx-auto px-4 py-8">
    <!-- Filters Header -->
    <div class="mb-6">
      <ProductFilters @filters-change="handleFiltersChange" />
    </div>

    <!-- Product Grid -->
    <div>
      <!-- 显示友好的提示信息 -->
      <div v-if="filteredProducts.length === 0" class="text-center mt-8">
        <p>抱歉，没有找到相关商品。</p>
      </div>

      <div v-else class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        <router-link
            v-for="product in filteredProducts"
            :key="product.id"
            :to="`/products/${product.id}`"
            class="block"
        >
          <ProductCard :product="product" />
        </router-link>
      </div>

      <!-- Pagination -->
      <div class="mt-8 flex justify-center">
        <a-pagination
            v-model:current="currentPage"
            :total="filteredProducts.length"
            :pageSize="pageSize"
            show-quick-jumper
            show-size-changer
            :pageSizeOptions="['12', '24', '36']"
        />
      </div>
    </div>
  </div>
</template>
