import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/home',
      name: 'home',
      component: HomeView,
        children:[{
            path: '/home/questionnairePreview',
            name: 'questionnairePreview',
            component:()=>import('../views/HomePageView/QuestionnairePreview.vue')
        },{
            path: '/home/publishQuestionnaire',
            name: 'publishQuestionnaire',
            component:()=>import('../views/HomePageView/PublishQuestionnaire.vue')
        },{
            path: '/home/myQuestionnaire',
            name: 'myQuestionnaire',
            component:()=>import('../views/HomePageView/MyQuestionnaire.vue')
        },

        ]
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue')
    },
      {
          path: '/',
          name: 'login',
          component: () => import('../views/Login.vue')
      }
  ]
}
)

export default router
