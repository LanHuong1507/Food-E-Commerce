import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { getSupplierById } from '../../api/api';
import './SupplierDetail.css';

const SupplierDetail = () => {
    const { id } = useParams();  // Get the supplier ID from the URL parameters
    const [supplier, setSupplier] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchSupplier = async () => {
            try {
                const response = await getSupplierById(id);
                setSupplier(response.data);
                setLoading(false);
            } catch (err) {
                console.error('Error fetching supplier:', err);
                setError('Error fetching supplier');
                setLoading(false);
            }
        };

        fetchSupplier();
    }, [id]);

    if (loading) return <div className="supplier-detail loading">Loading...</div>;
    if (error) return <div className="supplier-detail error">Error: {error}</div>;
    if (!supplier) return <div className="supplier-detail">Supplier not found</div>;

    return (
        <div className="supplier-detail">
            <div className="supplier-detail__card">
                <h1 className="supplier-detail__title">Supplier Details</h1>
                <div className="supplier-detail__info">
                    <div className="supplier-detail__info-item">
                        <span className="supplier-detail__label">Name:</span>
                        <span className="supplier-detail__value">{supplier.name}</span>
                    </div>
                    <div className="supplier-detail__info-item">
                        <span className="supplier-detail__label">Phone Number:</span>
                        <span className="supplier-detail__value">{supplier.phoneNumber}</span>
                    </div>
                    <div className="supplier-detail__info-item">
                        <span className="supplier-detail__label">Address:</span>
                        <span className="supplier-detail__value">{supplier.address}</span>
                    </div>
                    <div className="supplier-detail__info-item">
                        <span className="supplier-detail__label">Email:</span>
                        <span className="supplier-detail__value">{supplier.email}</span>
                    </div>
                    <div className="supplier-detail__info-item">
                        <span className="supplier-detail__label">Description:</span>
                        <span className="supplier-detail__value">{supplier.description}</span>
                    </div>
                    <div className="supplier-detail__info-item">
                        <span className="supplier-detail__label">Website:</span>
                        <a href={supplier.website} target="_blank" rel="noopener noreferrer" className="supplier-detail__value">
                            {supplier.website}
                        </a>
                    </div>
                    <div className="supplier-detail__info-item">
                        <span className="supplier-detail__label">Category:</span>
                        <span className="supplier-detail__value">{supplier.category}</span>
                    </div>
                    <div className="supplier-detail__info-item">
                        <span className="supplier-detail__label">Rating:</span>
                        <span className="supplier-detail__value">{supplier.rating}</span>
                    </div>
                    <div className="supplier-detail__info-item">
                        <span className="supplier-detail__label">Created At:</span>
                        <span className="supplier-detail__value">{new Date(supplier.createdAt).toLocaleString()}</span>
                    </div>
                    <div className="supplier-detail__info-item">
                        <span className="supplier-detail__label">Last Updated:</span>
                        <span className="supplier-detail__value">{new Date(supplier.lastUpdated).toLocaleString()}</span>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default SupplierDetail;
