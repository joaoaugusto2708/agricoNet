'use client';

import React from 'react';
import { Chart } from 'react-google-charts';
import { useApi } from '../hooks/useApi';

const SilosChart = () => {
  const { data: silos, loading } = useApi('/silos');

  const data = [
    ['Modelo', 'Quantidade'],
    ...Object.entries(
      silos.reduce((acc, silo) => {
        acc[silo.modelo] = (acc[silo.modelo] || 0) + 1;
        return acc;
      }, {})
    ),
  ];

  const options = {
    title: 'Distribuição dos Modelos dos Silos',
    pieHole: 0.4,
    is3D: false,
  };

  if (loading) return <p>Carregando Silos...</p>;

  return (
    <div style={{ border: '1px solid #ddd', borderRadius: '8px', padding: '16px' }}>
      <h2>Silos por Modelo</h2>
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

export default SilosChart;
