package wbPisteporssi;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.Frame;

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
	private JTextField textField;
	private final JComboBox<Object> comboBox = new JComboBox<Object>();

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
			textField = new JTextField();
			textField.setBounds(56, 111, 169, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblPelipaikka = new JLabel("Pelipaikka:");
			lblPelipaikka.setBounds(286, 86, 88, 14);
			contentPanel.add(lblPelipaikka);
		}
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Maalivahti", "Hy\u00F6kk\u00E4\u00E4j\u00E4", "Puolustaja"}));
		comboBox.setBounds(286, 111, 88, 20);
		
		contentPanel.add(comboBox);
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
				cancelButton.addActionListener(e->setVisible(false));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		setVisible(true);
	}
	
	/**
	 * Lisää pelaajan
	 * Ei toimi vielä kunnolla
	 * @param pisteporssi Pistepörssi johon lisätään
	 * @param model taulukko pelaajista
	 */
	protected void lisaa(Pisteporssi pisteporssi, DefaultTableModel model) {
		Pelaaja uusiPelaaja = new Pelaaja();
		uusiPelaaja.rekisteroi();
		uusiPelaaja.vastaaPelaaja();
		pisteporssi.lisaa(uusiPelaaja);
		model.addRow(new Object[]{uusiPelaaja, "0", "0", "0", "0", "0"});
	    setVisible(false);
    }
}
