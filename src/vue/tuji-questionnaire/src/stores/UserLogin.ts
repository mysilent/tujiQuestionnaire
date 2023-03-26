import {ref, computed, reactive} from 'vue'
import { defineStore } from 'pinia'

export const useCounterStore = defineStore('userLoginStore',  {
    state: () => {
        return {
            id:'',
            username:'',
            nickname:''
        }
    }

})
