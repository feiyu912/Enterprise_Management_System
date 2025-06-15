import dayjs from 'dayjs'

// 格式化日期
export function formatDate(date, format = 'YYYY-MM-DD HH:mm:ss') {
  if (!date) return ''
  return dayjs(date).format(format)
}

// 格式化金额
export function formatAmount(amount) {
  if (!amount) return '0.00'
  return Number(amount).toFixed(2)
}

// 格式化百分比
export function formatPercent(value) {
  if (!value) return '0%'
  return `${value}%`
} 