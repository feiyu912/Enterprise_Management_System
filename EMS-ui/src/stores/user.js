import { defineStore } from 'pinia'
import { ref } from 'vue'
import { login as loginApi } from '@/api/user'

export const useUserStore = defineStore('user', () => {
  const token = ref(localStorage.getItem('token') || '')
  const userInfo = ref(JSON.parse(localStorage.getItem('userInfo') || '{}'))

  // 登录
  const login = async (loginForm) => {
    try {
      const res = await loginApi(loginForm)
      if (res.code === 200) {
        token.value = res.data.token
        userInfo.value = res.data.userInfo
        localStorage.setItem('token', res.data.token)
        localStorage.setItem('userInfo', JSON.stringify(res.data.userInfo))
        return Promise.resolve(res)
      }
      return Promise.reject(res)
    } catch (error) {
      return Promise.reject(error)
    }
  }

  // 登出
  const logout = () => {
    token.value = ''
    userInfo.value = {}
    localStorage.removeItem('token')
    localStorage.removeItem('userInfo')
  }

  return {
    token,
    userInfo,
    login,
    logout
  }
}) 