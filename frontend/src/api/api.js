
import axios from 'axios';

const API_URL = 'http://localhost:8080/api'; // Ensure this is the correct URL for your API

export const getFoods = () => axios.get(`${API_URL}/foods`);
export const getFoodById = (id) => axios.get(`${API_URL}/foods/${id}`);
export const getSuppliers = () => axios.get(`${API_URL}/suppliers`);
export const getSupplierById = (id) => axios.get(`${API_URL}/suppliers/${id}`);
export const getCarts = () => axios.get(`${API_URL}/carts`);
export const getCartById = (id) => axios.get(`${API_URL}/carts/${id}`);