<template>
  <a-layout-header class="header">
    <div class="logo" />
    <div style="float: right; color: white">
      您好：{{ member.mobile }}
      <router-link to="/login">退出登录</router-link>
    </div>
    <a-menu
      v-model:selectedKeys="selectedKeys"
      theme="dark"
      mode="horizontal"
      :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="/welcome">
        <router-link to="/welcome"> <coffee-outlined /> 欢迎 </router-link>
      </a-menu-item>
      <a-menu-item key="/passenger">
        <router-link to="/passenger">
          <user-outlined /> 乘车人管理
        </router-link>
      </a-menu-item>

      <a-menu-item key="/ticket">
        <router-link to="/ticket">
          <user-outlined /> 余票查询
        </router-link>
      </a-menu-item>

      <a-menu-item key="/seat">
        <router-link to="/seat">
          <usergroup-add-outlined /> 座位销售图
        </router-link>
      </a-menu-item>
    </a-menu>
  </a-layout-header>
</template>

<script setup>
import router from '@/router'
import store from '@/store'
import { ref, watch } from 'vue'
const member = store.state.member

const selectedKeys = ref([])

watch(
  () => router.currentRoute.value.path,
  (newValue) => {
    console.log('watch', newValue)
    selectedKeys.value = []
    selectedKeys.value.push(newValue)
  },
  { immediate: true }
)
</script>

<style scoped></style>
