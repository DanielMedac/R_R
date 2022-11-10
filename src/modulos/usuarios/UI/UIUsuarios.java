package modulos.usuarios.UI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import Utils.Helper;
import Utils.RoundedBorder;
import Utils.UiHelper;
import modulos.login.AdminHome;
import javax.swing.SwingConstants;

public class UIUsuarios extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private RWUsuario rwUsuario = new RWUsuario();

	private JButton btnModificar = new JButton("Modificar");
	private JButton btnEliminar = new JButton("Eliminar");
	private JButton btnAnyadir = new JButton("A\u00F1adir");
	ImageIcon img = new ImageIcon("./src/ficheros/ciclista_morao.png");
	ImageIcon img2 = new ImageIcon("./src/ficheros/perfil.png");
	ImageIcon img3 = new ImageIcon("./src/ficheros/flecha.png");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIUsuarios frame = new UIUsuarios();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*private void cambiarEstado(boolean estado) {
		this.txt_Id.setEditable(estado);
		this.txt_Nombre.setEditable(estado);
		this.txt_Apellido.setEditable(estado);
		this.txt_Dni.setEditable(estado);
		this.txt_Contrasena.setEditable(estado);
		this.btnModificar.setEnabled(!estado);
		this.btnEliminar.setEnabled(!estado);
		this.btnAnyadir.setEnabled(!estado);
		this.btnGuardar.setEnabled(estado);
	}*/

	// metodo para dejar campos en blanco una vez que se pulse insertar
	/*private void limpiarCampos() {
		this.txt_Id.setText("");
		this.txt_Nombre.setText("");
		this.txt_Apellido.setText("");
		this.txt_Dni.setText("");
		this.txt_Contrasena.setText("");
	}*/

	public boolean SeleccionarUsuario() {
		// validar que se selecciona un registro de la tabla
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un usuario", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// id (usuario) seleccionado de la tabla
		String idSeleccionado = (String) this.table.getValueAt(table.getSelectedRow(), 0);
		// Buscar en la lista por el id.
		AdUsuario usuarioSeleccionado = this.rwUsuario.buscarUsuarioPorId(idSeleccionado);
		// rellena la informacion en lo txtlabel del usuario seleccionado
		/*this.txt_Id.setText(usuarioSeleccionado.getId());
		this.txt_Nombre.setText(usuarioSeleccionado.getNombre());
		this.txt_Apellido.setText(usuarioSeleccionado.getApellido());
		this.txt_Dni.setText(usuarioSeleccionado.getDni());
		this.txt_Contrasena.setText(usuarioSeleccionado.getContrasena());
		*/
		return true;
	}

	/*protected void guardarUsuario() {
		if (!validarCampos()) {
			return;
		}
		AdUsuario usuario = this.rwUsuario.buscarUsuarioPorId(this.txt_Id.getText());
		if (usuario != null) {
			guardarUsuarioModificado(usuario);
		} else {
			guardarUsuarioNuevo();
		}
		cambiarEstado(false);

	}*/

	/*private boolean validarCampos() {
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
			JOptionPane.showMessageDialog(rootPane, "Campo 'contraseña' sin rellenar", "Error",
					JOptionPane.ERROR_MESSAGE);
			txt_Contrasena.requestFocus();
			return false;
		}
		if (txt_Contrasena.getText().length() < 8) {
			JOptionPane.showMessageDialog(rootPane, "La contraseña debe tener al menos 8 caracteres.", "Error",
					JOptionPane.ERROR_MESSAGE);
			txt_Contrasena.requestFocus();
			return false;
		}
		return true;
	}*/

	/*private void guardarUsuarioNuevo() {
		if (txt_Contrasena.getText().length() < 8) {
			JOptionPane.showMessageDialog(rootPane, "La contraseña debe tener al menos 8 caracteres", "Error",
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
		cargarTabla();
		limpiarCampos();
	}*/

	/*private void guardarUsuarioModificado(AdUsuario usuario) {
		if (txt_Contrasena.getText().length() < 8) {
			JOptionPane.showMessageDialog(rootPane, "La contraseña debe tener al menos 8 caracteres", "Error",
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
		cargarTabla();
		limpiarCampos();
	}*/
	/*
	 * Metodo cargar, este metodo rellena la tabla. Se instancia un objeto de la
	 * clase RWUsuario y un objeto de la clase DefaultTableModel que es la clase que
	 * convierte los datos recibidos en forma de tabla... Con el for rellenamos la
	 * tabla
	 */

	public void cargarTabla() {
		DefaultTableModel modelo = (DefaultTableModel) this.table.getModel();
		modelo.setRowCount(0);
		for (AdUsuario usuario : rwUsuario.getUsuarios()) {
			modelo.addRow(new Object[] { usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getDni(),
					usuario.getContrasena(),

			});
		}
	}

	public void borrarUsuario() {
		this.btnEliminar.setEnabled(true);
		// validar que se selecciona un registro de la tabla
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un usuario", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		// id (usuario) seleccionado de la tabla
		String idSeleccionado = (String) this.table.getValueAt(table.getSelectedRow(), 0);
		// Buscar en la lista por el id.

		this.rwUsuario.borrar(idSeleccionado);
		cargarTabla();
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public UIUsuarios() {
		setTitle("Usuarios");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 824, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setIconImage(img.getImage());
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(88, 229, 611, 169);
		contentPane.add(scrollPane);
		contentPane.setBackground(Color.decode("#EFD0F5"));
		scrollPane.setBackground(Color.decode("#EFD0F5"));
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
				new String[] { "Id", "Nombre", "Apellido", "Dni", "Contraseña" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);

		JButton btnRetroceso = new JButton("");

		btnRetroceso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminHome ah = new AdminHome();
				ah.setLocationRelativeTo(null);
				ah.setVisible(true);
				dispose();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				AdminHome ah = new AdminHome();
				ah.setLocationRelativeTo(null);
				ah.setVisible(true);
				dispose();
			}
		});
		btnRetroceso.setBounds(21, 448, 103, 42);
		btnRetroceso.setIcon(img3);
		btnRetroceso.setBackground(Color.decode("#EFD0F5"));
		contentPane.add(btnRetroceso);
		btnRetroceso.setBorderPainted(false);
		btnRetroceso.setBackground(new Color(239, 208, 245));
		contentPane.add(btnRetroceso);
		cargarTabla();

		UiHelper.AplicarMouseListenerButtons(contentPane);

		UiHelper.AplicarEstilos(table);
		btnAnyadir.setBounds(88, 409, 127, 19);
		contentPane.add(btnAnyadir);
		btnModificar.setBounds(263, 409, 120, 18);
		contentPane.add(btnModificar);
		btnEliminar.setBounds(596, 409, 103, 18);
		contentPane.add(btnEliminar);

		JButton btnInformes = new JButton("Informes");
		btnInformes.setBounds(273, 448, 273, 21);
		contentPane.add(btnInformes);
		btnInformes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(rootPane, "Pagina en proceso, sentimos las molestias", "Advertencia",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				JOptionPane.showMessageDialog(rootPane, "Pagina en proceso, sentimos las molestias", "Advertencia",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
		});

		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarUsuario();
			}
		});


		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioEdicion edit = new UsuarioEdicion();
				edit.setLocationRelativeTo(null);
				edit.setVisible(true);
				dispose();
				/*if (!SeleccionarUsuario()) {
					txt_Contrasena.setEditable(false);
					cambiarEstado(false);
				} else {
					txt_Contrasena.setEditable(true);
					cambiarEstado(true);
				}*/
			}
		});

		btnAnyadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioEdicion add = new UsuarioEdicion();
				add.setLocationRelativeTo(null);
				add.setVisible(true);
				dispose();
			}
		});
		// Establecemos el nombre del usuario logeado.
		this.setTitle(UsuarioLoggeado.getInstance().getUsuario());
	}
}
