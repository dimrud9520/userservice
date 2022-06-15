Описание: Сервис регистрации и учета пользователей

Средства разработки: Java, Maven/Gradle
База данных: PostgreSQL (Можно использовать мокирование обращений к БД)
Фреймворк: Spring (обязательно Spring Web, Spring Data, Spring Boot)

Функционал (запросы):
- Создание пользователя.
Передаем на сервер необходимые данные о пользователе.
Поля у пользователя:
1 Имя
2 Дата рождения
3 Электронный адрес
Сохраняем информацию в базу данных
Ответ сервера - персональные данные нового пользователя.

- Получение информации о пользователе.
Передаем на сервер уникальный ID пользователя.
Читаем информацию из базы данных.
Ответ сервера - персональные данные пользователя.
Поля у пользователя:
1 Имя
2 Дата рождения
3 Возраст
3 Электронный адрес
4 Статус

- Изменение статуса пользователя (Online, Offline).
Передаем на сервер уникальный ID пользователя и новый статус (Online, Offline).
Изменяем статус пользователя.
Ответ сервера - уникальный ID пользователя, новый и предыдущий статус.

- Статистика сервера.
Передаем параметры на сервер: запрашиваемый статус клиентов (Online, Offline или отсутствует)
и совершеннолетние ли клиенты нужны (более 18 лет) для статистики (да или нет, не
обязательный параметр).
Ответ сервера – Статистика сервера
Поля у статистики:
1 Общее количество пользователей
2 В случае указанного статуса - список пользователей находящихся в соответствующем статусе
3 В случае указанного требуемого возраста – список подходящих пользователей
4 Средний возраст подходящих пользователей

Обязательные требования:
- REST.
- Обработка ошибок.

Необязательные требования (желательно):
- Документирование кода
- Краткая документация созданного API в любом виде (список запросов, параметры запросов,
форматы запросов, форматы ответов и т.д.)
- Архитектура Сервера API должна быть рассчитана на высокую нагрузку и масштабирование
- Тесты
- Dockerfile

Предоставление на проверку результатов тестового задания:
- Репозиторий на github или zip архив с подробным описанием и рекомендациями по
развертыванию.

Документация REST SERVICE предоставлена по адресу http://localhost:8080/swagger-ui/index.html

Написаны unit тесты на сервис слой приложения

Добавлена частичная документация на сервис методы