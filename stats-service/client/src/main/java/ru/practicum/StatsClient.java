package ru.practicum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Map;

@Service
public class StatsClient extends MainStatsClient {
    /**
     * Создает новый объект StatsClient.
     *
     * @param serverUrl Ссылка на сервис статистики. Получается из аннотации "@Value" с ключом "${stats-service.url}".
     * @param builder   Объект RestTemplateBuilder, используемый для настройки объекта RestTemplate.
     */
    @Autowired
    public StatsClient(@Value("${stats-service.url}") String serverUrl, RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build());
    }


    /**
     * Класс предоставляет два публичных метода:
     * Метод getStats выполняет GET-запрос для получения статистики с сервера. Он принимает параметры, такие как start (начальная дата),
     * end (конечная дата), uris (список URI) и unique (логический флаг). Метод формирует URL запроса и параметры запроса с помощью Map
     * и вызывает метод get для выполнения фактического запроса.
     * Метод save выполняет POST-запрос для сохранения объекта EndpointHitDto на сервере. Он принимает объект EndpointHitDto
     * в качестве параметра и вызывает метод post с путем запроса и объектом в качестве тела запроса.
     */

    public ResponseEntity<Object> getStats(String start, String end, String uri, Boolean unique) {
        Map<String, Object> parameters = Map.of(
                "start", start,
                "end", end,
                "uris", uri,
                "unique", unique
        );
        return get("/stats?start={start}&end={end}&uris={uris}&unique={unique}", parameters);
    }

    public ResponseEntity<Object> save(EndpointHitDto endpointHit) {
        return post("/hit", endpointHit);
    }
}
