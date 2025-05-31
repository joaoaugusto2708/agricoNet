import SilosChart from '../components/SilosChart';
import TemperaturasChart from '../components/TemperaturasChart';
import AlarmesChart from '../components/AlarmesChart';

export default function Home() {
  return (
    <main style={{ padding: '20px' }}>
      <header style={{ marginBottom: '20px' }}>
        <h1 style={{ fontSize: '32px', color: '#333' }}>Dashboard dos Silos</h1>
      </header>

      <div style={{ display: 'grid', gap: '20px', gridTemplateColumns: 'repeat(auto-fit, minmax(300px, 1fr))' }}>
        <SilosChart />
        <TemperaturasChart />
        <AlarmesChart />
      </div>
    </main>
  );
}
