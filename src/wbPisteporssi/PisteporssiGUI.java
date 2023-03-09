package wbPisteporssi;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import porssi.Pelaaja;
import porssi.Pisteporssi;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.WindowEvent;

import javax.swing.ListSelectionModel;

import java.awt.event.KeyAdapter;
import java.awt.print.PrinterException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Pistepörssin käyttöliittymä
 * Pelaajan muokkaus ei toimi
 * Varmistusviestit puuttuvat
 * @author Esa Hyyryläinen
 * @version 31.7.2015
 *
 */
public class PisteporssiGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String versio = "31.7.2015";
    private static final String tekija = "Esa Hyyryläinen";
    
	private JPanel contentPane;
	private final JPanel panelNappulat = new JPanel();
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnTiedosto = new JMenu("Tiedosto");
	private final JMenu mnMuokkaa = new JMenu("Muokkaa");
	private final JMenu mnApua = new JMenu("Apua");
	private final JMenuItem mntmAvaa = new JMenuItem("Avaa");
	private final JMenuItem mntmTallenna = new JMenuItem("Tallenna");
	private final JMenuItem mntmTulosta = new JMenuItem("Tulosta");
	private final JMenuItem mntmLopeta = new JMenuItem("Lopeta");
	private final JMenuItem mntmLisUusiPelaaja = new JMenuItem("Lis\u00E4\u00E4 uusi pelaaja");
	private final JMenuItem mntmLisUusiOttelu = new JMenuItem("Lis\u00E4\u00E4 ottelu");
	private final JMenuItem mntmPoistaPelaaja = new JMenuItem("Poista pelaaja");
	private final JMenuItem mntmPoistaOttelu = new JMenuItem("Poista ottelu");
	private final JMenuItem mntmApua = new JMenuItem("Apua");
	private final JMenuItem mntmTietoja = new JMenuItem("Tietoja");
	private final JPanel lisaaPoistaPelaaja = new JPanel();
	private final JButton btnLisPelaaja = new JButton("Lis\u00E4\u00E4/muokkaa pelaaja(a)");
	private final JPanel lisaaPoistaOttelu = new JPanel();
	private final JButton btnHaeOttelut = new JButton("Hae, muokkaa ja poista otteluita");
	private final JButton btnLisOttelu = new JButton("Lis\u00E4\u00E4 ottelu");
	private final JPanel panelTaulukko = new JPanel();
	private final JScrollPane scrollPane = new JScrollPane();
	private DefaultTableModel modelTaulukko = new DefaultTableModel();
	private final JTable table = new JTable(modelTaulukko);
	private final JPanel panel = new JPanel();
	private final JSplitPane splitPane = new JSplitPane();
	private final JPanel panel_1 = new JPanel();
	private final JPanel panel_2 = new JPanel();
	private final JLabel lblNewLabel = new JLabel("Haku:");
	private final JTextField hakuTextField = new JTextField();

	private Pisteporssi pisteporssi;
	
	/**
	 * Launch the application.
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
            public void run() {
				try {
					Pisteporssi pisteporssi = new Pisteporssi();
					PisteporssiGUI frame = new PisteporssiGUI(pisteporssi);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param pisteporssi Haluttu pistepörssi
	 */
	public PisteporssiGUI(Pisteporssi pisteporssi) {
		this.pisteporssi = pisteporssi;
		hakuTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				pelaajaHaku();
			}
		});
		hakuTextField.setColumns(10);
		setTitle("Pistep\u00F6rssi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 665, 458);
		
		setJMenuBar(menuBar);
		
		menuBar.add(mnTiedosto);
		mntmAvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		
		mntmAvaa.addActionListener(e->avaa());
		mnTiedosto.add(mntmAvaa);
		mntmTallenna.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		
		mntmTallenna.addActionListener(e->tallenna());
		mnTiedosto.add(mntmTallenna);
		mntmTulosta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		
		mntmTulosta.addActionListener(e->tulosta());
		mnTiedosto.add(mntmTulosta);
		mntmLopeta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		
		mntmLopeta.addActionListener(e->lopeta());
		mnTiedosto.add(mntmLopeta);
		
		menuBar.add(mnMuokkaa);
		
		mntmLisUusiPelaaja.addActionListener(e->lisaaPelaaja());
		mnMuokkaa.add(mntmLisUusiPelaaja);
		
		mntmLisUusiOttelu.addActionListener(e->lisaaOttelu());
		mnMuokkaa.add(mntmLisUusiOttelu);
		
		mntmPoistaPelaaja.addActionListener(e->poistaPelaaja());
		mnMuokkaa.add(mntmPoistaPelaaja);
		
		mntmPoistaOttelu.addActionListener(e->haeOttelu());
		mnMuokkaa.add(mntmPoistaOttelu);
		
		menuBar.add(mnApua);
		mntmApua.addActionListener(e->apua());
		
		mnApua.add(mntmApua);
		mntmTietoja.addActionListener(e->tietoja());
		
		mnApua.add(mntmTietoja);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(panelNappulat, BorderLayout.SOUTH);
		panelNappulat.setLayout(new BorderLayout(0, 0));
		
		panelNappulat.add(lisaaPoistaPelaaja, BorderLayout.NORTH);
		lisaaPoistaPelaaja.setLayout(new BorderLayout(0, 0));
		
		btnLisPelaaja.addActionListener(e->lisaaPelaaja());
		lisaaPoistaPelaaja.add(btnLisPelaaja, BorderLayout.NORTH);
		
		btnLisOttelu.addActionListener(e->lisaaOttelu());
		lisaaPoistaPelaaja.add(btnLisOttelu, BorderLayout.SOUTH);
		
		panelNappulat.add(lisaaPoistaOttelu, BorderLayout.SOUTH);
		lisaaPoistaOttelu.setLayout(new BorderLayout(0, 0));
		
		btnHaeOttelut.addActionListener(e->haeOttelu());
		lisaaPoistaOttelu.add(btnHaeOttelut, BorderLayout.SOUTH);
		
		contentPane.add(panelTaulukko, BorderLayout.CENTER);
		panelTaulukko.setLayout(new BorderLayout(0, 0));
		
		panelTaulukko.add(scrollPane, BorderLayout.CENTER);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Pelaaja", "Ottelut", "Maalit", "Sy\u00F6t\u00F6t", "Pisteet", "J\u00E4\u00E4hyt (min)"
				}
		));
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		
		scrollPane.setViewportView(table);
		
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel.add(splitPane);
		
		splitPane.setLeftComponent(panel_1);
		
		panel_1.add(lblNewLabel);
		
		splitPane.setRightComponent(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		panel_2.add(hakuTextField);
	}
	

	/**
	 * Poistaa valitun pelaajan pörssistä
	 */
	protected void poistaPelaaja() {
		if(table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "Valitse ensin poistettava pelaaja.");
			return;
		}
		Pelaaja poistettavaPelaaja = (Pelaaja) table.getValueAt(table.getSelectedRow(), 0);
		pisteporssi.poistaPelaaja(poistettavaPelaaja.getTunnusNro());
		modelTaulukko.removeRow(table.getSelectedRow());
    }

	
	/**
	 * Näyttää tietoja ohjelmasta
	 */
	protected void tietoja() {
		JOptionPane.showMessageDialog(this, "Tekijä: " + tekija + "\nVersio: " + versio);
    }
	
	
	/**
	 * Näyttää apua
	 */
	protected void apua() {
		JOptionPane.showMessageDialog(this, "Katso toiminta ohjelman suunnitelmasta:\n<HTML><U>https://trac.cc.jyu.fi/projects/ohj2ht/wiki/k2015/suunnitelmat/espeemhy</U></HTML>");
    }

	
	/**
	 * Tallentaa tehdyt muutokset tiedostoon
	 * Toimii, mutta ei kysy käyttäjältä mihin tallennetaan
	 * Voisi ottaa tarvittaessa tulevan poikkeuksen vastaan
	 */
	protected void tallenna() {
	    try {
	        pisteporssi.tallenna();
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(this, "Tallennus epäonnistui");
        }
    }
	
	
	/**
	 * Avaa tallennetun tiedoston
	 * Toimii, mutta ei kysy käyttäjältä mikä tiedosto avataan
	 * Voisi ottaa tarvittaessa tulevan poikkeuksen vastaan
	 */
	protected void avaa() {
		pisteporssi = new Pisteporssi();
	    try {
	        pisteporssi.lueTiedostosta();
        } catch (FileNotFoundException e) {
        	JOptionPane.showMessageDialog(this, "Tiedostoa ei löydy");
        }
	    alustaTaulukko();
	    OttelujenHaku.paivitaTaulukko(pisteporssi, modelTaulukko);
	    OttelujenHaku.lajittele(modelTaulukko);
    }
	
	
	/**
	 * Alustaa taulukon
	 */
	protected void alustaTaulukko() {
		modelTaulukko = (DefaultTableModel) table.getModel();
		int poistettavia = modelTaulukko.getRowCount();
		for(int i=0; i<poistettavia;i++) {
			modelTaulukko.removeRow(0);
		}
		for(int i=0; i<pisteporssi.getPelaajia(); i++) {
			Pelaaja lisattavaPelaaja = pisteporssi.annaPelaaja(i);
			modelTaulukko.addRow(new Object[]{lisattavaPelaaja, "0", "0", "0", "0", "0"});
		}
	}

	
	/**
	 * Näyttää ottelujen haku, muokkaus ja poisto dialogin
	 */
	@SuppressWarnings("unused")
    private void haeOttelu() {
	    new OttelujenHaku(pisteporssi, modelTaulukko);
    }

	
	/**
	 * Näyttää lisää pelaaja dialogin
	 */
	@SuppressWarnings("unused")
    protected void lisaaPelaaja() {
		modelTaulukko = (DefaultTableModel) table.getModel();
		new LisaaPelaaja(pisteporssi, modelTaulukko);
    }
	

	/**
	 *  Näyttää lisää ottelu dialogin
	 */
	@SuppressWarnings("unused")
    protected void lisaaOttelu() {
	    new OttelunLisays(pisteporssi);
    }
	
	
	/**
	 * Lopettaa ohjelman
	 */
	protected void lopeta() {
		processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	
	/**
	 * Etsii pelaajaTaulukosta hakuehdon täyttävän pelaajan/pelaajat
	 */
	protected void pelaajaHaku() {
		alustaTaulukko();
		OttelujenHaku.paivitaTaulukko(pisteporssi, modelTaulukko);
		OttelujenHaku.lajittele(modelTaulukko);
		for(int i=0; i<modelTaulukko.getRowCount();i++) {
			Pelaaja pelaaja = (Pelaaja) modelTaulukko.getValueAt(i, 0);
			if(pelaaja.toString().length()<hakuTextField.getText().length() || !pelaaja.toString().substring(0, hakuTextField.getText().length()).equals(hakuTextField.getText())) {
				modelTaulukko.removeRow(i);
				i--;
			}
		}
	}
	
	
	/**
	 * Tulostaa näkyvissä olevan taulukon
	 */
	protected void tulosta() {
		try {
	        table.print();
        } catch (PrinterException e) {
        	JOptionPane.showMessageDialog(this, "Tulostus ei onnistu???");
        }
	}
}
