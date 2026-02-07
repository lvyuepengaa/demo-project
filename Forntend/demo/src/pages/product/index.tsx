
import { useState, useEffect } from 'react';
import { View, Text, Button, Input } from '@tarojs/components';
import { fetchProducts, createOrder } from '../../api';
import { Product, OrderResult } from '../../../types/index';
import './index.css';

export default function Index() {
  const [products, setProducts] = useState<Product[]>([]);
  const [selectedId, setSelectedId] = useState<number | null>(null);
  const [quantity, setQuantity] = useState<string>('1');
  const [orderInfo, setOrderInfo] = useState<OrderResult | null>(null);
  const [errMsg, setErrMsg] = useState('');

  // 加载商品列表
  useEffect(() => {
    fetchProducts().then(res => {
      setProducts(res);
      if (res.length > 0) {
        setSelectedId(res[0].id)
      } else {
        setErrMsg('加载商品失败');
      };
    }).catch((e) => {
      setErrMsg('加载商品失败：' + (e.message || '网络异常'));
    });;
  }, []);

  // 提交订单
  const handleSubmit = async () => {
    setErrMsg('');
    setOrderInfo(null);
    if (!selectedId) {
      setErrMsg('请选择商品');
      return;
    }
    const num = parseInt(quantity, 10);
    if (isNaN(num) || num <= 0) {
      setErrMsg('数量必须大于0');
      return;
    }
    try {
      const res = await createOrder(selectedId, num);
      setOrderInfo(res);
      const productsAlter = products.map(p => {
        if (p.id === selectedId) {
          return {
            ...p, 
            stock: p.stock - num // 赋值：库存 = 原库存 - 购买数量
          };
        }
        return p;
      });
      setProducts(productsAlter);
    } catch (e: any) {
      setErrMsg(e.response?.data?.error || '下单失败');
    }
  };

  return (
    <View className="page">
      <Text className="title">商品列表</Text>

      {/* 商品列表 */}
      <View className="list">
        {products.map(p => (
          <View
            key={p.id}
            className={`item ${selectedId === p.id ? 'active' : ''}`}
            onClick={() => setSelectedId(p.id)}
          >
            <Text>{p.name} - ￥{p.price} - 库存:{p.stock}</Text>
          </View>
        ))}
      </View>

      {/* 数量输入 */}
      <View className="form">
        <Text>购买数量：</Text>
        <Input
          type="number"
          value={quantity}
          onInput={(e) => setQuantity(e.detail.value)}
          className="input"
        />
      </View>

      {/* 提交按钮 */}
      <Button onClick={handleSubmit} className="btn">提交订单</Button>

      {/* 错误提示 */}
      {errMsg && <Text className="err">{errMsg}</Text>}

      {/* 订单成功结果 */}
      {orderInfo && (
        <View className="success">
          <Text>订单提交成功！</Text>
          <Text>订单ID：{orderInfo.orderId}</Text>
          <Text>总价：￥{orderInfo.totalPrice.toFixed(2)}</Text>
        </View>
      )}
    </View>
  );
}
