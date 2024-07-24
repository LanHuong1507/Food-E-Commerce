import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './components/HomePage/HomePage';
import FoodList from './components/FoodList/FoodList';
import SupplierList from './components/SupplierList/SupplierList';
import FoodDetail from './components/FoodDetail/FoodDetail';
import SupplierDetail from './components/SupplierDetail/SupplierDetail';
import CartList from './components/CartListPage/CartListPage';
import CartDetail from './components/CartDetail/CartDetail';
function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/foods" element={<FoodList />} />
        <Route path="/suppliers" element={<SupplierList />} />
        <Route path="/foods/:id" element={<FoodDetail />} />
        <Route path="/suppliers/:id" element={<SupplierDetail />} />
        <Route path="/carts" element={<CartList />} />
        <Route path="/carts/:id" element={<CartDetail />} />
      </Routes>
    </Router>
  );
}

export default App;