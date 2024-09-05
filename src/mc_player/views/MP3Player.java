package mc_player.views;
import java.io.BufferedReader;
import java.io.File;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Thread.State;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import mc_player.models.MusicInfo_Model;
import org.jaudiotagger.audio.*;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.*;
import org.jaudiotagger.tag.datatype.Artwork;
public class MP3Player {
    private String filename;
    private AdvancedPlayer player;
    private Thread playerThread;
    private Thread saveThread;
    private static ArrayList<String> liked=new ArrayList<>();
    private boolean playing = false;
    FileInputStream fis ;
    public MP3Player(String filename) {
        this.filename = filename;
    
    }
public boolean getPlaying(){return playing;}
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public State getState(){return playerThread.getState();}
    public String getFilename() {
        return filename;
    }
    public static  void saveAtLiked(String file) throws IOException{
    if(!verifContain(file)){
            Path pth =Paths.get("C:","Users","DELL","Documents","NetBeansProjects","MC_Player","src","mc_player","putsong","liked.txt");
    try(FileWriter fw=new FileWriter(pth.toFile(),true)){
    fw.write(file);
    fw.write("\n");
    liked.add(file);
    }
    }}
    private static boolean verifContain(String file){
    boolean verif=false;
    for(String s:liked){
    if(s.equals(file)){verif=true;}
    }
    return verif;
    }
    public static  void removeAtLiked(String file) throws IOException{
    if(verifContain(file)){
    liked.remove(file);
        Path pth =Paths.get("C:","Users","DELL","Documents","NetBeansProjects","MC_Player","src","mc_player","putsong","liked.txt");
    try(FileWriter fw=new FileWriter(pth.toFile())){
    for(String s:liked){
    fw.write(s);
    fw.write("\n");
    }
    }}}
    public byte[] extractImage(String path){
        try {
            File mp3File =new File(path);
            AudioFile audiofile=AudioFileIO.read(mp3File);
            Tag tag=audiofile.getTag();
            if(tag!=null){
                Artwork artwork =tag.getFirstArtwork();
                if(artwork!=null){
                    byte[] imagedata=artwork.getBinaryData();
                    return imagedata;
                }
            }   } catch (Exception e) {
        
    }
       return null; }
    // Method to start playing the MP3 file
    public void play() {

        playerThread = new Thread(() -> {
            try {
                fis = new FileInputStream(filename);
                player = new AdvancedPlayer(fis);
                playing = true;
                player.play();
            } catch (JavaLayerException e) {
                System.err.println("Error playing MP3 file: " + e.getMessage());
            } catch (IOException ex) {
                Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                playing = false;
                if (player != null) {
                    player.close();
                }
            }
        });
        playerThread.start();
    }
  public void getBytes(){

      
    }
    // Method to stop the MP3 file
    public void stop() {
        if (playing && player != null) {
            player.close();
        }
    }
    public Integer extractLength(String filename){
        try {
            Integer length;
            AudioFile audioFile=AudioFileIO.read(new java.io.File(filename));
            length=audioFile.getAudioHeader().getTrackLength();
            return length;
        } catch (Exception e) {
     
        }
    return 0;
    }
    public MusicInfo_Model getInfos(String filename){
    String title="";
    String artist="";
    String album="";
    String year="";
    try{
    AudioFile audioFile=AudioFileIO.read(new java.io.File(filename));
    Tag tag=audioFile.getTag();
    if(tag!=null){
    title=tag.getFirst(FieldKey.TITLE);
    artist=tag.getFirst(FieldKey.ARTIST);
    album=tag.getFirst(FieldKey.ALBUM);
    year=tag.getFirst(FieldKey.YEAR);
    }
    }
    catch(Exception e){
    e.printStackTrace();
    }
    return new MusicInfo_Model(title,artist,album,year,null,null,extractImage(filename),framesNumbers(filename));
    }  
    public String extractMetaData(String filename){
    String title="";
    try{
    AudioFile audioFile=AudioFileIO.read(new java.io.File(filename));
    Tag tag=audioFile.getTag();
    if(tag!=null){
    title=tag.getFirst(FieldKey.TITLE);
    }
    }
    catch(Exception e){
    e.printStackTrace();
    }
    return title;
    }
    public void play(int framedeb,int frameend) {
        playerThread = new Thread(() -> {
            try {
                FileInputStream fis = new FileInputStream(filename);
                player = new AdvancedPlayer(fis);
                playing = true;
                player.play(framedeb,frameend);
            } catch (FileNotFoundException | JavaLayerException e) {
                System.err.println("Error playing MP3 file: " + e.getMessage());
            } finally {
                playing = false;
                if (player != null) {
                    player.close();
                }
            }
        });
        playerThread.start();
    }
    public  int extractFrames(String filename){
        try {
            long length;
            AudioFile audioFile=AudioFileIO.read(new java.io.File(filename));
            length=audioFile.getAudioHeader().getBitRateAsNumber();
            System.out.println(audioFile.displayStructureAsPlainText());
            return ((int)length*extractLength(filename))/8;
        } catch (Exception e) {
     
        }
    return 0;
    }
    public static int framesNumbers(String filename){
        FileInputStream fis = null;
        Bitstream bs=null;
        try {
            System.out.println("mc_player.views.MP3Player.framesNumbers()");
            int bitRate;
            double sampleRate;
            Integer total;
            Integer header;
            fis = new FileInputStream(filename);
            bs=new Bitstream(new FileInputStream(filename));
            total=fis.readAllBytes().length;
            header=bs.getRawID3v2().readAllBytes().length;
            bitRate=bs.readFrame().bitrate()/1000;
            sampleRate=((double)bs.readFrame().frequency())/1000;
            Integer frameLength=(int)((144*bitRate)/sampleRate)+1;
            Integer length=total-header;
            return length/frameLength;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BitstreamException ex) {
            Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
                bs.close();
            } catch (IOException ex) {
                Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BitstreamException ex) {
                Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
    public MP3Player() {
    }

    public static void main(String[] args){
   //    String filename="C:\\Users\\SGI\\Desktop\\Music\\[SPOTIFY-DOWNLOADER.COM] Gibran Alcocer Mix\\[SPOTIFY-DOWNLOADER.COM] Ballerina.mp3";
       // MP3Player mp3Player = new MP3Player(filename);
        //ArrayList<String> a=new ArrayList<>();
        //Bitstream bs=new Bitstream(new FileInputStream(filename));
        //    length=audioFile.getAudioHeader().getBitRateAsNumber();
     //   System.out.println(audioFile.displayStructureAsPlainText());
          //      System.out.println(audioFile.getAudioHeader().getTrackLength());
       // System.out.println(mp3Player.extractFrames(filename));
         //       System.out.println(bs.readFrame().frequency());
           //     System.out.println(bs.getRawID3v2().readAllBytes().length);
    //         framesNumbers(filename);

        // Start playing in a separate thread
     //  mp3Player.play();
       ///mp3Player.play(6096, 99999);

        // Wait for a while (in main thread) before stopping
        //try(BufferedReader reader=new BufferedReader(new FileReader("C:\\Users\\SGI\\Desktop\\putsong\\result.txt"))){
        //String line;
        //while((line=reader.readLine())!=null){
        //String title=mp3Player.extractMetaData(line);
          //  System.out.println(title);
       // a.add(title);
        //}
        //}
       //System.out.println(a);
        // Stop playback
        //mp3Player.stop();
        
    }
}