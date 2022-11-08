package Utils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

public class UiHelper {
	
	public static void  AplicarEstilos (JTable table) {
		// Color de la cabecera del table
		 table.getTableHeader().setBackground(Color.decode("#e2abed"));
		 Font font =  table.getTableHeader().getFont();
		 //tipo de letra de la cabecera
		 table.getTableHeader().setFont(new Font(font.getFontName(), Font.BOLD, font.getSize()));
		 table.getTableHeader().setForeground(Color.decode("#5b146b"));
		 // color de fondo y de los registros.
		 table.setBackground(Color.decode("#f8eafb"));
		 table.setForeground(Color.decode("#5b146b"));
		 //Registro seleccionado
		 table.setSelectionBackground(Color.decode("#b629d6"));
		 table.setSelectionForeground(Color.decode("#f8e9fb"));
	}
	//Se aplica estilo al escuchador del raton 
	public static void AplicarMouseListenerButtons(JPanel panel) {		
		for(Component comp: panel.getComponents())
		{
			if(comp instanceof JButton) {
				JButton button = (JButton)comp;		
				//si el boton no esta vacio se le aplica los siguientes estilos
				if(!button.getText().isEmpty()) {
					
					//todos los botones tendran el mismo color y fondon de letra				
					button.setBackground(new Color(153, 51, 153));				
					button.setForeground(Color.WHITE);
					//escuchador del raton, donde cambiara de color si se le situa elraton encima y el icono tambien cambiaria a una mano
					button.addMouseListener(new MouseAdapter() {
				         Color foreground = button.getForeground();
				         Color background = button.getBackground();
				         Cursor cursor= Cursor.getDefaultCursor(); 
				         public void mouseEntered(MouseEvent me) {
				            
				            button.setForeground(Color.decode("#b629d6"));
				            button.setBackground(Color.decode("#f8eafb"));	
				        
				            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// cambia el color cuando pasa el raton por encima
				         }
				         public void mouseExited(MouseEvent me) {
				            button.setForeground(foreground);
				            button.setBackground(background);	
				            button.setCursor(Cursor.getPredefinedCursor(cursor.getType()));// 
				         }
				      });	
					//Si el boton no tiene texto, que es el caso de los botones que son imagenes, se le aplicara solo lo del cursor
				}else {
					button.addMouseListener(new MouseAdapter() {
				         Cursor cursor= Cursor.getDefaultCursor(); 
				         public void mouseEntered(MouseEvent me) {
				            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// cambia el color cuando pasa el raton por encima
				         }
				         public void mouseExited(MouseEvent me) {	
				            button.setCursor(Cursor.getPredefinedCursor(cursor.getType()));// 
				         }
				      });	
				}
				
			}
		}
		
	}

}
