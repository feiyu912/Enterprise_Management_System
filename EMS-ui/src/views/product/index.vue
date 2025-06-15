<template>
  <div class="product-container">
    <!-- 搜索栏 -->
    <div class="search-bar">
      <el-input
        v-model="queryParams.productName"
        placeholder="请输入产品名称"
        style="width: 200px"
        clearable
        @keyup.enter="handleQuery"
      />
      <el-button type="primary" @click="handleQuery">查询</el-button>
    </div>

    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="productList"
      border
      style="width: 100%"
    >
      <el-table-column prop="id" label="ID" width="80" />
      <el-table-column prop="name" label="产品名称" />
      <el-table-column prop="price" label="价格" width="120">
        <template #default="{ row }">
          ¥{{ row.price }}
        </template>
      </el-table-column>
      <el-table-column prop="status" label="状态" width="100">
        <template #default="{ row }">
          <el-tag :type="row.status === 1 ? 'success' : 'info'">
            {{ row.status === 1 ? '上架' : '下架' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200" fixed="right">
        <template #default="{ row }">
          <el-button type="primary" link @click="handleEdit(row)">编辑</el-button>
          <el-button type="danger" link @click="handleDelete(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <div class="pagination-container">
      <el-button type="primary" @click="handleAdd">新增产品</el-button>
      <el-pagination
        v-model:current-page="queryParams.pageNum"
        v-model:page-size="queryParams.pageSize"
        :page-sizes="[10, 20, 50, 100]"
        :total="total"
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import axios from 'axios'

const loading = ref(false)
const productList = ref([])
const total = ref(0)
const queryParams = ref({
  pageNum: 1,
  pageSize: 10,
  productName: ''
})

// 查询产品列表
const getList = async () => {
  loading.value = true
  try {
    const response = await axios.get('/api/product/page', {
      params: queryParams.value
    })
    productList.value = response.data.records
    total.value = response.data.total
  } catch (error) {
    ElMessage.error('获取产品列表失败')
  } finally {
    loading.value = false
  }
}

// 查询按钮点击
const handleQuery = () => {
  queryParams.value.pageNum = 1
  getList()
}

// 分页大小改变
const handleSizeChange = (val) => {
  queryParams.value.pageSize = val
  getList()
}

// 页码改变
const handleCurrentChange = (val) => {
  queryParams.value.pageNum = val
  getList()
}

// 新增产品
const handleAdd = () => {
  // TODO: 实现新增功能
  ElMessage.info('新增功能开发中')
}

// 编辑产品
const handleEdit = (row) => {
  // TODO: 实现编辑功能
  ElMessage.info('编辑功能开发中')
}

// 删除产品
const handleDelete = async (row) => {
  try {
    await ElMessageBox.confirm('确认删除该产品吗？', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    // TODO: 实现删除功能
    ElMessage.success('删除成功')
    getList()
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error('删除失败')
    }
  }
}

onMounted(() => {
  getList()
})
</script>

<style scoped>
.product-container {
  padding: 20px;
}

.search-bar {
  margin-bottom: 20px;
  display: flex;
  gap: 10px;
}

.pagination-container {
  margin-top: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style> 