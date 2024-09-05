/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package mc_player.views;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import mc_player.models.Music_Model;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import mc_player.models.MusicInfo_Model;
/**
 *
 * @author SGI
 */
public class MainMusic_Chnason extends javax.swing.JPanel {

    /**
     * Creates new form MainMusic
     */
    Integer length;
    Integer frames;
    MP3Player mp3Player;
    byte[] imageData;
    ArrayList<String> al;
    ArrayList<MusicInfo_Model> infos;
     public JLabel getLyrics(){
 return jLabel4;
 }
       public JLabel getLyricsf(){
 return jLabel3;
 }
    public ArrayList<String> getPaths() {
        return al;
    }
    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
    public void setFrames(Integer frames) {
        this.frames = frames;
    }

    public Integer getFrames() {
        return frames;
    }
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
    public MainMusic_Chnason() {
        try {
            initComponents();
            init();
        } catch (Exception ex) {
            Logger.getLogger(MainMusic_Chnason.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void PlayFinish(){
                int index= listMusic1.getSelectedIndex();
                index++;
                listMusic1.setSelectedIndex(index);
               
    }
    public void UpdateSongs() throws FileNotFoundException, IOException{
    al.removeAll(al);
    infos.removeAll(infos);
    DefaultListModel dlm=(DefaultListModel)listMusic1.getModel();
    dlm.removeAllElements();
    listMusic1.setSelectedIndex(-1);
        Path pth =Paths.get("C:","Users","DELL","Documents","NetBeansProjects","MC_Player","src","mc_player","putsong","result.txt");
    try(BufferedReader reader=new BufferedReader(new FileReader(pth.toFile()))){
        String line;
        Integer no=1;
        while((line=reader.readLine())!=null){
        String title=mp3Player.extractMetaData(line);
        length=mp3Player.extractLength(line);
        Integer minutes=length/60;
        Integer secondes=length%60;
        String duration="00:00";
        if(minutes<10 && secondes<10){
        duration="0"+String.valueOf(minutes)+":"+"0"+String.valueOf(secondes);
        }
        else if(secondes<10){
        duration=String.valueOf(minutes)+":"+"0"+String.valueOf(secondes);
        }
        else if(minutes<10){
        duration="0"+String.valueOf(minutes)+":"+String.valueOf(secondes);
        }
        else{
        duration=String.valueOf(minutes)+":"+String.valueOf(secondes);
        }
        if(title.length()!=0){
        if(title.length()>65){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<53;i++){
        sb.append(title.charAt(i));
        }
        sb.append("...");
        listMusic1.addItem(new Music_Model(String.valueOf(no),sb.toString(),duration));
        al.add(line);
        MusicInfo_Model model=mp3Player.getInfos(line);
        model.setDuration(duration);
        model.setLength(length);
        infos.add(model);
        no++;
        }
        else{
        listMusic1.addItem(new Music_Model(String.valueOf(no),title,duration));
        al.add(line);
        MusicInfo_Model model=mp3Player.getInfos(line);
        model.setDuration(duration);
        model.setLength(length);
        infos.add(model);
        no++;        }}
        }
        }
    }
    public ArrayList<MusicInfo_Model> getInfos() {
        return infos;
    }
    public void init() throws FileNotFoundException, IOException {
    al=new ArrayList<>();
    infos=new ArrayList<>();
    mp3Player = new MP3Player();
      UpdateSongs();
      listMusic1.addListSelectionListener(new ListSelectionListener(){
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(!e.getValueIsAdjusting()){
            Integer index=listMusic1.getSelectedIndex();
            if(index !=-1){
                MusicInfo_Model model=infos.get(listMusic1.getSelectedIndex());
                imageData=model.getImage();
                setFrames(model.getFrames());
                setLength(model.getLength());
                mp3Player.setFilename(al.get(index));
                mp3Player.stop();
                mp3Player.play();
               // mp3Player.getBytes();
                LyricsManager.resetThread();
                try {

                    LyricsManager.lyricUpdater(jLabel4,jLabel3,0);
                } catch (IOException ex) {
                    Logger.getLogger(MainMusic_Chnason.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainMusic_Chnason.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            }
             SwingUtilities.invokeLater(()->{
            listMusic1.setVisible(false);
            listMusic1.repaint();
            listMusic1.setVisible(true);
             });
        }
    });
    }
          public void jump(int framedeb,int frameend){
          mp3Player.stop();
          mp3Player.play(framedeb,frameend);
         
}
          public void stop(){mp3Player.stop();}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        listMusic1 = new mc_player.views.ListMusic<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Feel the music Listen Now");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Chansons");

        listMusic1.setBackground(new java.awt.Color(153, 153, 153));
        listMusic1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listMusic1MouseClicked(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(153, 153, 153));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("jLabel3");

        jLabel4.setBackground(new java.awt.Color(153, 153, 153));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(listMusic1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listMusic1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addContainerGap(7, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private void listMusic1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMusic1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_listMusic1MouseClicked
public ListMusic getListmusic(){
return listMusic1;
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private mc_player.views.ListMusic<String> listMusic1;
    // End of variables declaration//GEN-END:variables
}
