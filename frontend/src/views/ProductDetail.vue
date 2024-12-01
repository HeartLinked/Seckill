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
          <p class="text-2xl text-indigo-600 mb-4">{{ product.price.toFixed(2) }} 元</p>
          <p class="text-gray-600 mb-6">{{ product.description }}</p>

          <div class="mb-6">
            <span class="font-semibold">库存 :</span>
            <span :class="product.stock > 0 ? 'text-green-600' : 'text-red-600'">
                {{ ` ${product.stock} ` }}
            </span>
          </div>

          <!-- 新增已售行 -->
          <div class="mb-6">
            <span class="font-semibold text-gray-600">已售 :</span>
            <span class="text-gray-600">
                {{ ` ${product.sales} ` }}
            </span>
          </div>

          <button
              class="w-full bg-indigo-600 text-white px-6 py-3 rounded-md hover:bg-indigo-700 disabled:bg-gray-400"
              :disabled="product.stock === 0"
          >
            加入购物车
          </button>
        </div>

      </div>
    </div>
  </div>
</template>
