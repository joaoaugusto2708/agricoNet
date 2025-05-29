using DashboardAPI.Interfaces;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace DashboardAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class DashboardController : ControllerBase
    {
        private readonly IDashboardRepository _repository;

        public DashboardController(IDashboardRepository repository)
        {
            _repository = repository;
        }

        [HttpGet("silos")]
        public async Task<IActionResult> GetSilos()
        {
            var silos = await _repository.ObterSilosAsync();
            return Ok(silos);
        }

        [HttpGet("temperaturas/recentes")]
        public async Task<IActionResult> GetTemperaturasRecentes()
        {
            var temps = await _repository.ObterTemperaturasRecentesAsync();
            return Ok(temps);
        }

        [HttpGet("alarmes")]
        public async Task<IActionResult> GetAlarmes()
        {
            var alarmes = await _repository.ObterAlarmesAsync();
            return Ok(alarmes);
        }
    }
}
