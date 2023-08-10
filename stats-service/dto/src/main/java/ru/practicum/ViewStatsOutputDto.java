package ru.practicum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ViewStatsOutputDto {
    private String app; // Название сервиса
    private String uri; // Uri сервиса
    private Long hits; // Количество просмотров, например 6

    @Override
    public String toString() {
        return "ViewStatsOutputDto{" +
                "app='" + app + '\'' +
                ", uri='" + uri + '\'' +
                ", hits=" + hits +
                '}';
    }
}
