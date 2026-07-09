# 味小栈 - 智慧餐饮交易服务平台

一个基于 Spring Boot + Vue + 微信小程序的全栈智慧餐饮交易服务平台，支持管理端后台与用户端小程序，实现从菜品管理、订单处理到数据统计的完整餐饮业务闭环。

## 项目结构

```
sky-take-out/
├── backend/                          # 后端 Spring Boot 多模块 Maven 项目
│   ├── pom.xml                       # 父 POM（依赖管理）
│   ├── sql/
│   │   └── sky_take_out.sql          # 数据库初始化脚本
│   ├── sky-common/                   # 公共模块
│   │   └── src/main/java/com/sky/
│   │       ├── constant/             # 常量定义
│   │       ├── context/              # ThreadLocal 上下文
│   │       ├── enumeration/          # 枚举类
│   │       ├── exception/            # 全局异常处理
│   │       ├── json/                 # Jackson 序列化配置
│   │       ├── properties/           # 配置属性类
│   │       ├── result/               # 统一响应结果
│   │       └── utils/                # 工具类（JWT、阿里云OSS、微信支付等）
│   ├── sky-pojo/                     # 实体类模块
│   │   └── src/main/java/com/sky/
│   │       ├── dto/                  # 数据传输对象
│   │       ├── entity/               # 数据库实体
│   │       └── vo/                   # 视图对象
│   └── sky-server/                   # 服务模块（主启动模块）
│       └── src/main/java/com/sky/
│           ├── SkyApplication.java   # Spring Boot 启动类
│           ├── annotation/           # 自定义注解（AutoFill）
│           ├── aspect/               # AOP 切面
│           ├── config/               # 配置类（WebMvc、Knife4j、Redis 等）
│           ├── controller/           # 控制器
│           │   ├── admin/            # 管理端 API
│           │   └── user/             # 用户端 API
│           ├── interceptor/          # JWT 拦截器
│           ├── mapper/               # MyBatis Mapper
│           ├── service/              # 业务逻辑层
│           └── websocket/            # WebSocket 服务
├── frontend/                         # 管理端前端（Vue 2 + TypeScript）
│   ├── src/
│   │   ├── api/                      # API 接口封装
│   │   ├── layout/                   # 页面布局组件
│   │   ├── router/                   # 路由配置
│   │   ├── store/                    # Vuex 状态管理
│   │   ├── styles/                   # 全局样式
│   │   ├── utils/                    # 工具函数
│   │   └── views/                    # 页面视图
│   │       ├── login/                # 登录页
│   │       ├── dashboard/            # 工作台
│   │       ├── category/             # 分类管理
│   │       ├── dish/                 # 菜品管理
│   │       ├── setmeal/              # 套餐管理
│   │       ├── orderDetails/         # 订单管理
│   │       ├── employee/             # 员工管理
│   │       ├── statistics/           # 数据统计
│   │       ├── inform/               # 通知管理
│   │       └── chart/                # 图表展示
│   ├── package.json
│   └── vue.config.js
└── mp-weixin/                        # 用户端微信小程序（uni-app）
    └── pages/
        ├── index/                    # 首页
        ├── category/                 # 菜品分类
        ├── cart/                     # 购物车
        ├── address/                  # 地址管理
        ├── order/                    # 下单
        ├── pay/                      # 支付
        ├── success/                  # 支付成功
        ├── historyOrder/             # 历史订单
        ├── login/                    # 用户登录
        ├── my/                       # 个人中心
        ├── remark/                   # 订单备注
        └── nonet/                    # 网络异常
```

## 技术栈

### 后端

| 技术             | 版本     | 说明                     |
| ---------------- | -------- | ------------------------ |
| Spring Boot      | 2.7.3    | 核心框架                 |
| MyBatis          | 2.2.0    | ORM 持久层               |
| MySQL            | 8.0      | 关系型数据库             |
| Redis            | —        | 缓存与 Session 管理      |
| Druid            | 1.2.1    | 数据库连接池             |
| Spring AOP       | 1.9.4    | 切面编程（公共字段填充）   |
| JWT (jjwt)       | 0.9.1    | 无状态认证鉴权           |
| Knife4j          | 3.0.2    | API 文档生成（Swagger 增强） |
| Fastjson         | 1.2.76   | JSON 序列化              |
| PageHelper       | 1.3.0    | 分页插件                 |
| Lombok           | 1.18.20  | 代码简化                 |
| Apache POI       | 3.16     | Excel 报表导出           |
| WebSocket        | —        | 订单实时通知             |
| 阿里云 OSS        | 3.10.2   | 文件存储                 |
| 微信支付 V3       | —        | 微信支付集成             |

### 管理端前端

| 技术             | 版本     | 说明                     |
| ---------------- | -------- | ------------------------ |
| Vue              | 2.6.10   | 前端框架                 |
| TypeScript       | 3.6.2    | 类型安全                 |
| Vue Router       | 3.1.2    | 路由管理                 |
| Vuex             | 3.1.1    | 状态管理                 |
| Element UI       | 2.12.0   | 桌面端 UI 组件库         |
| ECharts          | 5.3.2    | 数据可视化图表           |
| Axios            | 0.19.0   | HTTP 请求库              |
| Sass             | 1.32.13  | CSS 预处理器             |

