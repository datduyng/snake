package test;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KeyBidings extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int x = 0;
    int y = 0;

    DrawPanel drawPanel = new DrawPanel();

    public KeyBidings(){
        Action rightAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                x +=10;
                drawPanel.repaint();
            }
        };

            InputMap inputMap = drawPanel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = drawPanel.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        actionMap.put("rightAction", rightAction);

        add(drawPanel);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class DrawPanel extends JPanel {


        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.GRAY);
                    g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.GREEN);
            g.fillRect(x, y, 50, 50);
        }

        public Dimension getPreferredSize() {
            return new Dimension(400, 200);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                new KeyBidings();
            }
        });
    }
}