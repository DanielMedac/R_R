package modulos.login;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Utils.UiHelper;
import modulos.usuarios.UI.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {
	JLabel lblUsuario, lblContrasenia, lblOlvidoContra, lblRecuperarContra;
	JTextField txtUsuarioInput;
	JPasswordField pfContraInput;
	JButton btnIngresar, btnAccesoPerfil;
	ImageIcon img = new ImageIcon("./src/ficheros/ciclista_morao.png");
	ImageIcon img2 = new ImageIcon("./src/ficheros/perfil.png");
	ImageIcon img3 = new ImageIcon("./src/ficheros/flecha.png");
	private JPanel contentPane;
	private JCheckBox chkboxRevelarContra;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		iniciarJFrame();
		iniciarBoton();
		iniciarEtiquetas();
		iniciarTextField();
		iniciarPasswordField();
		iniciarChkbox();
		iniciarAccionesChk();
		iniciarAccionesBotones();

	}

	private void iniciarEtiquetas() {
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblUsuario.setBounds(308, 166, 109, 13);
		lblUsuario.setForeground(Color.decode("#61276C"));
		contentPane.add(lblUsuario);

		lblContrasenia = new JLabel("Contrase\u00F1a");
		lblContrasenia.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblContrasenia.setBounds(308, 235, 109, 13);
		lblContrasenia.setForeground(Color.decode("#61276C"));
		contentPane.add(lblContrasenia);
		
	}

	private void iniciarTextField() {
		txtUsuarioInput = new JTextField();
		txtUsuarioInput.setBounds(308, 180, 171, 23);
		txtUsuarioInput.setBackground(Color.decode("#BD88C8"));
		contentPane.add(txtUsuarioInput);
		txtUsuarioInput.setColumns(10);
	}

	private void iniciarChkbox() {
		chkboxRevelarContra = new JCheckBox("Ver contrase\u00F1a");
		chkboxRevelarContra.setFont(new Font("Dialog", Font.PLAIN, 16));
		chkboxRevelarContra.setBounds(503, 246, 207, 21);
		chkboxRevelarContra.setBackground(Color.decode("#EFD0F5"));
		chkboxRevelarContra.setForeground(Color.decode("#BD88C8"));
		contentPane.add(chkboxRevelarContra);

	}

	private void iniciarPasswordField() {

		pfContraInput = new JPasswordField();

		pfContraInput.setBounds(308, 248, 171, 23);
		pfContraInput.setBackground(Color.decode("#BD88C8"));
		contentPane.add(pfContraInput);

	}

	private void iniciarBoton() {
		JButton btnRetroceso = new JButton("");
		btnRetroceso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home h = new Home();
				h.setLocationRelativeTo(null);
				h.setVisible(true);
				dispose();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				Home h = new Home();
				h.setLocationRelativeTo(null);
				h.setVisible(true);
				dispose();
			}
		});
		btnRetroceso.setBounds(10, 426, 109, 64);
		btnRetroceso.setIcon(img3);
		btnRetroceso.setBackground(Color.decode("#EFD0F5"));
		btnRetroceso.setBorderPainted(false);
		contentPane.add(btnRetroceso);

		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		btnIngresar.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnIngresar.setBackground(new Color(153, 51, 153));

		btnIngresar.setBounds(308, 310, 171, 23);
		btnIngresar.setForeground(Color.WHITE);

		contentPane.add(btnIngresar);

		btnAccesoPerfil = new JButton("");
		btnAccesoPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		btnAccesoPerfil.setBounds(341, 78, 109, 64);
		btnAccesoPerfil.setIcon(img2);
		btnAccesoPerfil.setBackground(Color.decode("#EFD0F5"));
		btnAccesoPerfil.setBorderPainted(false);
		contentPane.add(btnAccesoPerfil);
		UiHelper.AplicarMouseListenerButtons(contentPane);
	}

	private void iniciarJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 824, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.decode("#EFD0F5"));

		this.setIconImage(img.getImage());
		setContentPane(contentPane);
		contentPane.setLayout(null);

	}

	private void iniciarAccionesChk() {
		chkboxRevelarContra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkboxRevelarContra.isSelected()) {
					pfContraInput.setEchoChar((char) 0);
				} else {
					pfContraInput.setEchoChar('*');
				}
			}
		});

	}

	private void iniciarAccionesBotones() {
		btnAccesoPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home h = new Home();
				h.setLocationRelativeTo(null);
				h.setVisible(true);
				dispose();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Home h = new Home();
				h.setLocationRelativeTo(null);
				h.setVisible(true);
				dispose();
			}
		});
		btnIngresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				accesoLogin();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				accesoLogin();
			}

			private void accesoLogin() {
				String usuariox = txtUsuarioInput.getText();

				String passx = "" + pfContraInput.getText();
				try {
					BufferedReader br = new BufferedReader(new FileReader("./src/ficheros/accounts.txt"));
					String s;
					boolean accesoPermitido = false;
					boolean admin = false;
					boolean socio = false;
					boolean gestor = false;
					while ((s = br.readLine()) != null && !accesoPermitido) {
						if (s != null) {
							String[] lectura = s.split(",");
							//Inicializo el usuario logeado.
							UsuarioLoggeado.getInstance().setUsuario(lectura[0]);
							accesoPermitido = usuariox.equals(lectura[0]) && passx.equals(lectura[1]);
							if (lectura[2].equals("Admin")) {
								admin = true;
							} else if (lectura[2].equals("Socio")) {
								socio = true;
							}
						}
					}
					if (accesoPermitido) {
						JOptionPane.showMessageDialog(null, "Bienvenido " + usuariox);
						if (admin) {
							AdminHome ah = new AdminHome();
							ah.setLocationRelativeTo(null);
							ah.setVisible(true);
							dispose();
						}/*else if(socio) {
							UIUsuarios uie = new UIUsuarios();
							uie.setLocationRelativeTo(null);
							uie.setVisible(true);
							dispose();
						}*/
					} else {
						JOptionPane.showMessageDialog(null, "Usuario y/o contraseña erroneos.");
					}

					br.close();

				} catch (IOException ex) {
					ex.getStackTrace();
				}

			}

		});

	}
}
