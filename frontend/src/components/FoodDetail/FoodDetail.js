// src/components/FoodDetail.js
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getFoodById } from '../../api/api';
import './FoodDetail.css';

const FoodDetail = ({ addToCart }) => {
    const { id } = useParams();
    const [food, setFood] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchFood = async () => {
            try {
                const response = await getFoodById(id);
                setFood(response.data);
                setLoading(false);
            } catch (err) {
                setError('Error fetching food details');
                setLoading(false);
            }
        };

        fetchFood();
    }, [id]);

    const handleAddToCart = () => {
        addToCart(food);
    };

    if (loading) {
        return <p>Loading...</p>;
    }

    if (error) {
        return <p>Error: {error}</p>;
    }

    return (
        <div className="food-detail">
            {food ? (
                <>
                    <h1>{food.name}</h1>
                    <div className="food-detail-content">
                        <div className="food-detail-left">
                            <img src={food.imageUrl} alt={food.name} className="food-detail-image" />
                            <div className="food-info">
                                <p><strong>Price:</strong> ${food.price}</p>
                                <p><strong>Size:</strong> {food.size}</p>
                                <p><strong>Brand:</strong> {food.brand}</p>
                                <p><strong>Category:</strong> {food.foodCategory}</p>
                                <p><strong>Stock Quantity:</strong> {food.stockQuantity}</p>
                                <p><strong>Discount:</strong> {food.discount}%</p>
                                <p><strong>Created At:</strong> {new Date(food.createdAt).toLocaleString()}</p>
                                <p><strong>Updated At:</strong> {new Date(food.updatedAt).toLocaleString()}</p>
                            </div>
                        </div>
                        <div className="food-detail-right">
                            <div className="ingredients">
                                <h2>Ingredients</h2>
                                {food.ingredients && food.ingredients.length > 0 ? (
                                    <ul>
                                        {food.ingredients.map((ingredient, index) => (
                                            <li key={index}>{ingredient}</li>
                                        ))}
                                    </ul>
                                ) : (
                                    <p>No ingredients available.</p>
                                )}
                            </div>
                            <div className="supplier-info">
                                <h2>Supplier Information</h2>
                                {food.supplier ? (
                                    <>
                                        <p><strong>Name:</strong> {food.supplier.name}</p>
                                        <p><strong>Phone Number:</strong> {food.supplier.phoneNumber}</p>
                                        <p><strong>Address:</strong> {food.supplier.address}</p>
                                    </>
                                ) : (
                                    <p>No supplier information available.</p>
                                )}
                            </div>
                            <div className="nutrition-info">
                                <h2>Nutritional Information</h2>
                                {food.nutritionalInfo ? (
                                    <>
                                        <p><strong>Calories:</strong> {food.nutritionalInfo.calories}</p>
                                        <p><strong>Fat:</strong> {food.nutritionalInfo.fat}g</p>
                                        <p><strong>Carbohydrates:</strong> {food.nutritionalInfo.carbohydrates}g</p>
                                        <p><strong>Protein:</strong> {food.nutritionalInfo.protein}g</p>
                                    </>
                                ) : (
                                    <p>No nutritional information available.</p>
                                )}
                            </div>
                            <div className="add-to-cart-container">
                                <button onClick={handleAddToCart} className="add-to-cart-btn">Add to Cart</button>
                            </div>
                        </div>
                    </div>
                </>
            ) : (
                <p>Food not found.</p>
            )}
        </div>
    );
};

export default FoodDetail;
