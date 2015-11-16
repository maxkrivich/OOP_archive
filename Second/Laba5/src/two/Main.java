package two;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {
        MyFrame f = new MyFrame();
    }

}

class MyFrame extends JFrame {

    private TopPanel text = new TopPanel();
    private MyImagePanel imgPanel = new MyImagePanel();
    private MyButtonPanel btnPanel = new MyButtonPanel(new MyButtonListener());
    private MyRadioPanel rbtnPanel = new MyRadioPanel(new MyRadioButtonListener());
    private BorderLayout layout = new BorderLayout();
    private MyCheckBoxPanel colorPanel = new MyCheckBoxPanel(new MyChekBoxListenner());

    public MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Part two");
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension screenSize = t.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        setSize(width / 2, height / 2);
        setLocation(width / 4, height / 4);
        setLayout(layout);
        add(text, BorderLayout.NORTH);
        add(btnPanel, BorderLayout.SOUTH);
        add(imgPanel, BorderLayout.CENTER);
        add(rbtnPanel, BorderLayout.EAST);
        add(colorPanel, BorderLayout.WEST);
        changeColor(colorPanel.getColor());
        this.setVisible(true);
    }

    void changeColor(Color c) {
        for (int i = 0; i < getContentPane().getComponentCount(); i++) {
            getContentPane().getComponent(i).setBackground(c);
        }
    }

    class MyButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String s;
            switch (s = ((JButton) ae.getSource()).getText()) {
                case "1": {
                    text.setText(s);
                    imgPanel.setI(Integer.parseInt(s));
                    break;
                }
                case "2": {
                    text.setText(s);
                    imgPanel.setI(Integer.parseInt(s));
                    break;
                }
                case "3": {
                    text.setText(s);
                    imgPanel.setI(Integer.parseInt(s));
                    break;
                }
                case "4": {
                    text.setText(s);
                    imgPanel.setI(Integer.parseInt(s));
                    break;
                }
            }
        }
    }

    class MyRadioButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String s;
            switch (s = ((JRadioButton) ae.getSource()).getText()) {
                case "YELLOW": {
                    text.setColor(Color.YELLOW);
                    break;
                }
                case "WHITE": {
                    text.setColor(Color.WHITE);
                    break;
                }
            }

        }
    }

    class MyChekBoxListenner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            changeColor(colorPanel.getColor());
        }
    }

}

//}
class MyButtonPanel extends JPanel {

    public MyButtonPanel(ActionListener al) {
        JButton b1 = new JButton("1");
        b1.addActionListener(al);
        JButton b2 = new JButton("2");
        b2.addActionListener(al);
        JButton b3 = new JButton("3");
        b3.addActionListener(al);
        JButton b4 = new JButton("4");
        b4.addActionListener(al);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}

class MyImagePanel extends JPanel {

    private ArrayList<File> arr;
    private BufferedImage image;
    private int i;

    public MyImagePanel() {
        arr = new ArrayList<File>();
        arr.add(new File("1.png"));
        arr.add(new File("2.jpg"));
        arr.add(new File("3.png"));
        arr.add(new File("4.jpg"));
        i = -1;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (i > -1) {
            try {
                image = ImageIO.read(arr.get(i));
                g.drawImage(image, this.getWidth() / 3, this.getHeight() / 8, null);
            } catch (IOException ex) {
            }
        }
    }

    public void setI(int i) {
        this.i = i - 1;
        repaint();

    }
}

class MyRadioPanel extends JPanel {

    public MyRadioPanel(ActionListener al) {
        JRadioButton color1 = new JRadioButton("YELLOW");
        JRadioButton color2 = new JRadioButton("WHITE");
        ButtonGroup group = new ButtonGroup();
        group.add(color1);
        group.add(color2);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        color1.setOpaque(false);
        color2.setOpaque(false);
        add(color1);
        add(color2);
        color1.addActionListener(al);
        color2.addActionListener(al);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}

class MyCheckBoxPanel extends JPanel implements ItemListener {

    private ArrayList<JCheckBox> arr;
    private Color colorMix = Color.BLACK;

    public MyCheckBoxPanel(ActionListener ae) {
        arr = new ArrayList<JCheckBox>();
        arr.add(new JCheckBox("RED", false));
        arr.add(new JCheckBox("GREEN", false));
        arr.add(new JCheckBox("BLUE", false));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (int i = 0; i < arr.size(); i++) {
            arr.get(i).setOpaque(false);
            add(arr.get(i));
            arr.get(i).addItemListener(this);
            arr.get(i).addActionListener(ae);
        }
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        for(int i = 0;i<arr.size();i++)
//            arr.get(i).setBackground(colorMix);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        //for(){}
        //((JCheckBox) e.getSource()).getActionCommand();
        JCheckBox c = (JCheckBox) e.getSource();
        switch (c.getText()) {
            case "RED":
                //Color nc;
                if (c.isSelected()) {
                    colorMix = blend(this.getColor(), new Color(255, 0, 0));
                } else {
                    colorMix = delColor(this.getColor(), new Color(0, 255, 255));
                }
                break;
            case "GREEN":
                if (c.isSelected()) {
                    colorMix = blend(this.getColor(), new Color(0, 255, 0));
                } else {
                    colorMix = delColor(this.getColor(), new Color(255, 0, 255));
                }
                break;
            case "BLUE":
                if (c.isSelected()) {
                    colorMix = blend(this.getColor(), new Color(0, 0, 255));
                } else {
                    colorMix = delColor(this.getColor(), new Color(255, 255, 0));
                }
                break;
        }
        this.setBackground(colorMix);

    }

    private Color blend(Color c1, Color c2) {
        return new Color(c1.getRed() | c2.getRed(), c1.getGreen() | c2.getGreen(), c1.getBlue() | c2.getBlue());
    }

    private Color delColor(Color c1, Color c2) {
        return new Color(c1.getRed() & c2.getRed(), c1.getGreen() & c2.getGreen(), c1.getBlue() & c2.getBlue());
    }

    Color getColor() {
        return colorMix;
    }
}

class TopPanel extends JPanel {

    private JLabel text = new JLabel("", JLabel.CENTER);

    TopPanel() {
        text.setForeground(Color.RED);
        add(text);
    }

    void setText(String s) {
        text.setText(s);
        repaint();
    }

    void setColor(Color c) {
        text.setForeground(c);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
