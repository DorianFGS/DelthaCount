package com.raven.form;

import DatosPrimarios.Eventos;
import DatosSecundarios.Catalogo;
import DatosSecundarios.Cuentas;

import com.raven.component.Form;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import table.TableCustom;

public class Transacciones_Form extends Form {

    Catalogo catalogo;
    boolean operacionValidada;
    String msg = "";
    
    public Transacciones_Form(Catalogo catalogo) {
        initComponents();
        this.catalogo = catalogo;
        this.operacionValidada = false;
        cargarLista();
        tablaCuentas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tablaCuentas.getTableHeader().setReorderingAllowed(false);
        tablaCuentas.getColumnModel().getColumn(0).setMaxWidth(280);
        tablaCuentas.getColumnModel().getColumn(0).setMinWidth(280);
        tablaCuentas.getColumnModel().getColumn(1).setMaxWidth(150);
        tablaCuentas.getColumnModel().getColumn(1).setMinWidth(150);
        tablaCuentas.getColumnModel().getColumn(2).setMaxWidth(150);
        tablaCuentas.getColumnModel().getColumn(2).setMinWidth(150);

        tablaCuentas.getModel().addTableModelListener(
                new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent evt) {
                sumarCargosAbonos();
            }
        });
        catalogo.cargarCatalogo();
        
        configureDarkTheme();
        TableCustom.apply(jScrollPane2, TableCustom.TableType.MULTI_LINE);
    }
    
    private void configureDarkTheme() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configurar colores oscuros para el JTable
        tablaCuentas.setBackground(new Color(51, 51, 51));
        tablaCuentas.setForeground(new Color(255, 255, 255));
        tablaCuentas.setSelectionBackground(new Color(102, 102, 102));
        tablaCuentas.setSelectionForeground(new Color(255, 255, 255));
    }

    // <editor-fold defaultstate="collapsed" desc="Metodos de utilidad">   
    public void cargarLista() {
        comboboxCuentas.removeAllItems();
        comboboxCuentas.addItem("ELIGE UNA CUENTA");

        // <editor-fold defaultstate="collapsed" desc="cargar en la lista las cuentas necesarias">   
        Cuentas actCirc = catalogo.buscar("11");
        Cuentas actNoCirc = catalogo.buscar("12");
        Cuentas pasivosCorto = catalogo.buscar("21");
        Cuentas capitalSocial = catalogo.buscar("31");
        Cuentas resDeud = catalogo.buscar("4");
        Cuentas resAcree = catalogo.buscar("5");
        
        String nombres = actCirc.toString();
        nombres += "\n"+actNoCirc.toString();
        nombres += "\n"+pasivosCorto.toString();
        nombres += "\n"+capitalSocial.toString();
        nombres += "\n"+resDeud.toString();
        nombres += "\n"+resAcree.toString();
        // </editor-fold>   
        
        String[] cuentas = nombres.split("\n");
        for (String cuenta : cuentas) {
            if (cuenta.isBlank()) {
                continue;
            }

            String[] datos = cuenta.split(",");

            if (datos[0].length() == 1) {
                continue;
            }
            
            if (datos[0].equals("11") || datos[0].equals("12") || datos[0].equals("21")) {
                continue;
            }

            String nombre = datos[1];
            comboboxCuentas.addItem(nombre);
        }
    }

    private double inputSaldo() {
        JTextField saldoInput = new JTextField();
        Object[][] message = {
            {"¿Cuánto quieres cargar?", null},
            {"Saldo de la cuenta: ", saldoInput}
        };

        int opcion = JOptionPane.showConfirmDialog(null, message, "Cargar cuenta", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            if (saldoInput.getText().isBlank() || saldoInput.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "No ingresaste nada");
                return -1;
            }

            try {
                double saldo = Double.parseDouble(saldoInput.getText());
                return saldo;
            } catch (InputMismatchException e) {
                JOptionPane.showMessageDialog(null, "Error: Sólo se deben ingresar números." + e.getMessage());
                return -1;
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Error: Entrada numérica no válida." + e.getMessage());
                return -1;
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error: Vuelve a intentarlo." + e.getMessage());
                return -1;
            }
        }

        return -1;
    }

    private void sumarCargosAbonos() {
        int filas = tablaCuentas.getModel().getRowCount();
        if (filas == 0) {
            return;
        }

        List<String> datosCargo = new ArrayList();
        List<String> datosAbono = new ArrayList();
        for (int i = 0; i < filas; i++) {
            datosCargo.add((String) tablaCuentas.getValueAt(i, 1));
            datosAbono.add((String) tablaCuentas.getValueAt(i, 2));
        }

        try {
            double cargos = 0;
            double abonos = 0;
            for (var s : datosCargo) {
                if (s != null && !s.isEmpty()) {
                    cargos += Double.parseDouble(s);
                }
            }

            for (var s : datosAbono) {
                if (s != null && !s.isEmpty()) {
                    abonos += Double.parseDouble(s);
                }
            }
            totalCargos.setText(String.valueOf(cargos));
            totalAbonos.setText(String.valueOf(abonos));

            if (cargos == abonos && cargos != 0) {
                estatus.setText("Los cargos son iguales a los abonos");
                botonValidar.setEnabled(true);
            } else {
                estatus.setText("Los cargos deben ser iguales a los abonos");
                botonValidar.setEnabled(false);
            }

        } catch (InputMismatchException e) {
            System.out.println("Input mismatch: " + e.getMessage());
        }
    }

    // </editor-fold> 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpanel1 = new javax.swing.JPanel();
        botonValidar = new javax.swing.JButton();
        botonCargo = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonAbono = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCuentas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        totalAbonos = new javax.swing.JTextField();
        totalLabel = new javax.swing.JLabel();
        totalCargos = new javax.swing.JTextField();
        estatus = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        comboboxCuentas = new componentes.Combobox();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        conceptoText = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(930, 753));

        jpanel1.setOpaque(false);

        botonValidar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        botonValidar.setText("Validar operación");
        botonValidar.setEnabled(false);
        botonValidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonValidarActionPerformed(evt);
            }
        });

        botonCargo.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        botonCargo.setText("Cargar");
        botonCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCargoActionPerformed(evt);
            }
        });

        botonEliminar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        botonEliminar.setText("Eliminar cuenta seleccionada");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        botonAbono.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        botonAbono.setText("Abonar");
        botonAbono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAbonoActionPerformed(evt);
            }
        });

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        tablaCuentas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tablaCuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cuenta", "Cargo", "Abono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCuentas.setFillsViewportHeight(true);
        tablaCuentas.setShowGrid(true);
        tablaCuentas.setShowHorizontalLines(false);
        tablaCuentas.setUpdateSelectionOnSort(false);
        jScrollPane2.setViewportView(tablaCuentas);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Total de abono");

        totalAbonos.setEditable(false);
        totalAbonos.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        totalLabel.setBackground(new java.awt.Color(0, 0, 0));
        totalLabel.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        totalLabel.setForeground(new java.awt.Color(204, 204, 204));
        totalLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalLabel.setText("Total de cargo");
        totalLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        totalCargos.setEditable(false);
        totalCargos.setBackground(new java.awt.Color(255, 255, 255));
        totalCargos.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        totalCargos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalCargos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalCargosActionPerformed(evt);
            }
        });

        estatus.setEditable(false);
        estatus.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        estatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estatusActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Estatus de la transacción");

        comboboxCuentas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona una cuenta", "Caja", "Ventas", "Compras", "Bancos" }));
        comboboxCuentas.setFocusable(false);
        comboboxCuentas.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        comboboxCuentas.setLabeText("");
        comboboxCuentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxCuentasActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Escribe el concepto:");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        conceptoText.setColumns(20);
        conceptoText.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        conceptoText.setLineWrap(true);
        conceptoText.setRows(2);
        conceptoText.setTabSize(4);
        jScrollPane1.setViewportView(conceptoText);

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Herramienta para realizar asientos contables");

        javax.swing.GroupLayout jpanel1Layout = new javax.swing.GroupLayout(jpanel1);
        jpanel1.setLayout(jpanel1Layout);
        jpanel1Layout.setHorizontalGroup(
            jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanel1Layout.createSequentialGroup()
                        .addComponent(comboboxCuentas, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jpanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanel1Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpanel1Layout.createSequentialGroup()
                                        .addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(botonValidar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(estatus, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpanel1Layout.createSequentialGroup()
                                            .addComponent(totalCargos, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(totalAbonos, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(27, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32))))))))
            .addGroup(jpanel1Layout.createSequentialGroup()
                .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanel1Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(botonCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(botonAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpanel1Layout.setVerticalGroup(
            jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboboxCuentas, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalLabel)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalCargos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalAbonos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(12, 12, 12)
                        .addComponent(estatus, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botonValidar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(200, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    // <editor-fold defaultstate="collapsed" desc="Eventos">  
    private void botonValidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonValidarActionPerformed
        int numeroCuentas = tablaCuentas.getRowCount();
        if (numeroCuentas == 0) {
            return;
        }
        
         // el texto del campo de concepto
         // ejemplo: venta de mercancía al contado
        String concepto = conceptoText.getText();
        if (concepto.isBlank() || concepto.isEmpty()){
            JOptionPane.showMessageDialog(null, "¡Por favor, escribe el concepto del asiento contable!");
            return;
        }

        Object[] vector = new Object[numeroCuentas];

        for (int i = 0; i < numeroCuentas; i++) {
            String nombre = (String) tablaCuentas.getValueAt(i, 0);
            String cargar = (String) tablaCuentas.getValueAt(i, 1);
            String abonar = (String) tablaCuentas.getValueAt(i, 2);

            Cuentas cuenta = catalogo.buscar(nombre);
            Cuentas root = cuenta.getRoot();
            char primerDigito = root.getId().charAt(0);
            boolean deudor = primerDigito == '1' || primerDigito == '4';

            Object[] datos = new String[5];
            datos[0] = cuenta.getNombre();
            datos[1] = String.valueOf(cuenta.getSaldo());

            if (cargar.isEmpty()) {
                try {
                    double abono = Double.parseDouble(abonar);
                    datos[3] = "Abonar"; //Esto es para que el registro sepa si fue abono
                    datos[4] = String.valueOf(abono);
                    if (deudor) {
                        cuenta.restarSaldo(abono);
                        System.out.println("Se resta " + abono + " a la cuenta " + nombre);
                    } else {
                        cuenta.sumarSaldo(abono);
                        System.out.println("Se suma " + abono + " a la cuenta " + nombre);
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Input Mismatch: " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("Exception: " + e.getMessage());
                }
            } else if (abonar.isEmpty()) {
                try {
                    double cargo = Double.parseDouble(cargar);
                    datos[3] = "Cargar"; //Esto es para saber si fue un cargo
                    datos[4] = String.valueOf(cargo);
                    if (deudor) {
                        cuenta.sumarSaldo(cargo);
                        System.out.println("Se suma " + cargo + " a la cuenta " + nombre);
                    } else {
                        cuenta.restarSaldo(cargo);
                        System.out.println("Se resta " + cargo + " a la cuenta " + nombre);
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Input Mismatch: " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("Exception: " + e.getMessage());
                }
            }

            datos[2] = String.valueOf(cuenta.getSaldo());
            vector[i] = datos;
        }

        Date fecha = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd HH:mm:ss");
        String hora = formatter.format(fecha);

        msg += "Registro " + hora + ":\n";
        msg += "Concepto: "+concepto + "\n";
        for (int i = 0; i < vector.length; i++) {
            Object[] datos = (Object[]) vector[i];
            msg += "\t"+datos[3]+" a " + datos[0] + ":\t" + "$"+ datos[4]+ "\n\t\tAntes: $" + datos[1] +"\n\t\tDespués: $"+datos[2]+ "\n";
        }
        System.out.println(msg);

        //operacionValidada = true;
        //botonValidar.setEnabled(false);
        estatus.setText("¡Se asentó la transacción!");
        
        Cuentas Compras = catalogo.buscar("41");
        Cuentas Ventas = catalogo.buscar("51");
        Cuentas Utilidad = catalogo.buscar("32");
        double UT = Ventas.getSaldo()-Compras.getSaldo();
        Utilidad.setSaldo(UT);
        
        Eventos.raiseEvent("actualizar", msg);
    }//GEN-LAST:event_botonValidarActionPerformed

    private void botonCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCargoActionPerformed
        int fila = tablaCuentas.getSelectedRow();
        if (fila == -1) {
            return;
        }

        double saldo = inputSaldo();
        if (saldo != -1) {

            DefaultTableModel model = (DefaultTableModel) tablaCuentas.getModel();
            model.setValueAt(String.format("%.2f",saldo), fila, 1);
            model.setValueAt("", fila, 2);
        }
    }//GEN-LAST:event_botonCargoActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        int fila = tablaCuentas.getSelectedRow();
        if (fila == -1) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tablaCuentas.getModel();
        model.removeRow(fila);

        if (tablaCuentas.getRowCount() > 0) {
            if (fila == 0) {
                tablaCuentas.setRowSelectionInterval(0, 0);
            } else {
                tablaCuentas.setRowSelectionInterval(fila - 1, fila - 1);
            }
        }
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonAbonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAbonoActionPerformed
        int fila = tablaCuentas.getSelectedRow();
        if (fila == -1) {
            return;
        }

        double saldo = inputSaldo();
        if (saldo != -1) {

            DefaultTableModel model = (DefaultTableModel) tablaCuentas.getModel();
            model.setValueAt(String.format("%.2f", saldo), fila, 2);
            model.setValueAt("", fila, 1);
        }
    }//GEN-LAST:event_botonAbonoActionPerformed

    private void totalCargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalCargosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalCargosActionPerformed

    private void estatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estatusActionPerformed

    private void comboboxCuentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxCuentasActionPerformed
        if (comboboxCuentas.getSelectedIndex() == 0) {
            return;
        }

        String item = (String) comboboxCuentas.getSelectedItem();
        Cuentas cuenta = catalogo.buscar(item);
        if (cuenta == null) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tablaCuentas.getModel();
        model.addRow(new String[]{cuenta.getNombre()});
    }//GEN-LAST:event_comboboxCuentasActionPerformed

    // </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="Variables">  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAbono;
    private javax.swing.JButton botonCargo;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonValidar;
    private componentes.Combobox comboboxCuentas;
    private javax.swing.JTextArea conceptoText;
    private javax.swing.JTextField estatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpanel1;
    private javax.swing.JTable tablaCuentas;
    private javax.swing.JTextField totalAbonos;
    private javax.swing.JTextField totalCargos;
    private javax.swing.JLabel totalLabel;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>  
}
