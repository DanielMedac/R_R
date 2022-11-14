package modulos.usuarios.UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class otroUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtdeseaAadirOtro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			otroUsuario dialog = new otroUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public otroUsuario() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtdeseaAadirOtro = new JTextField();
		txtdeseaAadirOtro.setText("\u00BFDesea a\u00F1adir otro usuario?");
		txtdeseaAadirOtro.setEditable(false);
		txtdeseaAadirOtro.setBounds(141, 95, 165, 20);
		contentPanel.add(txtdeseaAadirOtro);
		txtdeseaAadirOtro.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						UsuarioEdicion otro = new UsuarioEdicion();
						otro.setLocationRelativeTo(null);
						otro.setVisible(true);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						UIUsuarios volver = new UIUsuarios();
						volver.setLocationRelativeTo(null);
						volver.setVisible(true);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
