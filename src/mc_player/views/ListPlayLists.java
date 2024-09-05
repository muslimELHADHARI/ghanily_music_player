package mc_player.views;

import mc_player.models.Playlist_Model;
import mc_player.views.ItemMusic;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionListener;
import mc_player.models.Music_Model;

public class ListPlayLists<E extends Object> extends JList<E> {

    private final DefaultListModel model;
    private int playIndex = -1;

    public ListPlayLists() {
        model = new DefaultListModel();
        setModel(model);
        setOpaque(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    playIndex = locationToIndex(me.getPoint());
                    repaint();
                }
            }
        });
     addListSelectionListener((e)->{
     playIndex=this.getSelectedIndex();
     repaint();
     });
  }
    @Override
    public ListCellRenderer getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object o, int index, boolean selected, boolean focus) {
                Playlist_Model data;
                if (o instanceof Playlist_Model) {
                    data = (Playlist_Model) o;
                } else {
                    String path="C:\\Users\\SGI\\Desktop\\Music\\[SPOTIFY-DOWNLOADER.COM] Nharek Zin\\[SPOTIFY-DOWNLOADER.COM] Hayala.mp3";
                    byte[] datos=new MP3Player().extractImage(path);
                    data = new Playlist_Model("1",datos, "00:00");
                }
                ItemPlayList item = new ItemPlayList(data);
                return item;
            }
        };
    }

    public void addItem(Playlist_Model data) {
        model.addElement(data);
    }
}
