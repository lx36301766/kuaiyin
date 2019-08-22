
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



## UI

底部导航：BottomNavigationView / BottomNavigationViewEx / TabLayout  +  Fragment  /  QMUITabSegment?

首页列表：ViewPager + Fragment + RecyclerView ( BRAVH + SmartRefreshLayout )

列表刷新（上拉/下拉）：BRAVH + SmartRefreshLayout  /  QMUIPullRefreshLayout

底部弹框：QMUIBottomSheet

个人中心：QMUIGroupListView

通用 Toast：

通用 Loading：QMUILoadingView

通用 Dialog：QMUIDialog



## BaseLibrary （SpManager，DbManager，YLog，NetManager，ImgLoader，PermissionsManager）


Jetpack 基础框架集成 -> DataBinding + Lifecycle + LiveData + ViewModel + Navgation + Paging

基础工具类 -> AndroidUtilCode

基础UI库 -> QMUI

屏幕适配 -> 今日头条方案（AndroidUtilCode）

SP封装 -> MMKV

数据库集成 -> Room

Log工具 -> YLog

网络/图片/下载：Retrofit + Glide + okdownload   /  Okgo + Glide  /  Ion

权限请求 -> AndroidUtilCode

路由框架 -> ARouter


数据统计 -> 友盟

崩溃统计 -> bugly

Push -> 个推

登录 -> 微信/QQ

分享 -> 微信/QQ/微博



## gradle 基础配置：

所有必须依赖的基础配置放在gradle根目录中，所有新建 module，library 统一依赖 base_config.gradle，保证项目中所有 module 配置统一

所有第三库，插件等依赖申明集中放置于 dependency.gradle 文件中

