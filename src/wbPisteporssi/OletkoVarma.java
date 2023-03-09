package wbPisteporssi;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * 
 * @author Esa
 * @version 12.6.2015
 *
 */
public class OletkoVarma extends JDialog {

    private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public OletkoVarma() {
		setTitle("Pistep\u00F6rssi");
		setBounds(100, 100, 315, 210);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblOletkoVarma = new JLabel("Oletko varma?");
			lblOletkoVarma.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblOletkoVarma.setBounds(94, 61, 120, 14);
			contentPanel.add(lblOletkoVarma);
		}
		{
			JLabel lblVoitVielPerua = new JLabel("Voit viel\u00E4 perua tehdyn toimenpiteen!");
			lblVoitVielPerua.setBounds(45, 86, 226, 29);
			contentPanel.add(lblVoitVielPerua);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Kyll\u00E4");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Peruuta");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
