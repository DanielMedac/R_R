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
import modulos.eventos.UIEventos;
import modulos.usuarios.UI.UIUsuarios;
import modulos.usuarios.UI.UsuarioLoggeado;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class AdminHome extends JFrame {

	JButton btnUsuarios, btnEventos, btnCerrarSesion;
	ImageIcon imgInicio = new ImageIcon("./src/ficheros/ciclista_morao.png");
	ImageIcon imgU = new ImageIcon("./src/ficheros/personas_2-1.png");
	ImageIcon imgU2 = new ImageIcon("./src/ficheros/bicis2.png");
	ImageIcon imgU3 = new ImageIcon("./src/ficheros/loguot2.png");
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminHome frame = new AdminHome();
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

	public AdminHome() {
		setResizable(false);
		inicioJFrame();
		inicioBotones();
		inicioAccionesBotones();
		//Establecemos el nombre del usuario logeado.
		this.setTitle(UsuarioLoggeado.getInstance().getUsuario());
	}

	private void inicioJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 824, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.decode("#EFD0F5"));

		this.setIconImage(imgInicio.getImage());
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	private void inicioBotones() {
		btnUsuarios = new JButton("");
		
		btnUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					UIUsuarios us = new UIUsuarios();
					us.setLocationRelativeTo(null);
					us.setVisible(true);
					dispose();
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				UIUsuarios us = new UIUsuarios();
				us.setLocationRelativeTo(null);
				us.setVisible(true);
				dispose();
				
			}
		});

		btnUsuarios.setBounds(242, 148, 124, 128);
		btnUsuarios.setIcon(imgU);
		btnUsuarios.setBackground(Color.decode("#EFD0F5"));
		btnUsuarios.setBorderPainted(false);
		contentPane.add(btnUsuarios);
		
		btnEventos = new JButton("");
		btnEventos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UIEventos uie = new UIEventos();
				uie.setLocationRelativeTo(null);
				uie.setVisible(true);
				dispose();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				UIEventos uie = new UIEventos();
				uie.setLocationRelativeTo(null);
				uie.setVisible(true);
				dispose();
			}
		});
		btnEventos.setIcon(imgU2);
		btnEventos.setBorderPainted(false);
		btnEventos.setBackground(new Color(239, 208, 245));
		btnEventos.setBounds(478, 148, 162, 128);
		
		contentPane.add(btnEventos);
		
        btnCerrarSesion = new JButton("");
        btnCerrarSesion.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (JOptionPane.showConfirmDialog(rootPane,
						"¿Desea cerrar sesión?", "Cierre de sesión",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					dispose();
				}
        	}
        	@Override
        	public void mousePressed(MouseEvent e) {
        		if (JOptionPane.showConfirmDialog(rootPane,
						"¿Desea cerrar sesión?", "Cierre de sesión",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
        			Login lg = new Login();
        			lg.setLocationRelativeTo(null);
        			lg.setVisible(true);
					dispose();
				}
        	}
        });
		btnCerrarSesion.setBounds(663, 413, 109, 64);
		btnCerrarSesion.setIcon(imgU3);
		btnCerrarSesion.setBorderPainted(false);
		btnCerrarSesion.setBackground(Color.decode("#EFD0F5"));
		contentPane.add(btnCerrarSesion);
		UiHelper.AplicarMouseListenerButtons(contentPane);
		
		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsuarios.setBounds(266, 281, 100, 14);
		contentPane.add(lblUsuarios);
		
		JLabel lblEventos = new JLabel("Eventos");
		lblEventos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEventos.setBounds(524, 281, 100, 14);
		contentPane.add(lblEventos);
	}

	private void inicioAccionesBotones() {
	}
}