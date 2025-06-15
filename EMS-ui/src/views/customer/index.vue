<template>
  <div class="customer-container">
    <div class="customer-header">
      <el-input
        v-model="searchForm.customerName"
        placeholder="请输入客户名称"
        class="search-input"
        clearable
        @keyup.enter="handleSearch"
      />
      <el-button type="primary" @click="handleSearch">搜索</el-button>
      <el-button type="success" @click="handleAdd">添加客户</el-button>
      <el-button type="warning" @click="handleAssign">客户指派</el-button>
    </div>

    <el-table
      v-loading="loading"
      :data="customerList"
      border
      style="width: 100%"
      :fit="true"
    >
      <el-table-column type="selection" width="55" />
      <el-table-column prop="customerName" label="客户名称" min-width="150" />
      <el-table-column prop="contactPerson" label="联系人" min-width="100" />
      <el-table-column prop="phone" label="联系电话" min-width="120" />
      <el-table-column prop="email" label="电子邮箱" min-width="180" />
      <el-table-column prop="address" label="地址" min-width="200" />
      <el-table-column prop="status" label="状态" width="100">
        <template #default="{ row }">
          <el-tag :type="row.status === 'active' ? 'success' : 'info'">
            {{ row.status === 'active' ? '活跃' : '非活跃' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150" fixed="right">
        <template #default="{ row }">
          <el-button type="primary" link @click="handleEdit(row)">编辑</el-button>
          <el-button type="danger" link @click="handleDelete(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      v-model:current-page="pageNum"
      v-model:page-size="pageSize"
      :total="total"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next"
      class="pagination"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />

    <!-- 添加/编辑客户对话框 -->
    <el-dialog
      v-model="dialogVisible"
      :title="dialogType === 'add' ? '添加客户' : '编辑客户'"
      width="500px"
    >
      <el-form
        ref="formRef"
        :model="form"
        :rules="rules"
        label-width="100px"
      >
        <el-form-item label="客户名称" prop="customerName">
          <el-input v-model="form.customerName" />
        </el-form-item>
        <el-form-item label="联系人" prop="contactPerson">
          <el-input v-model="form.contactPerson" />
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="form.phone" />
        </el-form-item>
        <el-form-item label="电子邮箱" prop="email">
          <el-input v-model="form.email" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" style="width: 100%">
            <el-option label="活跃" value="active" />
            <el-option label="非活跃" value="inactive" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit">确定</el-button>
      </template>
    </el-dialog>

    <!-- 客户指派对话框 -->
    <el-dialog
      v-model="assignDialogVisible"
      title="客户指派"
      width="500px"
    >
      <el-form label-width="100px">
        <el-form-item label="选择客户">
          <el-select
            v-model="selectedCustomers"
            multiple
            filterable
            placeholder="请选择客户"
            style="width: 100%"
          >
            <el-option
              v-for="item in customerList"
              :key="item.id"
              :label="item.customerName"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="选择销售">
          <el-select
            v-model="selectedSalesman"
            filterable
            placeholder="请选择销售人员"
            style="width: 100%"
          >
            <el-option
              v-for="item in salesStaffList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="assignDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleAssignSubmit">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import {
  getCustomerList,
  addCustomer,
  updateCustomer,
  deleteCustomer,
  getSupervisorCustomers,
  assignCustomer
} from '@/api/customer'
import { getSalesStaffList } from '@/api/user'

// 数据列表
const customerList = ref([])
const loading = ref(false)
const pageNum = ref(1)
const pageSize = ref(10)
const total = ref(0)

// 搜索表单
const searchForm = reactive({
  customerName: ''
})

// 表单数据
const formRef = ref(null)
const dialogVisible = ref(false)
const dialogType = ref('add')
const form = reactive({
  id: undefined,
  customerName: '',
  contactPerson: '',
  phone: '',
  email: '',
  address: '',
  status: 'active'
})

// 表单校验规则
const rules = {
  customerName: [{ required: true, message: '请输入客户名称', trigger: 'blur' }],
  contactPerson: [{ required: true, message: '请输入联系人', trigger: 'blur' }],
  phone: [{ required: true, message: '请输入联系电话', trigger: 'blur' }],
  email: [{ required: true, message: '请输入电子邮箱', trigger: 'blur' }],
  address: [{ required: true, message: '请输入地址', trigger: 'blur' }],
  status: [{ required: true, message: '请选择状态', trigger: 'change' }]
}

// 客户指派
const assignDialogVisible = ref(false)
const selectedCustomers = ref([])
const selectedSalesman = ref(null)
const salesStaffList = ref([])

// 获取客户列表
const getList = async () => {
  loading.value = true
  try {
    const res = await getCustomerList({
      pageNum: pageNum.value,
      pageSize: pageSize.value,
      customerName: searchForm.customerName
    })
    customerList.value = res.data.records
    total.value = res.data.total
  } catch (error) {
    console.error('获取客户列表失败：', error)
  } finally {
    loading.value = false
  }
}

// 搜索
const handleSearch = () => {
  pageNum.value = 1
  getList()
}

// 添加客户
const handleAdd = () => {
  dialogType.value = 'add'
  form.id = undefined
  form.customerName = ''
  form.contactPerson = ''
  form.phone = ''
  form.email = ''
  form.address = ''
  form.status = 'active'
  dialogVisible.value = true
}

// 编辑客户
const handleEdit = (row) => {
  dialogType.value = 'edit'
  form.id = row.id
  form.customerName = row.customerName
  form.contactPerson = row.contactPerson
  form.phone = row.phone
  form.email = row.email
  form.address = row.address
  form.status = row.status
  dialogVisible.value = true
}

// 删除客户
const handleDelete = (row) => {
  ElMessageBox.confirm('确认删除该客户吗？', '提示', {
    type: 'warning'
  }).then(async () => {
    try {
      await deleteCustomer(row.id)
      ElMessage.success('删除成功')
      getList()
    } catch (error) {
      console.error('删除客户失败：', error)
    }
  })
}

// 提交表单
const handleSubmit = async () => {
  if (!formRef.value) return
  await formRef.value.validate(async (valid) => {
    if (valid) {
      try {
        if (dialogType.value === 'add') {
          await addCustomer(form)
          ElMessage.success('添加成功')
        } else {
          await updateCustomer(form)
          ElMessage.success('更新成功')
        }
        dialogVisible.value = false
        getList()
      } catch (error) {
        console.error('保存客户失败：', error)
      }
    }
  })
}

// 客户指派
const handleAssign = () => {
  selectedCustomers.value = []
  selectedSalesman.value = null
  assignDialogVisible.value = true
}

// 提交指派
const handleAssignSubmit = async () => {
  if (!selectedCustomers.value.length || !selectedSalesman.value) {
    ElMessage.warning('请选择客户和销售人员')
    return
  }
  try {
    await assignCustomer({
      customerIds: selectedCustomers.value,
      salesmanId: selectedSalesman.value
    })
    ElMessage.success('指派成功')
    assignDialogVisible.value = false
    getList()
  } catch (error) {
    console.error('指派客户失败：', error)
  }
}

// 获取销售人员列表
const getSalesList = async () => {
  try {
    const res = await getSalesStaffList()
    salesStaffList.value = res.data
  } catch (error) {
    console.error('获取销售人员列表失败：', error)
  }
}

// 分页大小改变
const handleSizeChange = (val) => {
  pageSize.value = val
  getList()
}

// 页码改变
const handleCurrentChange = (val) => {
  pageNum.value = val
  getList()
}

onMounted(() => {
  getList()
  getSalesList()
})
</script>

<style scoped>
.customer-container {
  padding: 20px;
}

.customer-header {
  margin-bottom: 20px;
  display: flex;
  gap: 10px;
}

.search-input {
  width: 200px;
}

.pagination {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}
</style> 