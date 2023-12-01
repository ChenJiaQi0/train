<template>
  <div class="order-train">
    <span class="order-train-main">{{ dailyTrainTicket.date }}</span>&nbsp;
    <span class="order-train-main">{{ dailyTrainTicket.trainCode }}</span>次&nbsp;
    <span class="order-train-main">{{ dailyTrainTicket.start }}</span>站
    <span class="order-train-main">({{ dailyTrainTicket.startTime }})</span>&nbsp;
    <span class="order-train-main">——</span>&nbsp;
    <span class="order-train-main">{{ dailyTrainTicket.end }}</span>站
    <span class="order-train-main">({{ dailyTrainTicket.endTime }})</span>&nbsp;

    <div class="order-train-ticket">
      <span v-for="item in seatTypes" :key="item.type">
        <span>{{ item.desc }}</span>：
        <span class="order-train-ticket-main">￥{{ item.price }}</span> &nbsp;
        <span class="order-train-ticket-main">{{ item.count }}</span>张票 &nbsp;
      </span>
    </div>
  </div>
  <a-divider></a-divider>
  <b>勾选要购票的乘客</b>
  <a-checkbox-group v-model:value="passengerChecks" :options="passengerOptions" />
  <div class="order-tickets">
    <a-row class="order-tickets-header">
      <a-col :span="2">乘客</a-col>
      <a-col :span="6">身份证</a-col>
      <a-col :span="4">票种</a-col>
      <a-col :span="4">座位类型</a-col>
    </a-row>
    <a-row class="order-tickets-row" v-for="ticket in  tickets" :key="ticket.passengerId">
      <a-col :span="2">{{ ticket.passengerName }}</a-col>
      <a-col :span="6">{{ ticket.passengerIdCard }}</a-col>
      <a-col :span="4">
        <a-select v-mode:value="ticket.passengerTypes" style="width:100%;">
          <a-select-option v-for="item in PASSENGER_TYPE_ARRAY" :key="item.code" :value="item.code">
            {{ item.desc }}</a-select-option>
        </a-select>
      </a-col>
      <a-col :span="4">
        <a-select v-model:value="ticket.seatTypeCode" style="width:100%">
          <a-select-option :key="item.code" :value="item.code" v-for="item in seatTypes">
            {{ item.desc }}
          </a-select-option>
        </a-select>
      </a-col>
    </a-row>
  </div>
  <div v-if="tickets.length > 0">
    <a-button type="primary" size="large" @click="finishCheckPassenger">提交订单</a-button>
  </div>
  <a-modal @ok="showImageCodeModal" v-model:visible="visible" title="请核对以下信息" style="top:50px; width: 800px;" ok-text="确认" cancel-text="取消">
    <div class="order-tickets">
      <a-row class="order-tickets-header" v-if="tickets.length > 0">
        <a-col :span="3">乘客</a-col>
        <a-col :span="15">身份证</a-col>
        <a-col :span="3">票种</a-col>
        <a-col :span="3">座位类型</a-col>
      </a-row>
      <a-row class="order-tickets-row" v-for="ticket in  tickets" :key="ticket.passengerId">
        <a-col :span="3">{{ ticket.passengerName }}</a-col>
        <a-col :span="15">{{ ticket.passengerIdCard }}</a-col>
        <a-col :span="3">
          <span v-for="item in PASSENGER_TYPE_ARRAY" :key="item.code">
            <span v-if="item.code === ticket.passengerType">{{ item.desc }}</span>
          </span>
        </a-col>
        <a-col :span="3">
          <sapn v-for="item in seatTypes" :key="item.code">
            <span v-if="item.code === ticket.seatTypeCode">
              {{ item.desc }}
            </span>
          </sapn>
        </a-col>
      </a-row>
      <br>
      选座类型chooseSeatType：{{ chooseSeatType }}
      <!-- <br>
      选座对象chooseSeatObj：{{ chooseSeatObj }} -->
      <br />
      <div v-if="chooseSeatType === 0" style="color: red">
        您购买的车票不支持选座
        <div>12306规则：只有全部是一等座或全部是二等座才支持选座</div>
        <div>12306规则：余票小于一定数量时，不允许选座（本项目以20为例）</div>
      </div>
      <div v-else style="text-align: center">
        <a-switch class="choose-seat-item" v-for="item in SEAT_COL_ARRAY" :key="item.code" v-model:checked="chooseSeatObj[item.code + '1']" :checkedChildren="item.desc" :unCheckedChildren="item.desc" />
        <div v-if="tickets.length > 1">
          <a-switch class="choose-seat-item" v-for="item in SEAT_COL_ARRAY" :key="item.code" v-model:checked="chooseSeatObj[item.code + '2']" :checkedChildren="item.desc" :unCheckedChildren="item.desc" />
        </div>
        <div style="color: #999">提示：您可以选择{{ tickets.length }}个座位</div>
      </div>
      <!-- 座位类型SEAT_COL_ARRAY：{{ SEAT_COL_ARRAY }} -->
      <!-- <br> -->
      <!-- 最终购票：{{ tickets }}
      最终选座：{{ chooseSeatObj }} -->
    </div>
  </a-modal>

  <!-- 验证码 -->
  <a-modal v-model:visible="imageCodeModalVisible" :title="null" :footer="null" :closable="false"
           style="top: 50px; width: 400px">
    <p style="text-align: center; font-weight: bold; font-size: 18px">使用验证码削弱瞬时高峰</p>
    <p>
      <a-input v-model:value="imageCode" placeholder="图片验证码">
        <template #suffix>
          <img v-show="!!imageCodeSrc" :src="imageCodeSrc" alt="验证码" v-on:click="loadImageCode()"/>
        </template>
      </a-input>
    </p>
    <a-button type="danger" block @click="handleOk">输入验证码后开始购票</a-button>
  </a-modal>
