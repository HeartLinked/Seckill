import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      redirect: '/products'
    },
    {
      path: '/products',
      name: 'products',
      component: () => import('../views/ProductList.vue') // 懒加载
    },
    {
      path: '/products/:id',
      name: 'product-detail',
      component: () => import('../views/ProductDetail.vue') // 懒加载
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/Login.vue') // 懒加载
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('../views/Register.vue') // 懒加载
    }
  ]
})


export default router