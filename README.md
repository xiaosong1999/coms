#COMS项目管理员后台接口与小程序接口
>开发环境IDEA+MySQL 8.0.26，运行环境Tomcat8&9  
> 数据库配置文件位于coms_db下的resources，sql文件需要清库使用

    供应商=提供商品的商家  
    采购商=档口=食堂购买方
    禁止物理删除任何用户，会导致订单或其他页面报错，涉及删除的统一修改状态

##一些功能添加想法
* 将密码加密相关BUG修复后引入
* 修改前端页面
* 整理代码（不一定

##更新记录
2021-12-13 修复404和500页面的跳转问题 数据库文件修复

2021-12-8 毕业设计分支开始

2021-11-26 由于修改相关用户资料会导致加密密码被二次加密，暂时去除密码加密相关

2021-11-8 最终版push更新

2021-10-5 功能覆盖基本全部完成

2021-9-29 测试版本4 修复订单页excel导出下载问题  小程序priceList接口优化

2021-9-28 测试版本3

2021-9-22 测试版本2

2021-9-22 测试版本1

2021-9-18 分页按钮优化，增加页码跳转下拉框

2021-9-15 微信端代码合并 数据库密码改为服务器

2021-9-14 小程序服务完善

2021-9-13 基本功能全部完成

2021-9-10 订单统计导出excel

2021-9-8 部分UI修改 按日期筛选功能

2021-9-7 订单页模糊搜索待完善，excel导出待完善

2021-10-5 基本功能全部完成

2021-9-4 用户管理-管理员界面完善

2021-9-3 stall_list修复 侧边栏跳转修改

2021-9-2 演示版 基本功能覆盖
