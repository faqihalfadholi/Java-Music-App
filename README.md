# Music App

Music App is an Android application that allows users to listen to music with a user-friendly interface. The app includes features such as login, registration, music playback, and playlist management.

## Key Features

1. User Authentication
2. Music Playback
3. Playlist Management
4. Responsive Interface

## Technologies Used

- **Programming Language**: Java
- **Platform**: Android
- **Database**: Room Persistence Library
- **UI Components**: AndroidX, Material Design
- **Navigation**: AndroidX Navigation Component
- **Dependency Injection**: -
- **Asynchronous Programming**: Java Threads
- **Image Loading**: -
- **Audio Playback**: Android MediaPlayer

## Application Structure

### 1. Authentication

#### LoginActivity
- Uses `SharedPreferences` to store login status
- Implements "Remember Me" functionality using `CheckBox`
- User input validation
- Navigation to `RegisterActivity` for new users

#### RegisterActivity
- User input validation
- Stores new user data in the database using Room

#### User Entity
- Represents the user table in the database
- Attributes: id, username, password

#### UserDAO
- Interface for user-related database operations
- Methods: insert, login, getUserByUsername

#### AppDatabase
- Room database configuration
- Definition of entities and database version

### 2. Start Page

- Displays a welcome message with the user's username
- Button to start the music application

### 3. Music Playback (MainActivity)

#### Music Class
- Representation of a music object
- Attributes: title, artist, image resource, audio resource

#### MusicAdapter
- Custom adapter for displaying the music list in a ListView
- Uses ViewHolder pattern for efficiency

#### Music Playback
- Uses `MediaPlayer` for audio playback
- Implementation of basic controls: play, pause, next, previous
- Displays information about the currently playing song

#### Music List
- Uses `ArrayList<Music>` to store the list of songs
- Hardcoded population of the song list (for demonstration purposes)

### 4. UI/UX

- Use of `ConstraintLayout` for responsive layouts
- Implementation of custom themes and styles
- Use of custom font (Adlam Display)
- Custom button designs using drawable resources

## How to Run the Application

1. Clone this repository
2. Open the project in Android Studio
3. Sync Gradle and ensure all dependencies are installed
4. Run the application on an emulator or Android device

## Future Development

- Implementation of song search feature
- Integration with online music APIs
- Addition of equalizer feature
- Implementation of shuffle and repeat features
- Enhancement of UI/UX with animations

## Contributing

Contributions are always welcome. To contribute:

1. Fork the repository
2. Create a new feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request


## Contact

## Contact

faqih fadholi - [faqihalfadholi@gmail.com](mailto:faqihalfadholi@gmail.com)

Project Link: [https://github.com/faqihalfadholi/Java-Music-App.git](https://github.com/faqihalfadholi/Java-Music-App.git)