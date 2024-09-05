/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mc_player.views;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import mc_player.models.Playlist_Model;

/**
 *
 * @author SGI
 */
public class MusicDistribution {
    public static LinkedHashMap<String,ArrayList<Playlist_Model>> distribute() throws FileNotFoundException, IOException
    {
    ArrayList<Playlist_Model> am;
    MP3Player mp3=new MP3Player();
    File path=new File("C:\\Users\\SGI\\Documents\\NetBeansProjects\\MC_Player\\src\\mc_player\\putsong\\result.txt");
    FileReader file=new FileReader(path);
    LinkedHashMap<String,ArrayList<Playlist_Model>> map=new LinkedHashMap<>();
    try(BufferedReader bff=new BufferedReader(file)){
    String line="";
    String artist="lol";
    String title="";
    while((line=bff.readLine())!= null){
        artist=mp3.getInfos(line).getArtist();
        title=mp3.getInfos(line).getTitle();
        if(!map.containsKey(artist)){
        am=new ArrayList<Playlist_Model>();
        am.add(new Playlist_Model(artist,title,mp3.extractImage(line),line));
        map.put(artist,am);
        am=null;
        }
        else{
        map.get(artist).add(new Playlist_Model(artist,title,mp3.extractImage(line),line));
        }
    }
    }
    return map;
    }
    public static void main(String[] args) throws IOException{
    distribute().forEach((k,v)->{System.out.println(k+"---->"+v);});
    }
}
