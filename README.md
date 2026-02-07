## 一、环境要求

- JDK 18+
- Node.js 18+
- Taro CLI 4.0+
- Maven 3.6+

## 二、后端运行（SpringBoot）

1. 进入 `Bcakend` 目录

   ```
   DemoApplication // 右键运行Run Java
   ```

2. 服务启动在 

   ```
   http://localhost:8080
   ```

   - GET /products 获取商品列表
   - POST /orders 创建订单

## 三、前端运行（Taro H5 模式）

1. 进入 `Forntend` 目录

2. 安装依赖：

   ```
   npm install
   ```

3. 启动 H5 开发服务：

   ```
   npm run dev:h5
   ```

4. 浏览器访问：`http://localhost:10086`