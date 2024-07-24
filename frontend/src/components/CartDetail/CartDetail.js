import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { getCartById } from '../../api/api'; // Adjust the import path as needed
import './CartDetail.css';

const CartDetail = () => {
    const { id } = useParams(); // Get the cart ID from the URL parameters
    const [cart, setCart] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchCart = async () => {
            try {
                const response = await getCartById(id);
                setCart(response.data);
                setLoading(false);
            } catch (err) {
                console.error('Error fetching cart:', err);
                setError('Error fetching cart');
                setLoading(false);
            }
        };

        fetchCart();
    }, [id]);

    if (loading) return <p className="loading">Loading...</p>;
    if (error) return <p className="error">Error: {error}</p>;
    if (!cart) return <p className="no-cart">Cart not found</p>;

    const totalCartPrice = cart.items.reduce((total, item) => {
        const itemPrice = item.food ? item.food.discountedPrice * item.quantity : 0;
        return total + itemPrice;
    }, 0);

    return (
        <div className="cart-detail-container">
            <div className="cart-detail-card">
                <h1 className="cart-detail-title">Cart Details</h1>
                <div className="cart-detail-info">
                    <div className="cart-detail-info-item">
                        <span className="cart-detail-label">Cart ID:</span>
                        <span className="cart-detail-value">{cart.id}</span>
                    </div>
                    <div className="cart-detail-info-item">
                        <span className="cart-detail-label">User ID:</span>
                        <span className="cart-detail-value">{cart.userId}</span>
                    </div>
                    <div className="cart-detail-info-item">
                        <span className="cart-detail-label">Total Cost:</span>
                        <span className="cart-detail-value">${totalCartPrice.toFixed(2)}</span>
                    </div>
                    <div className="cart-detail-info-item">
                        <span className="cart-detail-label">Created At:</span>
                        <span className="cart-detail-value">{cart.createdAt ? new Date(cart.createdAt).toLocaleString() : 'N/A'}</span>
                    </div>
                    <div className="cart-detail-info-item">
                        <span className="cart-detail-label">Last Updated:</span>
                        <span className="cart-detail-value">{cart.updatedAt ? new Date(cart.updatedAt).toLocaleString() : 'N/A'}</span>
                    </div>
                    <div className="cart-detail-info-item">
                        <span className="cart-detail-label">Status:</span>
                        <span className="cart-detail-value">{cart.status}</span>
                    </div>
                    <div className="cart-detail-info-item">
                        <span className="cart-detail-label">Delivery Address:</span>
                        <span className="cart-detail-value">{cart.deliveryAddress || 'N/A'}</span>
                    </div>
                    <div className="cart-detail-info-item">
                        <span className="cart-detail-label">Payment Method:</span>
                        <span className="cart-detail-value">{cart.paymentMethod}</span>
                    </div>
                    <div className="cart-detail-info-item">
                        <span className="cart-detail-label">Notes:</span>
                        <span className="cart-detail-value">{cart.notes || 'N/A'}</span>
                    </div>
                    <div className="cart-detail-info-item">
                        <span className="cart-detail-label">Items:</span>
                        <div className="cart-detail-items-container">
                            {cart.items && cart.items.length > 0 ? (
                                cart.items.map((item) => (
                                    <div key={item.id} className="cart-detail-item">
                                        <img src={item.food.imageUrl} alt={item.food.name} className="cart-detail-item-image"/>
                                        <div className="cart-detail-item-details">
                                            <span className="cart-detail-item-food-name">{item.food.name}</span>
                                            <div className="cart-detail-item-info">
                                                <span className="cart-detail-item-label">Quantity:</span>
                                                <span className="cart-detail-item-value">{item.quantity}</span>
                                            </div>
                                            <div className="cart-detail-item-info">
                                                <span className="cart-detail-item-label">Price:</span>
                                                <span className="cart-detail-item-value">${item.food.discountedPrice.toFixed(2)} each</span>
                                            </div>
                                            <div className="cart-detail-item-info">
                                                <span className="cart-detail-item-label">Total:</span>
                                                <span className="cart-detail-item-value">${(item.food.discountedPrice * item.quantity).toFixed(2)}</span>
                                            </div>
                                        </div>
                                    </div>
                                ))
                            ) : (
                                <div className="cart-detail-item">No items in cart</div>
                            )}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default CartDetail;
