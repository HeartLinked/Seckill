<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '../stores/auth'
import {
  ShoppingCartOutlined,
  UserOutlined,
  LogoutOutlined
} from '@ant-design/icons-vue'

const router = useRouter()
const authStore = useAuthStore()
const searchQuery = ref('')

const handleSearch = (value: string) => {
  router.push({
    path: '/products',
    query: { search: value }
  })
}
</script>

<template>
  <a-layout-header style="background-color: #e9ecef;" class="shadow-sm px-4">
    <div class="max-w-7xl mx-auto flex items-center justify-between h-16">
      <div class="flex items-center">
        <router-link to="/" class="text-2xl font-bold text-primary">电商秒杀系统</router-link>
      </div>

      <div class="flex items-center space-x-6">
        <a-input-search
            v-model:value="searchQuery"
            placeholder="输入商品关键词搜索商品..."
            class="w-64"
            @search="handleSearch"
        />

        <a-badge :count="0">
          <a-button type="link" class="align-middle">
            <shopping-cart-outlined class="text-xl align-middle" />
          </a-button>
        </a-badge>


        <template v-if="authStore.isAuthenticated">
          <a-dropdown>
            <a-button type="link">
              <user-outlined class="text-xl" />
            </a-button>
            <template #overlay>
              <a-menu>
                <a-menu-item @click="authStore.logout">
                  <logout-outlined />
                  <span>退出登录</span>
                </a-menu-item>
              </a-menu>
            </template>
          </a-dropdown>
        </template>
        <template v-else>
          <router-link to="/login">
            <a-button type="link">登录</a-button>
          </router-link>
          <router-link to="/register">
            <a-button type="primary">注册</a-button>
          </router-link>
        </template>
      </div>
    </div>
  </a-layout-header>
</template>
