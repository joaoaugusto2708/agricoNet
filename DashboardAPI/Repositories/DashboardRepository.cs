using Dapper;
using DashboardAPI.Interfaces;
using DashboardAPI.Models;
using DashboardAPI.Database;
using MySql.Data.MySqlClient;

namespace DashboardAPI.Repositories
{
    public class DashboardRepository : IDashboardRepository
    {
        private readonly DbConnectionFactory _connectionFactory;

        public DashboardRepository(DbConnectionFactory connectionFactory)
        {
            _connectionFactory = connectionFactory;
        }

        public async Task<IEnumerable<Silo>> ObterSilosAsync()
        {
            const string sql = "SELECT * FROM silo";
            using var connection = _connectionFactory.CreateConnection();
            return await connection.QueryAsync<Silo>(sql);
        }

        public async Task<IEnumerable<Temperatura>> ObterTemperaturasRecentesAsync()
        {
            const string sql = @"
            SELECT * FROM temperatura
            WHERE dataMedicao >= NOW() - INTERVAL 7 DAY
            ORDER BY dataMedicao DESC";
            using var connection = _connectionFactory.CreateConnection();
            return await connection.QueryAsync<Temperatura>(sql);
        }

        public async Task<IEnumerable<Alarme>> ObterAlarmesAsync()
        {
            const string sql = "SELECT * FROM alarme ORDER BY dataHoraAlarme DESC";
            using var connection = _connectionFactory.CreateConnection();
            return await connection.QueryAsync<Alarme>(sql);
        }
    }
}
