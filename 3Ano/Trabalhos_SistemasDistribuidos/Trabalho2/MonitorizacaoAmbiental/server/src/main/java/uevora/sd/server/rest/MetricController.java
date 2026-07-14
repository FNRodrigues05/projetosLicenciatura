package uevora.sd.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uevora.sd.server.model.DeviceEntity;
import uevora.sd.server.model.MetricEntity;
import uevora.sd.server.repository.DeviceRepository;
import uevora.sd.server.repository.MetricRepository;
import uevora.sd.server.service.MetricsProcessor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/metrics")
public class MetricController {

    @Autowired
    private MetricRepository metricRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private MetricsProcessor processor;

    @GetMapping
    public List<MetricEntity> getAllMetrics() {
        return metricRepository.findAll();
    }

    // Implementar endpoint POST /api/metrics/ingest
    @PostMapping("/ingest")
    public ResponseEntity<String> createMetric(@RequestBody MetricEntity metric) {
        if (metric.getTimestamp() == null)
            metric = new MetricEntity(metric.getDeviceId(), metric.getTemperature(), metric.getHumidity(),
                    LocalDateTime.now().toString());
        boolean sucesso = processor.processMetric(metric);
        return sucesso ? ResponseEntity.ok("Aceite") : ResponseEntity.badRequest().body("Rejeitado");
    }

    // Implementar endpoint GET /api/metrics/raw
    @GetMapping("/raw")
    public List<MetricEntity> getRawMetrics(
            @RequestParam String deviceId,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to) {

        LocalDateTime start = (from != null) ? LocalDateTime.parse(from) : LocalDateTime.now().minusHours(24);
        LocalDateTime end = (to != null) ? LocalDateTime.parse(to) : LocalDateTime.now();

        return metricRepository.findRawData(deviceId, start, end);
    }

    // Implementar endpoint GET /api/metrics/average
    @GetMapping("/average")
    public ResponseEntity<Map<String, Object>> getAverageMetrics(
            @RequestParam String level,
            @RequestParam String id,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to) {

        LocalDateTime start = (from != null) ? LocalDateTime.parse(from) : LocalDateTime.now().minusHours(24);
        LocalDateTime end = (to != null) ? LocalDateTime.parse(to) : LocalDateTime.now();

        List<DeviceEntity> devices = new ArrayList<>();
        switch (level.toLowerCase()) {
            case "sala" -> devices = deviceRepository.findByRoom(id);
            case "piso" -> devices = deviceRepository.findByFloor(id);
            case "edificio" -> devices = deviceRepository.findByBuilding(id);
            case "departamento" -> devices = deviceRepository.findByDepartment(id);
            default -> {
                return ResponseEntity.badRequest().body(Map.of("erro", "Nível inválido"));
            }
        }

        if (devices.isEmpty()) {
            return ResponseEntity.ok(Map.of("message", "Nenhum dispositivo encontrado para " + level + " " + id));
        }

        List<String> deviceIds = devices.stream().map(DeviceEntity::getId).collect(Collectors.toList());

        List<Object[]> result = metricRepository.findAggregatedData(deviceIds, start, end);

        Map<String, Object> response = new HashMap<>();
        if (result != null && !result.isEmpty() && result.get(0)[0] != null) {
            response.put("level", level);
            response.put("id", id);
            response.put("averageTemperature", result.get(0)[0]);
            response.put("averageHumidity", result.get(0)[1]);
            response.put("sensorsAnalyzed", deviceIds.size());
        } else {
            response.put("message", "Sem dados de métricas para o período selecionado.");
        }

        return ResponseEntity.ok(response);
    }
}