import axios from 'axios';

export const api = axios.create({
  baseURL: 'http://localhost:5164/api/Dashboard',
});
