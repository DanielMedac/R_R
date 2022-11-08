package modulos.login;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Utils.UiHelper;
import modulos.usuarios.UI.UsuarioLoggeado;
import javax.swing.JTextArea;

public class Home extends JFrame {

	JButton btnInicio, btnPrimerAcceso;
	ImageIcon img = new ImageIcon("./src/ficheros/ciclista_morao.png");

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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

	public Home() {
		setResizable(false);
		inicioJFrame();
		inicioBotones();
		inicioAccionesBotones();
		
	}

	private void inicioJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 824, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.decode("#EFD0F5"));

		this.setIconImage(img.getImage());
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	private void inicioBotones() {
		btnInicio = new JButton("");
		btnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(rootPane,
						"Ya se encuentra en el inicio de la aplicación, ¿desea salir?", "Advertencia",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					dispose();
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(rootPane,
						"Ya se encuentra en el inicio de la aplicación, ¿desea salir?", "Advertencia",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					dispose();
				}
			}
		});

		btnInicio.setBounds(296, 104, 220, 155);
		btnInicio.setIcon(img);
		btnInicio.setBackground(Color.decode("#EFD0F5"));
		btnInicio.setBorderPainted(false);
		contentPane.add(btnInicio);

		btnPrimerAcceso = new JButton("Entrar");
		
		btnPrimerAcceso.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
		btnPrimerAcceso.setBackground(new Color(153, 51, 153));

		btnPrimerAcceso.setBounds(316, 319, 174, 27);
		btnPrimerAcceso.setForeground(Color.WHITE);
		contentPane.add(btnPrimerAcceso);

		UiHelper.AplicarMouseListenerButtons(contentPane);
	}

	private void inicioAccionesBotones() {
		btnPrimerAcceso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login lg = new Login();
				lg.setLocationRelativeTo(null);
				lg.setVisible(true);
				dispose();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Login lg = new Login();
				lg.setLocationRelativeTo(null);
				lg.setVisible(true);
				dispose();
			}
		});
	}
}

