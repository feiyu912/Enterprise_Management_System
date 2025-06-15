<template>
  <div class="region-cascader">
    <el-cascader
      v-model="selectedRegions"
      :props="props"
      :options="options"
      placeholder="请选择省市区"
      clearable
    />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import axios from 'axios'

const selectedRegions = ref([])
const options = ref([])

const props = {
  lazy: true,
  lazyLoad(node, resolve) {
    const { level, value } = node
    
    if (level === 0) {
      // 加载省份
      loadProvinces().then(resolve)
    } else {
      // 加载市或区
      loadChildren(value).then(resolve)
    }
  }
}

const loadProvinces = async () => {
  try {
    const response = await axios.get('/api/region/provinces')
    return response.data
  } catch (error) {
    ElMessage.error('加载省份数据失败')
    return []
  }
}

const loadChildren = async (parentCode) => {
  try {
    const response = await axios.get(`/api/region/children/${parentCode}`)
    return response.data
  } catch (error) {
    ElMessage.error('加载地区数据失败')
    return []
  }
}

onMounted(() => {
  loadProvinces().then(data => {
    options.value = data
  })
})
</script>

<style scoped>
.region-cascader {
  width: 300px;
}
</style> 