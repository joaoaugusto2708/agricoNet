namespace DashboardAPI.Models
{
    public class Temperatura
    {
        public int Id { get; set; }
        public int SiloId { get; set; }
        public float Valor { get; set; }
        public DateTime DataHora { get; set; }
    }

}
