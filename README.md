# layui-admin
基于layui2.x的带后台的通用管理系统

# 博客地址
[https://blog.csdn.net/GAMEloft9/article/details/79045636](https://blog.csdn.net/GAMEloft9/article/details/79045636)

# 更新说明 #

## v1.0  ##

第一版


## v1.1 ##
1、修改Model检查逻辑

2、修改logback配置 

3、修复个人资料权限bug

## v1.2 ##
1、去掉加载遮罩层

2、解耦后台请求api，封装ajax操作

3、加入oracle版本代码，并注释掉，可根据数据库自行切换

4、修复一些已知bug

## v1.2.1 ##
1、修复添加已删除同名角色保存失败bug

2、去掉角色表角色名称唯一索引

3、修复机构查询bug

4、修复二级机构添加失败bug

5、修复权限设置bug

## v1.2.2 ##
1、权限动态更新失败bug修复

2、已登录过如果再返回登录页面，自动跳转到首页

3、初始化数据修改

4、修复session失效后，login页面和index页面不停来回跳转bug
