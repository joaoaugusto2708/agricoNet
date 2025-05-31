'use client';

import React from 'react';
import { Chart } from 'react-google-charts';
import { useApi } from '../hooks/useApi';

const AlarmesChart = () => {
  const { data: alarmes, loading } = useApi('/alarmes');

  const data = [
    ['Tipo de Alarme', 'Quantidade'],
    ...Object.entries(
      alarmes.reduce((acc, alarme) => {
        acc[alarme.tipo] = (acc[alarme.tipo] || 0) + 1;
        return acc;
      }, {})
    ),
  ];

  const options = {
    title: 'Alarmes Registrados',
    is3D: true,
  };

  if (loading) return <p>Carregando Alarmes...</p>;

  return (
    <div style={{ border: '1px solid #ddd', borderRadius: '8px', padding: '16px' }}>
      <h2>Alarmes</h2>
      <Chart
        chartType="PieChart"
        width="100%"
        height="300px"
        data={data}
        options={options}
      />
    </div>
  );
};

export default AlarmesChart;
