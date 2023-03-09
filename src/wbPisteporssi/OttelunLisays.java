package wbPisteporssi;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.WindowConstants;

import porssi.Ottelu;
import porssi.Pisteporssi;

import java.awt.Frame;

/**
 * 
 * @author Esa Hyyryl‰inen
 * @version 12.6.2015
 *
 */
public class OttelunLisays extends JDialog {

    private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField ottelunNimiTextField;

	
	/**
	 * Create the dialog.
	 * @param pisteporssi pistepˆrssi johon lis‰t‰‰n
	 */
	public OttelunLisays(Pisteporssi pisteporssi) {
		super((Frame)null,"Tietoja",true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Pistep\u00F6rssi");
		setBounds(100, 100, 387, 170);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(5, 5, 356, 27);
			contentPanel.add(panel);
			{
				JLabel lblOttelunLisys = new JLabel("Ottelun lis\u00E4ys:");
				lblOttelunLisys.setFont(new Font("Tahoma", Font.BOLD, 14));
				panel.add(lblOttelunLisys);
			}
		}
		{
			JLabel lblOttelunNimi = new JLabel("Ottelun nimi:");
			lblOttelunNimi.setBounds(15, 43, 89, 14);
			contentPanel.add(lblOttelunNimi);
		}
		{
			ottelunNimiTextField = new JTextField();
			ottelunNimiTextField.setBounds(114, 40, 247, 20);
			contentPanel.add(ottelunNimiTextField);
			ottelunNimiTextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(e->lisaa(pisteporssi));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Peruuta");
				cancelButton.addActionListener(e->setVisible(false));
				buttonPane.add(cancelButton);
			}
		}
		
		setVisible(true);
	}


	/**
	 * Lis‰‰ ottelun
	 * @param pisteporssi pisteporssi johon lis‰t‰‰n
	 */
	protected void lisaa(Pisteporssi pisteporssi) {
		if(pisteporssi.onkoTamanNimistaOttelua(ottelunNimiTextField.getText()) != -1 || ottelunNimiTextField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "T‰m‰n niminen ottelu on jo olemassa tai\n et ole kirjoittanut ottelulle nime‰.");
			return;
		}
		if(ottelunNimiTextField.getText().contains("|")) {
			JOptionPane.showMessageDialog(this, "Laiton merkki '|'.");
			return;
		}
		Ottelu uusiOttelu = new Ottelu();
		pisteporssi.lisaa(uusiOttelu);
		uusiOttelu.rekisteroi();
		uusiOttelu.setNimi(ottelunNimiTextField.getText());
	    setVisible(false);
    }
}
