<template>
    <p>
        <a-space>
            <a-button type="primary" @click="handleQuery()">刷新</a-button>
                            <a-button type="primary" @click="onAdd">新增</a-button>
        </a-space>
    </p>
    <a-table :dataSource="students"
             :columns="columns"
             :pagination="pagination"
             @change="handleTableChange"
             :loading="loading">
        <template #bodyCell="{ column, record }">
            <template v-if="column.dataIndex === 'operation'">
                    <a-space>
                        <a-popconfirm
                                title="删除后不可恢复，确认删除?"
                                @confirm="onDelete(record)"
                                ok-text="确认" cancel-text="取消">
                            <a style="color: red">删除</a>
                        </a-popconfirm>
                        <a @click="onEdit(record)">编辑</a>
                    </a-space>
            </template>
        </template>
    </a-table>
        <a-modal v-model:visible="visible" title="" @ok="handleOk"
                 ok-text="确认" cancel-text="取消">
            <a-form :model="student" :label-col="{span: 4}" :wrapper-col="{ span: 20 }">
                        <a-form-item label="手机号">
                                <a-input v-model:value="student.phone"/>
                        </a-form-item>
                        <a-form-item label="姓名">
                                <a-input v-model:value="student.username"/>
                        </a-form-item>
                        <a-form-item label="性别">
                                <a-input v-model:value="student.gender"/>
                        </a-form-item>
                        <a-form-item label="电子邮箱">
                                <a-input v-model:value="student.email"/>
                        </a-form-item>
                        <a-form-item label="学校">
                                <a-input v-model:value="student.school"/>
                        </a-form-item>
            </a-form>
        </a-modal>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { notification } from 'ant-design-vue'
import axios from 'axios'

const visible = ref(false)
const student = ref({
  id: undefined,
  phone: undefined,
  username: undefined,
  gender: undefined,
  email: undefined,
  school: undefined
})
const students = ref([])
// 分页的三个属性名是固定的
const pagination = ref({
  total: 0,
  current: 1,
  pageSize: 10
})
const loading = ref(false)
const columns = [
  {
    title: '手机号',
    dataIndex: 'phone',
    key: 'phone'
  },
  {
    title: '姓名',
    dataIndex: 'username',
    key: 'username'
  },
  {
    title: '性别',
    dataIndex: 'gender',
    key: 'gender'
  },
  {
    title: '电子邮箱',
    dataIndex: 'email',
    key: 'email'
  },
  {
    title: '学校',
    dataIndex: 'school',
    key: 'school'
  },
  {
    title: '操作',
    dataIndex: 'operation'
  }
]

const onAdd = () => {
  student.value = {}
  visible.value = true
}

const onEdit = (record) => {
  student.value = window.Tool.copy(record)
  visible.value = true
}

const onDelete = (record) => {
  axios.delete('/business/admin/student/delete/' + record.id).then((response) => {
    const data = response.data
    if (data.success) {
      notification.success({ description: '删除成功！' })
      handleQuery({
        page: pagination.value.current,
        size: pagination.value.pageSize
      })
    } else {
      notification.error({ description: data.message })
    }
  })
}

const handleOk = () => {
  axios.post('/business/admin/student/save', student.value).then((response) => {
    const data = response.data
    if (data.success) {
      notification.success({ description: '保存成功！' })
      visible.value = false
      handleQuery({
        page: pagination.value.current,
        size: pagination.value.pageSize
      })
    } else {
      notification.error({ description: data.message })
    }
  })
}

const handleQuery = (param) => {
  if (!param) {
    param = {
      page: 1,
      size: pagination.value.pageSize
    }
  }
  loading.value = true
  axios.get('/business/admin/student/query-list', {
    params: {
      page: param.page,
      size: param.size
    }
  }).then((response) => {
    loading.value = false
    const data = response.data
    if (data.success) {
      students.value = data.content.list
      // 设置分页控件的值
      pagination.value.current = param.page
      pagination.value.total = data.content.total
    } else {
      notification.error({ description: data.message })
    }
  })
}

const handleTableChange = (pagination) => {
  handleQuery({
    page: pagination.current,
    size: pagination.pageSize
  })
}

onMounted(() => {
  handleQuery({
    page: 1,
    size: pagination.value.pageSize
  })
})
</script>
