<template>
  <a-button type="primary" @click="showModal" style="float: left"
    >新增</a-button
  >
  <a-table :dataSource="dataSource" :columns="columns" />
  <a-modal
    v-model:visible="visible"
    title="乘客人"
    @ok="handleOk"
    ok-text="确认"
    cancel-text="取消"
  >
    <a-form
      :model="passenger"
      :label-col="{ span: 4 }"
      :wrapper-col="{ span: 20 }"
    >
      <a-form-item label="姓名">
        <a-input v-model:value="passenger.name" />
      </a-form-item>
      <a-form-item label="身份证">
        <a-input v-model:value="passenger.idCard" />
      </a-form-item>
      <a-form-item label="类型">
        <a-select v-model:value="passenger.type">
          <a-select-option value="1">成人</a-select-option>
          <a-select-option value="2">儿童</a-select-option>
          <a-select-option value="3">学生</a-select-option>
        </a-select>
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script setup>
import { ref, reactive } from 'vue'
import { notification } from 'ant-design-vue'
import axios from 'axios'

const dataSource = [
  {
    key: '1',
    name: '胡彦斌',
    age: 32,
    address: '西湖区湖底公园1号'
  },
  {
    key: '2',
    name: '胡彦祖',
    age: 42,
    address: '西湖区湖底公园1号'
  }
]

const columns = [
  {
    title: '姓名',
    dataIndex: 'name',
    key: 'name'
  },
  {
    title: '年龄',
    dataIndex: 'age',
    key: 'age'
  },
  {
    title: '住址',
    dataIndex: 'address',
    key: 'address'
  }
]
const visible = ref(false)
const passenger = reactive({
  id: undefined,
  memeberId: undefined,
  name: undefined,
  idCard: undefined,
  type: undefined,
  createTime: undefined,
  updateTime: undefined
})

const showModal = () => {
  visible.value = true
}

const handleOk = (e) => {
  axios.post('/member/passenger/save', passenger).then((resp) => {
    const data = resp.data
    if (data.success) {
      notification.success({ description: '保存成功!' })
      visible.value = false
    } else {
      notification.error({ description: data.message })
    }
  })
}
</script>

<style scoped></style>
