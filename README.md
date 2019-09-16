# layui-admin
基于layui2.x的带后台的通用管理系统

# 博客地址
[https://blog.csdn.net/GAMEloft9/article/details/79045636](https://blog.csdn.net/GAMEloft9/article/details/79045636)

# 部署步骤
后端应用部署大家应该都很熟悉了，maven打包过后，采用spring-boot的方式启动即可。

这里仅对前后的分离下的前端部署作一个说明：

1、修改layuicms/common/js/config.js，修改生产和测试环境的api请求地址和前端资源请求地址。
2、将layuicms包部署至nginx服务器上，并配置访问连接，避免发生跨域问题。

注意：
如果是本地测试，或者不采用前后端分离的部署方式（前端工程放在webapp下面），那么api请求地址和前端资源请求地址这两个地址是一样的。
启动应用后直接访问http://xxx.xxx.xxx.xxx:xxxx/manager/layuicms/login.html即可。
layuicms在webapp目录下，因此这种方式类似于传统jsp web页面开发模式。

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

## v.1.3.0
1、项目改用spring-boot启动方式

2、修复新增非admin角色用户，登录后跳转401页面的bug

3、修复验证码刷新无效bug

4、修正了几处SQL语句，包括oracle版本初始化存储过程、mysql版本初始化存储过程

5、修复admin角色用户无权限查看组织机构菜单bug