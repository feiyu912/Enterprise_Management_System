<template>
  <div class="opportunity-container">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>商机管理</span>
          <el-button type="primary" @click="handleAdd">
            <el-icon><Plus /></el-icon>添加商机
          </el-button>
        </div>
      </template>

      <!-- 添加查询表单 -->
      <el-form :inline="true" :model="queryForm" class="query-form">
        <el-form-item label="商机标题">
          <el-input v-model="queryForm.title" placeholder="请输入商机标题" clearable />
        </el-form-item>
        <el-form-item label="客户">
          <el-select v-model="queryForm.customerId" placeholder="请选择客户" clearable style="width: 200px">
            <el-option
              v-for="item in customerOptions"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="商机阶段">
          <el-select v-model="queryForm.stage" placeholder="请选择商机阶段" clearable style="width: 200px">
            <el-option label="初步接触" value="initial" />
            <el-option label="谈判中" value="negotiation" />
            <el-option label="方案制定" value="proposal" />
            <el-option label="合同签订" value="contract" />
            <el-option label="成交" value="success" />
            <el-option label="失败" value="fail" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleQuery">
            <el-icon><Search /></el-icon>查询
          </el-button>
          <el-button @click="handleReset">
            <el-icon><Refresh /></el-icon>重置
          </el-button>
        </el-form-item>
      </el-form>

      <el-table
        v-loading="loading"
        :data="tableData"
        border
        style="width: 100%"
      >
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="title" label="商机标题" min-width="150" show-overflow-tooltip />
        <el-table-column prop="amount" label="商机金额" width="120">
          <template #default="{ row }">
            {{ row.amount.toLocaleString('zh-CN', { style: 'currency', currency: 'CNY' }) }}
          </template>
        </el-table-column>
        <el-table-column prop="expectedDate" label="预计成交日期" width="120" />
        <el-table-column prop="stage" label="商机阶段" width="120">
          <template #default="{ row }">
            <el-tag
              :type="getStageType(row.stage)"
              effect="light"
              size="small"
            >
              {{ getStageLabel(row.stage) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="probability" label="成交概率" width="100">
          <template #default="{ row }">
            {{ row.probability }}%
          </template>
        </el-table-column>
        <el-table-column prop="description" label="商机描述" min-width="200" show-overflow-tooltip />
        <el-table-column label="操作" width="200" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" link @click="handleEdit(row)">
              <el-icon><Edit /></el-icon>编辑
            </el-button>
            <el-button type="danger" link @click="handleDelete(row)">
              <el-icon><Delete /></el-icon>删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination-container">
        <el-pagination
          v-model:current-page="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[10, 20, 50, 100]"
          :total="total"
          layout="total, sizes, prev, pager, next, jumper"
          background
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
    </el-card>

    <!-- 添加/编辑商机对话框 -->
    <el-dialog
      v-model="dialogVisible"
      :title="dialogType === 'add' ? '添加商机' : '编辑商机'"
      width="500px"
      destroy-on-close
    >
      <el-form
        ref="formRef"
        :model="form"
        :rules="rules"
        label-width="100px"
        label-position="right"
      >
        <el-form-item label="商机标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入商机标题" />
        </el-form-item>
        <el-form-item label="客户" prop="customerId">
          <el-select v-model="form.customerId" placeholder="请选择客户" style="width: 100%">
            <el-option
              v-for="item in customerOptions"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="商机金额" prop="amount">
          <el-input-number
            v-model="form.amount"
            :precision="2"
            :step="1000"
            :min="0"
            style="width: 100%"
          />
        </el-form-item>
        <el-form-item label="预计成交日期" prop="expectedDate">
          <el-date-picker
            v-model="form.expectedDate"
            type="date"
            placeholder="请选择预计成交日期"
            style="width: 100%"
          />
        </el-form-item>
        <el-form-item label="商机阶段" prop="stage">
          <el-select v-model="form.stage" placeholder="请选择商机阶段" style="width: 100%">
            <el-option label="初步接触" value="initial" />
            <el-option label="谈判中" value="negotiation" />
            <el-option label="方案制定" value="proposal" />
            <el-option label="合同签订" value="contract" />
            <el-option label="成交" value="success" />
            <el-option label="失败" value="fail" />
          </el-select>
        </el-form-item>
        <el-form-item label="成交概率" prop="probability">
          <el-slider
            v-model="form.probability"
            :min="0"
            :max="100"
            :step="10"
            show-stops
          />
        </el-form-item>
        <el-form-item label="商机描述" prop="description">
          <el-input
            v-model="form.description"
            type="textarea"
            :rows="3"
            placeholder="请输入商机描述"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleSubmit">确定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Plus, Edit, Delete, Search, Refresh } from '@element-plus/icons-vue'
import { getOpportunityList, addOpportunity, updateOpportunity, deleteOpportunity } from '@/api/opportunity'
import { getCustomerList } from '@/api/customer'

// 表格数据
const loading = ref(false)
const tableData = ref([])
const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(0)

// 客户选项
const customerOptions = ref([])

// 对话框相关
const dialogVisible = ref(false)
const dialogType = ref('add')
const formRef = ref(null)
const form = reactive({
  id: undefined,
  title: '',
  customerId: undefined,
  amount: 0,
  expectedDate: '',
  stage: '',
  probability: 0,
  description: ''
})

// 表单验证规则
const rules = {
  title: [
    { required: true, message: '请输入商机标题', trigger: 'blur' },
    { min: 2, max: 100, message: '长度在 2 到 100 个字符', trigger: 'blur' }
  ],
  customerId: [
    { required: true, message: '请选择客户', trigger: 'change' }
  ],
  amount: [
    { required: true, message: '请输入商机金额', trigger: 'blur' }
  ],
  expectedDate: [
    { required: true, message: '请选择预计成交日期', trigger: 'change' }
  ],
  stage: [
    { required: true, message: '请选择商机阶段', trigger: 'change' }
  ],
  probability: [
    { required: true, message: '请选择成交概率', trigger: 'change' }
  ],
  description: [
    { required: true, message: '请输入商机描述', trigger: 'blur' }
  ]
}

// 查询表单
const queryForm = reactive({
  title: '',
  customerId: undefined,
  stage: ''
})

// 获取商机列表
const getList = async () => {
  loading.value = true
  try {
    const res = await getOpportunityList({
      pageNum: currentPage.value,
      pageSize: pageSize.value,
      ...queryForm
    })
    tableData.value = res.data.records
    total.value = res.data.total
  } catch (error) {
    console.error('获取商机列表失败:', error)
    ElMessage.error('获取商机列表失败')
  } finally {
    loading.value = false
  }
}

// 获取客户列表
const getCustomers = async () => {
  try {
    const res = await getCustomerList({
      pageNum: 1,
      pageSize: 1000
    })
    customerOptions.value = res.data.records
  } catch (error) {
    console.error('获取客户列表失败:', error)
    ElMessage.error('获取客户列表失败')
  }
}

// 分页相关方法
const handleSizeChange = (val) => {
  pageSize.value = val
  getList()
}

const handleCurrentChange = (val) => {
  currentPage.value = val
  getList()
}

// 添加商机
const handleAdd = () => {
  dialogType.value = 'add'
  Object.assign(form, {
    id: undefined,
    title: '',
    customerId: undefined,
    amount: 0,
    expectedDate: '',
    stage: '',
    probability: 0,
    description: ''
  })
  dialogVisible.value = true
}

// 编辑商机
const handleEdit = (row) => {
  dialogType.value = 'edit'
  Object.assign(form, row)
  dialogVisible.value = true
}

// 删除商机
const handleDelete = (row) => {
  ElMessageBox.confirm(
    '确认删除该商机吗？',
    '警告',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(async () => {
    try {
      await deleteOpportunity(row.id)
      ElMessage.success('删除成功')
      getList()
    } catch (error) {
      console.error('删除商机失败:', error)
      ElMessage.error('删除商机失败')
    }
  })
}

// 提交表单
const handleSubmit = async () => {
  if (!formRef.value) return
  await formRef.value.validate(async (valid) => {
    if (valid) {
      try {
        const submitData = {
          ...form,
          userId: JSON.parse(localStorage.getItem('userInfo')).id,
          expectedDate: form.expectedDate ? new Date(form.expectedDate).toISOString().split('T')[0] : null
        }
        
        if (dialogType.value === 'add') {
          await addOpportunity(submitData)
          ElMessage.success('添加成功')
        } else {
          await updateOpportunity(submitData)
          ElMessage.success('更新成功')
        }
        dialogVisible.value = false
        getList()
      } catch (error) {
        console.error('保存商机失败:', error)
        ElMessage.error('保存商机失败')
      }
    }
  })
}

// 获取商机阶段标签类型
const getStageType = (stage) => {
  const types = {
    initial: 'info',
    negotiation: 'warning',
    proposal: 'primary',
    contract: 'success',
    success: 'success',
    fail: 'danger'
  }
  return types[stage] || 'info'
}

// 获取商机阶段标签文本
const getStageLabel = (stage) => {
  const labels = {
    initial: '初步接触',
    negotiation: '谈判中',
    proposal: '方案制定',
    contract: '合同签订',
    success: '成交',
    fail: '失败'
  }
  return labels[stage] || stage
}

// 查询
const handleQuery = () => {
  currentPage.value = 1
  getList()
}

// 重置
const handleReset = () => {
  Object.assign(queryForm, {
    title: '',
    customerId: undefined,
    stage: ''
  })
  handleQuery()
}

onMounted(() => {
  getList()
  getCustomers()
})
</script>

<style scoped>
.opportunity-container {
  padding: 20px;
  height: 100%;
  width: 100%;
  box-sizing: border-box;
  overflow-x: hidden;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.pagination-container {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}

.query-form {
  margin-bottom: 20px;
  padding: 20px;
  background-color: #f5f7fa;
  border-radius: 4px;
}
</style> 