import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function Dashboard() {
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem('token');
    axios.get('http://localhost:8080/api/user/profile', {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
    .then(res => setMessage(res.data))
    .catch(() => setMessage('Unauthorized'));
  }, []);

  const handleLogout = () => {
    localStorage.removeItem('token');
    navigate('/login');
  };

  return (
    <div className="p-8">
      <h2 className="text-2xl font-bold mb-4">Dashboard</h2>
      <p>{message}</p>
      <button onClick={handleLogout} className="mt-4 px-4 py-2 bg-red-500 text-white rounded">
        Logout
      </button>
    </div>
  );
}

export default Dashboard;
