import {ref, computed, reactive} from 'vue'
import { defineStore } from 'pinia'

export const useCounterStore = defineStore('counter', () => {

    const user = reactive({
        id: '',
        nickname: '',
        username: '',
    })


    return {  }
})