</template>

<script setup>
import { ref, onMounted, watch, computed } from 'vue'
import axios from 'axios'
import { notification } from 'ant-design-vue'

const chooseSeatType = ref(0)
const SEAT_COL_ARRAY = computed(() => {
  return window.SEAT_COL_ARRAY.filter(item => item.type === chooseSeatType.value)
})
const chooseSeatObj = ref({})
watch(() => SEAT_COL_ARRAY.value, () => {
  chooseSeatObj.value = {}
  for (let i = 0; i <= 2; i++) {
    SEAT_COL_ARRAY.value.forEach((item) => {
      chooseSeatObj.value[item.code + i] = false
    })
  }
  console.log('初始化两排座位，都是未选中：', chooseSeatObj.value)
}, { immediate: true })
const passengers = ref([])
const passengerOptions = ref([])
const passengerChecks = ref([])
const dailyTrainTicket = SessionStorage.get(SESSION_ORDER) || {}
console.log('下单的车次信息', dailyTrainTicket)

const SEAT_TYPE = window.SEAT_TYPE
console.log(SEAT_TYPE)
// 本车次提供的座位类型seatTypes，含票价，余票等信息，例：
// {
//   type: "YDZ",
//   code: "1",
//   desc: "一等座",
//   count: "100",
//   price: "50",
// }
// 关于SEAT_TYPE[KEY]：当知道某个具体的属性xxx时，可以用obj.xxx，当属性名是个变量时，可以使用obj[xxx]
const seatTypes = []
for (const KEY in SEAT_TYPE) {
  const key = KEY.toLowerCase()
  if (dailyTrainTicket[key] >= 0) {
    seatTypes.push({
      type: KEY,
      code: SEAT_TYPE[KEY].code,
      desc: SEAT_TYPE[KEY].desc,
      count: dailyTrainTicket[key],
      price: dailyTrainTicket[key + 'Price']
    })
  }
}

console.log('本车次提供的座位：', seatTypes)

const tickets = ref([])
const PASSENGER_TYPE_ARRAY = window.PASSENGER_TYPE_ARRAY
const visible = ref(false)

watch(() => passengerChecks.value, (newVal, oldVal) => {
  console.log('勾选乘客发生变化', newVal, oldVal)

  tickets.value = []

  passengerChecks.value.forEach((item) => tickets.value.push({
    passengerId: item.id,
    passengerType: item.type,
    seatTypeCode: seatTypes[0].code,
    passengerName: item.name,
    passengerIdCard: item.idCard
  }))
}, { immediate: true })

const handleQueryPassenger = () => {
  axios.get('/member/passenger/query-mine').then((response) => {
    const data = response.data
    if (data.success) {
      passengers.value = data.content
      passengers.value.forEach((item) => passengerOptions.value.push({
        label: item.name,
        value: item
      }))
    } else {
      notification.error({ description: data.message })
    }
  })
}

