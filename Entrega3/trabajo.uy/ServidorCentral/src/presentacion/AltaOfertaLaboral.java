package presentacion;


import excepciones.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa;
import excepciones.ExceptionCostoPaqueteNoNegativo;
import excepciones.ExceptionDescuentoInvalido;
import excepciones.ExceptionEmpresaInvalida;
import excepciones.ExceptionPaqueteNoVigente;
import excepciones.ExceptionRemuneracionOfertaLaboralNegativa;
import excepciones.ExceptionUsuarioNoEncontrado;
import excepciones.NoExistePaquete;

import logica.Fabrica;
import logica.datatypes.DTHora;
import logica.datatypes.DTHorario;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;

import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JDesktopPane;
//import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
//import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
//import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
//import javax.swing.ScrollPaneConstants;
//import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JComponent;
import javax.swing.SpinnerNumberModel;
import javax.swing.ButtonGroup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;


@SuppressWarnings("serial")
public class AltaOfertaLaboral extends JInternalFrame {
    private JTable table;
    private JTextField nombre;
    private JTextField ciudad;
    private JTextField remuneracion;
    private JTextField fechaAlta1;
    private JComboBox<String> listadoEmpresas;
    private JComboBox<String> listadoOfertas;
    //private JComboBox<String> listadoKeywords;
    private JComboBox<String> listadoDepartamentos;
    private ICtrlUsuario icUsuario;
    private JTextArea descripcion;
    private String empresa;
    private String ofertaLab;
    private ICtrlOferta icOferta;
    private JSpinner desdehora;
    private JSpinner desdemin;
    private JSpinner hastahora;
    private JSpinner hastamin;
    private List<String> keywords;
    private String dep;
    private JRadioButton botonSinPaq;
    private JRadioButton botonConPaq;

    private JList<String> availableList;
    private JList<String> selectedList;
    private DefaultListModel<String> availableListModel;
    private DefaultListModel<String> selectedListModel;
    private String opcionPaq;
    private JComboBox<String> comboPaquete;

