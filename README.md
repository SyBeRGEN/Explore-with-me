# Explore with me!🌟
#### _Explore with me - это приложение, которое позволяет пользователям делиться информацией об интересных событиях и находить компанию для участия в них. 🎪_

##### Приложение состоит из двух сервисов: основного сервиса и сервиса статистики:
- **Основной сервис** содержит всё необходимое для работы продукта;
- **Сервис статистики** хранит количество просмотров и позволит делать различные выборки для анализа работы приложения.

## Основной сервис:
##### Он предоставляет три API: публичный, приватный и административный:
- **Публичный API** доступен всем пользователям и предоставляет следующие эндпоинты:
- 
  1️⃣- API для работы с событиями
- 
  2️⃣- API для работы с категориями
- 
  3️⃣- API для работы с подборками событий
- 
  4️⃣- API для работы с комментариями


- **Приватный API** доступен только зарегистрированным пользователям и предоставляет следующие эндпоинты:
- 
  1️⃣- API для работы с событиями
- 
  2️⃣️- API для работы с запросами на участие в событиях
- 
  3️⃣- API для работы с комментариями


- **Административный API** доступен только администратору проекта и предоставляет следующие эндпоинты:
- 
  1️⃣- API для работы с событиями
- 
  2️⃣- API для работы с категориями
- 
  3️⃣- API для работы с пользователями
- 
  4️⃣- API для работы с подборками событий
- 
  5️⃣- API Для работы с комментариями


##### ⬇ Более детально в API REST SWAGGER:
- [Основной сервис](https://github.com/SyBeRGEN/Explore-with-me/blob/main/ewm-main-service-spec.json)
- [Сервис статистики](https://github.com/SyBeRGEN/Explore-with-me/blob/main/ewm-stats-service-spec.json)

##### ⬇ POSTMAN тесты:
- [Основной сервис](https://github.com/yandex-praktikum/java-explore-with-me/blob/feature_comments/postman/ewm-main-service.json)
- [Сервис статистики](https://github.com/yandex-praktikum/java-explore-with-me/blob/feature_comments/postman/ewm-stat-service.json)
- [Комментарии](https://github.com/SyBeRGEN/Explore-with-me/blob/feature_comments/postman/feature.json)

##### ⬇ Ссылки на PullRequest:
- [stat-svc](https://github.com/SyBeRGEN/Explore-with-me/pull/1)
- [main-svc](https://github.com/SyBeRGEN/Explore-with-me/pull/2)
- [feature-comments](https://github.com/SyBeRGEN/Explore-with-me/pull/3)

##### ⬇ Инструкция по установке:
```sh
- Скопирвать репозиторий
- mvn package
- docker-compose up
```

##### ⬇ ER диаграммы:
- Основной сервис

  ![ER-stats](https://i.ibb.co/kXGSzMB/Untitled-1.png)
- Сервис статистики

  ![ER-stats](https://i.ibb.co/MgqGhVz/Untitled-2.png)

##### ⬇ Created By:
[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/ru/)
[![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/)
[![Postgree](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)](https://hibernate.org/)
[![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)](https://www.postman.com/)