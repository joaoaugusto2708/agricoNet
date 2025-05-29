using DashboardAPI.Models;

namespace DashboardAPI.Interfaces
{
    public interface IDashboardRepository
    {
        Task<IEnumerable<Silo>> ObterSilosAsync();
        Task<IEnumerable<Temperatura>> ObterTemperaturasRecentesAsync();
        Task<IEnumerable<Alarme>> ObterAlarmesAsync();
    }
}
