/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mc_player.views;
import java.io.*;
import java.nio.file.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class GetallPaths {
    public static void ListFiles(String directoryPath) throws IOException{
    Path pth =Paths.get("C:","Users","DELL","Documents","NetBeansProjects","MC_Player","src","mc_player","putsong","result.txt");
    FileWriter writer=new FileWriter(pth.toFile());
    Files.walk(Paths.get(directoryPath)).filter(Files::isRegularFile).forEach(file->{
    try{
    String path=file.toString();
    if(verifMp3(path)){
    writer.write(path+"\n");}
    }
    catch(IOException e){e.printStackTrace();}
    });
    
    }
    private static boolean verifMp3(String file){
    String s=file.substring(file.length()-4,file.length());
    return(s.equals(".mp3") ? true:false);
    }
}
