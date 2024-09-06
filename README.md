
![Capture](https://github.com/user-attachments/assets/9d36b93f-5fcf-45b2-9f57-3b047eeff057)

**# Ghanily: Your Personal Music Oasis**

**Ghanily** is a lightweight and user-friendly Java Swing music player designed to enhance your listening experience. It allows you to manage your music library, create playlists, and discover new favorites with ease.

**Features:**

* **Play Your Music:** Load and play your favorite songs in various formats (MP3, WAV, etc.).
* **Like Songs:** Add songs to your "Liked Songs" playlist for quick access to your most-loved tracks.
* **Coming Soon:**
    * **Album & Artist Management:** Organize your music collection by albums and artists for a more structured library.
    * **Lyrics Manager:** View song lyrics within the player for a deeper connection with your music.

**Getting Started**

**Prerequisites:**

* Java Development Kit (JDK) 8 or later ([https://www.oracle.com/java/technologies/downloads/](https://www.oracle.com/java/technologies/downloads/))

**Building and Running:**

1. Clone this repository:

   ```bash
   git clone https://github.com/<your-username>/ghanily.git
   ```

2. Navigate to the project directory:

   ```bash
   cd ghanily
   ```

3. Compile the project:

   ```bash
   mvn compile
   ```

4. Run the application:

   ```bash
   mvn exec:java -Dexec.mainClass=com.example.ghanily.GhanilyApp
   ```

   (Replace `com.example.ghanily.GhanilyApp` with the actual main class name if it's different)

**Using Ghanily:**

* **File Menu:**
    * Open: Select music files or folders to load into your playlist.
    * Exit: Close the application.
* **Playback Controls:**
    * Play/Pause: Control playback of the current song.
    * Previous/Next: Navigate through your playlist.
    * Volume Slider: Adjust the playback volume.
* **Liked Songs:**
    * Click the heart icon next to a song to add it to your "Liked Songs" playlist.
    * Access your "Liked Songs" playlist through a dedicated tab or menu option.

**Planned Features:**

* Album and artist management for a more organized music library.
* Integrated lyrics manager to view song lyrics within the player.
* Additional features based on user feedback and suggestions.

**Contributing**

We welcome contributions to Ghanily! Feel free to fork the repository, create a pull request with your changes, and open an issue for any bugs or feature requests.

**Enjoy your music with Ghanily!**

**Note:**

* This README assumes a basic directory structure and main class name. Adjust the paths and commands based on your project setup.
* Replace placeholders like `<your-username>` with your actual GitHub username.
* Consider adding screenshots or GIFs to further showcase the application's functionality.
* Include dependency information in your project (e.g., using a `pom.xml` file) and update the build commands accordingly.
* Feel free to customize the README further to reflect your project's specific details and style.
