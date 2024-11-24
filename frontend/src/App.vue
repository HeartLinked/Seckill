<template>
  <a-layout class="min-h-screen">
    <the-header />
    <a-layout-content>
      <router-view></router-view>
    </a-layout-content>
  </a-layout>
</template>

<script setup lang="ts">
import TheHeader from './components/TheHeader.vue'
</script>