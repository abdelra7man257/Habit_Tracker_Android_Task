# 📱 Habit Tracker Android Application

## 📋 Overview

The **Habit Tracker** is an Android application designed to help users build and maintain habits by tracking their daily activities. Users can create, monitor, and analyze their habits to foster personal growth and productivity.

## ✨ Features

- **Habit Creation**: Add new habits with customizable parameters.
- **Edit Existing Habits**: Modify habit details such as name, frequency, or reminders.
- **Daily Tracking**: Mark habits as completed or missed each day.
- **Progress Visualization**: View statistics and charts to monitor habit streaks and overall progress.

## 🛠️ Tech Stack

- **Programming Language**: Kotlin
- **Architecture**: MVI (Model-View-Intent)
- **UI Framework**: Jetpack Compose
- **Dependency Injection**: Hilt
- **Navigation**: Jetpack Navigation Component
- **Database**: Room
- **Networking**: Retrofit
- **Asynchronous Programming**: Coroutines and Flow
- **Unit Testing**: Junit4 and Mocck

## 🚀 Getting Started

### Prerequisites

- **Android Studio**: Arctic Fox or newer
- **JDK**: Java 8 or higher

## 📂 Project Structure

```
📦 Habit_Tracker_Android_Task
 ┣ 📂 app
 ┃ ┣ 📂 src
 ┃ ┃ ┣ 📂 main
 ┃ ┃ ┃ ┣ 📂 java/com/example/habittracker
 ┃ ┃ ┃ ┃ ┣ 📂 data
 ┃ ┃ ┃ ┃ ┃ ┣ HabitRepository.kt
 ┃ ┃ ┃ ┃ ┃ ┣ local
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ HabitDao.kt
 ┃ ┃ ┃ ┃ ┃ ┣ remote
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ ApiService.kt
 ┃ ┃ ┃ ┃ ┣ 📂 ui
 ┃ ┃ ┃ ┃ ┃ ┣ HabitViewModel.kt
 ┃ ┃ ┃ ┃ ┃ ┣ screens
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ HabitListScreen.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ AddHabitScreen.kt
 ┃ ┃ ┃ ┃ ┣ 📂 utils
 ┃ ┃ ┃ ┃ ┃ ┣ Constants.kt
 ┃ ┃ ┃ ┣ 📂 res
 ┃ ┃ ┃ ┃ ┣ layout
 ┃ ┃ ┃ ┃ ┣ values
 ┃ ┃ ┃ ┃ ┣ drawable
 ┃ ┃ ┃ ┣ AndroidManifest.xml
 ┣ build.gradle
 ┣ settings.gradle
 ┗ README.md
```

## 🧪 Testing

- **Unit Tests**: Located in `src/test/java/com/example/habittracker`
- **Instrumented Tests**: Located in `src/androidTest/java/com/example/habittracker`

To run tests:

- Right-click on the `test` or `androidTest` directory.
- Select **Run Tests**.

## 🤝 Contribution

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new branch: `git checkout -b feature/YourFeature`
3. Commit your changes: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin feature/YourFeature`
5. Open a pull request.

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

