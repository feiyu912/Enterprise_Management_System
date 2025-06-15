<template>
  <div class="dashboard-container">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card shadow="hover" class="dashboard-card">
          <template #header>
            <div class="card-header">
              <span>用户总数</span>
            </div>
          </template>
          <div class="card-body">
            <el-statistic :value="1234">
              <template #title>
                <div style="display: inline-flex; align-items: center">
                  活跃用户
                  <el-icon style="margin-left: 4px">
                    <User />
                  </el-icon>
                </div>
              </template>
            </el-statistic>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" class="dashboard-card">
          <template #header>
            <div class="card-header">
              <span>产品总数</span>
            </div>
          </template>
          <div class="card-body">
            <el-statistic :value="5678">
              <template #title>
                <div style="display: inline-flex; align-items: center">
                  在售产品
                  <el-icon style="margin-left: 4px">
                    <Goods />
                  </el-icon>
                </div>
              </template>
            </el-statistic>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" class="dashboard-card">
          <template #header>
            <div class="card-header">
              <span>订单总数</span>
            </div>
          </template>
          <div class="card-body">
            <el-statistic :value="9012">
              <template #title>
                <div style="display: inline-flex; align-items: center">
                  本月订单
                  <el-icon style="margin-left: 4px">
                    <ShoppingCart />
                  </el-icon>
                </div>
              </template>
            </el-statistic>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" class="dashboard-card">
          <template #header>
            <div class="card-header">
              <span>系统状态</span>
            </div>
          </template>
          <div class="card-body">
            <el-statistic :value="100">
              <template #title>
                <div style="display: inline-flex; align-items: center">
                  系统运行
                  <el-icon style="margin-left: 4px">
                    <Monitor />
                  </el-icon>
                </div>
              </template>
              <template #suffix>
                <span style="color: #67C23A">%</span>
              </template>
            </el-statistic>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :span="12">
        <el-card shadow="hover">
          <template #header>
            <div class="card-header">
              <span>最近订单</span>
            </div>
          </template>
          <el-table :data="recentOrders" style="width: 100%">
            <el-table-column prop="id" label="订单号" width="180" />
            <el-table-column prop="customer" label="客户" width="180" />
            <el-table-column prop="amount" label="金额" />
            <el-table-column prop="status" label="状态">
              <template #default="scope">
                <el-tag :type="scope.row.status === '已完成' ? 'success' : 'warning'">
                  {{ scope.row.status }}
                </el-tag>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="hover">
          <template #header>
            <div class="card-header">
              <span>系统公告</span>
            </div>
          </template>
          <el-timeline>
            <el-timeline-item
              v-for="(activity, index) in activities"
              :key="index"
              :timestamp="activity.timestamp"
              :type="activity.type"
            >
              {{ activity.content }}
            </el-timeline-item>
          </el-timeline>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { User, Goods, ShoppingCart, Monitor } from '@element-plus/icons-vue'

const recentOrders = ref([
  {
    id: 'ORD20240101001',
    customer: '张三',
    amount: '¥1,234.00',
    status: '已完成'
  },
  {
    id: 'ORD20240101002',
    customer: '李四',
    amount: '¥2,345.00',
    status: '处理中'
  },
  {
    id: 'ORD20240101003',
    customer: '王五',
    amount: '¥3,456.00',
    status: '已完成'
  }
])

const activities = ref([
  {
    content: '系统更新至最新版本',
    timestamp: '2024-01-01 10:00:00',
    type: 'primary'
  },
  {
    content: '新增产品管理功能',
    timestamp: '2024-01-02 14:30:00',
    type: 'success'
  },
  {
    content: '优化用户界面体验',
    timestamp: '2024-01-03 09:15:00',
    type: 'info'
  }
])
</script>

<style scoped>
.dashboard-container {
  padding: 20px;
}

.dashboard-card {
  height: 180px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.card-body {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100px;
}
</style> 