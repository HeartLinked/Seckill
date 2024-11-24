<script setup lang="ts">
import { ref, watch } from 'vue'
import { useProductStore } from '../stores/products'

const props = defineProps<{
  onFiltersChange: (filters: any) => void
}>()

const productStore = useProductStore()
const formState = ref({
  categories: [],
  brands: [],
  priceRange: [0, 1000],
})

watch(formState, (newVal) => {
  props.onFiltersChange(newVal)
}, { deep: true })
</script>

<template>
  <a-form
      layout="inline"
      :model="formState"
      class="filter-container"
  >
    <a-form-item label="类别" class="filter-item">
      <a-select
          v-model:value="formState.categories"
          mode="multiple"
          placeholder="选择商品类别"
          :options="productStore.categories.map(c => ({ value: c, label: c }))"
      />
    </a-form-item>

    <a-form-item label="品牌" class="filter-item">
      <a-select
          v-model:value="formState.brands"
          mode="multiple"
          placeholder="选择商品品牌"
          :options="productStore.brands.map(b => ({ value: b, label: b }))"
      />
    </a-form-item>

    <a-form-item label="价格范围" class="filter-item">
      <a-slider
          v-model:value="formState.priceRange"
          range
          :min="0"
          :max="1000"
          :step="10"
      />
    </a-form-item>

  </a-form>
</template>

<style scoped>
.filter-container {
  display: flex;
  flex-wrap: nowrap;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}

.filter-item {
  flex: 1;
  min-width: 150px;
  margin-right: 16px;
}

.filter-item:last-child {
  margin-right: 0; /* 去掉最后一个 item 的右边距 */
}
</style>
