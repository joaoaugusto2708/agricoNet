import './globals.css';

export const metadata = {
  title: 'Dashboard Silos',
  description: 'Monitoramento de Silos',
};

export default function RootLayout({ children }) {
  return (
    <html lang="pt">
      <body style={{ backgroundColor: '#f4f4f4', margin: 0 }}>{children}</body>
    </html>
  );
}