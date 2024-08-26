package gui;
import javax.swing.*;
import java.awt.*;


public class Them_Form extends JFrame {
	/**
	 * 
	 */
	JPanel pnCenter = new JPanel();
	private static final long serialVersionUID = 1L;

	public Them_Form() {
		doShow();
	}
	public void add(JPanel pn) {
		pnCenter.removeAll();
		pnCenter.add(pn);
		validate();
	}
	public void doShow() {
		setSize(1200, 800);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Quản lý Quầy Thuốc");
		Container cp = getContentPane();

		// pnCenter
		
		

		cp.setLayout(new BorderLayout());
		cp.add(pnCenter, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		new Them_Form().setVisible(true);
	}
}
