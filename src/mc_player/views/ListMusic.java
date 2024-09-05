package mc_player.views;

import java.awt.Color;
import mc_player.views.ItemMusic;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionListener;
import mc_player.models.Music_Model;

public class ListMusic<E extends Object> extends JList<E> {

    private final DefaultListModel model;
    private int playIndex = -1;
    public ListMusic() {
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
                Music_Model data;
                if (o instanceof Music_Model) {
                    data = (Music_Model) o;
                } else {
                    data = new Music_Model("1", "No Music", "00:00");
                }
                ItemMusic item = new ItemMusic(data);
                item.setPlay(index == playIndex);
                return item;
            }
        };
    }

    public void addItem(Music_Model data) {
        model.addElement(data);
    }
}
