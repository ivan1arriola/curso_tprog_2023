package trabajoUy.logica.controladores;


import java.time.format.DateTimeFormatter; 
import java.util.List; 
import trabajoUy.excepciones.ExceptionEmpresaInvalida; 
import trabajoUy.excepciones.ExceptionUsuarioNoEncontrado; 
import java.util.ArrayList;

import trabajoUy.logica.datatypes.DTHora; 
import trabajoUy.logica.datatypes.DTHorario; 
import trabajoUy.logica.enumerados.DepUY; 
import trabajoUy.logica.enumerados.EstadoOL; 
import trabajoUy.logica.interfaces.ICtrlCargaDeDatos; 
import trabajoUy.logica.interfaces.ICtrlOferta; 
import trabajoUy.logica.interfaces.ICtrlUsuario;
import trabajoUy.logica.utils.Fabrica;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

// import java.io.IOException; NO SE USA (CHECKSTYLE)
import java.time.LocalDate;



public class CtrlCargaDeDatos implements ICtrlCargaDeDatos {

	public CtrlCargaDeDatos() {}
	
	public void cargarDatos() {
		Fabrica fabrica = Fabrica.getInstance();
		ICtrlUsuario ICU = fabrica.getICtrlUsuario();
		ICtrlOferta ICO = fabrica.getICtrlOferta();
		try (InputStream inputStream1 = getClass().getResourceAsStream("/Usuarios.csv");
			     BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream1))) {
            String linea;
            String linea1;
        	String linea2;
            reader.readLine(); // Leer y descartar la primera línea de encabezados
            while ((linea = reader.readLine()) != null) {
            	String[] campos = linea.split(";");
            	String tipo = campos[1];
            	
	            // URL de la imagen que deseas descargar
	            String imageUrl = campos[7];
            	
            	if (tipo.equals("P")) {
            		try (InputStream inputStream2 = getClass().getResourceAsStream("/Usuarios-Postulantes.csv");
           			     BufferedReader reader1 = new BufferedReader(new InputStreamReader(inputStream2))) {
                		reader1.readLine();
	                	while ((linea1 = reader1.readLine()) != null) {
	                		String[] campos1 = linea1.split(";");
	                		String user1 = campos1[0];
	                		
	                		String user = campos[0];
	                		
	                		if (user.equals(user1)) {
	                	        String dateString = campos1[1];
	                	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	                	        LocalDate localDate = LocalDate.parse(dateString,  formatter);
                	        	ICU.altaPostulanteImagen(campos[2], campos[6],  campos[3], campos[4], localDate, campos[5], campos1[2],  campos[7]);
	                		}
	                	}
            		}
            	} else {
            		try (InputStream inputStream2 = getClass().getResourceAsStream("/Usuarios-Empresas.csv");
            			     BufferedReader reader2 = new BufferedReader(new InputStreamReader(inputStream2))) {
                		reader2.readLine();
	                	while ((linea2 = reader2.readLine()) != null) {
	                		String[] campos2 = linea2.split(";");
		                    String user = campos[0];
	                		String user2 = campos2[0];
	                		if (user.equals(user2)) {
	                			
	                			if (campos2.length == 2) {
	                				try {
	                					ICU.altaEmpresaImagen(campos[2], campos[6], campos[3], campos[4],  campos[5],  campos2[1],  campos[7]);
	                				} catch (IllegalArgumentException e2) {
	                					e2.printStackTrace();
	                				}
	                			} else {
	                				
	                				try {
	                					ICU.altaEmpresaURLyImagen(campos[2], campos[6], campos[3], campos[4],  campos[5],  campos2[1], campos2[2],  campos[7]);
	                				} catch (IllegalArgumentException e3) {
	                					e3.printStackTrace();
	                				}
	                				
	                			}
	                		}	                		
	                	} 
            		} catch (IOException e5) {
                        e5.printStackTrace();
                    }		                		
            	}
            } 
        } catch (IOException e4) {
        	e4.printStackTrace();
        }
		
        try (InputStream inputStream2 = getClass().getResourceAsStream("/TipoPublicacion.csv");
       	     BufferedReader reader3 = new BufferedReader(new InputStreamReader(inputStream2))) {
        	String linea3;
        	reader3.readLine();
        	
        	while ((linea3 = reader3.readLine()) != null) {
        		String[] campos3 = linea3.split(";");
        		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        		LocalDate fechaLocal = LocalDate.parse(campos3[6],  formatter);
        		
        		ICO.altaTipoPublicacionOL(campos3[1],  campos3[2],  Integer.parseInt(campos3[3]),  Integer.parseInt(campos3[4]),  Float.valueOf(campos3[5]),  fechaLocal);
        	}
        } catch (IOException e7) {
        	e7.printStackTrace();
        }
        try (InputStream inputStream2 = getClass().getResourceAsStream("/Keywords.csv");
          	     BufferedReader reader4 = new BufferedReader(new InputStreamReader(inputStream2))) {
        	String linea4;
        	reader4.readLine();
        	
        	while ((linea4 = reader4.readLine()) != null) {
        		String[] campos4 = linea4.split(";");

        		ICO.altaKeyword(campos4[1]);
        	}
        } catch (IOException e8) {
        	e8.printStackTrace();
        }
        
        
        
        try (InputStream inputStream2 = getClass().getResourceAsStream("/Paquetes.csv");
        	     BufferedReader reader13 = new BufferedReader(new InputStreamReader(inputStream2))) {
        	String linea13;
        	reader13.readLine();
        	while ((linea13 = reader13.readLine()) != null) {
        		String[] campos13 = linea13.split(";");
        		
	            // URL de la imagen que deseas descargar
	            String imageUrl = campos13[7];
	            
        		
        		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        		LocalDate fecha = LocalDate.parse(campos13[5],  formatter);
        		
        		ICO.altaPaqueteOL(campos13[1],  campos13[2],  Integer.parseInt(campos13[3].split(" ")[0]),  fecha,  Float.valueOf(campos13[4]), campos13[7]);
        	}
        } catch (IOException e40) {
        	e40.printStackTrace();
        }
        
        try (InputStream inputStream2 = getClass().getResourceAsStream("/OfertasLaborales.csv");
       	     BufferedReader reader5 = new BufferedReader(new InputStreamReader(inputStream2))) {
        	String linea5;
        	reader5.readLine();
        	
        	while ((linea5 = reader5.readLine()) != null) {
        		String[] campos5 = linea5.split(";");
        		
        		// Obtener imagen
        		
	            // URL de la imagen que deseas descargar
	            String imageUrl = campos5[12];
        		
        		// Obtener hora
        		String horario = campos5[5];
        		String[] desdehasta = horario.split(" - ");
        		String[] horadesde = desdehasta[0].split(":");
        		String[] horahasta = desdehasta[1].split(":");
        		DTHora desde = new DTHora(Integer.parseInt(horadesde[0]), Integer.parseInt(horadesde[1]));
        		DTHora hasta = new DTHora(Integer.parseInt(horahasta[0]), Integer.parseInt(horahasta[1]));
        		DTHorario hor = new DTHorario(desde, hasta);
        		
        		// Obtener fecha
        		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        		LocalDate fecha = LocalDate.parse(campos5[9],  formatter);
        						
        		// Nickname del Usuario
        		String nickname_e = null;
        		
        		// Tipo de Publicación
        		String tipodeP = null;
        		
        		// Lista de keywords
        		List<String> keys = new ArrayList<>();
        		
        		
        		
        		try (InputStream inputStream3 = getClass().getResourceAsStream("/Usuarios.csv");
                 	     BufferedReader reader6 = new BufferedReader(new InputStreamReader(inputStream3))) {
        			String linea6;
                	reader6.readLine();
                	while ((linea6 = reader6.readLine()) != null) {
                		String[] campos6 = linea6.split(";");
                		if (campos6[0].equals(campos5[7])) {
                			nickname_e = campos6[2];
                		}
                	}
        		} catch (IOException e20) {
        			e20.printStackTrace();
        		}
        		
        		
        		try (InputStream inputStream3 = getClass().getResourceAsStream("/TipoPublicacion.csv");
               	     BufferedReader reader7 = new BufferedReader(new InputStreamReader(inputStream3))) {
        			String linea7;
                	reader7.readLine();
                	while ((linea7 = reader7.readLine()) != null) {
                		String[] campos7 = linea7.split(";");
                		if (campos7[0].equals(campos5[8])) {
                			tipodeP = campos7[1];
                		}
                	}
        		} catch (IOException e21) {
        			e21.printStackTrace();
        		}
        		
        		
        		try (InputStream inputStream3 = getClass().getResourceAsStream("/OfertasLaboralesKeywords.csv");
        	       	     BufferedReader reader8 = new BufferedReader(new InputStreamReader(inputStream3))) {
        			String linea8;
                	reader8.readLine();
                	while ((linea8 = reader8.readLine()) != null) {
                		String[] campos8 = linea8.split(";");
                		if (campos8[0].equals(campos5[0])) {
                			String keyss = campos8[1];
                			String[] kss = keyss.split(",  ");
                			for (int i = 0; i <= kss.length-1; i++) {
                				
                				
                    			try (InputStream inputStream4 = getClass().getResourceAsStream("/Keywords.csv");
                          	       	     BufferedReader reader9 = new BufferedReader(new InputStreamReader(inputStream4))) {
                    				String linea9;
                    				reader9.readLine();
                    				while ((linea9 = reader9.readLine()) != null) {
                    					String[] campos9 = linea9.split(";");
                    					if (kss[i].equals(campos9[0])) {
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
                
                EstadoOL estado = null;
                switch (campos5[10]) {
                case "Confirmada":
                    estado = EstadoOL.Confirmada;
                    break;
                case "Ingresada":
                    estado = EstadoOL.Ingresada;
                    break;
                case "Rechazada":
                    estado = EstadoOL.Rechazada;
                    break;
                default:
                    // System.out.println("Unknown department: " + input);
                    break;
                }
                
                String paq = campos5[11];
                if (campos5[11].equals("Sin paquete")) { 
                	paq = null; } else {
                		
                	try (InputStream inputStream4 = getClass().getResourceAsStream("/Paquetes.csv");
             	       	     BufferedReader reader15 = new BufferedReader(new InputStreamReader(inputStream4))) {
                    	String linea15;
                    	reader15.readLine();
                    	boolean fin = false;
                    	while ((linea15 = reader15.readLine()) != null && !fin) {
                    		String[] campos15 = linea15.split(";");
                    		if (paq.equals(campos15[0])) {	
                    			paq = campos15[1];
                    			fin = true;
                    		}
                    	}
                    	
                	}
                }
                
        		try {
        			ICU.altaOfertaLaboral(nickname_e,  tipodeP,  campos5[1],  campos5[2],  hor,  Float.valueOf(campos5[6]),  campos5[4],  dep,  fecha,  keys,  estado,  campos5[12],  paq);
        		} catch (ExceptionUsuarioNoEncontrado eune) {
        			eune.printStackTrace();
        		} catch (ExceptionEmpresaInvalida eei) {
        			eei.printStackTrace();
        		}
        	}
        } catch (IOException e9) {
        	e9.printStackTrace();
        }
        
        try (InputStream inputStream1 = getClass().getResourceAsStream("/Postulaciones.csv");
       	     BufferedReader reader10 = new BufferedReader(new InputStreamReader(inputStream1))) {
        	String linea10;
        	reader10.readLine();
        	
        	while ((linea10 = reader10.readLine()) != null) {
        		String[] campos10 = linea10.split(";");
        		
        		// Usuario
        		String user = null;
        		
        		// Oferta Laboral
        		String oferLab = null;
        		
        		// Hallar usuario
        		try (InputStream inputStream2 = getClass().getResourceAsStream("/Usuarios.csv");
        	       	     BufferedReader reader11 = new BufferedReader(new InputStreamReader(inputStream2))) {
        			String linea11;
                	reader11.readLine();
                	while ((linea11 = reader11.readLine()) != null) {
                		String[] campos11 = linea11.split(";");
                		if (campos11[0].equals(campos10[1])) {
                			user = campos11[2];
                		}
                	}
                } catch (IOException e31) {
                	e31.printStackTrace();
                }
        		
        		// Hallar Oferta Laboral
        		
        		try (InputStream inputStream2 = getClass().getResourceAsStream("/OfertasLaborales.csv");
       	       	     BufferedReader reader12 = new BufferedReader(new InputStreamReader(inputStream2))) {
        			String linea12;
                	reader12.readLine();
                	while ((linea12 = reader12.readLine()) != null) {
                		String[] campos12 = linea12.split(";");
                		if (campos12[0].equals(campos10[5])) {
                			oferLab = campos12[1];
                		}
                	}
                } catch (IOException e32) {
                	e32.printStackTrace();
                }
        		
        		// Obtener fecha
        		String[] partes = campos10[4].split("/");
        		if (partes[0].length() == 1) {
        		    partes[0] = "0" + partes[0];
        		    campos10[4] = String.join("/",  partes);
        		}
        		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        		LocalDate fecha = LocalDate.parse(campos10[4],  formatter);
        		
        		// No hay URLDocExtras,  por eso el "".
        		if (!ICO.altaPostulacion(oferLab,  user,  campos10[2],  campos10[3],  "",  fecha)) {
        			// EXCEPCIÓN.
        		}
        		
        	}                	
        } catch (IOException e30) {
        	e30.printStackTrace();
        }
        
        
        try (InputStream inputStream3 = getClass().getResourceAsStream("/TiposPublicacionPaquetes.csv");
         	     BufferedReader reader14 = new BufferedReader(new InputStreamReader(inputStream3))) {
        	String linea14;
        	reader14.readLine();
        	while ((linea14 = reader14.readLine()) != null) {
        		String[] campos14 = linea14.split(";");
        		
        		// Paquete
        		String paq = null;
        		
        		
        		// TipoPub
        		String TipoPub = null;
        		try (InputStream inputStream4 = getClass().getResourceAsStream("/TipoPublicacion.csv");
               	     BufferedReader reader15 = new BufferedReader(new InputStreamReader(inputStream4))) {
                	String linea15;
                	reader15.readLine();
                	while ((linea15 = reader15.readLine()) != null) {
                		String[] campos15 = linea15.split(";");
                		if ((campos14[2].substring(1)).equals(campos15[0])) {
                			TipoPub = campos15[1];
                		}
                	}
        		} catch (IOException e42) {
        			e42.printStackTrace();
        		}
        		
        		
        		try (InputStream inputStream5 = getClass().getResourceAsStream("/Paquetes.csv");
        	       	     BufferedReader reader16 = new BufferedReader(new InputStreamReader(inputStream5))) {
                	String linea16;
                	reader16.readLine();
                	while ((linea16 = reader16.readLine()) != null) {
                		String[] campos16 = linea16.split(";");
                		if ((campos14[1].substring(1)).equals(campos16[0])) {
                			paq = campos16[1];
                		}
                	}
        		} catch (IOException e42) {
        			e42.printStackTrace();
        		}
        		ICO.agregarTipoOfertaPaq(paq,  TipoPub,  Integer.parseInt(campos14[3].substring(1)));
        	}
        } catch (IOException e41) {
        	e41.printStackTrace();
        }
	
        try (InputStream inputStream3 = getClass().getResourceAsStream("/PaquetesCompras.csv");
        	     BufferedReader reader17 = new BufferedReader(new InputStreamReader(inputStream3))) {
			   	String linea17;
			   	reader17.readLine();
			   	while ((linea17 = reader17.readLine()) != null) {
			   		String[] campos14 = linea17.split(";");
			   		String nickname_e = campos14[1];
			   		String paq = campos14[2];
			   		
			   		try (InputStream inputStream4 = getClass().getResourceAsStream("/Usuarios.csv");
			  	       	     BufferedReader reader18 = new BufferedReader(new InputStreamReader(inputStream4))) {
			               	String linea18;
			               	reader18.readLine();
			               	while ((linea18 = reader18.readLine()) != null) {
			               		String[] campos18 = linea18.split(";");
			               		if (campos18[0].equals(nickname_e)) {
			               			nickname_e = campos18[2];
			               		}
			               	}
				   		} catch (IOException e42) {
				   			e42.printStackTrace();
				   		}
			   		
			   		try (InputStream inputStream5 = getClass().getResourceAsStream("/Paquetes.csv");
			     	       	     BufferedReader reader19 = new BufferedReader(new InputStreamReader(inputStream5))) {
			               	String linea19;
			               	reader19.readLine();
			               	while ((linea19 = reader19.readLine()) != null) {
			               		String[] campos19 = linea19.split(";");
			               		if (campos19[0].equals(paq)) {
			               			paq = campos19[1];
			               		}
			               	}
				   		} catch (IOException e42) {
				   			e42.printStackTrace();
				   		}
			   		
			   		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			   		LocalDate fecha = LocalDate.parse(campos14[3],  formatter);
			   		
			   		ICO.compraPaquetes(nickname_e,  paq, fecha, Integer.parseInt(campos14[4]));
			   	}
       	} catch (IOException e41) {
			e41.printStackTrace();
		}
	}
}