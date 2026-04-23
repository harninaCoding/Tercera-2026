import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.geom.Area;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;


public class PantallaLibro extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btnAtras;
	private JButton btnAdelante;
	private Librero libro;
	private int alturaMaxima;
	private JLabel label;

	public void consigueAlturaMaxima(){
		
		alturaMaxima=textArea.getHeight();
	}
	public void calcularPantalla(){
		textArea.setVisible(false);
		libro.leerComienzoPagina();
		textArea.setText(libro.getFrase());
		Dimension yavale=textArea.getPreferredSize();
		while(alturaMaxima>yavale.getHeight()){
			libro.anadirLetra();
			textArea.setText(libro.getFrase());
			yavale=textArea.getPreferredSize();
			
		}
		libro.quitarLetra();
		textArea.setText(libro.getFrase());
		textArea.setVisible(true);
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaLibro frame = new PantallaLibro();
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
	public PantallaLibro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		libro=new Librero();
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 11, 310, 417);
		contentPane.add(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		consigueAlturaMaxima();
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(libro.getPaginaActual()>0)
					libro.setPaginaActual(libro.getPaginaActual()-1);
				libro.atrasarPagina();
				calcularPantalla();
				label.setText(Integer.toString(libro.getPaginaActual()));
				
			}
		});
		btnAtras.setBounds(10, 439, 89, 23);
		contentPane.add(btnAtras);
		
		btnAdelante = new JButton("Adelante");
		btnAdelante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				libro.setPaginaActual(libro.getPaginaActual()+1);
				calcularPantalla();
				label.setText(Integer.toString(libro.getPaginaActual()));
			}
		});
		btnAdelante.setBounds(231, 439, 89, 23);
		contentPane.add(btnAdelante);
		
		label = new JLabel("");
		label.setBounds(135, 439, 46, 14);
		contentPane.add(label);
	}
}
