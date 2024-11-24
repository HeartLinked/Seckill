import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
// import axios from "axios";

export interface Product {
  id: number
  name: string
  price: number
  description: string
  stock: number
  images: string[]
  category: string
  brand: string
  sales: number
}

export const useProductStore = defineStore('products', () => {
  const products = ref<Product[]>([
    {
      id: 1,
      name: 'Premium Wireless Headphones',
      price: 199.99,
      description: 'High-quality wireless headphones with noise cancellation.',
      stock: 10,
      images: [
        'https://picsum.photos/id/1/800/600',
        'https://picsum.photos/id/2/800/600'
      ],
      category: 'Electronics',
      brand: 'SoundMax',
      sales: 120
    },
    {
      id: 2,
      name: 'Ultra Smartphone X1',
      price: 699.99,
      description: 'Latest smartphone with advanced camera features.',
      stock: 15,
      images: [
        'https://picsum.photos/id/3/800/600',
        'https://picsum.photos/id/4/800/600'
      ],
      category: 'Electronics',
      brand: 'TechPro',
      sales: 350
    },
    {
      id: 3,
      name: 'Smart Watch Pro',
      price: 299.99,
      description: 'Advanced smartwatch with health monitoring.',
      stock: 8,
      images: [
        'https://picsum.photos/id/5/800/600',
        'https://picsum.photos/id/6/800/600'
      ],
      category: 'Wearables',
      brand: 'TechPro',
      sales: 89
    },
    {
      id: 4,
      name: 'Wireless Charging Pad',
      price: 49.99,
      description: 'Fast wireless charging for your smartphone.',
      stock: 20,
      images: [
        'https://picsum.photos/id/7/800/600',
        'https://picsum.photos/id/8/800/600'
      ],
      category: 'Accessories',
      brand: 'PowerPlus',
      sales: 200
    },
    {
      id: 5,
      name: 'Wireless Charging Pad',
      price: 49.99,
      description: 'Fast wireless charging for your smartphone.',
      stock: 20,
      images: [
        'https://picsum.photos/id/7/800/600',
        'https://picsum.photos/id/8/800/600'
      ],
      category: 'Accessories',
      brand: 'PowerPlus',
      sales: 200
    },
    {
      id: 6,
      name: 'Wireless Charging Pad',
      price: 49.99,
      description: 'Fast wireless charging for your smartphone.',
      stock: 20,
      images: [
        'https://picsum.photos/id/7/800/600',
        'https://picsum.photos/id/8/800/600'
      ],
      category: 'Accessories',
      brand: 'PowerPlus',
      sales: 200
    },
    {
      id: 7,
      name: 'Wireless Charging Pad',
      price: 49.99,
      description: 'Fast wireless charging for your smartphone.',
      stock: 20,
      images: [
        'https://picsum.photos/id/7/800/600',
        'https://picsum.photos/id/8/800/600'
      ],
      category: 'Accessories',
      brand: 'PowerPlus',
      sales: 200
    },
    {
      id: 8,
      name: 'Wireless Charging Pad',
      price: 49.99,
      description: 'Fast wireless charging for your smartphone.',
      stock: 20,
      images: [
        'https://picsum.photos/id/7/800/600',
        'https://picsum.photos/id/8/800/600'
      ],
      category: 'Accessories',
      brand: 'PowerPlus',
      sales: 200
    },
  ])

  // const products = ref<Product[]>([])
  //
  // // 从后端获取商品数据
  // const fetchProducts = async () => {
  //   try {
  //     const response = await axios.get('/api/products')
  //     products.value = response.data
  //   } catch (error) {
  //     console.error('Failed to fetch products:', error)
  //   }
  // }

  const categories = computed(() => [...new Set(products.value.map(p => p.category))])
  const brands = computed(() => [...new Set(products.value.map(p => p.brand))])

  const getProduct = (id: number) => products.value.find(p => p.id === id)

  const searchProducts = (
      query: string = '',
      filters: {
        category?: string[],
        brand?: string[],
        priceRange?: [number, number],
        minRating?: number
      } = {}
  ) => {
    return products.value.filter(product => {
      const matchesQuery = !query ||
          product.name.toLowerCase().includes(query.toLowerCase()) ||
          product.description.toLowerCase().includes(query.toLowerCase())

      const matchesCategory = !filters.category?.length ||
          filters.category.includes(product.category)

      const matchesBrand = !filters.brand?.length ||
          filters.brand.includes(product.brand)

      const matchesPriceRange = !filters.priceRange ||
          (product.price >= filters.priceRange[0] && product.price <= filters.priceRange[1])

      return matchesQuery && matchesCategory && matchesBrand &&
          matchesPriceRange
    })
  }

  return {
    products,
    // fetchProducts,
    categories,
    brands,
    getProduct,
    searchProducts
  }
})