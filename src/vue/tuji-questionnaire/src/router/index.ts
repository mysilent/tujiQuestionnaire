import {createRouter, createWebHistory} from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from "@/views/LoginView.vue";
import Login from '../views/Login/Login.vue';
import BackgroundHome from '../views/Background/BackgroundHome.vue'

const router = createRouter({
        history: createWebHistory(import.meta.env.BASE_URL),
        routes: [
            {
                path: '/home',
                name: 'home',
                component: HomeView,
                children: [{
                    path: '/home/questionnaireCenter',
                    name: 'questionnaireCenter',
                    component: () => import('../views/HomePageView/QuestionnaireCenter.vue'),
                }, {
                    path: '/home/questionnaireCenter/answer',
                    name: 'answer',
                    component: () => import('../views/QuestionCenter/Answer.vue'),
                }, {
                    path: '/home/publishQuestionnaire',
                    name: 'publishQuestionnaire',
                    component: () => import('../views/PublishQuestion/QuestionCreat.vue'),
                }, {
                    path: '/home/myQuestionnaire',
                    name: 'myQuestionnaire',
                    component: () => import('../views/HomePageView/MyQuestionnaire.vue'),
                }, {
                    path: '/home/myQuestionnaire/preview',
                    name: 'preview',
                    component: () => import('../views/MyQuestion/Preview.vue'),
                }, {
                    path: '/home/myQuestionnaire/revise',
                    name: 'revise',
                    component: () => import('../views/MyQuestion/Revise.vue'),
                },
                ]
            }, {
                path: '/personal',
                name: 'personal',
                component: () => import('../views/PersonalView.vue'),
                children: [
                    {
                        path: '/personal/personalCenter/personalInformation',
                        name: 'personalInformation',
                        component: () => import('../views/Personal/son/personalInformation.vue'),
                    }, {
                        path: '/personal/personalCenter/historicalAnswers',
                        name: 'historicalAnswers',
                        component: () => import('../views/Personal/son/historicalAnswers.vue'),
                    }, {
                        path: '/personal/personalCenter/changePassword',
                        name: 'changePassword',
                        component: () => import('../views/Personal/son/changePassword.vue'),
                    },
                ]
            }, {
                path: '/login',
                name: 'login',
                redirect: '/',
                component: LoginView,
                children: [{
                    path: '/',
                    name: 'login',
                    component: Login,
                }, {
                    path: '/enroll',
                    name: 'enroll',
                    component: () => import('../views/Login/Enroll.vue')
                },
                ]
            },{
                path: '/personal/personalCenter/historicalAnswers/personalAnswer',
                name: 'personalAnswer',
                component: () => import('../views/Personal/PersonalAnswer.vue')
            },{
                path: '/top',
                name: 'top',
                component: () => import('../components/top.vue')
            },{
                path: '/background',
                name: 'background',
                component: () => import('../views/Background/ManageView.vue'),
                children: [{
                    path: '/background/home',
                    name: 'backgroundUser',
                    component: BackgroundHome
                },{
                    path: '/user',
                    name: 'backgroundUser',
                    component: () => import('../views/Background/User.vue')
                },{
                    path: '/administrator',
                    name: 'backgroundAdministrator',
                    component: () => import('../views/Background/Administrator.vue')
                }
                ]
            },
        ]
    }
)
export default router
