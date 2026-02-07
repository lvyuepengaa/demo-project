export interface Product {
  id: number;
  name: string;
  price: number;
  stock: number;
}

export interface OrderResult {
  orderId: string;
  totalPrice: number;
}