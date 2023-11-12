package main.java.presentacion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import main.java.excepciones.ExceptionEmpresaInvalida;
import main.java.excepciones.ExceptionUsuarioNoEncontrado;

import java.util.ArrayList;

import main.java.logica.Datatypes.DTHora;
import main.java.logica.Datatypes.DTHorario;
import main.java.logica.Enumerados.DepUY;
import main.java.logica.Interfaces.ICtrlOferta;
import main.java.logica.Interfaces.ICtrlUsuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CargarDatos extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ICtrlUsuario ICU;
	private ICtrlOferta ICO;
	/**
	 * Create the application.
	 */
	public CargarDatos(ICtrlUsuario icu, ICtrlOferta ico) {
		ICU = icu;
		ICO = ico;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Cargar datos");
        setBounds(30, 30, 477, 153);
        getContentPane().setLayout(null); //Absolute Layout
        
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                try (BufferedReader reader = new BufferedReader(new FileReader("src/main/datos/Usuarios.csv"))) {
	                String linea;
	                String linea1;
                	String linea2;
	                reader.readLine(); // Leer y descartar la primera línea de encabezados
	                while ((linea = reader.readLine()) != null) {
	                	String[] campos = linea.split(";");
	                	String tipo = campos[1];
	                	if(tipo.equals("P")) {
	                		try (BufferedReader reader1 = new BufferedReader(new FileReader("src/main/datos/Usuarios-Postulantes.csv"))) {
		                		reader1.readLine();
			                	while((linea1 = reader1.readLine()) != null) {
			                		String[] campos1 = linea1.split(";");
			                		String user1 = campos1[0];
			                		
			                		String user = campos[0];
			                		
			                		if(user.equals(user1)) {
			                	        String dateString = campos1[1];
			                	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			                	        LocalDate localDate = LocalDate.parse(dateString, formatter);
			                	        try {
			                	        	ICU.altaPostulante(campos[2],campos[3],campos[4],campos[5],localDate,campos1[2]);
			                	        } catch (Exception e1){
			                	        	
			                	        }
			                		}	                		
			                	} 
	                		}catch (IOException e6) {
	                			e6.printStackTrace();
			                }
	                	}
	                	else {
	                		try (BufferedReader reader2 = new BufferedReader(new FileReader("src/main/datos/Usuarios-Empresas.csv"))) {
		                		reader2.readLine();
			                	while((linea2 = reader2.readLine()) != null) {
			                		String[] campos2 = linea2.split(";");
				                    String user = campos[0];
			                		String user2 = campos2[0];
			                		if(user.equals(user2)) {
			                			if(campos2.length == 2) {
			                				try {
			                					ICU.altaEmpresa(campos[2],campos[3],campos[4],campos[5], campos[2], campos2[1]);
			                				} catch (Exception e2) {
			                					
			                				}
			                			}
			                			else {
			                				
			                				try {
			                					ICU.altaEmpresaURL(campos[2],campos[3],campos[4],campos[5],campos[2], campos2[1],campos2[2]);
			                				} catch (Exception e3) {
			                					
			                				}
			                				
			                			}
			                		}	                		
			                	} 
	                		} catch (IOException e5) {
		                        e5.printStackTrace();
		                    }		                		
	                	}
	                } 
                } catch(IOException e4) {
                	e4.printStackTrace();
                }
                
                try (BufferedReader reader3 = new BufferedReader(new FileReader("src/main/datos/TipoPublicacion.csv"))) {
                	String linea3;
                	reader3.readLine();
                	
                	while ((linea3 = reader3.readLine()) != null) {
                		String[] campos3 = linea3.split(";");
                		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                		LocalDate fechaLocal = LocalDate.parse(campos3[6], formatter);
                		
                		ICO.altaTipoPublicacionOL(campos3[1], campos3[2], Integer.parseInt(campos3[3]), Integer.parseInt(campos3[4]), Float.valueOf(campos3[5]), fechaLocal);
                	}
                } catch (IOException e7) {
                	e7.printStackTrace();
                }
                
                try (BufferedReader reader4 = new BufferedReader(new FileReader("src/main/datos/Keywords.csv"))) {
                	String linea4;
                	reader4.readLine();
                	
                	while ((linea4 = reader4.readLine()) != null) {
                		String[] campos4 = linea4.split(";");
 
                		ICO.altaKeyword(campos4[1]);
                	}
                } catch (IOException e8) {
                	e8.printStackTrace();
                }
                
                try (BufferedReader reader5 = new BufferedReader(new FileReader("src/main/datos/OfertasLaborales.csv"))) {
                	String linea5;
                	reader5.readLine();
                	
                	while ((linea5 = reader5.readLine()) != null) {
                		String[] campos5 = linea5.split(";");
                		
                		// Obtener hora
                		String horario = campos5[5];
                		String[] desdehasta = horario.split(" - ");
                		String[] horadesde = desdehasta[0].split(":");
                		String[] horahasta = desdehasta[1].split(":");
                		DTHora desde = new DTHora(Integer.parseInt(horadesde[0]),Integer.parseInt(horadesde[1]));
                		DTHora hasta = new DTHora(Integer.parseInt(horahasta[0]),Integer.parseInt(horahasta[1]));
                		DTHorario hor = new DTHorario(desde,hasta);
                		
                		// Obtener fecha
                		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
                		LocalDate fecha = LocalDate.parse(campos5[9], formatter);
                						
                		// Nickname del Usuario
                		String nickname_e = null;
                		
                		// Tipo de Publicación
                		String tipodeP = null;
                		
                		// Lista de keywords
                		List<String> keys = new ArrayList<>();
                		
                		try (BufferedReader reader6 = new BufferedReader(new FileReader("src/main/datos/Usuarios.csv"))) {
                			String linea6;
                        	reader6.readLine();
                        	while ((linea6 = reader6.readLine()) != null) {
                        		String[] campos6 = linea6.split(";");
                        		if(campos6[0].equals(campos5[7])) {
                        			nickname_e = campos6[2];
                        		}
                        	}
                		} catch (IOException e20) {
                			e20.printStackTrace();
                		}
                		
                		try (BufferedReader reader7 = new BufferedReader(new FileReader("src/main/datos/TipoPublicacion.csv"))) {
                			String linea7;
                        	reader7.readLine();
                        	while ((linea7 = reader7.readLine()) != null) {
                        		String[] campos7 = linea7.split(";");
                        		if(campos7[0].equals(campos5[8])) {
                        			tipodeP = campos7[1];
                        		}
                        	}
                		} catch (IOException e21) {
                			e21.printStackTrace();
                		}
                		
                		try (BufferedReader reader8 = new BufferedReader(new FileReader("src/main/datos/OfertasLaboralesKeywords.csv"))) {
                			String linea8;
                        	reader8.readLine();
                        	while ((linea8 = reader8.readLine()) != null) {
                        		String[] campos8 = linea8.split(";");
                        		if(campos8[0].equals(campos5[0])) {
                        			String ks = campos8[1];
                        			String[] kss = ks.split(", ");
                        			for(int i = 0; i <= kss.length-1; i++) {
		                    			try (BufferedReader reader9 = new BufferedReader(new FileReader("src/main/datos/Keywords.csv"))) {
		                    				String linea9;
		                    				reader9.readLine();
		                    				while((linea9 = reader9.readLine()) != null) {
		                    					String[] campos9 = linea9.split(";");
		                    					if(kss[i].equals(campos9[0])) {
		                    						keys.add(campos9[1]);
		                    					}
		                    				}
		                    			} catch (IOException e23) {
		                        			e23.printStackTrace();
		                    			}
                        			}
                        		}
                        	}
                		} catch (IOException e22) {
                			e22.printStackTrace();
                		}
                		
                        DepUY dep = null;
                        	
                        switch (campos5[3]) {
                            case "Artigas":
                                dep = DepUY.Artigas;
                                break;
                            case "Salto":
                                dep = DepUY.Salto;
                                break;
                            case "Paysandú":
                                dep = DepUY.Paysandú;
                                break;
                            case "Rionegro":
                                dep = DepUY.RioNegro;
                                break;
                            case "Soriano":
                                dep = DepUY.Soriano;
                                break;
                            case "Colonia":
                                dep = DepUY.Colonia;
                                break;
                            case "Rivera":
                                dep = DepUY.Rivera;
                                break;
                            case "Tacuarembo":
                                dep = DepUY.Tacuarembo;
                                break;
                            case "Durazno":
                                dep = DepUY.Durazno;
                                break;
                            case "Flores":
                                dep = DepUY.Flores;
                                break;
                            case "Florida":
                                dep = DepUY.Florida;
                                break;
                            case "Sanjosé":
                                dep = DepUY.SanJosé;
                                break;
                            case "Canelones":
                                dep = DepUY.Canelones;
                                break;
                            case "Montevideo":
                                dep = DepUY.Montevideo;
                                break;
                            case "Cerrolargo":
                                dep = DepUY.CerroLargo;
                                break;
                            case "Treintaytres":
                                dep = DepUY.TreintaYTres;
                                break;
                            case "Lavalleja":
                                dep = DepUY.Lavalleja;
                                break;
                            case "Rocha":
                                dep = DepUY.Rocha;
                                break;
                            case "Maldonado":
                                dep = DepUY.Maldonado;
                                break;
                            default:
                                // System.out.println("Unknown department: " + input);
                                break;
                        }
                        
                		try {
                			ICU.altaOfertaLaboral(nickname_e, tipodeP, campos5[1], campos5[2], hor, Float.valueOf(campos5[6]), campos5[4], dep, fecha, keys);
                		} catch (ExceptionUsuarioNoEncontrado eune) {
                			JOptionPane.showMessageDialog(CargarDatos.this, eune.getMessage(), "ERROR - Carga de Datos", JOptionPane.ERROR_MESSAGE);
                		} catch(ExceptionEmpresaInvalida eei) {
                			JOptionPane.showMessageDialog(CargarDatos.this, eei.getMessage(), "ERROR - Carga de Datos", JOptionPane.ERROR_MESSAGE);
                		}
                	}
                } catch (IOException e9) {
                	e9.printStackTrace();
                }
                
                try (BufferedReader reader10 = new BufferedReader(new FileReader("src/main/datos/Postulaciones.csv"))) {
                	String linea10;
                	reader10.readLine();
                	
                	while ((linea10 = reader10.readLine()) != null) {
                		String[] campos10 = linea10.split(";");
                		
                		// Usuario
                		String user = null;
                		
                		// Oferta Laboral
                		String ol = null;
                		
                		// Hallar usuario
                		try (BufferedReader reader11 = new BufferedReader(new FileReader("src/main/datos/Usuarios.csv"))) {
                			String linea11;
                        	reader11.readLine();
                        	while ((linea11 = reader11.readLine()) != null) {
                        		String[] campos11 = linea11.split(";");
                        		if(campos11[0].equals(campos10[1])) {
                        			user = campos11[2];
                        		}
                        	}
                        } catch (IOException e31) {
                        	e31.printStackTrace();
                        }
                		
                		// Hallar Oferta Laboral
                		try (BufferedReader reader12 = new BufferedReader(new FileReader("src/main/datos/OfertasLaborales.csv"))) {
                			String linea12;
                        	reader12.readLine();
                        	while ((linea12 = reader12.readLine()) != null) {
                        		String[] campos12 = linea12.split(";");
                        		if(campos12[0].equals(campos10[5])) {
                        			ol = campos12[1];
                        		}
                        	}
                        } catch (IOException e32) {
                        	e32.printStackTrace();
                        }
                		
                		// Obtener fecha
                		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
                		LocalDate fecha = LocalDate.parse(campos10[4], formatter);
                		
                		// No hay URLDocExtras, por eso el "".
                		if (!ICO.altaPostulacion(ol, user, campos10[2], campos10[3], "", fecha)) {
                			JOptionPane.showMessageDialog(CargarDatos.this, "La postulación ya existe", "ERROR - Carga de Datos", JOptionPane.ERROR_MESSAGE);
                		}
                		
                	}                	
                } catch (IOException e30) {
                	e30.printStackTrace();
                }
                
                try (BufferedReader reader13 = new BufferedReader(new FileReader("src/main/datos/Paquetes.csv"))) {
                	String linea13;
                	reader13.readLine();
                	while ((linea13 = reader13.readLine()) != null) {
                		String[] campos13 = linea13.split(";");
                		
                		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
                		LocalDate fecha = LocalDate.parse(campos13[5], formatter);
                		
                		ICO.altaPaqueteOL(campos13[1], campos13[2], Integer.parseInt(campos13[3].split(" ")[0]), fecha, Float.valueOf(campos13[4]));
                	}
                } catch (IOException e40) {
                	e40.printStackTrace();
                }
                
                try (BufferedReader reader14 = new BufferedReader(new FileReader("src/main/datos/TiposPublicacionPaquetes.csv"))) {
                	String linea14;
                	reader14.readLine();
                	while ((linea14 = reader14.readLine()) != null) {
                		String[] campos14 = linea14.split(";");
                		
                		// Paquete
                		String paq = null;
                		
                		
                		// TipoPub
                		String TipoPub = null;
                		
                		try (BufferedReader reader15 = new BufferedReader(new FileReader("src/main/datos/TipoPublicacion.csv"))) {
                        	String linea15;
                        	reader15.readLine();
                        	while ((linea15 = reader15.readLine()) != null) {
                        		String[] campos15 = linea15.split(";");
                        		if((campos14[2].substring(1)).equals(campos15[0])) {
                        			TipoPub = campos15[1];
                        		}
                        	}
                		} catch (IOException e42) {
                			e42.printStackTrace();
                		}
                		
                		try (BufferedReader reader16 = new BufferedReader(new FileReader("src/main/datos/Paquetes.csv"))) {
                        	String linea16;
                        	reader16.readLine();
                        	while ((linea16 = reader16.readLine()) != null) {
                        		String[] campos16 = linea16.split(";");
                        		if((campos14[1].substring(1)).equals(campos16[0])) {
                        			paq = campos16[1];
                        		}
                        	}
                		} catch (IOException e42) {
                			e42.printStackTrace();
                		}
                		ICO.agregarTipoOfertaPaq(paq, TipoPub, Integer.parseInt(campos14[3].substring(1)));
                	}
                	
                } catch (IOException e41) {
                	e41.printStackTrace();
                }
                
                JOptionPane.showMessageDialog(CargarDatos.this, "Se han cargado los datos exitosamente.", "Carga de Datos", JOptionPane.INFORMATION_MESSAGE);
                
                setVisible(false);
        	}
        });
        btnAceptar.setBounds(63, 78, 117, 25);
        getContentPane().add(btnAceptar);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        	}
        });
        btnCerrar.setBounds(297, 78, 117, 25);
        getContentPane().add(btnCerrar);
        
        JLabel lblSeleccioneAceptarPara = new JLabel("Seleccione aceptar para cargar los datos.");
        lblSeleccioneAceptarPara.setBounds(81, 28, 332, 15);
        getContentPane().add(lblSeleccioneAceptarPara);
	}
}