    /**
     * Create the frame.
     */
    public AltaOfertaLaboral(ICtrlUsuario icu) {
        Fabrica fabrica = Fabrica.getInstance();
        ICtrlOferta ico = fabrica.getICtrlOferta();
        icUsuario = icu;
        icOferta = ico;

        setResizable(true);
        setIconifiable(false);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta Oferta Laboral");
        setBounds(30, 30, 550, 550);
        getContentPane().setLayout(null);


        JLabel lblNewLabel = new JLabel("Listado Empresas:");
        lblNewLabel.setBounds(21, 25, 191, 14);
        getContentPane().add(lblNewLabel);

        table = new JTable();
        table.setBounds(67, 45, 1, 1);
        getContentPane().add(table);

        //KEYWORDS
        
        /*listadoKeywords = new JComboBox<String>();
        listadoKeywords.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String k = (String) listadoKeywords.getSelectedItem();
        		ks.add(k);
        		// listadoKeywords.removeItem(k);
        	}
        });
        listadoKeywords.setBounds(140,  350,  382,  22);
        getContentPane().add(listadoKeywords);*/

        ////////////
        /*availableListModel = new DefaultListModel<>();
        selectedListModel = new DefaultListModel<>();
        HashSet<String> keys = icOferta.listarKeywords();
        List<String> keysSorted = new ArrayList<>(keys);
        Collections.sort(keysSorted,  String.CASE_INSENSITIVE_ORDER);
        
        for (String item : keysSorted) {
            availableListModel.addElement(item);
        }*/
        ///////////////

        availableListModel = new DefaultListModel<>();
        selectedListModel = new DefaultListModel<>();
        
        /*HashSet<String> keys = icOferta.listarKeywords();
        List<String> keysSorted = new ArrayList<>(keys);
        Collections.sort(keysSorted,  String.CASE_INSENSITIVE_ORDER);
        for (String item : keysSorted) {
            availableListModel.addElement(item);
        }*/


        availableList = new JList<>(availableListModel);
        availableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectedList = new JList<>(selectedListModel);
        selectedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane AvailableL = new JScrollPane(availableList);
        AvailableL.setSize(180, 60);
        AvailableL.setLocation(140, 330);
        getContentPane().add(AvailableL);

        JScrollPane SelectedL = new JScrollPane(selectedList);
        SelectedL.setLocation(342, 330);
        SelectedL.setSize(180, 60);
        getContentPane().add(SelectedL);

        ///BOTONES

        JButton addButton = new JButton("Agregar>");
        addButton.setSize(109, 20);
        addButton.setLocation(211, 400);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                String selectedValue = availableList.getSelectedValue(); // Obtiene el valor del elemento seleccionado

                if (selectedValue != null) { // Verifica si se ha seleccionado un elemento
                    String key = (String) selectedValue; // Convierte el valor en una cadena si es necesario
                    transferirElemento(availableList, selectedListModel, availableListModel);
                    keywords.add(key);
                }
            }
        });

        JButton removeButton = new JButton("<Quitar");
        removeButton.setSize(114, 20);
        removeButton.setLocation(342, 400);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {

                if (selectedList.getSelectedIndex() != -1) {
                    String key = (String) selectedList.getSelectedValue();
                    keywords.remove(key);
                    transferirElemento(selectedList, availableListModel, selectedListModel);

                }
            }
        });


        getContentPane().add(addButton);
        getContentPane().add(removeButton);


        ///////////////////
        listadoEmpresas = new JComboBox<String>();
        listadoEmpresas.setBounds(270, 21, 253, 22);
        getContentPane().add(listadoEmpresas);

        listadoEmpresas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                empresa = (String) listadoEmpresas.getSelectedItem();
            }
        });

        JLabel lblNewLabel_1 = new JLabel("Listado de Tipo de Oferta:");
        lblNewLabel_1.setBounds(21, 52, 191, 18);
        getContentPane().add(lblNewLabel_1);

        listadoOfertas = new JComboBox<String>();
        listadoOfertas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {

                ofertaLab = (String) listadoOfertas.getSelectedItem();
            }
        });
        listadoOfertas.setBounds(269, 50, 253, 22);
        getContentPane().add(listadoOfertas);


        JLabel lblNewLabel_2 = new JLabel("Nombre:");
        lblNewLabel_2.setBounds(21, 93, 138, 14);
        getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Descripcion:");
        lblNewLabel_3.setBounds(21, 120, 139, 14);
        getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Desde:");
        lblNewLabel_4.setBounds(140, 180, 98, 14);
        getContentPane().add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Hasta:");
        lblNewLabel_5.setBounds(371, 180, 53, 14);
        getContentPane().add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("Remuneracion:");
        lblNewLabel_6.setBounds(21, 248, 177, 14);
        getContentPane().add(lblNewLabel_6);

        JLabel lblNewLabel_7 = new JLabel("Departamento:");
        lblNewLabel_7.setBounds(21, 280, 139, 14);
        getContentPane().add(lblNewLabel_7);

        JLabel lblNewLabel_8 = new JLabel("Ciudad:");
        lblNewLabel_8.setBounds(359, 280, 65, 14);
        getContentPane().add(lblNewLabel_8);

        JLabel lblNewLabel_9 = new JLabel("Fecha de Alta:");
        lblNewLabel_9.setBounds(21, 310, 177, 14);
        getContentPane().add(lblNewLabel_9);

        JLabel lblNewLabel_10 = new JLabel("Keywords:");
        lblNewLabel_10.setBounds(21, 338, 139, 14);
        getContentPane().add(lblNewLabel_10);


        JLabel lblNewLabel_11 = new JLabel("Paquete:");
        lblNewLabel_11.setBounds(21, 435, 145, 14);
        getContentPane().add(lblNewLabel_11);

        ////////// BOTONES PAQUETE

        ButtonGroup buttonGroup = new ButtonGroup();
        botonSinPaq = new JRadioButton("Sin Paquete");

        botonConPaq = new JRadioButton("Con Paquete");

        buttonGroup.add(botonConPaq);
        buttonGroup.add(botonSinPaq);
        botonSinPaq.setBounds(140, 427, 109, 23);
        getContentPane().add(botonSinPaq);
        botonConPaq.setBounds(251, 427, 109, 23);
        getContentPane().add(botonConPaq);

        opcionPaq = null;

        botonSinPaq.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eboton) {
                comboPaquete.setEnabled(false);
                opcionPaq = null;
                comboPaquete.setSelectedIndex(-1);
            }
        });

        comboPaquete = new JComboBox<String>();
        comboPaquete.setBounds(261, 457, 180, 22);
        getContentPane().add(comboPaquete);
        comboPaquete.setSelectedIndex(-1);


        botonConPaq.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eboton) {
                comboPaquete.setEnabled(true);
                //comboPaquete.setSelectedItem("Opción 1");
                //seleccionar en combobox el paquete
                //opcionPaq = "Sin Paquete";

            }
        });

        comboPaquete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eboton) {
                opcionPaq = (String) comboPaquete.getSelectedItem();
            }
        });

        ////////////


        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                DTHora desde = new DTHora((int) desdehora.getValue(),
                        (int) desdemin.getValue());

                DTHora hasta = new DTHora((int) hastahora.getValue(),
                        (int) hastamin.getValue());
                DTHorario horario = new DTHorario(desde, hasta);

                DepUY departamento = null;

                switch (dep) {
                    case "Artigas":
                        departamento = DepUY.Artigas;
                        break;
                    case "Salto":
                        departamento = DepUY.Salto;
                        break;
                    case "Paysandú":
                        departamento = DepUY.Paysandu;
                        break;
                    case "RioNegro":
                        departamento = DepUY.RioNegro;
                        break;
                    case "Soriano":
                        departamento = DepUY.Soriano;
                        break;
                    case "Colonia":
                        departamento = DepUY.Colonia;
                        break;
                    case "Rivera":
                        departamento = DepUY.Rivera;
                        break;
                    case "Tacuarembo":
                        departamento = DepUY.Tacuarembo;
                        break;
                    case "Durazno":
                        departamento = DepUY.Durazno;
                        break;
                    case "Flores":
                        departamento = DepUY.Flores;
                        break;
                    case "Florida":
                        departamento = DepUY.Florida;
                        break;
                    case "SanJosé":
                        departamento = DepUY.SanJose;
                        break;
                    case "Canelones":
                        departamento = DepUY.Canelones;
                        break;
                    case "Montevideo":
                        departamento = DepUY.Montevideo;
                        break;
                    case "Cerro Largo":
                        departamento = DepUY.CerroLargo;
                        break;
                    case "Treinta y Tres":
                        departamento = DepUY.TreintaYTres;
                        break;
                    case "Lavalleja":
                        departamento = DepUY.Lavalleja;
                        break;
                    case "Rocha":
                        departamento = DepUY.Rocha;
                        break;
                    case "Maldonado":
                        departamento = DepUY.Maldonado;
                        break;
                    default:
                        JOptionPane.showMessageDialog(AltaOfertaLaboral.this, "Departamento = Null", "ERROR - Alta de Postulante", JOptionPane.ERROR_MESSAGE);
                        break;
                }

                String nomb = nombre.getText();
                String ciu = ciudad.getText();
                String desc = descripcion.getText();
                String remuString = remuneracion.getText();
                //Float remu = Float.parseFloat(remuneracion.getText());

                if (nomb.isEmpty() || ciu.isEmpty() || desc.isEmpty() || remuneracion.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(AltaOfertaLaboral.this, "No pueden existir campos vacíos.", "ERROR - Alta de Oferta", JOptionPane.ERROR_MESSAGE);
                } else if (!nomb.matches("^[\\p{L} ]+$")) {
                    JOptionPane.showMessageDialog(AltaOfertaLaboral.this, "El nombre indicado se compone de carácteres que no son letras.", "ERROR - Alta Oferta Laboral", JOptionPane.ERROR_MESSAGE);
                } else if (!ciu.matches("^[\\p{L} ]+$")) {
                    JOptionPane.showMessageDialog(AltaOfertaLaboral.this, "La ciudad indicada se compone de carácteres que no son letras.", "ERROR - Alta Oferta Laboral", JOptionPane.ERROR_MESSAGE);
                } else if (!botonConPaq.isSelected() && !botonSinPaq.isSelected()) {
                    JOptionPane.showMessageDialog(AltaOfertaLaboral.this, "Debe seleccionar si la oferta corresponde a un paquete o no.", "ERROR - Alta Oferta Laboral", JOptionPane.ERROR_MESSAGE);

                } else {


                    try {
                    	Float remu = Float.parseFloat(remuneracion.getText());
                    	
                    	
                        boolean noexiste = icUsuario.altaOfertaLaboral(empresa, ofertaLab, nomb, desc, horario, remu, ciu, departamento, LocalDate.now(), keywords, EstadoOL.Ingresada, null, opcionPaq);
                        if (!noexiste) {
                            JOptionPane.showMessageDialog(AltaOfertaLaboral.this, "Ya existe una oferta laboral con el nombre indicado.", "ERROR - Alta Oferta Laboral", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(AltaOfertaLaboral.this, "La oferta laboral se dio de alta exitosamente", "Alta Oferta Laboral", JOptionPane.INFORMATION_MESSAGE);
                            botonSinPaq.setSelected(false);
                            botonConPaq.setSelected(false);
                            setVisible(false);
                            limpiarFormulario();
                        }
                    } catch (ExceptionUsuarioNoEncontrado | ExceptionEmpresaInvalida |
                             ExceptionRemuneracionOfertaLaboralNegativa e1) {
                        JOptionPane.showMessageDialog(AltaOfertaLaboral.this, e1.getMessage(), "ERROR - Alta Oferta Laboral", JOptionPane.ERROR_MESSAGE);

                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(AltaOfertaLaboral.this, "La remuneración debe ser un número.", "ERROR - Alta Oferta Laboral", JOptionPane.ERROR_MESSAGE);
                    } catch (ExceptionPaqueteNoVigente exc) {
                        JOptionPane.showMessageDialog(AltaOfertaLaboral.this, "Paquete no vigente", "ERROR - Alta Oferta Laboral", JOptionPane.ERROR_MESSAGE);
                    } catch (ExceptionDescuentoInvalido exc) {
                        JOptionPane.showMessageDialog(AltaOfertaLaboral.this, "Descuento no válido", "ERROR - Alta Oferta Laboral", JOptionPane.ERROR_MESSAGE);
                    } catch (ExceptionCostoPaqueteNoNegativo exc) {
                        JOptionPane.showMessageDialog(AltaOfertaLaboral.this, "Costo no puede ser negativo", "ERROR - Alta Oferta Laboral", JOptionPane.ERROR_MESSAGE);
                    } catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa exc) {
                        JOptionPane.showMessageDialog(AltaOfertaLaboral.this, "Sin disponibilidad del Tipo Oferta en Paquete Seleccionado", "ERROR - Alta Oferta Laboral", JOptionPane.ERROR_MESSAGE);
                    } catch (NoExistePaquete e) {
                        throw new RuntimeException(e);
                    }


                }


            }
        });

        btnAceptar.setBounds(342, 490, 89, 23);
        getContentPane().add(btnAceptar);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                limpiarFormulario();
                setVisible(false);
            }
        });
        btnCerrar.setBounds(433, 490, 89, 23);
        getContentPane().add(btnCerrar);

        nombre = new JTextField();
        nombre.setBounds(205, 90, 318, 20);
        getContentPane().add(nombre);
        nombre.setColumns(10);

        descripcion = new JTextArea();
        descripcion.setBounds(205, 120, 317, 54);
        getContentPane().add(descripcion);

        listadoDepartamentos = new JComboBox<String>();
        listadoDepartamentos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                dep = (String) listadoDepartamentos.getSelectedItem();
            }
        });
        listadoDepartamentos.setBounds(140, 275, 191, 22);
        getContentPane().add(listadoDepartamentos);

        listadoDepartamentos.addItem("Artigas");
        listadoDepartamentos.addItem("Canelones");
        listadoDepartamentos.addItem("Cerro Largo");
        listadoDepartamentos.addItem("Colonia");
        listadoDepartamentos.addItem("Durazno");
        listadoDepartamentos.addItem("Flores");
        listadoDepartamentos.addItem("Florida");
        listadoDepartamentos.addItem("Lavalleja");
        listadoDepartamentos.addItem("Maldonado");
        listadoDepartamentos.addItem("Montevideo");
        listadoDepartamentos.addItem("Paysandú");
        listadoDepartamentos.addItem("RioNegro");
        listadoDepartamentos.addItem("Rivera");
        listadoDepartamentos.addItem("Rocha");
        listadoDepartamentos.addItem("Salto");
        listadoDepartamentos.addItem("SanJosé");
        listadoDepartamentos.addItem("Soriano");
        listadoDepartamentos.addItem("Tacuarembo");
        listadoDepartamentos.addItem("Treinta y Tres");


        ciudad = new JTextField();
        ciudad.setBounds(414, 275, 109, 20);
        getContentPane().add(ciudad);
        ciudad.setColumns(10);

        remuneracion = new JTextField();
        remuneracion.setBounds(140, 245, 382, 20);
        getContentPane().add(remuneracion);
        remuneracion.setColumns(10);
        
        /*JList list = new JList();
        list.setBounds(172,  366,  51,  -25);
        getContentPane().add(list);*/


        fechaAlta1 = new JTextField();
        fechaAlta1.setBounds(140, 304, 100, 20);
        getContentPane().add(fechaAlta1);
        fechaAlta1.setColumns(10);


        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate currentDate = LocalDate.now();
        fechaAlta1.setText(currentDate.format(dateFormatter));
        fechaAlta1.setEditable(false);

        desdehora = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        desdehora.setBounds(111, 205, 46, 20);
        getContentPane().add(desdehora);
        JComponent editor = desdehora.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            ((JSpinner.DefaultEditor) editor).getTextField().setEditable(false);
        }


        desdemin = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        desdemin.setBounds(194, 205, 46, 20);
        getContentPane().add(desdemin);
        JComponent editor1 = desdemin.getEditor();
        if (editor1 instanceof JSpinner.DefaultEditor) {
            ((JSpinner.DefaultEditor) editor1).getTextField().setEditable(false);
        }

        hastahora = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        hastahora.setBounds(333, 205, 46, 20);
        getContentPane().add(hastahora);
        JComponent editor2 = hastahora.getEditor();
        if (editor2 instanceof JSpinner.DefaultEditor) {
            ((JSpinner.DefaultEditor) editor2).getTextField().setEditable(false);
        }

        hastamin = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        hastamin.setBounds(420, 205, 46, 20);
        getContentPane().add(hastamin);
        JComponent editor3 = hastamin.getEditor();
        if (editor3 instanceof JSpinner.DefaultEditor) {
            ((JSpinner.DefaultEditor) editor3).getTextField().setEditable(false);
        }

        JLabel lblHora = new JLabel("Hora");
        lblHora.setBounds(72, 205, 70, 15);
        getContentPane().add(lblHora);

        JLabel lblHora_1 = new JLabel("Hora");
        lblHora_1.setBounds(301, 205, 70, 15);
        getContentPane().add(lblHora_1);

        JLabel lblMin = new JLabel("Min");
        lblMin.setBounds(163, 205, 35, 15);
        getContentPane().add(lblMin);

        JLabel lblMin_1 = new JLabel("Min");
        lblMin_1.setBounds(389, 205, 35, 15);
        getContentPane().add(lblMin_1);


    }

    public void actualizar() {
        keywords = new ArrayList<>();

        listadoEmpresas.removeAllItems();
        //listadoKeywords.removeAllItems();
        Set<String> empresas = icUsuario.listarEmpresas();
        Set<String> keys = icOferta.listarKeywords();
        List<String> keysSorted = new ArrayList<>(keys);
        Collections.sort(keysSorted, String.CASE_INSENSITIVE_ORDER);

        // listadoKeywords.addItem("");
        /*for (String elemento1 : keysSorted) {
        	listadoKeywords.addItem(elemento1);
        }*/

        //availableListModel.clear();

        availableListModel.clear();
        selectedListModel.clear();

        for (String item : keysSorted) {
            availableListModel.addElement(item);
        }

        /////

        listadoEmpresas.addItem("");
        List<String> empresaSorted = new ArrayList<>(empresas);
        Collections.sort(empresaSorted, String.CASE_INSENSITIVE_ORDER);

        for (String elemento : empresaSorted) {
            listadoEmpresas.addItem(elemento);
        }

        Set<String> tiposDePub = icOferta.listarTipoDePublicaciones();
        List<String> tipoSorted = new ArrayList<>(tiposDePub);
        Collections.sort(tipoSorted, String.CASE_INSENSITIVE_ORDER);


        listadoOfertas.removeAllItems();
        listadoOfertas.addItem("");
        for (String elemento : tipoSorted) {
            listadoOfertas.addItem(elemento);

        }

        comboPaquete.removeAllItems();
        comboPaquete.setEnabled(false);
        Set<String> packs = icOferta.listarPaquetes();
        List<String> packSorted = new ArrayList<>(packs);
        Collections.sort(packSorted, String.CASE_INSENSITIVE_ORDER);
        comboPaquete.addItem("");
        for (String elem : packSorted) {
            comboPaquete.addItem(elem);
        }


    }

    private void limpiarFormulario() {
        nombre.setText("");
        descripcion.setText("");
        remuneracion.setText("");
        ciudad.setText("");
        desdehora.setValue(0);
        desdemin.setValue(0);
        hastahora.setValue(0);
        hastamin.setValue(0);
        listadoEmpresas.setSelectedIndex(-1);
        listadoOfertas.setSelectedIndex(-1);
        botonSinPaq.setSelected(false);
        botonConPaq.setSelected(false);
        //fechaAlta1.setText("");
        //listadoKeywords.removeAllItems();
    }

    private void transferirElemento(JList<String> sourceList, DefaultListModel<String> destinationModel, DefaultListModel<String> sourceModel) {
        int selectedIndex = sourceList.getSelectedIndex();

        if (selectedIndex != -1) {
            String selectedValue = sourceList.getSelectedValue();
            destinationModel.addElement(selectedValue);
            sourceList.clearSelection();
            sourceList.revalidate();
            //destinationModel.removeElementAt(selectedIndex);
            sourceModel.removeElementAt(selectedIndex);

            ordenarModeloAlfabeticamente(destinationModel);

            sourceList.revalidate();

        }
    }

    private void ordenarModeloAlfabeticamente(DefaultListModel<String> model) {
        List<String> elements = new ArrayList<>();

        for (int i = 0; i < model.size(); i++) {
            elements.add(model.getElementAt(i));
        }
        Collections.sort(elements, String.CASE_INSENSITIVE_ORDER);

        model.clear();

        for (String element : elements) {
            model.addElement(element);
        }
    }
}