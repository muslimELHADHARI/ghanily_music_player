package mc_player.views;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class GradientPanel extends JPanel {

    private final Color gradientStart;
    private final Color gradientEnd;

    public GradientPanel(Color gradientStart, Color gradientEnd) {
        this.gradientStart = gradientStart;
        this.gradientEnd = gradientEnd;
        setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2=(Graphics2D)g;
        //g2.setColor(getBackground());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp=new GradientPaint(0,0,Color.decode("#ff512f"),0,getHeight(),Color.decode("#dd2476"));
        g2.setPaint(gp);
        g2.fillRoundRect(0, 0,getWidth(),getHeight(), 20, 20);
        g2.fillRect(getWidth()-25, 0, getWidth(),getHeight());
        super.paintComponent(g);
    }
}
