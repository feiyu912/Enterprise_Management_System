<template>
  <div class="plan-container">
    <div class="page-header">
      <h2>关系计划管理</h2>
    </div>
    <el-card class="box-card" shadow="hover">
      <template #header>
        <div class="card-header">
          <el-button type="primary" @click="handleAdd">
            <el-icon><Plus /></el-icon>添加计划
          </el-button>
        </div>
      </template>
      
      <el-timeline>
        <el-timeline-item
          v-for="plan in planList"
          :key="plan.id"
          :timestamp="formatDate(plan.startDate)"
          :type="getTimelineItemType(plan.progress)"
          :hollow="plan.progress < 100"
        >
          <el-card class="plan-card">
            <template #header>
              <div class="plan-header">
                <span class="title">{{ plan.title }}</span>
                <div class="actions">
                  <el-button type="primary" link @click="handleEdit(plan)">
                    <el-icon><Edit /></el-icon>编辑
                  </el-button>
                  <el-button type="danger" link @click="handleDelete(plan)">
                    <el-icon><Delete /></el-icon>删除
                  </el-button>
                </div>
              </div>
            </template>
            <div class="plan-content">
              <div class="progress-info">
                <span>进度：{{ plan.progress }}%</span>
                <el-progress :percentage="plan.progress" :status="getProgressStatus(plan.progress)" />
              </div>
              <div class="date-info">
                <p>开始时间：{{ formatDate(plan.startDate) }}</p>
                <p>结束时间：{{ formatDate(plan.endDate) }}</p>
              </div>
            </div>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </el-card>

    <!-- 添加/编辑计划对话框 -->
    <el-dialog
      v-model="dialogVisible"
      :title="dialogType === 'add' ? '添加计划' : '编辑计划'"
      width="500px"
      destroy-on-close
    >
      <el-form
        ref="formRef"
        :model="form"
        :rules="rules"
        label-width="80px"
        label-position="right"
      >
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入计划标题" />
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <el-input
            v-model="form.content"
            type="textarea"
            :rows="3"
            placeholder="请输入计划内容"
          />
        </el-form-item>
        <el-form-item label="开始时间" prop="startDate">
          <el-date-picker
            v-model="form.startDate"
            type="datetime"
            placeholder="选择开始时间"
            style="width: 100%"
          />
        </el-form-item>
        <el-form-item label="结束时间" prop="endDate">
          <el-date-picker
            v-model="form.endDate"
            type="datetime"
            placeholder="选择结束时间"
            style="width: 100%"
          />
        </el-form-item>
        <el-form-item label="进度" prop="progress">
          <el-slider
            v-model="form.progress"
            :min="0"
            :max="100"
            :step="10"
            show-stops
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
import { Plus, Edit, Delete } from '@element-plus/icons-vue'
import { getPlanList, addPlan, updatePlan, deletePlan } from '@/api/plan'
import { formatDate } from '@/utils/format'

// 计划列表
const planList = ref([])

// 对话框相关
const dialogVisible = ref(false)
const dialogType = ref('add')
const formRef = ref(null)
const form = reactive({
  id: undefined,
  title: '',
  content: '',
  startDate: '',
  endDate: '',
  progress: 0
})

// 表单验证规则
const rules = {
  title: [
    { required: true, message: '请输入计划标题', trigger: 'blur' }
  ],
  content: [
    { required: true, message: '请输入计划内容', trigger: 'blur' }
  ],
  startDate: [
    { required: true, message: '请选择开始时间', trigger: 'change' }
  ],
  endDate: [
    { required: true, message: '请选择结束时间', trigger: 'change' }
  ],
  progress: [
    { required: true, message: '请设置进度', trigger: 'change' }
  ]
}

// 获取计划列表
const getList = async () => {
  try {
    const res = await getPlanList()
    planList.value = res.data.records
  } catch (error) {
    console.error('获取计划列表失败:', error)
    ElMessage.error('获取计划列表失败')
  }
}

// 获取时间线项目类型
const getTimelineItemType = (progress) => {
  if (!progress) return 'info'
  if (progress === 100) return 'success'
  if (progress >= 50) return 'primary'
  return 'warning'
}

// 获取进度条状态
const getProgressStatus = (progress) => {
  if (!progress) return 'exception'
  if (progress === 100) return 'success'
  if (progress >= 50) return ''
  return 'exception'
}

// 添加计划
const handleAdd = () => {
  dialogType.value = 'add'
  Object.assign(form, {
    id: undefined,
    title: '',
    content: '',
    startDate: '',
    endDate: '',
    progress: 0
  })
  dialogVisible.value = true
}

// 编辑计划
const handleEdit = (plan) => {
  dialogType.value = 'edit'
  Object.assign(form, plan)
  dialogVisible.value = true
}

// 删除计划
const handleDelete = (plan) => {
  ElMessageBox.confirm(
    '确认删除该计划吗？',
    '警告',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(async () => {
    try {
      await deletePlan(plan.id)
      ElMessage.success('删除成功')
      getList()
    } catch (error) {
      console.error('删除计划失败:', error)
      ElMessage.error('删除计划失败')
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
          userId: JSON.parse(localStorage.getItem('userInfo')).id
        }
        
        if (dialogType.value === 'add') {
          await addPlan(submitData)
          ElMessage.success('添加成功')
        } else {
          await updatePlan(submitData)
          ElMessage.success('更新成功')
        }
        dialogVisible.value = false
        getList()
      } catch (error) {
        console.error('保存计划失败:', error)
        ElMessage.error('保存计划失败')
      }
    }
  })
}

onMounted(() => {
  getList()
})
</script>

<style scoped>
.plan-container {
  padding: 20px;
  height: 100%;
  width: 100%;
  box-sizing: border-box;
  overflow-x: hidden;
}

.page-header {
  margin-bottom: 20px;
  width: 100%;
}

.page-header h2 {
  margin: 0;
  font-size: 24px;
  font-weight: 500;
  color: #303133;
}

.box-card {
  height: calc(100% - 60px);
  display: flex;
  flex-direction: column;
  width: 100%;
  border-radius: 8px;
}

.card-header {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  width: 100%;
}

.plan-card {
  margin-bottom: 10px;
}

.plan-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.plan-header .title {
  font-size: 16px;
  font-weight: bold;
}

.plan-content {
  padding: 10px 0;
}

.progress-info {
  margin-bottom: 10px;
}

.progress-info span {
  display: block;
  margin-bottom: 5px;
  color: #606266;
}

.date-info p {
  margin: 5px 0;
  color: #909399;
  font-size: 14px;
}

.dialog-footer {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}

:deep(.el-timeline-item__node) {
  background-color: var(--el-color-primary);
}

:deep(.el-timeline-item__node--success) {
  background-color: var(--el-color-success);
}

:deep(.el-timeline-item__node--warning) {
  background-color: var(--el-color-warning);
}

:deep(.el-timeline-item__node--danger) {
  background-color: var(--el-color-danger);
}

:deep(.el-timeline-item__node--info) {
  background-color: var(--el-color-info);
}
</style> 