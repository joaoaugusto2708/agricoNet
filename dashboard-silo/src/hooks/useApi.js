'use client';

import { useEffect, useState } from 'react';
import { api } from '../services/api';

export function useApi(endpoint) {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    api.get(endpoint)
      .then((res) => setData(res.data))
      .catch((err) => console.error(err))
      .finally(() => setLoading(false));
  }, [endpoint]);

  return { data, loading };
}