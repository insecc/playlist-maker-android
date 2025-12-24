# Чек-лист Sprint-10

- ✅ Добавлены зависимости Room (runtime, ktx, compiler) и DataStore в build.gradle.
- ✅ Созданы TrackEntity и PlaylistEntity с аннотациями @Entity, @PrimaryKey.
- ✅ Настроены TrackDao и PlaylistDao с методами @Insert, @Update, @Delete, @Query для треков/плейлистов (включая getAllPlaylists, getPlaylist, insertTrackToPlaylist и т.д.).
- ✅ Реализован абстрактный класс с @Database, дао для плейлистов и треков.
- ✅ PlaylistsRepositoryImpl и TracksLocalRepositoryImpl используют DAO Room вместо моков (методы getAllPlaylists, addNewPlaylist, insertTrackToPlaylist и т.д. с маппингом Entity <-> Domain модели).
- ✅ Плейлисты/треки/избранное сохраняются в БД, отображаются после перезапуска (проверено на устройстве/эмуляторе).
- ✅ Создан SearchHistoryPreferences с методами addEntry/getEntries (max 10, без дублей, новые сверху, разделитель ",").
- ✅ Работает с DataStore, методы addSearchQuery/getSearchHistory.
- ✅ Добавление в историю при поиске, отображение истории при пустом запросе (кликабельные элементы).
- ✅ Удалены DatabaseMock и моки в репозиториях.