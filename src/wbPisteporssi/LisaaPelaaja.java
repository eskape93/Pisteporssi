package wbPisteporssi;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.Frame;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;

import porssi.Pelaaja;
import porssi.Pisteporssi;

/**
 * 
 * @author Esa Hyyryläinen
 * @version 12.6.2015
 *
 */
public class LisaaPelaaja extends JDialog {

    private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField pelaajanNimiTextField;
	private final JComboBox<Object> pelipaikkaComboBox = new JComboBox<Object>();

	/**
	 * Create the dialog.
	 * @param pisteporssi pistepörssi johon lisätään
	 * @param model pelaaja taulukko
	 */
	@SuppressWarnings({ })
    public LisaaPelaaja(Pisteporssi pisteporssi, DefaultTableModel model) {
		super((Frame)null,"Tietoja",true);
		setTitle("Pistep\u00F6rssi");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(5, 5, 424, 27);
			contentPanel.add(panel);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel lblPelaajanLisys = new JLabel("Pelaajan lis\u00E4ys/muokkaus:");
				lblPelaajanLisys.setFont(new Font("Tahoma", Font.BOLD, 14));
				panel.add(lblPelaajanLisys);
			}
		}
		{
			JLabel lblPelaajanNimi = new JLabel("Pelaajan nimi:");
			lblPelaajanNimi.setBounds(60, 86, 82, 14);
			contentPanel.add(lblPelaajanNimi);
		}
		{
			pelaajanNimiTextField = new JTextField();
			pelaajanNimiTextField.setBounds(56, 111, 169, 20);
			contentPanel.add(pelaajanNimiTextField);
			pelaajanNimiTextField.setColumns(10);
		}
		{
			JLabel lblPelipaikka = new JLabel("Pelipaikka:");
			lblPelipaikka.setBounds(286, 86, 88, 14);
			contentPanel.add(lblPelipaikka);
		}
		pelipaikkaComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Maalivahti", "Hy\u00F6kk\u00E4\u00E4j\u00E4", "Puolustaja"}));
		pelipaikkaComboBox.setBounds(286, 111, 88, 20);
		
		contentPanel.add(pelipaikkaComboBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Lis\u00E4\u00E4/muokkaa");
				okButton.addActionListener(e->lisaa(pisteporssi, model));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Peruuta");
				cancelButton.addActionListener(e->processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		setVisible(true);
	}
	
	
	/**
	 * Lisää pelaajan
	 * Pelipaikka tallennetaan, mutta ohjelmassa ei tällä hetkellä käyttöä pelipaikalle
	 * @param pisteporssi Pistepörssi johon lisätään
	 * @param model taulukko pelaajista
	 */
	protected void lisaa(Pisteporssi pisteporssi, DefaultTableModel model) {
		if(pisteporssi.onkoTamanNimista(pelaajanNimiTextField.getText()) != -1 || pelaajanNimiTextField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Tämän niminen pelaaja on jo olemassa tai\n et ole kirjoittanut pelaajalle nimeä.");
			return;
		}
		if(pelaajanNimiTextField.getText().contains("|")) {
			JOptionPane.showMessageDialog(this, "Laiton merkki '|'.");
			return;
		}
		Pelaaja uusiPelaaja = new Pelaaja();
		uusiPelaaja.rekisteroi();
		uusiPelaaja.setNimi(pelaajanNimiTextField.getText());
		uusiPelaaja.setPelipaikka( (String) pelipaikkaComboBox.getSelectedItem() );
		pisteporssi.lisaa(uusiPelaaja);
		model.addRow(new Object[]{uusiPelaaja, "0", "0", "0", "0", "0"});
		processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	    //setVisible(false);
    }
}
