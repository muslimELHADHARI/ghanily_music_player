/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mc_player.models;

/**
 *
 * @author SGI
 */
public class Lyrics_Model {
    Integer delay;
    Integer time;
    String lyric;

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getTime() {
        return time;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public String getLyric() {
        return lyric;
    }

    public Integer getDelay() {
        return delay;
    }

    public Lyrics_Model(Integer delay, String lyric,Integer time) {
        this.delay = delay;
        this.lyric = lyric;
        this.time  = time;
    }

    public Lyrics_Model() {
    }
    
}
