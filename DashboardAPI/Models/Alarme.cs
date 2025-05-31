namespace DashboardAPI.Models
{
    public class Alarme
    {
        public int Id { get; set; }
        public int SiloId { get; set; }
        public string? Tipo { get; set; }
        public string? Descricao { get; set; }
        public DateTime DataHoraAlarme { get; set; }
    }
}
