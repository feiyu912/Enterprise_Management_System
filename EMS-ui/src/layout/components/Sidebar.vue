<template>
  <el-menu
    :default-active="activeMenu"
    class="sidebar-menu"
    :collapse="isCollapse"
    background-color="#304156"
    text-color="#bfcbd9"
    active-text-color="#409EFF"
    router
  >
    <template v-for="item in menuList" :key="item.path">
      <!-- 没有子菜单 -->
      <el-menu-item v-if="!item.children" :index="item.path">
        <el-icon><component :is="item.icon" /></el-icon>
        <template #title>{{ item.title }}</template>
      </el-menu-item>

      <!-- 有子菜单 -->
      <el-sub-menu v-else :index="item.path">
        <template #title>
          <el-icon><component :is="item.icon" /></el-icon>
          <span>{{ item.title }}</span>
        </template>
        <el-menu-item
          v-for="child in item.children"
          :key="child.path"
          :index="child.path"
        >
          <el-icon><component :is="child.icon" /></el-icon>
          <template #title>{{ child.title }}</template>
        </el-menu-item>
      </el-sub-menu>
    </template>
  </el-menu>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRoute } from 'vue-router'
import { House, User, Goods, UserFilled, Calendar, Setting } from '@element-plus/icons-vue'
import menuList from '@/config/menu'

const route = useRoute()
const isCollapse = ref(false)

const activeMenu = computed(() => {
  return route.path
})
</script>

<style scoped>
.sidebar-menu {
  height: 100%;
  border-right: none;
}

.sidebar-menu:not(.el-menu--collapse) {
  width: 200px;
}
</style> 