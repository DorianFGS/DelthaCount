package Pantallas;

import ScroolbarWin11.ScrollBarWin11UI;
import com.raven.main.Main;
import javax.swing.JOptionPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
public class Cargar extends javax.swing.JFrame {

    public Cargar() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BackGround = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        BarraDeCarga = new javax.swing.JProgressBar();
        Cargar1 = new javax.swing.JLabel();
        Carga = new javax.swing.JLabel();
        BackGroundImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        BackGround.setBackground(new java.awt.Color(102, 102, 102));
        BackGround.setForeground(new java.awt.Color(153, 153, 153));
        BackGround.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/zyro-image.png"))); // NOI18N
        BackGround.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, 300, 330));

        jLabel1.setText("______________________________________________________________");
        BackGround.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 410, -1, -1));
        BackGround.add(BarraDeCarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 624, 1220, 10));

        Cargar1.setForeground(new java.awt.Color(255, 255, 255));
        Cargar1.setText("Cargando....");
        BackGround.add(Cargar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 240, -1));

        Carga.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Carga.setForeground(new java.awt.Color(255, 255, 255));
        Carga.setText("0 %");
        BackGround.add(Carga, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 590, 60, -1));

        BackGroundImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Diseño sin título.png"))); // NOI18N
        BackGround.add(BackGroundImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 640));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackGround, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackGround, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Cargar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cargar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cargar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cargar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        UIDefaults ui = UIManager.getDefaults();
        ui.put("ScrollBarUI", ScrollBarWin11UI.class.getCanonicalName());
        Cargar sp = new Cargar();
        sp.setVisible(true);
        Main inicio = new Main();

        try {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(5);
                sp.Carga.setText(i + "%");

                if (i == 10) {
                    sp.Cargar1.setText("Inicializndo");
                }
                if (i == 20) {
                    sp.Cargar1.setText("Conectando Base De Datos");
                }
                if (i == 30) {
                    sp.Cargar1.setText("Cargando Jtable");
                }
                if (i == 50) {
                    sp.Cargar1.setText("Ordenando Archivos");
                }
                if (i == 60) {
                    sp.Cargar1.setText("Diseño Pre Cargado");
                }
                if (i == 70) {
                    sp.Cargar1.setText("Finalizando Ajustes");
                }
                if (i == 80) {
                    sp.Cargar1.setText("Correciones Completas");
                }
                if (i == 90) {
                    inicio.setVisible(true);
                    sp.setVisible(false);
                    sp.Cargar1.setText("Todo Listo");
                }
                if (i == 100) {
                    sp.Cargar1.setText("Bienvenido");

                }
                sp.BarraDeCarga.setValue(i);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(sp, "Erro Java corruputo");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BackGround;
    private javax.swing.JLabel BackGroundImage;
    private javax.swing.JProgressBar BarraDeCarga;
    private javax.swing.JLabel Carga;
    private javax.swing.JLabel Cargar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
