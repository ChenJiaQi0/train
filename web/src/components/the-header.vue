<template>
  <a-layout-header class="header">
    <div class="logo" />
    <div style="float: right; color: white">
      您好：{{ member.mobile }}
      <router-link to="/login">退出登录</router-link>
    </div>
    <a-menu
      v-model:selectedKeys="selectedKeys1"
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
    </a-menu>
  </a-layout-header>
</template>

<script setup>
import store from '@/store'
import { ref } from 'vue'
const member = store.state.member

const selectedKeys1 = ref(['2'])
</script>

<style scoped></style>
