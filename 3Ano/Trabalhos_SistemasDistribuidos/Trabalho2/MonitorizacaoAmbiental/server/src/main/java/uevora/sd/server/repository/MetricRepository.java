package uevora.sd.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uevora.sd.server.model.MetricEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MetricRepository extends JpaRepository<MetricEntity, Long> {

    //endpoint /raw: Buscar métricas de um device entre duas datas
    @Query("SELECT m FROM MetricEntity m WHERE m.deviceId = :deviceId AND m.timestamp BETWEEN :start AND :end ORDER BY m.timestamp DESC")
    List<MetricEntity> findRawData(
            @Param("deviceId") String deviceId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    //endpoint /average: Calcular média de uma lista de devices
    @Query("SELECT AVG(m.temperature) as avgTemp, AVG(m.humidity) as avgHum FROM MetricEntity m WHERE m.deviceId IN :deviceIds AND m.timestamp BETWEEN :start AND :end")
    List<Object[]> findAggregatedData(
            @Param("deviceIds") List<String> deviceIds,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}