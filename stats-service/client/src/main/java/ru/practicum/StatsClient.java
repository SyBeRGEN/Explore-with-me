package ru.practicum;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.List;
import java.util.Map;

@Service
public class StatsClient {
    /**
     * В ТЗ построить модуль клиента сервиса статистики на RestTemplate
     */
    private final RestTemplate rest;

    public StatsClient(@Value("${stats-service.url}") String serverUrl, RestTemplateBuilder builder) {
        this.rest = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build();
    }

    /**
     * Принимаем ResponseEntity, проверяем код-статус, если начинает с 2 (успешный код), метод возвращает исходный ответ,
     * в противном случае, он создает новый ответ с тем же статус-кодом и телом, если оно присутствует
     */
    private static ResponseEntity<Object> prepareResponse(ResponseEntity<Object> response) {
        if (response.getStatusCode().is2xxSuccessful()) {
            return response;
        }

        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.status(response.getStatusCode());

        if (response.hasBody()) {
            return responseBuilder.body(response.getBody());
        }

        return responseBuilder.build();
    }

    /**
     * Класс предоставляет несколько защищенных методов для выполнения GET- и POST-запросов:
     * Метод get выполняет GET-запрос с указанным path и опциональными параметрами запроса (parameters).
     * Метод post выполняет POST-запрос с указанным path, опциональными параметрами запроса (parameters) и телом запроса (body).
     * Эти методы внутренне вызывают метод makeAndSendRequest для создания HTTP-запроса и его отправки с использованием RestTemplate.
     */
    protected ResponseEntity<Object> get(String path, @Nullable Map<String, Object> parameters) {
        return makeAndSendRequest(HttpMethod.GET, path, parameters, null);
    }

    protected <T> ResponseEntity<Object> post(String path, T body) {
        return post(path, null, body);
    }

    protected <T> ResponseEntity<Object> post(String path, @Nullable Map<String, Object> parameters, T body) {
        return makeAndSendRequest(HttpMethod.POST, path, parameters, body);
    }

    /**
     * Метод makeAndSendRequest является приватным обобщенным методом, который обрабатывает создание и отправку фактического HTTP-запроса.
     * Он принимает HTTP-метод (method), путь запроса (path), опциональные параметры запроса (parameters) и опциональное тело запроса (body).
     * Метод создает HttpEntity с телом запроса и заголовками по умолчанию, затем использует RestTemplate для отправки запроса и получения ResponseEntity.
     * Если возникает исключение (например, HttpStatusCodeException), оно обрабатывается, и возвращается новый ResponseEntity с соответствующим статус-кодом и ошибочным телом.
     */
    private <T> ResponseEntity<Object> makeAndSendRequest(HttpMethod method, String path,
                                                          @Nullable Map<String, Object> parameters, @Nullable T body) {
        HttpEntity<T> requestEntity = new HttpEntity<>(body, defaultHeaders());

        ResponseEntity<Object> serverResponse;
        try {
            if (parameters != null) {
                serverResponse = rest.exchange(path, method, requestEntity, Object.class, parameters);
            } else {
                serverResponse = rest.exchange(path, method, requestEntity, Object.class);
            }
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsByteArray());
        }
        return prepareResponse(serverResponse);
    }

    /**
     * Метод defaultHeaders является приватным вспомогательным методом, который создает и возвращает HttpHeaders
     * со значением типа содержимого application/json и принимаемым типом медиа application/json.
     */
    private HttpHeaders defaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }

    /**
     * Класс предоставляет два публичных метода:
     * Метод getStats выполняет GET-запрос для получения статистики с сервера. Он принимает параметры, такие как start (начальная дата),
     * end (конечная дата), uris (список URI) и unique (логический флаг). Метод формирует URL запроса и параметры запроса с помощью Map
     * и вызывает метод get для выполнения фактического запроса.
     * Метод save выполняет POST-запрос для сохранения объекта EndpointHitDto на сервере. Он принимает объект EndpointHitDto
     * в качестве параметра и вызывает метод post с путем запроса и объектом в качестве тела запроса.
     */

    public ResponseEntity<Object> getStats(String start, String end, List<String> uris, Boolean unique) {
        Map<String, Object> parameters = Map.of(
                "start", start,
                "end", end,
                "uris", uris,
                "unique", unique
        );
        return get("/stats?start={start}&end={end}&uris={uris}&unique={unique}", parameters);
    }

    public ResponseEntity<Object> save(EndpointHitDto endpointHit) {
        return post("/hit", endpointHit);
    }
}
