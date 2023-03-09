package wbPisteporssi;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import porssi.Ottelu;
import porssi.OtteluPelaaja;
import porssi.Pelaaja;
import porssi.Pisteporssi;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 * 
 * @author Esa Hyyryläinen
 * @version 10.7.2015
 *
 */
public class OttelujenHaku extends JDialog {

    private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField hakuTextField;
	private JTextField pelaajanNimiTextField;
	private JList<Ottelu> listaOtteluista;
	private JList<Pelaaja> listaPelaajista;
	private JList<Pelaaja> listaMaaleista;
	private JList<Pelaaja> listaSyotoista;
	private JList<Pelaaja> listaJaahyt2min;
	private JList<Pelaaja> listaJaahyt5min;
	private JComboBox<Object> rooliComboBox;
	private JComboBox<Object> poistettavaComboBox;
	private JComboBox<Object> hakuEhtoComboBox;

	/**
	 * Create the dialog.
	 * @param pisteporssiAlk pörssi josta otteluja haetaan
	 * @param pelaajaTaulukkoModel Taulukko malli pelaajista
	 */
	@SuppressWarnings({ })
    public OttelujenHaku(Pisteporssi pisteporssiAlk, DefaultTableModel pelaajaTaulukkoModel) {
		super((Frame)null,"Tietoja",true);
		Pisteporssi pisteporssi = pisteporssiAlk.clone();
		setTitle("Pistep\u00F6rssi");
		setBounds(100, 100, 693, 469);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(5, 5, 740, 27);
			contentPanel.add(panel);
			{
				JLabel lblOttelut = new JLabel("Ottelut:");
				lblOttelut.setFont(new Font("Tahoma", Font.BOLD, 14));
				panel.add(lblOttelut);
			}
		}
		{
			JLabel lblHaku = new JLabel("Haku:");
			lblHaku.setBounds(15, 44, 46, 14);
			contentPanel.add(lblHaku);
		}
		{
			hakuTextField = new JTextField();
			hakuTextField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					pelaajaHaku(pisteporssi);
				}
			});
			hakuTextField.setBounds(51, 41, 99, 20);
			contentPanel.add(hakuTextField);
			hakuTextField.setColumns(10);
		}
		{
			JLabel lblOttelu = new JLabel("Ottelu:");
			lblOttelu.setBounds(48, 82, 46, 14);
			contentPanel.add(lblOttelu);
		}
		{
			JPanel panel = new JPanel();
			panel.setBounds(25, 107, 133, 254);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane);
				{
					listaOtteluista = new JList<Ottelu>();
					listaOtteluista.addListSelectionListener(e->valittu(listaOtteluista.getSelectedValue(), pisteporssi)); //Korjattava ei saa osoitta lisää
					alustaOtteluLista(pisteporssi);
					scrollPane.setViewportView(listaOtteluista);
				}
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBounds(198, 107, 117, 254);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane);
				{
					listaPelaajista = new JList<Pelaaja>();
					scrollPane.setViewportView(listaPelaajista);
				}
			}
		}
		{
			JLabel lblNewLabel = new JLabel("Pelaajat:");
			lblNewLabel.setBounds(233, 82, 82, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblHakuehto = new JLabel("Hakuehto");
			lblHakuehto.setBounds(160, 43, 60, 14);
			contentPanel.add(lblHakuehto);
		}
		{
			hakuEhtoComboBox = new JComboBox<Object>();
			hakuEhtoComboBox.addActionListener(new ActionListener() {
				@Override
                public void actionPerformed(ActionEvent arg0) {
					pelaajaHaku(pisteporssi);
				}
			});
			hakuEhtoComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Ottelun nimi", "Pelanneet", "Maalintekij\u00E4t", "Sy\u00F6tt\u00E4j\u00E4t", "J\u00E4\u00E4hyll\u00E4"}));
			hakuEhtoComboBox.setBounds(215, 41, 133, 20);
			contentPanel.add(hakuEhtoComboBox);
		}
		{
			JLabel lblMaalit = new JLabel("Maalit:");
			lblMaalit.setBounds(377, 82, 46, 14);
			contentPanel.add(lblMaalit);
		}
		{
			JPanel panel = new JPanel();
			panel.setBounds(345, 107, 122, 123);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane);
				{
					listaMaaleista = new JList<Pelaaja>();
					scrollPane.setViewportView(listaMaaleista);
				}
			}
		}
		{
			JLabel lblSytt = new JLabel("Sy\u00F6t\u00F6t:");
			lblSytt.setBounds(377, 241, 46, 14);
			contentPanel.add(lblSytt);
		}
		{
			JPanel panel = new JPanel();
			panel.setBounds(345, 263, 122, 119);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane);
				{
					listaSyotoista = new JList<Pelaaja>();
					scrollPane.setViewportView(listaSyotoista);
				}
			}
		}
		{
			JLabel lblJhytmin = new JLabel("J\u00E4\u00E4hyt 2min");
			lblJhytmin.setBounds(558, 82, 82, 14);
			contentPanel.add(lblJhytmin);
		}
		{
			JPanel panel = new JPanel();
			panel.setBounds(528, 107, 133, 123);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane);
				{
					listaJaahyt2min = new JList<Pelaaja>();
					scrollPane.setViewportView(listaJaahyt2min);
				}
			}
		}
		{
			JLabel lblJhytmin_1 = new JLabel("J\u00E4\u00E4hyt 5min");
			lblJhytmin_1.setBounds(558, 241, 82, 14);
			contentPanel.add(lblJhytmin_1);
		}
		{
			JPanel panel = new JPanel();
			panel.setBounds(528, 263, 133, 119);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane);
				{
					listaJaahyt5min = new JList<Pelaaja>();
					scrollPane.setViewportView(listaJaahyt5min);
				}
			}
		}
		{
			pelaajanNimiTextField = new JTextField();
			pelaajanNimiTextField.setBounds(380, 41, 92, 20);
			contentPanel.add(pelaajanNimiTextField);
			pelaajanNimiTextField.setColumns(10);
		}
		{
			rooliComboBox = new JComboBox<Object>();
			rooliComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Pelaaja", "Maali", "Sy\u00F6tt\u00F6", "J\u00E4\u00E4hy(2min)", "J\u00E4\u00E4hy(5min)"}));
			rooliComboBox.setBounds(482, 41, 115, 20);
			contentPanel.add(rooliComboBox);
		}
		{
			JButton btnNewButton = new JButton("Lis\u00E4\u00E4");
			btnNewButton.addActionListener(e->lisaa(pisteporssi, listaOtteluista.getSelectedValue(),comboBoxRooliksi() ));
			btnNewButton.setBounds(602, 40, 65, 23);
			contentPanel.add(btnNewButton);
		}
		{
			JButton btnPoista = new JButton("Poista");
			btnPoista.addActionListener(e->poista(pisteporssi));
			btnPoista.setBounds(35, 372, 89, 23);
			contentPanel.add(btnPoista);
		}
		{
			poistettavaComboBox = new JComboBox<Object>();
			poistettavaComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Ottelu", "Pelaaja", "Maali", "Sy\u00F6tt\u00F6", "J\u00E4\u00E4hy(2min)", "J\u00E4\u00E4hy(5min)"}));
			poistettavaComboBox.setBounds(152, 373, 117, 20);
			contentPanel.add(poistettavaComboBox);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(e->ok(pisteporssiAlk, pisteporssi, pelaajaTaulukkoModel));
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
	 * Tehdyt muutokset hyväksytään
	 * @param pelaajaTaulukkoModel Taulukko malli pelaajista
	 */
	private void ok(Pisteporssi pisteporssiAlk, Pisteporssi pisteporssi, DefaultTableModel pelaajaTaulukkoModel) {
		pisteporssiAlk.kopioi(pisteporssi);
		paivitaTaulukko(pisteporssiAlk, pelaajaTaulukkoModel);
		lajittele(pelaajaTaulukkoModel);
		processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	    //setVisible(false);
    }
	
	
	/**
	 * Päivittää pääikkunan pelaaja taulukon PelaajaTiedoilla
	 * @param pisteporssi porssi jossa tietoja
	 * @param pelaajaTaulukkoModel taulukko johon pörssin tietoja laitetaan
	 */
	protected static void paivitaTaulukko(Pisteporssi pisteporssi, DefaultTableModel pelaajaTaulukkoModel) {
		for(int i = 0; i<pelaajaTaulukkoModel.getRowCount();i++) {
			int pelaajanTunnusNro = ((Pelaaja) pelaajaTaulukkoModel.getValueAt(i,0)).getTunnusNro();
			pelaajaTaulukkoModel.setValueAt(pisteporssi.annaPelaajanOttelujenMaara(pelaajanTunnusNro),i,1);
			pelaajaTaulukkoModel.setValueAt(pisteporssi.annaPelaajanMaalit(pelaajanTunnusNro),i,2);
			pelaajaTaulukkoModel.setValueAt(pisteporssi.annaPelaajanSyotot(pelaajanTunnusNro),i,3);
			pelaajaTaulukkoModel.setValueAt(pisteporssi.annaPelaajanPisteet(pelaajanTunnusNro),i,4);
			pelaajaTaulukkoModel.setValueAt(pisteporssi.annaPelaajanJaahyt(pelaajanTunnusNro), i,5);
		}
	}

	
	/**
	 * Ottelujen, pelaajien, jne. poistaminen
	 * @param pisteporssi pisteporssi, josta poistetaan
	 */
	protected void poista(Pisteporssi pisteporssi) {
		poistettavaComboBox.getSelectedIndex();
		Ottelu ottelu = listaOtteluista.getSelectedValue();
		if(ottelu == null) {
			JOptionPane.showMessageDialog(this, "Yhtään ottelua ei ole valittuna.\nValitse ensin ottelu, joka poistetaan");
			return;
		}
		switch(poistettavaComboBox.getSelectedIndex()) {
		case 0: 
			pisteporssi.poistaOttelu(ottelu.getOtteluNro());
			alustaLista(listaPelaajista, new Pelaaja[0]);
			alustaLista(listaMaaleista, new Pelaaja[0]);
			alustaLista(listaSyotoista, new Pelaaja[0]);
			alustaLista(listaJaahyt2min, new Pelaaja[0]);
			alustaLista(listaJaahyt5min, new Pelaaja[0]);
			alustaOtteluLista(pisteporssi);
			break;
		case 1:
			Pelaaja pelannut = listaPelaajista.getSelectedValue();
			if(pelannut == null) {
				JOptionPane.showMessageDialog(this, "Yhtään pelaajaa ei ole valittuna.\nValitse ensin pelannut pelaaja, joka poistetaan");
				return;
			}
			pisteporssi.poistaYksittainen(pelannut.getTunnusNro(), ottelu.getOtteluNro(), 5);
			Pelaaja[] pelanneet = pisteporssi.annaOttelunPelaajat(ottelu.getOtteluNro(), 5);
			alustaLista(listaPelaajista, pelanneet);
			break;
		case 2:
			Pelaaja maalinTekija = listaMaaleista.getSelectedValue();
			if(maalinTekija == null) {
				JOptionPane.showMessageDialog(this, "Yhtään maalintekijää ei ole valittuna.\nValitse ensin maalintekijä, joka poistetaan");
				return;
			}
			pisteporssi.poistaYksittainen(maalinTekija.getTunnusNro(), ottelu.getOtteluNro(), 1);
			Pelaaja[] maalanneet = pisteporssi.annaOttelunPelaajat(ottelu.getOtteluNro(), 1);
			alustaLista(listaMaaleista, maalanneet);
			break;
		case 3:
			Pelaaja syottaja = listaSyotoista.getSelectedValue();
			if(syottaja == null) {
				JOptionPane.showMessageDialog(this, "Yhtään syöttäjää ei ole valittuna.\nValitse ensin syöttäjä, joka poistetaan");
				return;
			}
			pisteporssi.poistaYksittainen(syottaja.getTunnusNro(), ottelu.getOtteluNro(), 2);
			Pelaaja[] syottaneet = pisteporssi.annaOttelunPelaajat(ottelu.getOtteluNro(), 2);
			alustaLista(listaSyotoista, syottaneet);
			break;
		case 4:
			Pelaaja jaahylla2 = listaJaahyt2min.getSelectedValue();
			if(jaahylla2 == null) {
				JOptionPane.showMessageDialog(this, "Yhtään jäähyllä 2min ollutta ei ole valittuna.\nValitse ensin jäähyllä olija, joka poistetaan");
				return;
			}
			pisteporssi.poistaYksittainen(jaahylla2.getTunnusNro(), ottelu.getOtteluNro(), 3);
			Pelaaja[] jaahyilijat2 = pisteporssi.annaOttelunPelaajat(ottelu.getOtteluNro(), 3);
			alustaLista(listaJaahyt2min, jaahyilijat2);
			break;
		case 5:
			Pelaaja jaahylla5 = listaJaahyt5min.getSelectedValue();
			if(jaahylla5 == null) {
				JOptionPane.showMessageDialog(this, "Yhtään jäähyllä 5min ollutta ei ole valittuna.\nValitse ensin jäähyllä olija, joka poistetaan");
				return;
			}
			pisteporssi.poistaYksittainen(jaahylla5.getTunnusNro(), ottelu.getOtteluNro(), 4);
			Pelaaja[] jaahyilijat5 = pisteporssi.annaOttelunPelaajat(ottelu.getOtteluNro(), 4);
			alustaLista(listaJaahyt5min, jaahyilijat5);
			break;
		default: break;
		}
    }
	
	
	/**
	 * Näyttää valitun ottelun tiedot
	 * @param ottelu tämä ottelu on valittu
	 * @param pisteporssi pistepörssi, josta halutaan tietoa etsiä
	 */
	protected void valittu(Ottelu ottelu, Pisteporssi pisteporssi) {
		if(ottelu == null) {
			return;
		}
		int otteluNro = ottelu.getOtteluNro();
		// Pelaajien roolit (1=maalintekijä, 2=syöttäjä, 3=jäähyllä2min, 4=jäähyllä5min, 5=pelannut)
		Pelaaja[] pelaajat = pisteporssi.annaOttelunPelaajat(otteluNro, 5);
		Pelaaja[] maalit = pisteporssi.annaOttelunPelaajat(otteluNro, 1);
		Pelaaja[] syotot = pisteporssi.annaOttelunPelaajat(otteluNro, 2);
		Pelaaja[] jaahyt2min = pisteporssi.annaOttelunPelaajat(otteluNro, 3);
		Pelaaja[] jaahyt5min = pisteporssi.annaOttelunPelaajat(otteluNro, 4);
		
		
		alustaLista(listaPelaajista, pelaajat);
		alustaLista(listaMaaleista, maalit);
		alustaLista(listaSyotoista, syotot);
		alustaLista(listaJaahyt2min, jaahyt2min);
		alustaLista(listaJaahyt5min, jaahyt5min);
	}
	
	
	/**
	 * Alustaa listan halutuilla pelaajilla
	 * @param alustettavaLista Lista jonka tiedot alustetaan
	 * @param pelaajat Taulukko pelaajista jotka listaan laitetaan
	 */
	public void alustaLista(JList<Pelaaja> alustettavaLista, Pelaaja[] pelaajat) {
		alustettavaLista.setModel(new AbstractListModel<Pelaaja>() {
			/**
			 * 
			 */
            private static final long serialVersionUID = 1L;
			@Override
            public Pelaaja getElementAt(int index) {
	            return pelaajat[index];
            }
			@Override
            public int getSize() {
	            return pelaajat.length;
            }
			});
	}
	
	
	/**
	 * Lisää ottelun maalin, syötön jne.
	 * Jos ottelua ei valittuna tulostaa virheen
	 * Jos pelaajaa ei olemassa tulostaa virheen
	 * @param pisteporssi pistepörssi johon lisätään
	 * @param ottelu ottelu johon lisätään
	 * @param rooli Pelaajan rooli
	 */
	protected void lisaa(Pisteporssi pisteporssi, Ottelu ottelu, int rooli) {
		if(ottelu == null) {
			JOptionPane.showMessageDialog(this, "Yhtään ottelua ei ole valittuna.\nValitse ensin ottelu, johon lisätään");
			return;
		}
		OtteluPelaaja otteluPelaaja = new OtteluPelaaja();
		if(pisteporssi.onkoTamanNimista(pelaajanNimiTextField.getText()) != -1) {
			//ok nappia varten voisi lisätä pisteporssi2 ja lopuksi pisteporssi=pisteporssi2
			otteluPelaaja.setPelaajaNro(pisteporssi.onkoTamanNimista(pelaajanNimiTextField.getText()));
			otteluPelaaja.setOtteluNro(ottelu.getOtteluNro());
			otteluPelaaja.setRooli(rooli);
			otteluPelaaja.setMontako(1);
			if(pisteporssi.loytyyko(otteluPelaaja) >= 0) {
				otteluPelaaja = pisteporssi.annaOtteluPelaaja(pisteporssi.loytyyko(otteluPelaaja));
				otteluPelaaja.setMontako(otteluPelaaja.getMontako()+1);
				valittu(ottelu, pisteporssi);
				return;
			}
			pisteporssi.lisaa(otteluPelaaja);
			valittu(ottelu, pisteporssi);
			return;
		}
		JOptionPane.showMessageDialog(this, "Pelaajaa " + pelaajanNimiTextField.getText() + " ei olemassa.\nTarkista oikeinkirjoitus tai luo pelaaja.");
    }
	
	
	/**
	 * Muuttaa comboBoxin valinnan pelaajan rooliksi
	 * @return palauttaa comboBoxin valinnan muutettuna roolinumeroksi
	 */
	private int comboBoxRooliksi() {
		//Voisi indeksin lukemisen sijasta lukea suoraan sisällön, näin ei haittaisi jos paikkoja joskus muuttaisi
		if(rooliComboBox.getSelectedIndex() == 0)
			return 5;
		return rooliComboBox.getSelectedIndex();
	}
	
	
	/**
	 * Alustaa ottelulistan pisteporssin otteluilla
	 * @param pisteporssi tällä pistepörssillä alustetaan
	 */
	private void alustaOtteluLista(Pisteporssi pisteporssi) {
		listaOtteluista.setModel(new AbstractListModel<Ottelu>() {
			/**
			 * 
			 */
            private static final long serialVersionUID = 1L;
			Ottelu[] values = new Ottelu[pisteporssi.getOtteluja()];
			{for(int i = 0; i < pisteporssi.getOtteluja(); i++)
				values[i] = pisteporssi.annaOttelu(i);}
			
			@Override
            public int getSize() {
				return values.length;
			}
			@Override
            public Ottelu getElementAt(int index) {
				return values[index];
			}
		});
	}
	
	
	/**
	 * Alustaa ottelulistan annetuilla otteluilla
	 * @param ottelut alustetaan näillä otteluilla
	 */
	private void alustaOtteluLista(Ottelu[] ottelut) {
		listaOtteluista.setModel(new AbstractListModel<Ottelu>() {
			/**
			 * 
			 */
            private static final long serialVersionUID = 1L;
			Ottelu[] values = ottelut;
			
			@Override
            public int getSize() {
				return values.length;
			}
			@Override
            public Ottelu getElementAt(int index) {
				return values[index];
			}
		});
	}
	
	
	/**
	 * Etsii otteluista hakuehdon täyttävän ottelun/ottelut
	 * @param pisteporssi Tästä pörssistä haetaan
	 */
	protected void pelaajaHaku(Pisteporssi pisteporssi) {
		if(hakuTextField.getText().length() < 1) {
			alustaOtteluLista(pisteporssi);
			return;
		}
		switch(hakuEhtoComboBox.getSelectedIndex()) {
		case 0:
			Ottelu[] ottelut = pisteporssi.annaOttelut(hakuTextField.getText());
			alustaOtteluLista(ottelut);
			alustaLista(listaPelaajista, new Pelaaja[0]);
			alustaLista(listaMaaleista, new Pelaaja[0]);
			alustaLista(listaSyotoista, new Pelaaja[0]);
			alustaLista(listaJaahyt2min, new Pelaaja[0]);
			alustaLista(listaJaahyt5min, new Pelaaja[0]);
			break;
		case 1:
			ottelut = pisteporssi.annaOttelut(hakuTextField.getText(), 5);
			alustaOtteluLista(ottelut);
			alustaLista(listaPelaajista, new Pelaaja[0]);
			alustaLista(listaMaaleista, new Pelaaja[0]);
			alustaLista(listaSyotoista, new Pelaaja[0]);
			alustaLista(listaJaahyt2min, new Pelaaja[0]);
			alustaLista(listaJaahyt5min, new Pelaaja[0]);
			break;
		case 2:
			ottelut = pisteporssi.annaOttelut(hakuTextField.getText(), 1);
			alustaOtteluLista(ottelut);
			alustaLista(listaPelaajista, new Pelaaja[0]);
			alustaLista(listaMaaleista, new Pelaaja[0]);
			alustaLista(listaSyotoista, new Pelaaja[0]);
			alustaLista(listaJaahyt2min, new Pelaaja[0]);
			alustaLista(listaJaahyt5min, new Pelaaja[0]);
			break;
		case 3:
			ottelut = pisteporssi.annaOttelut(hakuTextField.getText(), 2);
			alustaOtteluLista(ottelut);
			alustaLista(listaPelaajista, new Pelaaja[0]);
			alustaLista(listaMaaleista, new Pelaaja[0]);
			alustaLista(listaSyotoista, new Pelaaja[0]);
			alustaLista(listaJaahyt2min, new Pelaaja[0]);
			alustaLista(listaJaahyt5min, new Pelaaja[0]);
			break;
		case 4:
			ottelut = pisteporssi.annaOttelut(hakuTextField.getText(), 3);
			alustaOtteluLista(ottelut);
			alustaLista(listaPelaajista, new Pelaaja[0]);
			alustaLista(listaMaaleista, new Pelaaja[0]);
			alustaLista(listaSyotoista, new Pelaaja[0]);
			alustaLista(listaJaahyt2min, new Pelaaja[0]);
			alustaLista(listaJaahyt5min, new Pelaaja[0]);
			break;
		case 5:
			ottelut = pisteporssi.annaOttelut(hakuTextField.getText(), 4);
			alustaOtteluLista(ottelut);
			alustaLista(listaPelaajista, new Pelaaja[0]);
			alustaLista(listaMaaleista, new Pelaaja[0]);
			alustaLista(listaSyotoista, new Pelaaja[0]);
			alustaLista(listaJaahyt2min, new Pelaaja[0]);
			alustaLista(listaJaahyt5min, new Pelaaja[0]);
			break;
		default:
			break;
		}
	}
	
	
	/**
	 * Lajittelee taulukon alkiot
	 * @param model malli taulukosta joka lajitellaan
	 */
	protected static void lajittele(DefaultTableModel model) {
		boolean siirretty = true;
		while(siirretty) {
			siirretty = false;
			for(int i=0; i<model.getRowCount()-1; i++) {
				if((int) model.getValueAt(i,4) < (int) model.getValueAt(i+1, 4)) {
					model.moveRow(i,i,i+1);
					siirretty = true;
				}
			}
		}
	}
}