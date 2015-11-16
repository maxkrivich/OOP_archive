
import java.awt.Dimension;
import java.awt.*;
import javax.swing.*;

public class Laba4 extends JFrame {

    Laba4() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("ChatApp");
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension screenSize = t.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        setSize(width / 2, height / 2);
        setLocation(width / 4, height / 4);
        this.getContentPane().setLayout(new BorderLayout());
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1, 2));
        add(p, BorderLayout.SOUTH);
        p.add(new JTextField(40000));
        p.add(new JButton("Send"));
        JTextField f = new JTextField();
        f.setMaximumSize(new Dimension(1000, 25));
        f.setPreferredSize(new Dimension(50, 25));
        add(f, BorderLayout.CENTER);
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(2, 2));
         JPanel p1 = new JPanel();
         p1.add(new JLabel("Local"));
         p1.add(new JTextField(10));
        top.add(p1);
        JPanel p2 = new JPanel();
         p2.add(new JLabel("login"));
         p2.add(new JTextField(10));
         p2.add(new JButton("Disconnect"));
        top.add(p2);
        JButton bb= new JButton("Apply");
//        bb.setMaximumSize(new Dimension(25,23));
//        bb.setPreferredSize(new Dimension(25,23));
        top.add(bb);
        JPanel p3 = new JPanel();
         p3.add(new JLabel("addr"));
         p3.add(new JTextField(10));
         p3.add(new JButton("Connect"));
        top.add(p3);
        add(top, BorderLayout.NORTH);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Laba4();

    }

}
