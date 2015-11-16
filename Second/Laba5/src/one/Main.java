package one;

import java.awt.BorderLayout;

public class Main {

    public static void main(String[] args) {
        MyFrame f = new MyFrame();
        f.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        System.out.println(f.getLocationOnScreen());

    }
}

class MyFrame extends javax.swing.JFrame {

    public MyFrame() {
        java.awt.Toolkit t = java.awt.Toolkit.getDefaultToolkit();
        java.awt.Dimension screenSize = t.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        setSize(width / 2, height / 2);
        setLocation(width / 4, height / 4);
        setTitle("MyProgram");
        //setResizable(false);
        setLayout(new java.awt.BorderLayout());
        add(new ButtonPanel(), BorderLayout.NORTH);
        //add(new javax.swing.JLabel("Hello, World!", javax.swing.JLabel.CENTER), BorderLayout.CENTER);
        add(new StringPanel());
    }

}

class StringPanel extends javax.swing.JPanel {

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.setColor(java.awt.Color.darkGray);
        g.drawString("Hello, World",this.getWidth()/2,this.getHeight()/2);
    }
}

class ButtonPanel extends javax.swing.JPanel {

    public ButtonPanel() {
        javax.swing.JButton b1 = new javax.swing.JButton("RED"), b2 = new javax.swing.JButton("GREEN");
        add(b1);
        add(b2);
        b1.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                setBackground(java.awt.Color.RED);
            }

        });
        b2.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                setBackground(java.awt.Color.GREEN);
            }

        });
    }

    public void paintComponenet(java.awt.Graphics g) {
        super.paintComponent(g);

    }
}
