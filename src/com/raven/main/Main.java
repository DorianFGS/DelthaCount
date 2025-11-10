package com.raven.main;

import DatosSecundarios.Catalogo;
import javax.swing.*;
import ScroolbarWin11.ScrollBarWin11UI;
import com.raven.event.EventColorChange;
import com.raven.form.Home_Form;
import com.raven.form.Integrantes;
import com.raven.form.Modificar_From;
import com.raven.form.Setting_Form;
import com.raven.form.Transacciones_Form;
import com.raven.menu.EventMenu;
import com.raven.properties.SystemProperties;
import com.raven.theme.SystemTheme;
import com.raven.theme.ThemeColor;
import com.raven.theme.ThemeColorChange;
import java.awt.Color;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;

public class Main extends javax.swing.JFrame {

    private Integrantes equipo;
    private Setting_Form settingForm;
    private Transacciones_Form transacciones;
    private Modificar_From modificar;
    Catalogo catalogo = new Catalogo();

    public Main() {
        catalogo.cargarCatalogo();
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        init();
    }


    private void init() {
        header.initMoving(this);
        header.initEvent(this, panelBackground1);
        menu.addEventMenu(new EventMenu() {
            @Override
            public void selectedMenu(int index) {
                switch (index) {
                    case 0 ->
                        mainBody.displayForm(new Home_Form());
                    case 1 -> {
                        mainBody.displayForm(modificar, "Modificar");
                    }
                    case 2 -> {
                        mainBody.displayForm(transacciones, "Transacciones");
                    }
                    case 3 -> {
                        System.out.println("Creando catalogo en pdf");
                        JOptionPane.showMessageDialog(null, "El Catálogo se Ha Exportado con Exito","Catálogo", 1);
                        catalogo.exportarPDF(FileSystemView.getFileSystemView().getHomeDirectory().getPath()); //exporta el pdf con las cuentas
                        
                    }
                    case 4 -> {
                        System.out.println("Genero exito el balanceGeneral");
                        JOptionPane.showMessageDialog(null, "El Balance General se Ha Exportado con Exito","Balance General", 1);
                        catalogo.balanceGeneral(FileSystemView.getFileSystemView().getHomeDirectory().getPath());
                        
                    }
                    case 5 -> {
                        System.out.println("Creando estado de resultados en el escritorio");
                        JOptionPane.showMessageDialog(null, "El Estado de Resultados se Ha Exportado con Exito","Estado de Resultados", 1);
                        catalogo.estadoResultados(FileSystemView.getFileSystemView().getHomeDirectory().getPath());
                        
                    }
                    case 6 -> {
                        mainBody.displayForm(settingForm, "Setting");
                    }
                    case 8 -> {
                        mainBody.displayForm(equipo, "Equipo");
                    }

                    case 9 -> {
                        int Cerrar = 0;
                        Cerrar = JOptionPane.showOptionDialog(null, "¿Seguro que quieres salir?", "Cerrar Programa", JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE, null, null, null);
                        if(Cerrar == 0){
                            System.exit(0);
                        }
                    }
                }
            }
        });
        ThemeColorChange.getInstance().addThemes(new ThemeColor(new Color(34, 34, 34), Color.WHITE) {
            @Override
            public void onColorChange(Color color) {
                panelBackground1.setBackground(color);
            }
        });
        ThemeColorChange.getInstance().addThemes(new ThemeColor(Color.WHITE, new Color(80, 80, 80)) {
            @Override
            public void onColorChange(Color color) {
                mainBody.changeColor(color);
            }
        });
        ThemeColorChange.getInstance().initBackground(panelBackground1);
        SystemProperties pro = new SystemProperties();
        pro.loadFromFile();
        if (!pro.isDarkMode()) {
            ThemeColorChange.getInstance().setMode(ThemeColorChange.Mode.LIGHT);
            panelBackground1.setBackground(Color.WHITE);
            mainBody.changeColor(new Color(80, 80, 80));
        }
        if (pro.getBackgroundImage() != null) {
            ThemeColorChange.getInstance().changeBackgroundImage(pro.getBackgroundImage());
        }
        SystemTheme.mainColor = pro.getColor();
        settingForm = new Setting_Form();
        settingForm.setEventColorChange(new EventColorChange() {
            @Override
            public void colorChange(Color color) {
                SystemTheme.mainColor = color;
                ThemeColorChange.getInstance().ruenEventColorChange(color);
                repaint();
                pro.save("theme_color", color.getRGB() + "");
            }
        });

        settingForm.setSelectedThemeColor(pro.getColor());
        settingForm.setDarkMode(pro.isDarkMode());
        settingForm.initBackgroundImage(pro.getBackgroundImage());
        mainBody.displayForm(new Home_Form());
        transacciones = new Transacciones_Form(catalogo);
        modificar = new Modificar_From(catalogo);
        equipo = new Integrantes();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBackground1 = new com.raven.swing.PanelBackground();
        header = new com.raven.component.Header();
        menu = new com.raven.menu.Menu();
        mainBody = new com.raven.component.MainBody();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBackground1.setBackground(new java.awt.Color(34, 34, 34));

        javax.swing.GroupLayout panelBackground1Layout = new javax.swing.GroupLayout(panelBackground1);
        panelBackground1.setLayout(panelBackground1Layout);
        panelBackground1Layout.setHorizontalGroup(
            panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBackground1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainBody, javax.swing.GroupLayout.DEFAULT_SIZE, 1008, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBackground1Layout.setVerticalGroup(
            panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackground1Layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                    .addComponent(mainBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        UIDefaults ui = UIManager.getDefaults();
        ui.put("ScrollBarUI", ScrollBarWin11UI.class.getCanonicalName());
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.component.Header header;
    private com.raven.component.MainBody mainBody;
    private com.raven.menu.Menu menu;
    private com.raven.swing.PanelBackground panelBackground1;
    // End of variables declaration//GEN-END:variables
}
