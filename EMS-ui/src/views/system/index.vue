<template>
  <div class="system-container">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>系统管理</span>
        </div>
      </template>
      <el-form :model="form" label-width="120px">
        <el-form-item label="系统名称">
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="系统版本">
          <el-input v-model="form.version" disabled />
        </el-form-item>
        <el-form-item label="系统描述">
          <el-input v-model="form.description" type="textarea" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary">保存设置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const form = ref({
  name: '智联企管平台',
  version: '1.0.0',
  description: '企业管理系统，提供用户管理、产品管理等功能。'
})
</script>

<style scoped>
.system-container {
  padding: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style> 