### 用户端小程序

| 技术             | 说明                     |
| ---------------- | ------------------------ |
| uni-app          | 跨平台小程序框架          |
| 微信原生组件      | 微信小程序 UI 组件        |

## 功能模块

### 管理端（Web）

| 模块     | 功能描述                                       |
| -------- | ---------------------------------------------- |
| 登录认证 | JWT 无状态登录，拦截器校验，账号状态管理        |
| 工作台   | 今日运营数据概览、订单趋势、菜品排行等           |
| 分类管理 | 菜品/套餐分类的增删改查、启用/禁用              |
| 菜品管理 | 菜品 CRUD、口味配置、图片上传、起售/停售         |
| 套餐管理 | 套餐 CRUD、包含菜品关联、图片上传、起售/停售     |
| 订单管理 | 订单查询、接单、拒单、派送、完成、取消等状态流转 |
| 员工管理 | 员工账号 CRUD、账号状态管理、密码重置           |
| 数据统计 | 营业额/用户/订单统计、销量 Top10 报表、Excel 导出 |
| 文件上传 | 阿里云 OSS 通用上传接口                        |
| 实时通知 | WebSocket 推送新订单提醒                       |

### 用户端（微信小程序）

| 模块     | 功能描述                                       |
| -------- | ---------------------------------------------- |
| 微信登录 | 微信授权一键登录，JWT 鉴权                      |
| 首页浏览 | 分类导航、菜品/套餐展示、购物车快捷入口          |
| 菜品浏览 | 按分类筛选菜品，查看详情与口味选择               |
| 购物车   | 菜品添加/删除/数量修改，清空购物车               |
| 地址管理 | 收货地址增删改查，默认地址设置                   |
| 下单支付 | 订单提交、微信支付集成、支付状态回调处理          |
| 历史订单 | 订单列表查询，按状态筛选，订单详情查看           |
| 个人中心 | 用户信息展示                                   |

## 快速开始

### 环境要求

- **JDK** 1.8+
- **Maven** 3.6+
- **MySQL** 8.0+
- **Redis** 5.0+
- **Node.js** 16.x（推荐 20.x）
- **微信开发者工具**（小程序端）

### 1. 克隆项目

```bash
git clone git@github.com:Hinacore/catering-order-platform.git
cd catering-order-platform
```

### 2. 初始化数据库

在 MySQL 中创建数据库，然后执行 SQL 脚本：

```sql
CREATE DATABASE sky_take_out CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

导入 `backend/sql/sky_take_out.sql`：

```bash
mysql -u root -p sky_take_out < backend/sql/sky_take_out.sql
```

### 3. 配置后端

复制配置模板并填入真实值：

```bash
cp backend/sky-server/src/main/resources/application-dev.yml.example \
   backend/sky-server/src/main/resources/application-dev.yml
```

编辑 `application-dev.yml`，配置以下信息：

```yaml
sky:
  datasource:
    host: localhost
    port: 3306
    database: sky_take_out
    username: 你的数据库用户名
    password: 你的数据库密码

  redis:
    host: localhost
    port: 6379
    password: 你的Redis密码

  alioss:
    access-key-id: 你的阿里云AccessKey
    access-key-secret: 你的阿里云AccessKeySecret
    bucket-name: 你的OSS Bucket名称

  wechat:
    appid: 你的小程序AppID
    secret: 你的小程序Secret
    # ... 其他微信支付相关配置
```

### 4. 启动后端

```bash
cd backend
mvn clean install -DskipTests
cd sky-server
mvn spring-boot:run
```

后端服务启动后访问：
- 管理端 API 文档：http://localhost:8080/doc.html
- 用户端 API 文档：http://localhost:8080/doc.html

### 5. 启动管理端前端

```bash
cd frontend
npm install --legacy-peer-deps
npm run serve
```

访问 http://localhost:3333 进入管理端。

### 6. 启动小程序

1. 使用微信开发者工具打开 `mp-weixin/` 目录
2. 配置小程序 AppID
3. 编译运行即可预览

## 配置说明

本项目敏感配置采用**环境变量外置**策略，所有密钥、密码等敏感信息存储在 `application-dev.yml` 中（已加入 `.gitignore`），仓库仅提供 `.example` 模板文件。

| 配置文件                                              | 说明                     |
| ----------------------------------------------------- | ------------------------ |
| `application.yml`                                     | 公共配置，可提交         |
| `application-dev.yml`（需自行创建）                    | 开发环境敏感配置，禁止提交 |
| `application-dev.yml.example`                         | 开发环境配置模板            |

## API 文档

启动后端后访问 Knife4j 接口文档：

- 管理端：`http://localhost:8080/doc.html`（分组：管理端接口）
- 用户端：`http://localhost:8080/doc.html`（分组：用户端接口）

## 致谢

- [Spring Boot](https://spring.io/projects/spring-boot)
- [MyBatis](https://mybatis.org/)
- [Vue.js](https://vuejs.org/)
- [Element UI](https://element.eleme.io/)
- [ECharts](https://echarts.apache.org/)
- [uni-app](https://uniapp.dcloud.io/)
- [Knife4j](https://doc.xiaominfo.com/)