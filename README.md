
# 预估时间

## 基础功能封装

基础架构（MMVM）+ 编译环境配置：6h

网络请求：2h

图片加载：2h

登录/分享：4h

友盟：2h

bugly：2h

个推：2h

## UI:

首页UI界面开发：12h

自定义播放进度条：10h

首页弹框UI（分享/举报/设置铃声）：6h

搜索页UI：6h

搜索结果页UI: 6h

已下载页面UI：6h

通用弹出框 / Toast / 错误提示页面 / ：6h

个人中心/登录页面UI：12h

工具类UI：6h

## 逻辑开发/api联调

抖音网页链接爬取：4h

视频去水印 / 音视频分离：15h?

下载功能：4h

首页逻辑+API联调：4h

已下载逻辑+API联调：4h

搜索/搜索结果页 逻辑+API联调：4h

个人中心/登录逻辑+API联调：6h




## UI基础控件

底部导航：BottomNavigationView / BottomNavigationViewEx / TabLayout  +  Fragment  /  QMUITabSegment?

列表刷新（上拉/下拉）：BRAVH + SmartRefreshLayout  /  QMUIPullRefreshLayout

个人中心：QMUIGroupListView

通用 Toast：

通用 Loading：QMUILoadingView

通用 Dialog：QMUIDialog

底部弹框：QMUIBottomSheet

WebView：X5 / QMUIWebView




## BaseLibrary （SpManager，DbManager，YLog，NetManager，ImgLoader，PermissionsManager）


Jetpack 基础框架集成 -> DataBinding + Lifecycle + LiveData + ViewModel + Navgation + Paging

基础工具类 -> AndroidUtilCode

基础UI库 -> QMUI

屏幕适配 -> 今日头条方案（AndroidUtilCode）

SP封装 -> MMKV

数据库集成 -> Room

Log工具 -> YLog

网络/图片/下载：Retrofit + Glide + okdownload   /  Okgo + Glide  /  Ion

json解析：Fastjson / GSON / Moshi

权限请求 -> AndroidUtilCode

路由框架 -> ARouter

Js交互 -> JsBridge

数据统计 -> 友盟

崩溃统计 -> bugly

Push -> 个推

登录 -> 微信/QQ

分享 -> 微信/QQ/微博




## gradle 基础配置：

所有必须依赖的基础配置放在gradle根目录中，所有新建 module，library 统一依赖 base_config.gradle，保证项目中所有 module 配置统一

所有第三库，插件等依赖申明集中放置于 dependency.gradle 文件中

