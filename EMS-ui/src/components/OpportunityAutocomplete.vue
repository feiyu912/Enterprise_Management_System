<template>
  <el-autocomplete
    v-model="inputValue"
    :fetch-suggestions="querySearch"
    :trigger-on-focus="false"
    :debounce="300"
    placeholder="请输入商机名称"
    class="opportunity-autocomplete"
    @select="handleSelect"
  >
    <template #default="{ item }">
      <div class="suggestion-item">
        <span class="name">{{ item.name }}</span>
        <span class="customer">客户：{{ item.customerName }}</span>
      </div>
    </template>
    <template #empty>
      <div class="no-data">未找到相关商机</div>
    </template>
  </el-autocomplete>
</template>

<script setup>
import { ref } from 'vue'
import { searchOpportunities } from '@/api/opportunity'

const props = defineProps({
  modelValue: {
    type: String,
    default: ''
  }
})

const emit = defineEmits(['update:modelValue', 'select'])

const inputValue = ref(props.modelValue)

// 搜索建议
const querySearch = async (query, callback) => {
  if (!query) {
    callback([])
    return
  }
  try {
    const res = await searchOpportunities(query)
    callback(res.data || [])
  } catch (error) {
    console.error('搜索商机失败:', error)
    callback([])
  }
}

// 选择建议项
const handleSelect = (item) => {
  emit('update:modelValue', item.name)
  emit('select', item)
}

// 监听输入值变化
watch(() => props.modelValue, (val) => {
  inputValue.value = val
})
</script>

<style scoped>
.opportunity-autocomplete {
  width: 100%;
}

.suggestion-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 4px 0;
}

.suggestion-item .name {
  font-weight: bold;
}

.suggestion-item .customer {
  color: #909399;
  font-size: 12px;
}

.no-data {
  color: #909399;
  font-size: 14px;
  text-align: center;
  padding: 8px 0;
}
</style> 