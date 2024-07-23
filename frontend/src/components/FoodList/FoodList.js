// src/components/FoodList.js
import React, { useEffect, useState } from 'react';
import { getFoods } from '../../api/api';
import './FoodList.css'; // Import the CSS file

const FoodList = () => {
    const [foods, setFoods] = useState([]); // Initialize as an empty array
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchFoods = async () => {
            try {
                const response = await getFoods(); // Use the API function
                setFoods(response.data);
                setLoading(false);
            } catch (err) {
                setError('Error fetching foods');
                setLoading(false);
            }
        };

        fetchFoods();
    }, []);

    if (loading) {
        return <p>Loading...</p>;
    }

    if (error) {
        return <p>Error: {error}</p>;
    }

    return (
        <div className="food-list">
            <h1>Food List</h1>
            {foods.length > 0 ? (
                <div className="food-container">
                    {foods.map(food => (
                        <div className="food-item" key={food.id}>
                            <a href={`/foods/${food.id}`}>
                                <img src={food.imageUrl} alt={food.name} className="food-image" />
                                <div className="food-details">
                                    <h2 className="food-name">{food.name}</h2>
                                    <p className="food-price">${food.price}</p>
                                </div>
                            </a>
                        </div>
                    ))}
                </div>
            ) : (
                <p>No foods available</p>
            )}
        </div>
    );
};

export default FoodList;
