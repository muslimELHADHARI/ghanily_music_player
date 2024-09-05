/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mc_player.models;

/**
 *
 * @author SGI
 */
public class MusicInfo_Model {
    private String title;
    private String artist;
    private String album;
    private String year;
    private String duration;
    private Integer length;
    private byte[] image;
    private Integer frames;
    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAlbum() {
        return album;
    }

    public MusicInfo_Model(String title, String artist, String album, String year, String duration, Integer length, byte[] image, Integer frames) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.duration = duration;
        this.length = length;
        this.image = image;
        this.frames = frames;
    }
    public void setFrames(Integer frames) {
        this.frames = frames;
    }

    public Integer getFrames() {
        return frames;
    }
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getLength() {
        return length;
    }
    public MusicInfo_Model(String title, String artist, String year, String duration,Integer length,byte[] image,Integer frames) {
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.duration = duration;
        this.length=length;
        this.image=image;
        this.frames=frames;
    }

    @Override
    public String toString() {
        return title+" "+artist;
    }

    public MusicInfo_Model() {
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * @param artist the artist to set
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public void fixLength(){
    if(title.length()>20){
    title=title.substring(0,10)+"...";
    }
    if(artist.length()>20){
    artist=artist.substring(0,10)+"...";
    }
    }
}
