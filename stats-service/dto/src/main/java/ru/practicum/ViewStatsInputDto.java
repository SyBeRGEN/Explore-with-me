package ru.practicum;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ViewStatsInputDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start; // Дата и время начала диапазона за который нужно выгрузить статистику (в формате "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime end; // Дата и время конца диапазона за который нужно выгрузить статистику (в формате "yyyy-MM-dd HH:mm:ss")
    private List<String> uris; // Список uri для которых нужно выгрузить статистику
    private Boolean unique; // Нужно ли учитывать только уникальные посещения (только с уникальным ip). Значение по-умолчанию : false

    @Override
    public String toString() {
        return "ViewStatsInputDto{" +
                "start=" + start +
                ", end=" + end +
                ", uris=" + uris +
                ", unique=" + unique +
                '}';
    }
}
