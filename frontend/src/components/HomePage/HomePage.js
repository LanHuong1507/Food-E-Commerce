// src/pages/HomePage.js
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getFoods, getSuppliers } from '../../api/api';
import './HomePage.css';
import { Link } from 'react-router-dom';

const HomePage = () => {
    const [foods, setFoods] = useState([]);
    const [suppliers, setSuppliers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchData = async () => {
            try {
                const [foodsResponse, suppliersResponse] = await Promise.all([
                    getFoods(),
                    getSuppliers()
                ]);
                setFoods(foodsResponse.data.slice(0, 10)); // Display 10 items
                setSuppliers(suppliersResponse.data.slice(0, 10)); // Display 10 items
                setLoading(false);
            } catch (err) {
                console.error('Error fetching data:', err);
                setError(err.message);
                setLoading(false);
            }
        };

        fetchData();
    }, []);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error: {error}</p>;

    const handleFoodClick = (id) => {
        navigate(`/foods/${id}`);
    };

    const handleSupplierClick = (id) => {
        navigate(`/suppliers/${id}`);
    };

  
    const handleViewCart = () => {
        navigate('/carts');
    };

    return (
        <div className="home-page">
            <div className="background-image">
                <h1>Welcome to the E-commerce App</h1>
            </div>
            <div className="previews">
                {/* Food and Supplier previews */}
                <div className="preview-section">
                    <h2>Featured Foods</h2>
                    <div className="preview-container">
                        {foods.map((food) => (
                            <div className="preview-item" key={food.id} onClick={() => handleFoodClick(food.id)}>
                                <img src={food.imageUrl} alt={food.name} className="item-image" />
                                <div className="item-details">
                                    <h3 className="item-name">{food.name}</h3>
                                    <p>${food.price}</p>
                                </div>
                            </div>
                        ))}
                        <Link to="/foods" className="view-more-food">View More</Link>
                    </div>
                </div>
                <div className="preview-section">
                    <h2>Featured Suppliers</h2>
                    <div className="preview-container">
                        {suppliers.map((supplier) => (
                            <div className="preview-item supplier-item" key={supplier.id} onClick={() => handleSupplierClick(supplier.id)}>
                                <div className="item-details">
                                    <h3 className="item-name">{supplier.name}</h3>
                                </div>
                            </div>
                        ))}
                        <Link to="/suppliers" className="view-more">View More</Link>
                    </div>
                </div>
                <button onClick={handleViewCart} className="view-cart-button">View Cart</button>
            </div>
        </div>
    );
}
export default HomePage;

