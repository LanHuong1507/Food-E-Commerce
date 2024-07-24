import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { getSupplierById } from '../../api/api'; // Assume this function is defined in your api.js file

const SupplierDetail = () => {
    const { id } = useParams();
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
                console.error('Error fetching supplier details:', err);
                setError('Error fetching supplier details');
                setLoading(false);
            }
        };

        fetchSupplier();
    }, [id]);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error: {error}</p>;
    if (!supplier) return <p>No supplier details available</p>;

    return (
        <div className="supplier-detail">
            <h1>{supplier.name}</h1>
            <p><strong>Phone number:</strong> {supplier.phoneNumber}</p>
            <p><strong>Address:</strong> {supplier.address}</p>
            <p><strong>Description:</strong> {supplier.description}</p>
        </div>
    );
};

export default SupplierDetail;
