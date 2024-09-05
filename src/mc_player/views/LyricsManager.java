/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mc_player.views;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JLabel;
import mc_player.models.Lyrics_Model;
/**
 *
 * @author SGI
 */
public class LyricsManager {
    private static ArrayList<Lyrics_Model> lyrics=new ArrayList<>();
    private static FileReader f;
    private static Thread lyricThread=null;
    public static void chargeAll() throws IOException{
            Path pth =Paths.get("C:","Users","DELL","Documents","NetBeansProjects","MC_Player","src","mc_player","putsong","lyrics.lrc");
        f=new FileReader(pth.toFile());
        try(BufferedReader bf=new BufferedReader(f)){
        String line="";
        String lyric="";
        Integer delay=0;
        Integer duration=0;
        Integer prevduration=0;
        while((line=bf.readLine())!=null){
            lyric=line.substring(10);
            prevduration=duration;
            Integer minutes=Integer.valueOf(line.substring(1,3))*(60000);
            Integer secondes=Integer.valueOf(line.substring(4, 6))*1000;
            Integer milisecondes=Integer.valueOf(line.substring(7, 9))*10;
            duration=(minutes+secondes+milisecondes);
            delay=duration-prevduration;
            lyrics.add(new Lyrics_Model(delay,lyric,duration));
        }
        }
        lyrics.get(0).setDelay(lyrics.get(0).getDelay()*2);
    }

    public static ArrayList<Lyrics_Model> getLyrics() {
        return lyrics;
    }
    public static void resetThread(){
    if(lyricThread!=null && lyricThread.isAlive()){
    lyricThread.interrupt();}
    lyricThread=null;
    }
    public static void lyricUpdater(JLabel label,JLabel labelf,Integer time) throws FileNotFoundException, IOException, InterruptedException {
        if(lyricThread==null){
        lyricThread =new Thread(()->{
         labelf.setText("");
         boolean begin=false;
         for(Lyrics_Model model:lyrics){
             if(model.getTime()==getApproxLyric(time)){
             begin=true;
             }
             if(begin){
             try {
                 label.setText(model.getLyric());
                 Thread.sleep(model.getDelay());
                 labelf.setText(label.getText());
             } catch (InterruptedException ex) {
                 label.setText("");
                 labelf.setText("");
                // resetThread();
                 break;
             }
        }
         else{
             labelf.setText(model.getLyric());
             }
         }
        });
                lyricThread.start();
        }
    }
public static int getApproxLyric(Integer t){
Integer prev=0;
Integer next=0;
for(Lyrics_Model model:lyrics){
    if(model.getTime()<t){
    prev=model.getTime();
    }
    if(model.getTime()>t){
    next=model.getTime();
    break;
    }
}   
    System.out.println(prev);
    System.out.println(next);
    if(prev==0){return next;}
    return ((t-prev)<(next-t)) ? prev : next ;
}
public static void main(String[] args) throws IOException{
chargeAll();
for(Lyrics_Model model:lyrics){
    System.out.println(model.getTime());   
}
Integer t=0;
System.out.println("************************************************");
System.out.println("the approx of "+t+" is:"+getApproxLyric(t));
System.out.println("************************************************");
}
}

