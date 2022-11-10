package modulos.usuarios.UI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UsuarioEdicion extends JFrame {

	private JPanel contentPane;
	private RWUsuario rwUsuario = new RWUsuario();
	private JTextField txt_Id;
	private JTextField txt_Nombre;
	private JTextField txt_Apellido;
	private JTextField txt_Dni;
	private JTextField txt_Contrasena;
	private JButton btnGuardar = new JButton("Guardar");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioEdicion frame = new UsuarioEdicion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// metodo para dejar campos en blanco una vez que se pulse insertar
	private void limpiarCampos() {
		this.txt_Id.setText("");
		this.txt_Nombre.setText("");
		this.txt_Apellido.setText("");
		this.txt_Dni.setText("");
		this.txt_Contrasena.setText("");
	}

	private boolean validarCampos() {
		if (txt_Nombre.getText().isEmpty()) {
			// si deja el campo vacio, salta una ventana de alerta mostrando mensaje
			JOptionPane.showMessageDialog(rootPane, "Campo 'nombre' sin rellenar", "Error", JOptionPane.ERROR_MESSAGE);
			txt_Nombre.requestFocus();
			return false;
		}
		if (txt_Apellido.getText().isEmpty()) {
			// si deja el campo vacio, salta una ventana de alerta mostrando mensaje
			JOptionPane.showMessageDialog(rootPane, "Campo 'apellido' sin rellenar", "Error",
					JOptionPane.ERROR_MESSAGE);
			txt_Apellido.requestFocus();
			return false;
		}
		if (txt_Dni.getText().isEmpty()) {
			// si deja el campo vacio, salta una ventana de alerta mostrando mensaje
			JOptionPane.showMessageDialog(rootPane, "Campo 'DNI' sin rellenar", "Error", JOptionPane.ERROR_MESSAGE);
			txt_Dni.requestFocus();
			return false;
		}
		if (txt_Contrasena.getText().isEmpty()) {
			// si deja el campo vacio, salta una ventana de alerta mostrando mensaje
			JOptionPane.showMessageDialog(rootPane, "Campo 'contraseņa' sin rellenar", "Error",
					JOptionPane.ERROR_MESSAGE);
			txt_Contrasena.requestFocus();
			return false;
		}
		if (txt_Contrasena.getText().length() < 8) {
			JOptionPane.showMessageDialog(rootPane, "La contraseņa debe tener al menos 8 caracteres.", "Error",
					JOptionPane.ERROR_MESSAGE);
			txt_Contrasena.requestFocus();
			return false;
		}
		return true;
	}
	
	private void guardarUsuarioNuevo() {
		if (txt_Contrasena.getText().length() < 8) {
			JOptionPane.showMessageDialog(rootPane, "La contraseņa debe tener al menos 8 caracteres", "Error",
					JOptionPane.ERROR_MESSAGE);
			txt_Contrasena.requestFocus();
			return;
		}
		// Con todos los campos rellenos, se pasa a crear el nuevo objeto AdUsuario
		AdUsuario nuevoUsuario = null;

		try {
			nuevoUsuario = new AdUsuario(txt_Nombre.getText(), txt_Apellido.getText(), txt_Dni.getText(),
					txt_Contrasena.getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rwUsuario.anyadir(nuevoUsuario);
		limpiarCampos();
	}
	
	protected void guardarUsuario() {
		if (!validarCampos()) {
			return;
		}
		AdUsuario usuario = this.rwUsuario.buscarUsuarioPorId(this.txt_Id.getText());
		if (usuario != null) {
			guardarUsuarioModificado(usuario);
		} else {
			guardarUsuarioNuevo();
		}

	}
	
	private void guardarUsuarioModificado(AdUsuario usuario) {
		if (txt_Contrasena.getText().length() < 8) {
			JOptionPane.showMessageDialog(rootPane, "La contraseņa debe tener al menos 8 caracteres", "Error",
					JOptionPane.ERROR_MESSAGE);
			txt_Contrasena.requestFocus();
			return;
		}
		try {
			usuario.Actualizar(txt_Nombre.getText(), txt_Apellido.getText(), txt_Dni.getText(),
					txt_Contrasena.getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.rwUsuario.modificar(usuario);
		limpiarCampos();
	}
	
	/*public void EscogeUsuario() {
		// id (usuario) seleccionado de la tabla
		String idSeleccionado = (String) this.table.getValueAt(table.getSelectedRow(), 0);
		// Buscar en la lista por el id.
		AdUsuario usuarioSeleccionado = this.rwUsuario.buscarUsuarioPorId(idSeleccionado);
		// rellena la informacion en lo txtlabel del usuario seleccionado
		this.txt_Id.setText(usuarioSeleccionado.getId());
		this.txt_Nombre.setText(usuarioSeleccionado.getNombre());
		this.txt_Apellido.setText(usuarioSeleccionado.getApellido());
		this.txt_Dni.setText(usuarioSeleccionado.getDni());
		this.txt_Contrasena.setText(usuarioSeleccionado.getContrasena());
	}*/

	/**
	 * Create the frame.
	 */
	public UsuarioEdicion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 824, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(239, 208, 245));
		panel.setBounds(0, 0, 808, 501);
		contentPane.add(panel);

		JLabel lbl_IDUsuario = new JLabel("ID Usuario");
		lbl_IDUsuario.setBounds(184, 188, 65, 13);
		panel.add(lbl_IDUsuario);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(184, 217, 49, 13);
		panel.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(184, 247, 65, 13);
		panel.add(lblApellido);

		JLabel lblDni = new JLabel("Dni");
		lblDni.setBounds(184, 277, 65, 13);
		panel.add(lblDni);

		JLabel lblContrasena = new JLabel("Contrase\u00F1a");
		lblContrasena.setBounds(184, 307, 74, 13);
		panel.add(lblContrasena);

		txt_Id = new JTextField();
		txt_Id.setEnabled(false);
		txt_Id.setEditable(false);
		txt_Id.setColumns(10);
		txt_Id.setBounds(259, 186, 345, 16);
		panel.add(txt_Id);

		txt_Nombre = new JTextField();
		txt_Nombre.setEditable(true);
		txt_Nombre.setColumns(10);
		txt_Nombre.setBounds(259, 213, 345, 19);
		panel.add(txt_Nombre);

		txt_Apellido = new JTextField();
		txt_Apellido.setEditable(true);
		txt_Apellido.setColumns(10);
		txt_Apellido.setBounds(259, 243, 345, 19);
		panel.add(txt_Apellido);

		txt_Dni = new JTextField();
		txt_Dni.setEditable(true);
		txt_Dni.setColumns(10);
		txt_Dni.setBounds(259, 273, 345, 19);
		panel.add(txt_Dni);

		txt_Contrasena = new JTextField();
		txt_Contrasena.setEditable(true);
		txt_Contrasena.setColumns(10);
		txt_Contrasena.setBounds(259, 303, 345, 19);
		panel.add(txt_Contrasena);
		
		btnGuardar.setBounds(305, 391, 112, 18);
		panel.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(471, 391, 112, 18);
		panel.add(btnCancelar);
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarUsuario();
			}
		});
	}
}
