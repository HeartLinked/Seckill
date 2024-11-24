<script setup lang="ts">
import {ref} from 'vue'
import {useRoute} from 'vue-router'
import {useProductStore} from '../stores/products'
import {Swiper, SwiperSlide} from 'swiper/vue'
import 'swiper/css'

const route = useRoute()
const productStore = useProductStore()
const product = ref(productStore.getProduct(Number(route.params.id)))
</script>

<template>
  <div class="min-h-screen bg-gray-100 flex items-center justify-center">
    <div class="max-w-4xl w-full bg-white p-8 rounded-lg shadow-lg">
      <div v-if="product" class="grid grid-cols-1 md:grid-cols-2 gap-8">
        <div>
          <swiper
              :slides-per-view="1"
              :space-between="50"
              class="rounded-lg overflow-hidden"
          >
            <swiper-slide v-for="(image, index) in product.images" :key="index">
              <img :src="image" :alt="product.name" class="w-full h-auto"/>
            </swiper-slide>
          </swiper>
        </div>
        <div>
          <h1 class="text-3xl font-bold mb-4">{{ product.name }}</h1>
          <p class="text-2xl text-indigo-600 mb-4">${{ product.price.toFixed(2) }}</p>
          <p class="text-gray-600 mb-6">{{ product.description }}</p>
          <div class="mb-6">
            <span class="font-semibold">Stock:</span>
            <span :class="product.stock > 0 ? 'text-green-600' : 'text-red-600'">
              {{ product.stock > 0 ? `${product.stock} units available` : 'Out of stock' }}
            </span>
          </div>
          <button
              class="w-full bg-indigo-600 text-white px-6 py-3 rounded-md hover:bg-indigo-700 disabled:bg-gray-400"
              :disabled="product.stock === 0"
          >
            Add to Cart
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
