package reproductormuski;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.File;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.swing.border.TitledBorder;
import javazoom.jlgui.basicplayer.BasicPlayer;
import org.tritonus.share.sampled.file.TAudioFileFormat;

public class Grafico extends JFrame implements ActionListener {

    ReproductorMuski rm = new ReproductorMuski();
    BasicPlayer Audio = new BasicPlayer();
    String song = null, auxsong, actualsong;
    JPanel pan, pan1, paneln, panelf;
    JButton bpl, bpa, bst, bco, bañ, bet, bsi, ban, bca;
    boolean repa = false, prim = false;
    JFileChooser fc;
    JList canciones;
    DefaultListModel mod = new DefaultListModel();
    Vector<InfoSong> v = new Vector<InfoSong>();
    JScrollPane lis;
    int ite = 1;
    Integer seconds;
    int segundos;
    int bpe;
    InfoSong is = new InfoSong();
    Long micro;
    JLabel fondo;
    JButton mute;
    String titusecu;
    boolean son = true;

    public Grafico() {
        setTitle("Reproductor Muski");
        setIconImage(new ImageIcon(getClass().getResource("/imagen/icono.png")).getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(0, 0, 1160, 568);
        setLocationRelativeTo(null);
        ventana();
        lista();
        v.addElement(new InfoSong("01 Welcome to the Jungle.mp3", "C:\\Users\\André\\Music\\Mi Musica\\01 Welcome to the Jungle.mp3"));
        mod.addElement(v.elementAt(0).getNombre());
        v.addElement(new InfoSong("01 Carly Rae Jepson - Call Me Maybe.mp3", "C:\\Users\\André\\Music\\Mi Musica\\01 Carly Rae Jepson - Call Me Maybe.mp3"));
        mod.addElement(v.elementAt(1).getNombre());
    }

    public void ventana() {
        pan = new JPanel();
        pan.setBackground(Color.black);
        paneln = new JPanel();
        paneln.setBackground(Color.black);
        bpl = new JButton();
        bpl.setBackground(Color.black);
        bpa = new JButton();
        bpa.setBackground(Color.black);
        bco = new JButton();
        bco.setBackground(Color.black);
        bst = new JButton();
        bst.setBackground(Color.black);
        bañ = new JButton();
        bañ.setBackground(Color.black);
        bet = new JButton();
        bet.setBackground(Color.black);
        bsi = new JButton();
        bsi.setBackground(Color.black);
        ban = new JButton();
        ban.setBackground(Color.black);
        bca = new JButton();
        bca.setBackground(Color.black);
        bpl.setPreferredSize(new Dimension(214, 52));
        ban.setPreferredSize(new Dimension(214, 52));
        bsi.setPreferredSize(new Dimension(214, 52));
        bpa.setPreferredSize(new Dimension(214, 52));
        bst.setPreferredSize(new Dimension(214, 52));
        bañ.setPreferredSize(new Dimension(214, 52));
        bca.setPreferredSize(new Dimension(214, 52));
        bco.setPreferredSize(new Dimension(214, 52));
        bet.setPreferredSize(new Dimension(214, 52));
        bpl.setIcon(new ImageIcon("reproducir.jpg"));
        ban.setIcon(new ImageIcon("anterior.jpg"));
        bsi.setIcon(new ImageIcon("siguiente.jpg"));
        bpa.setIcon(new ImageIcon("seguir.jpg"));
        bst.setIcon(new ImageIcon("parar.jpg"));
        bañ.setIcon(new ImageIcon("añadir.jpg"));
        bca.setIcon(new ImageIcon("carpetas.jpg"));
        bet.setIcon(new ImageIcon("elimlista.jpg"));
        bco.setIcon(new ImageIcon("elimmusi.jpg"));
        bpl.addActionListener(this);
        bpa.addActionListener(this);
        bst.addActionListener(this);
        bco.addActionListener(this);
        bañ.addActionListener(this);
        bet.addActionListener(this);
        bsi.addActionListener(this);
        ban.addActionListener(this);
        bca.addActionListener(this);
        mute = new JButton();
        mute.setIcon(new ImageIcon("sound.jpg"));
        mute.setPreferredSize(new Dimension(214, 52));
        mute.addActionListener(this);
        pan.add(bpl);
        pan.add(ban);
        pan.add(bpa);
        pan.add(bsi);
        pan.add(bst);

        paneln.add(bañ);
        paneln.add(bca);
        paneln.add(bco);
        paneln.add(bet);
        paneln.add(mute);

        add(paneln, BorderLayout.NORTH);
        add(pan, BorderLayout.SOUTH);
    }

    public void lista() {
        pan1 = new JPanel();
        pan1.setLayout(new GridLayout());
        pan1.setBackground(Color.black);
        pan1.setBorder(BorderFactory.createTitledBorder(null, "Lista de reproducción", TitledBorder.CENTER, 1, new Font("Lucida Handwriting", 1, 16), Color.yellow));
        pan1.setPreferredSize(new Dimension(235, 0));
        canciones = new JList(v);
        canciones.setModel(mod);
        canciones.setBackground(Color.black);
        canciones.setForeground(Color.WHITE);
        canciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lis = new JScrollPane(canciones);
        pan1.add(lis);
        canciones.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evento) {
                if (canciones.isSelectionEmpty()) {
                } else {
                    auxsong = canciones.getSelectedValue().toString();
                    for (int i = 0; i < v.size(); i++) {
                        if (auxsong.equals(v.elementAt(i).getNombre())) {
                            song = v.elementAt(i).getDireccion();
                        }
                    }

                }
            }
        }
        );
        canciones.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 1) {
                    try {
                        for (int i = 0; i < v.size(); i++) {
                            if (canciones.getSelectedValue().equals(v.elementAt(i).getNombre())) {
                                String titusecu = v.elementAt(i).getNombre();
                                rm.setTitsecu(titusecu);
                            }
                        }

                    } catch (Exception ex) {
                        System.err.println("Ha ocurrido un error aqui " + ex);
                    }
                }
                if (evt.getClickCount() == 2) {
                    try {
                        rm.AbrirFichero(song);
                        rm.Play();
                        rm.setRuta(song);
                        rm.CaratulaCancion(song);
                        bpa.setIcon(new ImageIcon("pausa.jpg"));
                        File file = new File(v.elementAt(canciones.getSelectedIndex()).getDireccion());
                        AudioFileFormat audioFileFormat = AudioSystem.getAudioFileFormat(file);
                        Map<String, Object> properties = ((TAudioFileFormat) audioFileFormat).properties();
                        micro = (Long) properties.get("duration");
                        int maxi = (int) (micro / 1);
                        rm.setMax(maxi);
                        repa = true;
                        prim = false;
                        actualsong = song;
                    } catch (Exception ex) {
                        System.err.println("Ha ocurrido un error " + ex);
                    }

                }
            }
        });
        add(pan1, BorderLayout.EAST);
    }

    private String getFileName(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf("\\") + 1);
        } catch (Exception ex) {
            return "Ha ocurrido un error " + ex;
        }
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception ex) {
            return "Ha ocurrido un error " + ex;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bpl && song != null) {
            try {
                rm.AbrirFichero(song);
                rm.Play();
                rm.setRuta(song);
                rm.CaratulaCancion(song);
                if (song != null) {
                    repa = true;
                }
                prim = false;
                actualsong = song;
            } catch (Exception ex) {
                System.err.println("Ha ocurrido un error " + ex);
            }
        }
        if (e.getSource() == bpa && repa == true) {
            try {
                if (prim == false) {
                    rm.Pausa();
                    bpa.setIcon(new ImageIcon("seguir.jpg"));
                    prim = true;
                } else {
                    rm.Continuar();
                    bpa.setIcon(new ImageIcon("pausa.jpg"));
                    prim = false;
                }

            } catch (Exception ex) {
                System.err.println("Ha ocurrido un error " + ex);
            }
        }
        if (e.getSource() == bst) {
            try {
                rm.Stop();
                bpa.setIcon(new ImageIcon("seguir.jpg"));
            } catch (Exception ex) {
                System.err.println("Ha ocurrido un error " + ex);
            }
        }
        if (e.getSource() == bco && !v.isEmpty()) {
            int lu = canciones.getSelectedIndex();
            mod.removeElementAt(lu);
            v.removeElementAt(lu);
        }
        if (e.getSource() == bet && !v.isEmpty()) {
            if (mod.isEmpty()) {

            } else {
                mod.removeAllElements();
                v.clear();
            }
        }
        if (e.getSource() == bsi) {
            try {
                prim = false;
                bpa.setIcon(new ImageIcon("pausa.jpg"));
                for (int i = 0; i < v.size(); i++) {
                    if (actualsong.equals(v.elementAt(i).getDireccion())) {
                        song = v.elementAt(i + 1).getDireccion();
                        rm.AbrirFichero(song);
                        rm.Play();
                        rm.CaratulaCancion(song);
                        actualsong = song;
                        titusecu = v.elementAt(i + 2).getNombre();
                        rm.setTitsecu(titusecu);
                        break;
                    }

                }

            } catch (Exception ex) {
            }
        }
        if (e.getSource() == ban) {
            try {
                prim = false;
                rm.CaratulaCancion(song);
                for (int i = 0; i < v.size(); i++) {
                    if (actualsong.equals(v.elementAt(i).getDireccion()) && i > 0) {
                        song = v.elementAt(i - 1).getDireccion();
                        rm.AbrirFichero(song);
                        rm.Play();
                        rm.CaratulaCancion(song);
                        actualsong = song;
                        titusecu = v.elementAt(i - 2).getNombre();
                        rm.setTitsecu(titusecu);
                        break;
                    }
                }

            } catch (Exception ex) {

            }
        }
        if (e.getSource() == bañ) {
            fc = new JFileChooser();
            FileNameExtensionFilter fil = new FileNameExtensionFilter("MP3", "mp3", "wav", "ogg");
            fc.setFileFilter(fil);
            fc.setMultiSelectionEnabled(true);
            int r = fc.showOpenDialog(this);
            if (r == JFileChooser.APPROVE_OPTION) {
                File archiv[] = fc.getSelectedFiles();
                for (int i = 0; i < archiv.length; i++) {
                    song = archiv[i].getAbsolutePath();
                    String name = archiv[i].getName();
                    mod.addElement(name);
                    v.add(new InfoSong(name, song));
                }
            }
        }
        if (e.getSource() == bca) {
            JFileChooser filechooser = new JFileChooser();
            filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            filechooser.setMultiSelectionEnabled(true);
            int r = filechooser.showOpenDialog(this);
            if (r == JFileChooser.APPROVE_OPTION) {
                String ruta = filechooser.getSelectedFile().getAbsolutePath();
                File dir = new File(ruta);
                for (String item : dir.list()) {
                    String name = item;
                    File f = new File(name);
                    if (getFileExtension(f).equals("mp3")) {
                        mod.addElement(item);
                        song = ruta + "\\" + name;
                        v.add(new InfoSong(name, song));
                    }
                }
            }
        }
        if (e.getSource() == mute) {
            if (son == true) {
                rm.setMute(0);
                mute.setIcon(new ImageIcon("mute.jpg"));
                son = false;
            } else {
                rm.setMute(1);
                mute.setIcon(new ImageIcon("sound.jpg"));
                son = true;
            }
        }
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public int getBpe() {
        return bpe;
    }

    public void setBpe(int bpe) {
        this.bpe = bpe;
    }

}
