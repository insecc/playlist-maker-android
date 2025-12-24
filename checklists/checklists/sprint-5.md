# Чек-лист Sprint-5

- ✅ Структура проекта разделена на слои Data, Domain, UI; пакеты creator и ui/activity созданы и используются.
- ✅ MainActivity перенесена в ui/activity, путь в манифесте указан корректно
- ✅ Эмулятор сервера (Storage) реализован: метод search корректно фильтрует треки по названию без учёта регистра.
- ✅ Слой Data содержит TrackDto, TracksSearchRequest, TracksSearchResponse и RetrofitNetworkClient, все классы соответствуют заданию.
- ✅ Слой Domain содержит интерфейсы NetworkClient и TracksRepository; репозиторий TracksRepositoryImpl преобразует TrackDto → Track и эмулирует задержку (delay(1000)).
- ✅ При ошибке запроса (resultCode != 200) searchTracks возвращает пустой список (emptyList()).