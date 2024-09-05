/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mc_player.main;

import mc_player.models.Model_Menu;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JSlider;
import javax.swing.RowFilter.Entry;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import mc_player.models.Lyrics_Model;
import mc_player.models.MusicInfo_Model;
import mc_player.models.Music_Model;
import mc_player.models.Playlist_Model;
import mc_player.views.GetallPaths;
import mc_player.views.ListPlayLists;
import mc_player.views.LyricsManager;
import mc_player.views.MP3Player;
import mc_player.views.MainMusic_Playlist;
import mc_player.views.MusicDistribution;
import mc_player.views.ScrollBar;
import mc_player.views.Settings;

/**
 *
 * @author SGI
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    Integer length;
    Integer value; 
    Timer kelma;
    Timer lyrics;
    MainMusic_Playlist mmp;
    LinkedHashMap<String,ArrayList<Playlist_Model>> map;
    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
    public void UpdateSongs(int index) throws FileNotFoundException, IOException{
    MP3Player mp3Player=new MP3Player();
    System.out.println("1");
    DefaultListModel dlm=(DefaultListModel)mmp.getListMusic1().getModel();
    System.out.println("2");
    dlm.removeAllElements();
    System.out.println("3");
        int ind=0;
    //mmp.getListMusic1().setSelectedIndex(-1);
    for(Map.Entry<String, ArrayList<Playlist_Model>> entry:map.entrySet()){
    if(ind==index){
    for(Playlist_Model model:map.get(entry.getKey())){
        System.out.println("mc_player.main.Main.UpdateSongs()");
     System.out.println(model.getDesc());
        System.out.println("mc_player.main.Main.UpdateSongs()");
    int no=1;
    String title=model.getName();
    mmp.getListMusic1().addItem(new Music_Model(String.valueOf(no),title,"00:00"));
    }
    }
    ind++;
    }
      
    }
    public void setLength(Integer length) {
        this.length = length;
    }
    public Main() {
        try{
        value=0;
        initComponents();
        init();
        LyricsManager.chargeAll();
        }
        catch(Exception e){e.printStackTrace();
        }
        System.out.println("mc_player.Views.Main.<init>()");
        listMenu1.addListSelectionListener((ListSelectionEvent e) -> {
            //setLength(mainMusic2.getLength());
            //getit();
            if (!e.getValueIsAdjusting()) {
                Integer index1 = listMenu1.getSelectedIndex();
                if (index1 == 0) {
                    scrollp.setViewportView(mainMusic2);
                    scrollp.revalidate();
                    scrollp.repaint();
                }
                if (index1 == 1) {
                    MP3Player mp3=new MP3Player();
                    mmp=new MainMusic_Playlist();
                    mmp.setBackground(new java.awt.Color(204, 204, 204));
                    scrollp.setViewportView(mmp);
                    scrollp.revalidate();
                    scrollp.repaint();
                    ListPlayLists<String> lp=mmp.getListPlayLists1();
                    try {
                        map=MusicDistribution.distribute();
                        map.forEach((k,v)->{
                            lp.addItem(v.get(0));
                        }); mmp.getListPlayLists1().addListSelectionListener((ListSelectionEvent e1) -> {
                            if (!e1.getValueIsAdjusting()) {
                                Integer index2 = mmp.getListPlayLists1().getSelectedIndex();
                                System.out.println(index2);
                                if (index2 != -1) {
                                    try {
                                        System.out.println(index2);
                                        UpdateSongs(index2);
                                    }catch (IOException ex) {
                                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        });
                    }catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        try {
            getit();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Integer getLength() {
        return length;
    }
    private void getit() throws FileNotFoundException, IOException{
        ArrayList<String> al=new ArrayList<>();
            Path pth =Paths.get("C:","Users","DELL","Documents","NetBeansProjects","MC_Player","src","mc_player","putsong","kelma.txt");
        try(BufferedReader reader=new BufferedReader(new FileReader(pth.toFile()))){
        String line;
        while((line=reader.readLine())!=null){
        al.add(line);
        }}
        Random r=new Random();
        Timer timer = new Timer(10000, new ActionListener() {
          
          @Override
            public void actionPerformed(ActionEvent e) {
                int n=r.nextInt(al.size());
                jLabel7.setText(al.get(n));
            }
        });
        timer.start();
        
    }
 
    private void init(){
       scrollp.setVerticalScrollBar(new ScrollBar());
       setBackground(new Color(0,0,0,0));
       listMenu1.addItem(new Model_Menu("Chansons", "song"));
       listMenu1.addItem(new Model_Menu("Liked songs", "love"));
       listMenu1.addItem(new Model_Menu("Albums", "albums"));
       listMenu1.addItem(new Model_Menu("Artists", "artists"));
       listMenu1.setSelectedIndex(0);
       // list2.addItem(new Model_Menu("Store", "store"));
        //list2.addItem(new Model_Menu("Radio", "radio"));
        //list2.addItem(new Model_Menu("For You", "love"));
        //list2.addItem(new Model_Menu("Browse", "browse"));
        ListMusic();
        downBar1.getSlider().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
            JSlider source=(JSlider)evt.getSource();
            if(!source.getValueIsAdjusting()){
            double newPosition=source.getValue()/100.0;
            Integer nbframes=mainMusic2.getFrames();
          System.out.println(nbframes);
          System.out.println((int)(nbframes*newPosition));
            if(mainMusic2.getFrames()!=null){
                mainMusic2.jump((int)(nbframes*newPosition),nbframes);
                Integer time=(int)((double)mainMusic2.getLength()*newPosition);
                try {
                    System.out.println(time);
                    System.out.println(mainMusic2.getLength());
                    LyricsManager.resetThread();
                    LyricsManager.lyricUpdater(mainMusic2.getLyrics(),mainMusic2.getLyricsf(),time*1000);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                setDeb(time);
            }
            }
        }
        });
        downBar1.getButtonPlay().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                 if(count==0){
                     mainMusic2.stop();
                     timer.stop();
                     count=1;}
                 else{
                     mainMusic2.jump((int)(mainMusic2.getFrames()*(downBar1.getSlider().getValue()/100.0)),mainMusic2.getFrames());
                     timer.start();
                     count=0;
       }
            }});
                downBar1.getButtonNext().addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                            mainMusic2.PlayFinish();
                            jLabel4.setIcon(new ImageIcon(mainMusic2.getImageData())); // NOI18N
                            length=mainMusic2.getLength();
                            downBar1.setEnd(length);
                            setDeb(0);
                            }
                });
                downBar1.getButtonLike().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int index=mainMusic2.getListmusic().getSelectedIndex();
                String file=mainMusic2.getPaths().get(index);
                if(count_love==0){
       changeIcon("love_bot");
                    try {
                        MP3Player.removeAtLiked(file);
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
       count_love=1;}
       else{
       changeIcon("love_selected");
       try {
       MP3Player.saveAtLiked(file);
       }
       catch (IOException ex) {
       Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
       }
       count_love=0;
       }
                
            }
        });
         settings1.getButtonSubmit().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            new Thread(()->{
                try {
            GetallPaths.ListFiles(settings1.getPath());
            mainMusic2.UpdateSongs();
            settings1.setPath("");
            // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
            }).start();   
            
            }
        });
    }
  /////////////////////////////////////////////
//////////////////////////////////////////
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new mc_player.views.Panel();
        menu1 = new mc_player.views.Menu();
        curvesPanel2 = new mc_player.views.CurvesPanel();
        listMenu1 = new mc_player.views.ListMenu<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        settings1 = new mc_player.views.Settings();
        downBar1 = new mc_player.views.DownBar();
        scrollp = new javax.swing.JScrollPane();
        mainMusic2 = new mc_player.views.MainMusic_Chnason();
        jLabel4 = new javax.swing.JLabel();
        menuLyrics1 = new mc_player.views.MenuLyrics();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        musicInfo1 = new mc_player.views.MusicInfo();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        panel1.setBackground(new java.awt.Color(204, 204, 204));

        menu1.setToolTipText("item");

        curvesPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                curvesPanel2MouseDragged(evt);
            }
        });
        curvesPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                curvesPanel2MousePressed(evt);
            }
        });

        listMenu1.setBorder(null);

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("BIBLIOTHEQUE");
        jLabel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mc_player/views/LogoMakerCa-1718955319691_copy_128x56.png"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mc_player/views/LogoMakerCa-1718954848834_copy_107x139.png"))); // NOI18N
        jLabel10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 100));

        javax.swing.GroupLayout curvesPanel2Layout = new javax.swing.GroupLayout(curvesPanel2);
        curvesPanel2.setLayout(curvesPanel2Layout);
        curvesPanel2Layout.setHorizontalGroup(
            curvesPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(curvesPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(curvesPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(curvesPanel2Layout.createSequentialGroup()
                        .addGroup(curvesPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(curvesPanel2Layout.createSequentialGroup()
                        .addComponent(listMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(settings1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        curvesPanel2Layout.setVerticalGroup(
            curvesPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, curvesPanel2Layout.createSequentialGroup()
                .addGroup(curvesPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(curvesPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10))
                    .addGroup(curvesPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel9)))
                .addGap(4, 4, 4)
                .addComponent(jLabel8)
                .addGap(62, 62, 62)
                .addComponent(listMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(settings1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        javax.swing.GroupLayout menu1Layout = new javax.swing.GroupLayout(menu1);
        menu1.setLayout(menu1Layout);
        menu1Layout.setHorizontalGroup(
            menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu1Layout.createSequentialGroup()
                .addComponent(curvesPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        menu1Layout.setVerticalGroup(
            menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(curvesPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        downBar1.setBackground(new java.awt.Color(51, 51, 51));
        downBar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                downBar1MouseEntered(evt);
            }
        });

        scrollp.setBackground(new java.awt.Color(204, 204, 204));
        scrollp.setBorder(null);
        scrollp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollp.setOpaque(false);

        mainMusic2.setBackground(new java.awt.Color(204, 204, 204));
        mainMusic2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainMusic2MouseClicked(evt);
            }
        });
        scrollp.setViewportView(mainMusic2);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Feel the music Listen Now");

        jLabel7.setFont(new java.awt.Font("Script MT Bold", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("5alik t8anily");

        javax.swing.GroupLayout menuLyrics1Layout = new javax.swing.GroupLayout(menuLyrics1);
        menuLyrics1.setLayout(menuLyrics1Layout);
        menuLyrics1Layout.setHorizontalGroup(
            menuLyrics1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLyrics1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuLyrics1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(menuLyrics1Layout.createSequentialGroup()
                        .addComponent(musicInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        menuLyrics1Layout.setVerticalGroup(
            menuLyrics1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLyrics1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(musicInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menuLyrics1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(scrollp, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(downBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53))))))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuLyrics1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollp, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(downBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
int count_love;
private Integer x;
private Integer y;
private Integer count=0;
private void changeIcon(String icon){
       downBar1.getButtonLike().setIcon(new javax.swing.ImageIcon(getClass().getResource("/mc_player/icons/"+icon+".png")));
}
    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered

            }//GEN-LAST:event_formMouseEntered

    private void mainMusic2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainMusic2MouseClicked
   // length=mainMusic2.getLength();
    //downBar1.setEnd(length);    // TODO add your handling code here:
    }//GEN-LAST:event_mainMusic2MouseClicked

    private void downBar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_downBar1MouseEntered
      // TODO add your handling code here:
    }//GEN-LAST:event_downBar1MouseEntered

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void curvesPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_curvesPanel2MousePressed
x=evt.getX();
y=evt.getY();

        // TODO add your handling code here:
    }//GEN-LAST:event_curvesPanel2MousePressed

    private void curvesPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_curvesPanel2MouseDragged
setLocation(evt.getXOnScreen()-x,evt.getYOnScreen()-y);
        // TODO add your handling code here:
    }//GEN-LAST:event_curvesPanel2MouseDragged
private Timer timer ;
private Integer deb;
private Integer index;
private void incIndex(){this.index++;}
    public Integer getDeb() {
        return deb;
    }

    public void setDeb(Integer deb) {
        this.deb = deb;
    }
public int manageLyrics(){

return 0;
}

private void ListMusic(){
mainMusic2.getListmusic().addMouseListener(new java.awt.event.MouseAdapter() {
            
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ArrayList<Lyrics_Model> lc=LyricsManager.getLyrics();
                MusicInfo_Model model=mainMusic2.getInfos().get(mainMusic2.getListmusic().getSelectedIndex());
                model.fixLength();
                musicInfo1.setTitle(model.getTitle());
                musicInfo1.setArtist(model.getArtist());
                musicInfo1.setYear(model.getYear());
                musicInfo1.setDuration(model.getDuration());
                changeIcon();
                length=mainMusic2.getLength();
                downBar1.setEnd(length);
                changeIcon("love_bot");
                count_love=0;
                if(timer !=null && timer.isRunning()){timer.stop();}
                setDeb(0);         
                timer = new Timer(1000, new ActionListener() {
          @Override
            public void actionPerformed(ActionEvent e) {
                if(deb<=length){
                    double db=deb;
                    double len=length;
                    double current=(db/len)*100;
                    downBar1.setSlideValue((int)current);
                    downBar1.setDeb(deb);
                    deb++;
                    
                 
                }
                else{timer.stop();
                mainMusic2.PlayFinish();
                setDeb(0);
                changeIcon();
                length=mainMusic2.getLength();
                downBar1.setEnd(length);
                timer.start();
                }
            }
        });
        timer.start();
        //lrc.start();
     //   lyrics.start();
            }
        });
}
    /**
     * @param args the command line arguments
     */
private Integer getIndex(){return this.index;}
private void changeIcon(){
                if(mainMusic2.getImageData()!=null){
                ImageIcon im=new ImageIcon(mainMusic2.getImageData());
                java.awt.Image original=im.getImage();
                java.awt.Image scaled=original.getScaledInstance(441,215,java.awt.Image.SCALE_SMOOTH);
                jLabel4.setIcon(new ImageIcon(scaled));}
}
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
private void Menulisten(){
   
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private mc_player.views.CurvesPanel curvesPanel2;
    private mc_player.views.DownBar downBar1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private mc_player.views.ListMenu<String> listMenu1;
    private mc_player.views.MainMusic_Chnason mainMusic2;
    private mc_player.views.Menu menu1;
    private mc_player.views.MenuLyrics menuLyrics1;
    private mc_player.views.MusicInfo musicInfo1;
    private mc_player.views.Panel panel1;
    private javax.swing.JScrollPane scrollp;
    private mc_player.views.Settings settings1;
    // End of variables declaration//GEN-END:variables
}
