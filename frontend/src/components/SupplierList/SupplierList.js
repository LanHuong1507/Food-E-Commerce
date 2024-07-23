import React, { useState, useEffect } from 'react';
import { getSuppliers } from '../../api/api';
import { Link } from 'react-router-dom';
import './SupplierList.css';

const SupplierList = () => {
    const [suppliers, setSuppliers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchSuppliers = async () => {
            try {
                const response = await getSuppliers();
                setSuppliers(response.data);
                setLoading(false);
            } catch (err) {
                console.error('Error fetching suppliers:', err);
                setError('Error fetching suppliers');
                setLoading(false);
            }
        };

        fetchSuppliers();
    }, []);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error: {error}</p>;
    if (suppliers.length === 0) return <p>No suppliers available</p>;

    return (
        <div className="supplier-list">
            <h1>Supplier List</h1>
            <ul>
                {suppliers.map(supplier => (
                    <li key={supplier.id}>
                        <h2>{supplier.name}</h2>
                        <p><strong>Phone number:</strong> {supplier.phoneNumber}</p>
                        <p><strong>Address:</strong> {supplier.address}</p>
                        <Link to={`/suppliers/${supplier.id}`}>View Details</Link>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default SupplierList;
