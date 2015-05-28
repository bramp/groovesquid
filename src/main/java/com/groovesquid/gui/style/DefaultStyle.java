package com.groovesquid.gui.style;

import com.groovesquid.gui.FontAwesomeIcon;

import javax.swing.*;
import javax.swing.plaf.TextUI;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

@SuppressWarnings({"rawtypes", "serial", "unchecked"})
public class DefaultStyle extends Style {

    public DefaultStyle() {
        super();
        initVariables();
    }

    private void initVariables() {
        undecorated = false;
        buttonBackgrounds = false;

        searchTableSelectionForeground = Color.WHITE;
        searchTableSelectionBackground = new Color(17, 108, 214);
        downloadTableSelectionForeground = Color.WHITE;
        downloadTableSelectionBackground = new Color(17, 108, 214);

        playIcon = new FontAwesomeIcon(FontAwesomeIcon.PLAY_ICON, 28, Color.DARK_GRAY);
        playIconActive = new FontAwesomeIcon(FontAwesomeIcon.PLAY_ICON, 28, Color.BLACK);

        pauseIcon = new FontAwesomeIcon(FontAwesomeIcon.PAUSE_ICON, 28, Color.DARK_GRAY);
        pauseIconActive = new FontAwesomeIcon(FontAwesomeIcon.PAUSE_ICON, 28, Color.BLACK);

        previousIcon = new FontAwesomeIcon(FontAwesomeIcon.BACKWARD_ICON, 18, Color.DARK_GRAY);
        previousIconActive = new FontAwesomeIcon(FontAwesomeIcon.BACKWARD_ICON, 18, Color.BLACK);

        nextIcon = new FontAwesomeIcon(FontAwesomeIcon.FORWARD_ICON, 18, Color.DARK_GRAY);
        nextIconActive = new FontAwesomeIcon(FontAwesomeIcon.FORWARD_ICON, 18, Color.BLACK);

        volumeOnIcon = new FontAwesomeIcon(FontAwesomeIcon.VOLUME_UP_ICON, 14, Color.DARK_GRAY);
        volumeOffIcon = new FontAwesomeIcon(FontAwesomeIcon.VOLUME_OFF_ICON, 14, Color.DARK_GRAY);
    }

    public TextUI getSearchTextFieldUI(JTextField text) {
        return new RoundedTextFieldUI();
    }

    public static class RoundedTextFieldUI extends BasicTextFieldUI {
        private int round = 5;
        private int shadeWidth = 1;
        private int textSpacing = 3;

        public void installUI(JComponent c) {
            super.installUI(c);

            c.setOpaque(false);

            int s = shadeWidth + 1 + textSpacing;
            c.setBorder(BorderFactory.createEmptyBorder(s, s, s, s));
        }

        protected void paintSafely(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Shape border = getBorderShape();

            Stroke os = g2d.getStroke();
            g2d.setStroke(new BasicStroke(shadeWidth * 2));
            g2d.setPaint(Color.LIGHT_GRAY);
            g2d.draw(border);
            g2d.setStroke(os);

            g2d.setPaint(Color.WHITE);
            g2d.fill(border);

            super.paintSafely(g);
        }

        private Shape getBorderShape() {
            JTextComponent component = getComponent();
            if (round > 0) {
                return new RoundRectangle2D.Double(shadeWidth, shadeWidth,
                        component.getWidth() - shadeWidth * 2 - 1,
                        component.getHeight() - shadeWidth * 2 - 1, round * 2, round * 2);
            } else {
                return new Rectangle2D.Double(shadeWidth, shadeWidth,
                        component.getWidth() - shadeWidth * 2 - 1,
                        component.getHeight() - shadeWidth * 2 - 1);
            }
        }
    }
}
