import React, { useState, useEffect } from 'react';
import { getCarts } from '../../api/api'; // Adjust the import path as needed
import './CartListPage.css';

const CartListPage = () => {
    const [carts, setCarts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchCarts = async () => {
            try {
                const response = await getCarts();
                setCarts(response.data);
                setLoading(false);
            } catch (err) {
                console.error('Error fetching carts:', err);
                setError('Error fetching carts');
                setLoading(false);
            }
        };

        fetchCarts();
    }, []);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error: {error}</p>;
    if (carts.length === 0) return <p>No carts available</p>;

    return (
        <div className="cart-list">
            <h1>Cart List</h1>
            <div className="cart-list__container">
                {carts.map(cart => (
                    <div key={cart.id} className="cart-list__card">
                        <h2 className="cart-list__title">Cart ID: {cart.id}</h2>
                        <div className="cart-list__info">
                            <strong>User ID:</strong> {cart.userId}
                        </div>
                        <div className="cart-list__info">
                            <strong>Total Cost:</strong> ${cart.totalCost.toFixed(2)}
                        </div>
                        <div className="cart-list__info">
                            <strong>Created At:</strong> {new Date(cart.createdAt).toLocaleString()}
                        </div>
                        <div className="cart-list__info">
                            <strong>Status:</strong> {cart.status}
                        </div>
                        <a href={`/carts/${cart.id}`} className="cart-list__link">View Details</a>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default CartListPage;
