
## 通用框架配置和第三库引用列举：

1.项目整体架构采用 MVVM 模式的 AAC 架构

2.support 库采用最新的 androidx 库依赖（需要compileSdkVersion > 28, gradle plugin > 3.2.0）

3.Jetpack 全家桶：Lifecycles, LiveData, ViewModel, DataBinding, Room, WorkManager?(android-job), Navgation, Paging，android-ktx

4.常用基础框架封装（必须）：Retrofit，Glide，Fastjson，ButterKnife，Router，权限请求，日志工具，

5.常用基础框架封装（可选）：RxAndroid，PUSH推送，事件统计，崩溃检测，第三方登录/分享，支付，JS交互，事件总线，插件/热修复，
                         内存检测，文件下载/上传，音视频播放，地图，二维码，资源混淆，WebSocket/MQTT，protobuf，anko

6.UI相关封装：Toast，通用 Loading，通用 Dialog，顶/底部导航，列表控件（上/下拉刷新），侧滑抽屉，圆角图片，动画相关

7.其余常用工具封装：环境管理，Activity栈管理，SP封装，Crash封装，String操作，文件操作，屏幕相关，设备信息相关，系统信息相关（可考虑直接集成第三库工具库）

8.Resource 集成：mainfest基础配置申明，常用颜色值，shape/selector 模板

9.屏幕适配：全新项目可考虑直接采用今日头条屏幕适配方案（ https://mp.weixin.qq.com/s/d9QCoBP6kV9VSWvVldVVwA ）


## 计划开发的框架配置集成 ( // 开头的表示未完成 )

Jetpack 基础框架集成 -> DataBinding + Lifecycle + LiveData + ViewModel + Navgation + Paging

网络请求模块 -> RetrofitManager

图片请求封装 -> Glide

路由跳转封装 -> JMRouter

注解绑定工具 -> ButterKnife / KotterKnife

响应式框架集成 -> RxAndroid / Rxjava

日志工具 -> JLog

异常捕获和上报 -> Bugly + CrashManager

Activity 栈管理 -> ActivityStackHelper

SP封装 -> SharedPreferencesHelper

// 通用 Toast，Loading，Dialog -> JMToast

文件操作工具 -> StringUtils

权限请求 -> PermissionUtils

PUSH推送 -> 个推

数据统计 -> 神策

登录/分享 -> 微信/支付宝/微博

支付 -> 微信/支付宝

数据库集成 -> Room

// 后台定时任务工具 -> 使用 android-job 进行封装

// 文件下载封装 -> FileDownload

// 屏幕适配工具 -> 

// 二维码封装 -> 


## gradle 基础配置：

所有必须依赖的基础配置放在gradle根目录中，所有新建 module，library 统一依赖 base_config.gradle，保证项目中所有 module 配置统一

所有第三库，插件等依赖申明集中放置于 dependency.gradle 文件中



基础工具类 -> AndroidUtilCode
基础UI库 -> QMUI
屏幕适配 -> 今日头条方案
SP封装 -> MMKV
Log工具 -> YLog ?
网络请求 -> Retrofit/Okgo/icon ？
图片加载 -> Glide/Ion ？
文件下载 -> okdownload/PRDownloader/Okgo/icon ？
权限请求 -> RxPermissions/AndPermission
路由框架 -> ARouter

数据统计 -> 友盟
崩溃统计 -> bugly
登录 -> 微信/QQ
分享 -> 微信/QQ/微博

