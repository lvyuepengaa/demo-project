import axios from 'axios';
import { Product, OrderResult } from '../../types/index';

const instance = axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 5000,
});

// 获取商品列表
export const fetchProducts = (): Promise<Product[]> => {
  return instance.get('/products').then(res => res.data);
};

// 创建订单
export const createOrder = (productId: number, quantity: number): Promise<OrderResult> => {
  return instance.post('/orders', { productId, quantity }).then(res => res.data);
};