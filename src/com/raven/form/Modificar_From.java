package com.raven.form;

import DatosPrimarios.Eventos;
import DatosSecundarios.Catalogo;
import DatosSecundarios.Cuentas;
import Interfaces.Listener;
import java.awt.Color;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import table.*;

public class Modificar_From extends javax.swing.JPanel {

    Catalogo catalogo;

    public Modificar_From(Catalogo catalogo) {
        initComponents();
        setEventoTabla();
        setCustomEvents();
        this.catalogo = catalogo;
        this.catalogo.cargarCatalogo();
        catalogoATabla();
        configureDarkTheme();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
    }
     private void configureDarkTheme() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Configurar colores oscuros para el JTable
        tablaCatalogo.setBackground(new Color(51, 51, 51));
        tablaCatalogo.setForeground(new Color(255, 255, 255));
        tablaCatalogo.setSelectionBackground(new Color(102, 102, 102));
        tablaCatalogo.setSelectionForeground(new Color(255, 255, 255));
    }

    private void setEventoTabla() {
        tablaCatalogo.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    datosCuentaTabla();
                }
            }
        });
    }

    private void setCustomEvents() {
        Eventos.addListener("actualizar", new Listener() {
            @Override
            public void accion(Object data) {
                catalogoATabla();
                datosCuentaTabla();

                if (data instanceof String) {
                    textArea.setText("\033[H\033[2J");
                    textArea.setText("\n");
                    textArea.setText(textArea.getText() + (String) data);
                    System.out.flush();
                }
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Metodos de utilidad">   
    private void catalogoATabla() {
        var model = new DefaultTableModel(
                new String[]{
                    "CÓDIGO", "CUENTA"
                }, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String datosCatalogo = catalogo.toString();
        String[] cuentas = datosCatalogo.split("\n");

        System.out.println(datosCatalogo);

        for (String cuenta : cuentas) {
            if (cuenta.isBlank()) {
                continue;
            }

            String[] datos = cuenta.split(",");
            String separador = "   ".repeat(datos[0].length());
            model.addRow(new String[]{datos[0], separador + datos[1]});
        }

        tablaCatalogo.setModel(model);
        tablaCatalogo.clearSelection();

        tablaCatalogo.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        tablaCatalogo.getTableHeader().setReorderingAllowed(false);
        tablaCatalogo.getColumnModel().getColumn(0).setMaxWidth(100);
        tablaCatalogo.getColumnModel().getColumn(0).setMinWidth(100);
        tablaCatalogo.getColumnModel().getColumn(1).setMaxWidth(400);
        tablaCatalogo.getColumnModel().getColumn(1).setMinWidth(400);
    }

    public void datosCuentaTabla() {
        Cuentas cuenta = cuentaSeleccionadaTabla();
        if (cuenta == null) {
            nombreTextfield.setText("");
            codigoTextfield.setText("");
            saldoTextfield.setText("");
            rubroTextfield.setText("");
            return; //No hay nada que mostrar
        }

        nombreTextfield.setText(cuenta.getNombre());
        codigoTextfield.setText(cuenta.getId());
        saldoTextfield.setText(String.valueOf(cuenta.getSaldo()));

        String idRoot = cuenta.getRoot().getId();
        String rubro = "";
        switch (idRoot) {
            case "1" ->
                rubro = "ACTIVO";
            case "2" ->
                rubro = "PASIVO";
            case "3" ->
                rubro = "CAPITAL";
            case "4" ->
                rubro = "CUENTA DE RESULTADOS DEUDORA";
            case "5" ->
                rubro = "CUENTA DE RESULTADOS ACREEDORA";
        }
        rubroTextfield.setText(rubro);
    }

    private Cuentas cuentaSeleccionadaTabla() {
        int fila = tablaCatalogo.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado nada de la tabla","Ayuda",1);
            System.out.println("No se ha seleccionado nada en la tabla");
            return null;
        }

        String id = (String) tablaCatalogo.getValueAt(fila, 0); //Conseguir el id de la tabla (fila n, columna 0)
        Cuentas cuentaSeleccionada = catalogo.buscar(id); //Buscar la cuenta por su código en el catalogo
        return cuentaSeleccionada; //Regresar el resultado (puede ser nulo)
    }

    // </editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCatalogo = new javax.swing.JTable();
        nombreTextfield = new javax.swing.JTextField();
        codigoTextfield = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        saldoTextfield = new javax.swing.JTextField();
        rubroTextfield = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        botonGuardar = new javax.swing.JButton();
        botonCargar = new javax.swing.JButton();
        editarBoton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);

        jScrollPane1.setFont(new java.awt.Font("Lucida Sans Typewriter", 1, 12)); // NOI18N

        tablaCatalogo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tablaCatalogo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Código", "Cuenta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaCatalogo);

        nombreTextfield.setEditable(false);
        nombreTextfield.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        nombreTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreTextfieldActionPerformed(evt);
            }
        });
        nombreTextfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreTextfieldKeyTyped(evt);
            }
        });

        codigoTextfield.setEditable(false);
        codigoTextfield.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        codigoTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoTextfieldActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Nombre :");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Código :");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Saldo : ");

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("Rubro :");

        saldoTextfield.setEditable(false);
        saldoTextfield.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        saldoTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saldoTextfieldActionPerformed(evt);
            }
        });

        rubroTextfield.setEditable(false);
        rubroTextfield.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        rubroTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rubroTextfieldActionPerformed(evt);
            }
        });

        textArea.setEditable(false);
        textArea.setColumns(20);
        textArea.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        textArea.setRows(10);
        textArea.setTabSize(4);
        textArea.setFocusable(false);
        jScrollPane2.setViewportView(textArea);

        botonGuardar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        botonGuardar.setText("Guardar");
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        botonCargar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        botonCargar.setText("Cargar Datos");
        botonCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCargarActionPerformed(evt);
            }
        });

        editarBoton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        editarBoton.setText("Modificar datos");
        editarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarBotonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Buscar cuenta:");

        jButton1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton1.setText("Buscar");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Datos de la cuenta seleccionada");

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Catálogo de cuentas de la empresa");

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("Registro de asientos contables");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(botonCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(botonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(editarBoton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(saldoTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(rubroTextfield)
                                    .addComponent(codigoTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(jScrollPane2)
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(nombreTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(nombreTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(codigoTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rubroTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(saldoTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addComponent(editarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonGuardar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 146, Short.MAX_VALUE))))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 830, 670));
    }// </editor-fold>//GEN-END:initComponents

    private void editarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarBotonActionPerformed
        Cuentas cuenta = cuentaSeleccionadaTabla();
        if (cuenta == null) {
            return;
        } else if (!cuenta.getSubcuentas().isEmpty()) {

            JOptionPane.showMessageDialog(null,
                """
                El saldo de esta cuenta se calcula como
                la suma de los saldos de sus subcuentas
                subcuentas. Solo se puede modificar el
                saldo de cuentas sin subcuentas anidadas.""");
                return;
            }

            JTextField editNombre = new JTextField(cuenta.getNombre());
            JTextField editSaldo = new JTextField(String.valueOf(cuenta.getSaldo()));
            Object[][] mensaje = {
                {"Nombre de la cuenta: ", editNombre},
                {"Saldo de la cuenta: ", editSaldo}
            };

            int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Ingresa los datos de la cuenta", JOptionPane.OK_CANCEL_OPTION);
            if (opcion == JOptionPane.OK_OPTION) {
                try{
                    if (editNombre.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "No dejes en blanco el nombre");
                    return;
                    }
                    
                    if (editSaldo.getText().isBlank()) {
                        JOptionPane.showMessageDialog(null, "Escribe un saldo válido");
                        return;
                    }

                    try {
                        String nombre = editNombre.getText();
                        double saldo = Double.parseDouble(editSaldo.getText());

                        cuenta.setNombre(nombre);
                        cuenta.setSaldo(saldo);

                        datosCuentaTabla();
                    
                } catch (InputMismatchException e) {
                    JOptionPane.showMessageDialog(null, "¡Escribe un saldo válido!");
                }
                }catch(java.lang.NumberFormatException e){
                    JOptionPane.showMessageDialog(editNombre, "Ingresa solo números en: Saldo de la cuenta","Error",1);
                    System.out.println("Ocurrio una Excepcion inesperada");
                }
                
            }

            // TODO add your handling code here:
    }//GEN-LAST:event_editarBotonActionPerformed

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
       JOptionPane.showMessageDialog(null, "Se guardo correctamente","Catálogo",1);
        catalogo.guardarCatalogo();
        // TODO add your handling code here:
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void rubroTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rubroTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rubroTextfieldActionPerformed

    private void saldoTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saldoTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saldoTextfieldActionPerformed

    private void codigoTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigoTextfieldActionPerformed

    private void nombreTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreTextfieldActionPerformed

    private void botonCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCargarActionPerformed
     JOptionPane.showMessageDialog(null, "¡Datos Completamente cargados!","Catálogo",1);
        catalogo.cargarCatalogo();
        catalogoATabla();
    }//GEN-LAST:event_botonCargarActionPerformed

    private void nombreTextfieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreTextfieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreTextfieldKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCargar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JTextField codigoTextfield;
    private javax.swing.JButton editarBoton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField nombreTextfield;
    private javax.swing.JTextField rubroTextfield;
    private javax.swing.JTextField saldoTextfield;
    private javax.swing.JTable tablaCatalogo;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
}
