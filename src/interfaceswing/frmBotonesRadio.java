package interfaceswing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmBotonesRadio extends JFrame {
    private JPanel pnlImagenes;
    private JRadioButton rbtOpcion1;
    private JRadioButton rbtOpcion2;
    private JRadioButton rbtOpcion3;
    private JLabel lblImagenes;

    public frmBotonesRadio(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pnlImagenes);
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(rbtOpcion1);
        buttonGroup1.add(rbtOpcion2);
        buttonGroup1.add(rbtOpcion3);
        rbtOpcion1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblImagenes.setIcon(new
                        javax.swing.ImageIcon(
                                getClass().getResource("/interfaceswing/recursos/img1.jpeg")));
            }
        });

        rbtOpcion2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblImagenes.setIcon(new
                        javax.swing.ImageIcon(
                        getClass().getResource("/interfaceswing/recursos/img2.jpeg")));
            }
        });

        rbtOpcion3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon imagen = new ImageIcon(
                        getClass().getResource("/interfaceswing/recursos/img3.jpeg"));
                lblImagenes.setIcon(imagen);
            }
        });
    }


}
