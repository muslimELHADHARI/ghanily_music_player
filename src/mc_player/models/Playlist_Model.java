/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mc_player.models;

import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author SGI
 */
public class Playlist_Model {
    private String artist;
    public Playlist_Model(String artist, String name, byte[] image, String desc) {
        this.artist = artist;
        this.name = name;
        this.image = image;
        this.desc = desc;
    }
    private String name;
    private byte[] image;
    private String desc;
    private ArrayList<String> songs;
    public Playlist_Model(String name, byte[] image, String desc) {
        songs=new ArrayList<>();
        this.name = name;
        this.image = image;
        this.desc = desc;
    }

    public Playlist_Model() {
    songs=new ArrayList<>();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public String toString() {
        return name+""+desc;
    }

    /**
     * @return the image
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
     
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the songs
     */
    public ArrayList<String> getSongs() {
        return songs;
    }

    /**
     * @param songs the songs to set
     */
    public void setSongs(ArrayList<String> songs) {
        this.songs = songs;
    }
    public void addSong(String song){
    if(!songs.contains(song)){
    songs.add(song);
    }}
    public void removeSong(String song){
    if(songs.contains(song)){
    songs.remove(song);
    }
    }
}
