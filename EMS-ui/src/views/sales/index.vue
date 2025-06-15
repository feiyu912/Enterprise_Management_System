<el-table
  v-loading="loading"
  :data="salesList"
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