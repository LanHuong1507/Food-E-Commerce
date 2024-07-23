import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './components/HomePage/HomePage';
import FoodList from './components/FoodList/FoodList';
import SupplierList from './components/SupplierList/SupplierList';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/foods" element={<FoodList />} />
        <Route path="/suppliers" element={<SupplierList />} />
      </Routes>
    </Router>
  );
}

export default App;