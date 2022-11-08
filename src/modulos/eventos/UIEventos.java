package modulos.eventos;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
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
import Utils.UiHelper;
import modulos.login.AdminHome;
import modulos.login.Login;
import modulos.usuarios.UI.UsuarioLoggeado;

public class UIEventos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txt_Id;
	private JTextField txt_Nombre;
	private JTextField txt_Patrocinador;
	private JTextField txt_Lugar;
	private JFormattedTextField txt_Fecha;
	private AFEvento afEvento = new AFEvento();

	private JButton btnModificar = new JButton("Modificar");
	private JButton btnRepetirEvento = new JButton("Repetir Evento");
	private JButton btnNewButton = new JButton("Contabilizar Plazas");
	private JButton btnGuardar = new JButton("Guardar");
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
					UIEventos frame = new UIEventos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void cambiarEstado(boolean estado) {
    	this.txt_Id.setEditable(estado);
        this.txt_Nombre.setEditable(estado);
        this.txt_Patrocinador.setEditable(estado);
        this.txt_Lugar.setEditable(estado);
        this.txt_Fecha.setEditable(estado);
        this.btnModificar.setEnabled(!estado);
        this.btnEliminar.setEnabled(!estado);
        this.btnAnyadir.setEnabled(!estado);
        this.btnGuardar.setEnabled(estado);
 }
	
	// metodo para dejar campos en blanco una vez que se pulse insertar
	private void limpiarCampos() {
		this.txt_Id.setText("");
		this.txt_Nombre.setText("");
		this.txt_Patrocinador.setText("");
		this.txt_Lugar.setText("");
		this.txt_Fecha.setText("");
	}

	public boolean SeleccionarEvento() {
		// validar que se selecciona un registro de la tabla
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un evento", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// id (evento) seleccionado de la tabla
		String idSeleccionado = (String) this.table.getValueAt(table.getSelectedRow(), 0);
		// Buscar en la lista por el id.
		Evento eventoSeleccionado = this.afEvento.buscarEventoPorId(idSeleccionado);
		// rellena la informacion en lo txtlabel del evento seleccionado
		this.txt_Id.setText(eventoSeleccionado.getId());
		this.txt_Nombre.setText(eventoSeleccionado.getNombre());
		this.txt_Patrocinador.setText(eventoSeleccionado.getPatrocinador());
		this.txt_Lugar.setText(eventoSeleccionado.getLugar());
		this.txt_Fecha.setText(Helper.formatearAStringFecha(eventoSeleccionado.getFecha()));
		return true;
	}

	protected void guardarEvento() {
		if (!validarCampos())
		{
			return;
		}
		Evento evento = this.afEvento.buscarEventoPorId(this.txt_Id.getText());
		if (evento != null) {
			guardarEventoModificado(evento);
		} else {
			guardarEventoNuevo();
		}
		cambiarEstado(false);

	}

	private boolean validarCampos() {
		if (txt_Nombre.getText().isEmpty()) {
			// si deja el campo vacio, salta una ventana de alerta mostrando mensaje
			JOptionPane.showMessageDialog(rootPane, "Campo 'nombre' sin rellenar", "Error", JOptionPane.ERROR_MESSAGE);
			txt_Nombre.requestFocus();
			return false;
		}
		if (txt_Patrocinador.getText().isEmpty()) {
			// si deja el campo vacio, salta una ventana de alerta mostrando mensaje
			JOptionPane.showMessageDialog(rootPane, "Campo 'patrocinador' sin rellenar", "Error",
					JOptionPane.ERROR_MESSAGE);
			txt_Patrocinador.requestFocus();
			return false;
		}
		if (txt_Lugar.getText().isEmpty()) {
			// si deja el campo vacio, salta una ventana de alerta mostrando mensaje
			JOptionPane.showMessageDialog(rootPane, "Campo 'lugar' sin rellenar", "Error", JOptionPane.ERROR_MESSAGE);
			txt_Lugar.requestFocus();
			return false;
		}
		if (txt_Fecha.getText().isEmpty()) {
			// si deja el campo vacio, salta una ventana de alerta mostrando mensaje
			JOptionPane.showMessageDialog(rootPane, "Campo 'fecha' sin rellenar", "Error", JOptionPane.ERROR_MESSAGE);
			txt_Fecha.requestFocus();
			return false;
		}
		
		try {
			Helper.parsearAFecha(txt_Fecha.getText());
		} catch (Exception e) {
			// si deja el campo vacio, salta una ventana de alerta mostrando mensaje
			JOptionPane.showMessageDialog(rootPane, "Formato de fecha no válida", "Error", JOptionPane.ERROR_MESSAGE);
			txt_Fecha.requestFocus();
			return false;
		}
		return true;
	}

	private void guardarEventoNuevo() {
		// final DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		// Con todos los campos rellenos, se pasa a crear el nuevo objeto Evento
		Evento nuevoEvento = null;

		try {
			nuevoEvento = new Evento(txt_Nombre.getText(), txt_Patrocinador.getText(), txt_Lugar.getText(),
					Helper.parsearAFecha(txt_Fecha.getText()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		afEvento.anyadir(nuevoEvento);
		cargarTabla();
		limpiarCampos();
	}

	private void guardarEventoModificado(Evento evento) {

		try {
			evento.Actualizar(txt_Nombre.getText(), txt_Patrocinador.getText(), txt_Lugar.getText(),
					Helper.parsearAFecha(txt_Fecha.getText()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.afEvento.modificar(evento);
		cargarTabla();
		limpiarCampos();
	}
	/*
	 * Metodo cargar, este metodo rellena la tabla. Se instancia un objeto de la
	 * clase AFEvento y un objeto de la clase DefaultTableModel que es la clase que
	 * convierte los datos recibidos en forma de tabla... Con el for rellenamos la
	 * tabla
	 */


	public void cargarTabla() {
		DefaultTableModel modelo = (DefaultTableModel) this.table.getModel();
		modelo.setRowCount(0);
		for (Evento evento : afEvento.getEventos()) {
			modelo.addRow(new Object[] { evento.getId(), evento.getNombre(), evento.getPatrocinador(),
					evento.getLugar(), Helper.formatearAStringFecha(evento.getFecha()),

			});
		}
	}

	public void borrarEvento() {
		this.btnEliminar.setEnabled(true);
		// validar que se selecciona un registro de la tabla
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un evento", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		// id (evento) seleccionado de la tabla
		String idSeleccionado = (String) this.table.getValueAt(table.getSelectedRow(), 0);
		// Buscar en la lista por el id.

		this.afEvento.borrar(idSeleccionado);
		cargarTabla();
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public UIEventos() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setIconImage(img.getImage());
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 180, 553, 169);
		contentPane.add(scrollPane);
		contentPane.setBackground(Color.decode("#EFD0F5"));
		scrollPane.setBackground(Color.decode("#EFD0F5"));
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
				new String[] { "Id", "Nombre", "Patrocinador", "Lugar", "Fecha" }) {
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

		JPanel panel = new JPanel();
		panel.setBounds(23, 21, 553, 160);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.decode("#EFD0F5"));
		JLabel lbl_IDEvento = new JLabel("ID Evento");
		lbl_IDEvento.setBounds(24, 41, 65, 13);
		panel.add(lbl_IDEvento);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(24, 64, 54, 13);
		panel.add(lblNombre);

		JLabel lblPatrocinador = new JLabel("Patrocinador");
		lblPatrocinador.setBounds(24, 87, 77, 13);
		panel.add(lblPatrocinador);

		JLabel lblLugar = new JLabel("Lugar");
		lblLugar.setBounds(24, 110, 45, 13);
		panel.add(lblLugar);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(24, 133, 45, 13);
		panel.add(lblFecha);

		txt_Id = new JTextField();
		txt_Id.setEnabled(false);
		txt_Id.setEditable(false);
		txt_Id.setBounds(141, 39, 175, 16);
		panel.add(txt_Id);
		txt_Id.setColumns(10);

		txt_Nombre = new JTextField();
		txt_Nombre.setBounds(141, 61, 175, 19);
		panel.add(txt_Nombre);
		txt_Nombre.setColumns(10);
		txt_Nombre.setEditable(false);

		txt_Patrocinador = new JTextField();
		txt_Patrocinador.setBounds(141, 84, 172, 19);
		panel.add(txt_Patrocinador);
		txt_Patrocinador.setColumns(10);
		txt_Patrocinador.setEditable(false);

		txt_Lugar = new JTextField();
		txt_Lugar.setBounds(141, 107, 175, 19);
		panel.add(txt_Lugar);
		txt_Lugar.setColumns(10);
		txt_Lugar.setEditable(false);
		
		MaskFormatter mask = null;
		try {
			// Create a MaskFormatter for accepting phone number, the # symbol accept
			// only a number. We can also set the empty value with a place holder
			// character.
			mask = new MaskFormatter("##/##/####");
			mask.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			e.printStackTrace();
		}

		txt_Fecha = new JFormattedTextField(mask);
		txt_Fecha.setBounds(141, 130, 65, 19);
		panel.add(txt_Fecha);
		txt_Fecha.setColumns(10);
		txt_Fecha.setEditable(false);

		btnAnyadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarEstado(true);
			}
		});
		btnAnyadir.setBounds(404, 10, 119, 21);
		panel.add(btnAnyadir);

		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!SeleccionarEvento()) {
				txt_Fecha.setEditable(false);
				cambiarEstado(false);
				}else {
					txt_Fecha.setEditable(true);
					cambiarEstado(true);
				}
			}
		});
		btnModificar.setBounds(404, 33, 119, 21);
		panel.add(btnModificar);

		btnRepetirEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(rootPane, "Pagina en proceso, sentimos las molestias", "Advertencia",
						JOptionPane.WARNING_MESSAGE);
				return;
			
			}
		});
		btnRepetirEvento.setBounds(404, 129, 119, 21);
		panel.add(btnRepetirEvento);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(rootPane, "Pagina en proceso, sentimos las molestias", "Advertencia",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
		});

		btnNewButton.setBounds(404, 108, 119, 21);
		panel.add(btnNewButton);

		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarEvento();				
			}
		});
		btnGuardar.setBounds(404, 58, 119, 21);
		panel.add(btnGuardar);

		btnEliminar.setBounds(404, 82, 119, 21);
		panel.add(btnEliminar);

		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarEvento();
			}
		});

		JButton btnInformes = new JButton("Informes");
		btnInformes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(rootPane, "Pagina en proceso, sentimos las molestias", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
			});
		btnInformes.setBounds(445, 359, 119, 21);
		contentPane.add(btnInformes);

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
		btnRetroceso.setBounds(23, 349, 103, 42);
		btnRetroceso.setIcon(img3);
		btnRetroceso.setBackground(Color.decode("#EFD0F5"));
		contentPane.add(btnRetroceso);
		btnRetroceso.setBorderPainted(false);
		btnRetroceso.setBackground(new Color(239, 208, 245));
		contentPane.add(btnRetroceso);
		
		cargarTabla();
		
		UiHelper.AplicarMouseListenerButtons(contentPane);
		UiHelper.AplicarMouseListenerButtons(panel);
		
		UiHelper.AplicarEstilos(table);	
		//Establecemos el nombre del usuario logeado.
		this.setTitle(UsuarioLoggeado.getInstance().getUsuario());
	}
}
