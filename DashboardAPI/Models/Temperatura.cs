namespace DashboardAPI.Models
{
    public class Temperatura
    {
        public int Id { get; set; }
        public int SiloId { get; set; }
        public float TemperaturaSilo { get; set; }
        public DateTime DataMedicao { get; set; }
    }

}
