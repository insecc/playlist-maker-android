# Playlist Maker

Учебный проект по созданию мобильного приложения для управления плейлистами.

## Сборка
- Android Studio Otter 2 Feature Drop | 2025.2.2+
- Min SDK 24, Target SDK 36
- Compose BOM 2025.12.00
- Kotlin 2.2.21
- Navigation Compose 2.9.6


## Зависимости
- Jetpack Compose (BOM 2025.12.00) для UI
- Compose Navigation 2.9.6 для навигации
- Room 2.8.4 для локальной базы данных
- DataStore 1.2.0 для хранения истории поиска
- Retrofit 3.0.0 для работы с API (эмулятор сервера)
- Coil 2.7.0 для загрузки изображений
- Gson 2.13.2 для парсинга JSON

## Сборка
- git clone https://github.com/insecc/playlist-maker-android.git

- Откройте Android Studio версии 2025.2.2 или новее
- Выберите "Open" и укажите папку проекта, которая была создана после клонирования

- Синхронизация Gradle
- Дождитесь завершения синхронизации зависимостей
- При необходимости обновите Gradle и зависимости
- Выберите сборку в меню Build → Make Project (Ctrl+F9)
- Убедитесь, что сборка завершена без ошибок

## Запуск на эмуляторе или устройстве
- Подключите Android-устройство с Android 7.0+ и включенной отладкой по USB
- Или создайте/запустите эмулятор Android 7.0+ в AVD Manager
- Нажмите Run → Run 'app' (Shift+F10)


## Изменения по спринтам
- **Sprint-3:** Главный экран, переходы на Search/Settings Activities.
- **Sprint-4:** Переход на Compose Navigation, рефакторинг MainActivity в Single Activity, создание `PlaylistNavHost` и enum `Screen`
- **Sprint-5:** Внедрена Clean Architecture, создан эмулятор сервера для поиска треков, реализован слой Data/Domain/UI с репозиторием и DTO
- **Sprint-6:** Реализован SearchScreen с MVVM, подпиской на состояние ViewModel, обработкой всех состояний (Empty/Loading/Content/Error)
- **Sprint-7:** Реализован PlaylistsScreen с плавающей кнопкой FloatingActionButton и логикой
- **Sprint-8:** Экран PlaylistsScreen со списком и созданием новых плейлистов, переход на PlaylistDetailsScreen, экран TrackDetailsScreen с добавлением в избранное и выбором плейлиста через BottomSheet
- **Sprint-9:** Реализованы экраны списка плейлистов, создания плейлиста и деталей с отображением названия, описания, треков и навигацией по Figma
- **Sprint-10:** Переход на реальное локальное хранилище: подключены Room (сущности, DAO, репозитории) и DataStore (история поиска), удалены все моки
- **Sprint-12:** Реализован экран «Избранное» и выполнено финальное задание варианта 1 — добавлено удаление плейлиста как из списка (long-tap), так и со страницы деталей плейлиста (кнопка "многоточие") с удалением из БД и возвратом к списку

## Чек-листы
- [Final-Project-Checklist](checklists/sprint-12.md)
- [Sprint-3](checklists/sprint-3.md)
- [Sprint-4](checklists/sprint-4.md)
- [Sprint-5](checklists/sprint-5.md)
- [Sprint-6](checklists/sprint-6.md)
- [Sprint-7](checklists/sprint-7.md)
- [Sprint-8](checklists/sprint-8.md)
- [Sprint-9](checklists/sprint-9.md)
- [Sprint-10](checklists/sprint-10.md)
- [Sprint-12](checklists/sprint-12.md)
