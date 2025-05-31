'use client';

import React from 'react';
import { Chart } from 'react-google-charts';
import { useApi } from '../hooks/useApi';

const TemperaturasChart = () => {
  const { data: temperaturas, loading } = useApi('/temperaturas/recentes');

  const data = [
    ['siloId', 'Temperatura'],
    ...temperaturas.map((t) => [
      new Date(t.dataMedicao).toLocaleDateString(),
      Number(t.temperaturaSilo) || 0,
    ]),
  ];

 const options = {
    title: 'Temperatura dos Silos',
    curveType: 'function',
    legend: { position: 'bottom' },
  };

  if (loading) return <p>Carregando Temperaturas...</p>;

  return (
    <div style={{ border: '1px solid #ddd', borderRadius: '8px', padding: '16px' }}>
      <h2>Temperaturas Recentes</h2>
    <Chart
      chartType="LineChart"
      width="100%"
      height="300px"
      data={data}
      options={options}
    />
    </div>
  );
};

export default TemperaturasChart;