const finishCheckPassenger = () => {
  console.log('购票列表：', tickets.value)
  if (tickets.value.length > 5) {
    notification.error({ description: '最多只能购买5张车票' })
    return
  }
  const seatTypesTemp = Tool.copy(seatTypes)
  for (let i = 0; i < tickets.value.length; i++) {
    const ticket = tickets.value[i]
    for (let j = 0; j < seatTypesTemp.length; j++) {
      const seatType = seatTypesTemp[j]
      if (ticket.seatTypeCode === seatType.code) {
        seatType.count--
        if (seatType.count < 0) {
          notification.error({ description: seatType.desc + '余票不足' })
          return
        }
      }
    }
  }
  console.log('前端余票检验通过')
  const ticketSeatTypeCodes = []
  for (let i = 0; i < tickets.value.length; i++) {
    const ticket = tickets.value[i]
    ticketSeatTypeCodes.push(ticket.seatTypeCode)
  }
  const ticketSeatTypeCodesSet = Array.from(new Set(ticketSeatTypeCodes))
  console.log('选好的座位类型：', ticketSeatTypeCodesSet)
  if (ticketSeatTypeCodesSet.length !== 1) {
    console.log('选了多种座位，不支持选座')
    chooseSeatType.value = 0
  } else {
    if (ticketSeatTypeCodesSet[0] === SEAT_TYPE.YDZ.code) {
      console.log('一等座选座')
      chooseSeatType.value = SEAT_TYPE.YDZ.code
    } else if (ticketSeatTypeCodesSet[0] === SEAT_TYPE.EDZ.code) {
      console.log('二等座选座')
      chooseSeatType.value = SEAT_TYPE.EDZ.code
    } else {
      console.log('不是一等座或二等座，不支持选座')
      chooseSeatType.value = 0
    }
    if (chooseSeatType.value !== 0) {
      for (let i = 0; i < seatTypes.length; i++) {
        const seatType = seatTypes[i]
        if (ticketSeatTypeCodesSet[0] === seatType.code) {
          if (seatType.count < 20) {
            console.log('余票小于20张就不支持选座')
            chooseSeatType.value = 0
            break
          }
        }
      }
    }
  }
  visible.value = true
}

const handleOk = () => {
  console.log('选好的座位：', chooseSeatObj.value)
  for (let i = 0; i < tickets.value.length; i++) {
    tickets.value[i].seat = null
  }
  let i = -1
  for (const key in chooseSeatObj.value) {
    if (chooseSeatObj.value[key]) {
      i++
      if (i > tickets.value.length - 1) {
        notification.error({ description: '所选座位数大于购票数' })
        return
      }
      tickets.value[i].seat = key
    }
  }
  if (i > -1 && i < (tickets.value.length - 1)) {
    notification.error({ description: '所选座位数小于购票数' })
    return
  }
  console.log('最终购票：', tickets.value)

  axios.post('/business/confirm-order/do', {
    dailyTrainTicketId: dailyTrainTicket.id,
    date: dailyTrainTicket.date,
    trainCode: dailyTrainTicket.trainCode,
    start: dailyTrainTicket.start,
    end: dailyTrainTicket.end,
    tickets: tickets.value
  }).then((resp) => {
    const data = resp.data
    if (data.success) {
      notification.success({ description: '下单成功！' })
    } else {
      notification.error({ description: data.message })
    }
  })
}

/* ------------------- 验证码 --------------------- */
const imageCodeModalVisible = ref()
const imageCodeToken = ref()
const imageCodeSrc = ref()
const imageCode = ref()
/**
 * 加载图形验证码
 */
const loadImageCode = () => {
  imageCodeToken.value = Tool.uuid(8)
  imageCodeSrc.value = process.env.VUE_APP_SERVER + '/business/kaptcha/image-code/' + imageCodeToken.value
}

const showImageCodeModal = () => {
  loadImageCode()
  imageCodeModalVisible.value = true
}

onMounted(() => {
  handleQueryPassenger()
})
</script>

<style scoped>
.order-train .order-train-main {
  font-size: 18px;
  font-weight: bold;
}

.order-train .order-train-ticket {
  margin-top: 15px;
}

.order-train .order-train-ticket .order-train-ticket-main {
  color: red;
  font-size: 18px;
}
.order-tickets {
  margin: 10px 0;
}
.order-tickets .ant-col {
  padding: 5px 10px;
}
.order-tickets .order-tickets-header {
  background-color: cornflowerblue;
  border: solid 1px cornflowerblue;
  color: white;
  font-size: 16px;
  padding: 5px 0;
}
.order-tickets .order-tickets-row {
  border: solid 1px cornflowerblue;
  border-top: none;
  vertical-align: middle;
  line-height: 30px;
}
.order-tickets .choose-seat-item {
  margin: 5px 5px;
}
</style>
