package presentacion.componentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


    public class PanelBotonesEditarCerrar extends JPanel {
    	
        private static final long serialVersionUID = 891665809012532109L;
        
		private JButton btnEditar;
        private JButton btnCerrar;
        private boolean modoEdicion;
        private IEditable clasePadre;

        public PanelBotonesEditarCerrar() {
            btnEditar = new JButton("Editar");
            btnCerrar = new JButton("Cerrar");

            btnEditar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (modoEdicion) {
                        // Si estamos en modo edición, guardar cambios
                        if (clasePadre != null) {
                            clasePadre.onGuardar();
                        }
                    } else {
                        // Si no estamos en modo edición, cambiar a modo edición
                        if (clasePadre != null) {
                            clasePadre.onEditar();
                        }
                    }
                }
            });

            btnCerrar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (modoEdicion) {
                        // Si estamos en modo edición, descarta los cambios
                        if (clasePadre != null) {
                        	clasePadre.onDescartar();
                        }
                    } else {
                        // Si no estamos en modo edición, cierra
                        if (clasePadre != null) {
                        	clasePadre.onCerrar();
                        }
                    }
                }
            });

            add(btnEditar);
            add(btnCerrar);

            // Inicialmente, configurar el componente en modo no edición
            modoEdicion = false;
            actualizarBotones();
        }

        public void setEditable(IEditable clasePadre) {
            this.clasePadre = clasePadre;
        }
        
        public void setModoEdicion(boolean modo) {
        	this.modoEdicion = modo;
        	actualizarBotones();
        }

        private void actualizarBotones() {
            if (modoEdicion) {
                btnEditar.setText("Guardar");
                btnCerrar.setText("Descartar");
            } else {
                btnEditar.setText("Editar");
                btnCerrar.setText("Cerrar");
            }
        }
    }