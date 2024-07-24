import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './components/HomePage/HomePage';
import FoodList from './components/FoodList/FoodList';
import SupplierList from './components/SupplierList/SupplierList';
import FoodDetail from './components/FoodDetail/FoodDetail';
import SupplierDetail from './components/SupplierDetail/SupplierDetail';
function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/foods" element={<FoodList />} />
        <Route path="/suppliers" element={<SupplierList />} />
        <Route path="/foods/:id" element={<FoodDetail />} />
        <Route path="/suppliers/:id" element={<SupplierDetail />} />
      </Routes>
    </Router>
  );
}

export default App;