
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

