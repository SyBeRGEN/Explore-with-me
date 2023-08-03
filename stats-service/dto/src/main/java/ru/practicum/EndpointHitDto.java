package ru.practicum;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EndpointHitDto {
    private Long id; // Идентификатор записи, например 1
    private String app; // Идентификатор сервиса для которого записывается информация, например ewm-main-service
    private String uri; // URI для которого был осуществлен запрос, например /events/1
    private String ip; // IP-адрес пользователя, осуществившего запрос, например 192.163.0.1
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp; // Дата и время, когда был совершен запрос к эндпоинту (в формате "yyyy-MM-dd HH:mm:ss"), например 2022-09-06 11:00:23
}
