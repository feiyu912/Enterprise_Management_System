<template>
  <div class="dept-tree">
    <el-tree
      :data="treeData"
      :props="defaultProps"
      node-key="id"
      default-expand-all
      :expand-on-click-node="false"
    >
      <template #default="{ node, data }">
        <span class="custom-tree-node">
          <span>{{ node.label }}</span>
          <span>
            <el-button
              type="text"
              size="small"
              @click="handleDelete(node, data)"
            >
              删除
            </el-button>
          </span>
        </span>
      </template>
    </el-tree>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import axios from 'axios'

const treeData = ref([])
const defaultProps = {
  children: 'children',
  label: 'label'
}

const loadTreeData = async () => {
  try {
    const response = await axios.get('/api/dept/tree')
    treeData.value = response.data
  } catch (error) {
    ElMessage.error('加载部门数据失败')
  }
}

const handleDelete = async (node, data) => {
  try {
    await ElMessageBox.confirm('确认删除该部门吗？', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    
    await axios.delete(`/api/dept/${data.id}`)
    ElMessage.success('删除成功')
    loadTreeData()
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error('删除失败')
    }
  }
}

onMounted(() => {
  loadTreeData()
})
</script>

<style scoped>
.dept-tree {
  padding: 20px;
}
.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}
</style> 