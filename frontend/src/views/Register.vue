<style scoped>
/* 全局输入框样式 */
.input-large {
  height: 40px !important; /* 调整输入框高度 */
  padding: 10px 16px !important; /* 增加内边距，增强舒适性 */
  font-size: 16px !important; /* 增大字体，提升可读性 */
}

/* 按钮样式 */
.button-large {
  height: 42px; /* 与输入框保持一致的高度 */
  font-size: 16px;
  padding: 0 16px;
}
</style>


<script setup lang="ts">
import {ref} from 'vue'
import {useRouter} from 'vue-router'
import {useAuthStore} from '../stores/auth'
import {publicKey} from "../config.ts";
import {message} from 'ant-design-vue'
import type {Rule} from 'ant-design-vue/es/form'
import axios from "axios";
import JSEncrypt from 'jsencrypt'

const router = useRouter()
const authStore = useAuthStore()
const formRef = ref()
const loading = ref(false)

const formState = ref({
  username: '',
  email: '',
  mobile: '',
  password: '',
  confirmPassword: '',
})

const validatePassword = async (_rule: Rule, value: string) => {
  if (!value) {
    return Promise.reject('请输入密码')
  }
  if (value.length < 8) {
    return Promise.reject('密码必须至少8个字符')
  }
  if (!/[A-Z]/.test(value)) {
    return Promise.reject('密码必须包含至少一个大写字母')
  }
  if (!/[a-z]/.test(value)) {
    return Promise.reject('密码必须包含至少一个小写字母')
  }
  if (!/[0-9]/.test(value)) {
    return Promise.reject('密码必须包含至少一个数字')
  }
  return Promise.resolve()
}

const validateConfirmPassword = async (_rule: Rule, value: string) => {
  if (!value) {
    return Promise.reject('请确认密码')
  }
  if (value !== formState.value.password) {
    return Promise.reject('两次输入的密码不一致')
  }
  return Promise.resolve()
}

const rules = {
  username: [
    {required: true, message: '请输入用户名', trigger: 'blur'},
    {min: 3, message: '用户名必须至少3个字符', trigger: 'blur'}
  ],
  email: [
    {required: true, message: '请输入邮箱', trigger: 'blur'},
    {type: 'email', message: '请输入有效的邮箱地址', trigger: 'blur'}
  ],
  mobile: [
    {required: true, message: '请输入手机号码', trigger: 'blur'},
    {pattern: /^1[3-9]\d{9}$/, message: '请输入有效的11位手机号码', trigger: 'blur'}
  ],
  password: [
    {required: true, message: '', trigger: 'blur'}, // 将 required 的 message 设置为空字符串
    {validator: validatePassword, trigger: 'blur'}
  ],
  confirmPassword: [
    {required: true, message: '', trigger: 'blur'}, // 将 required 的 message 设置为空字符串
    {validator: validateConfirmPassword, trigger: 'blur'}
  ]
}

const handleSubmit = async () => {
  try {
    loading.value = true
    await formRef.value.validate()

    // 创建 JSEncrypt 实例并设置公钥
    const encryptor = new JSEncrypt()
    encryptor.setPublicKey(publicKey)

    // 加密密码
    const encryptedPassword = encryptor.encrypt(formState.value.password)
    if (!encryptedPassword) {
      message.error('密码加密失败，请稍后再试')
      return
    }

    // 准备提交的数据
    const submitData = {
      username: formState.value.username,
      email: formState.value.email,
      mobile: formState.value.mobile,
      password: encryptedPassword
    }
    console.log(encryptedPassword);


    // 发送加密后的数据到后端
    const response = await axios.post('/api/register', submitData)

    // 处理后端响应
    if (response.status === 200) {
      // 更新 authStore 中的用户状态
      authStore.user = {
        username: formState.value.username,
        email: formState.value.email,
        mobile: formState.value.mobile
      }
      authStore.isAuthenticated = true

      message.success('注册成功！')
      await router.push('/products')
    } else {
      message.error('注册失败，请稍后再试')
    }
  } catch (error) {
    if (error instanceof Error) {
      message.error(error.message)
    }
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <div class="min-h-screen bg-gray-100 flex items-center justify-center">
    <div class="max-w-md w-full bg-white p-8 rounded-lg shadow-lg">
      <h1 class="text-3xl font-bold mb-8 text-center">用户注册</h1>
      <a-form
          ref="formRef"
          :model="formState"
          :rules="rules"
          layout="vertical"
          @finish="handleSubmit"
      >
      <a-form-item label="用户名" name="username">
        <a-input
            v-model:value="formState.username"
            class="input-large"
            placeholder="请输入用户名"
        />
      </a-form-item>

      <a-form-item label="邮箱" name="email">
        <a-input
            v-model:value="formState.email"
            class="input-large"
            placeholder="请输入邮箱"
        />
      </a-form-item>

      <a-form-item label="手机号码" name="mobile">
        <a-input
            v-model:value="formState.mobile"
            class="input-large"
            placeholder="请输入手机号码"
        />
      </a-form-item>

      <a-form-item
          label="密码"
          name="password"
          extra="密码必须包含至少8个字符，包括大写字母、小写字母和数字"
      >
        <a-input-password
            v-model:value="formState.password"
            class="input-large"
            placeholder="请输入密码"
        />
      </a-form-item>

      <a-form-item label="确认密码" name="confirmPassword">
        <a-input-password
            v-model:value="formState.confirmPassword"
            class="input-large"
            placeholder="请再次输入密码"
        />
      </a-form-item>

      <a-form-item>
        <a-button
            type="primary"
            html-type="submit"
            :loading="loading"
            class="button-large w-full"
        >
          用户注册
        </a-button>
      </a-form-item>
      </a-form>
    </div>
  </div>
</template>
