package com.mycompany.proyecto1alospits;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana donde se escoge el tipo de servicio que se va a registrar
 * @author Luis Barreda, Fabian Miranda, Franco Rojas
 * @see tiposervicioGUI
 */
public class tiposervicioGUI {
    JFrame frame = new JFrame("Tipo de Servicio");

    static JLabel fondo = new JLabel(new ImageIcon("fondo.jpg"));
    static JLabel tipo_servicio = new JLabel();
    static JLabel indicador_id = new JLabel();
    public String[] id_array;
    public JComboBox combobox_id;
    public JButton mecanica_general = new JButton();
    public JButton enderezado_pintura = new JButton();
    Cliente cliente = new Cliente();

    /**
     * Metodo constructor donde se editan los objetos de la ventana
     */
    tiposervicioGUI() {
        //Labels
        tipo_servicio.setFont(new Font("", Font.PLAIN, 21));
        tipo_servicio.setForeground(Color.blue);
        tipo_servicio.setBackground(Color.white);
        tipo_servicio.setOpaque(true);
        tipo_servicio.setText("Tipo de Servicio");
        tipo_servicio.setBounds(70, 10, 200, 25);

        indicador_id.setFont(new Font("", Font.BOLD, 12));
        indicador_id.setForeground(Color.blue);
        indicador_id.setBackground(Color.white);
        indicador_id.setOpaque(true);
        indicador_id.setText("Identificacion");
        indicador_id.setBounds(100, 70, 185, 20);

        //Combobox
        id_array = cliente.getIDs();
        combobox_id = new JComboBox(id_array);
        combobox_id.setBounds(50, 50, 185, 20);
        combobox_id.setBackground(Color.white);

        //Buttons
        mecanica_general.setFont(new Font("", Font.BOLD, 12));
        mecanica_general.setText("Mecanica General");
        mecanica_general.setBackground(Color.white);
        mecanica_general.setBounds(55, 110, 170, 20);

        enderezado_pintura.setFont(new Font("", Font.BOLD, 12));
        enderezado_pintura.setText("Enderezado/Pintura");
        enderezado_pintura.setBackground(Color.white);
        enderezado_pintura.setBounds(55, 130, 170, 20);

        frame.setLayout(null);
        frame.setBounds(500, 200, 300, 200);

        fondo.setBounds(0, 0, frame.getWidth(), frame.getHeight());

        frame.add(tipo_servicio);
        frame.add(indicador_id);
        frame.add(combobox_id);
        frame.add(mecanica_general);
        frame.add(enderezado_pintura);
        frame.add(fondo);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        /**
         * Acciones asociadas a los botones que abren las ventanas de registro de enderezado/pintura o mecanica general
         */
        mecanica_general.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identificacion = String.valueOf(combobox_id.getSelectedItem());
                mecanica_generalGUI ventana_mecanica_general = new mecanica_generalGUI(identificacion);
            }
        });

        enderezado_pintura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identificacion = String.valueOf(combobox_id.getSelectedItem());
                enderezado_pinturaGUI ventana_enderezado_pintura = new enderezado_pinturaGUI(identificacion);
            }
        });
    }
}
