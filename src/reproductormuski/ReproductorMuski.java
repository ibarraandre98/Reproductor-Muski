package reproductormuski;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jlgui.basicplayer.BasicPlayer;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

public class ReproductorMuski implements BasicPlayerListener {

    ReproductorMuski rep;
    static BasicPlayer player = new BasicPlayer();
    static Grafico g = new Grafico();
    InfoSong gra = new InfoSong();
    static JPanel pancentro = new JPanel();
    static JPanel paninfo=new JPanel();
    static JSlider progreso = new JSlider();
    static JSlider volumen=new JSlider(JSlider.VERTICAL);
    static JLabel reproduciendo = new JLabel(),repro=new JLabel();
    static JLabel autor=new JLabel(),artista=new JLabel(),album=new JLabel(),titulo=new JLabel(),año=new JLabel(),genero=new JLabel(),compositor=new JLabel(),publicador=new JLabel();
    static JLabel inicio=new JLabel(),fin=new JLabel();
    static int max;
    static String titsecu;
    static TitledBorder bordeinfo;
    static JLabel imagenmusica=new JLabel();
    static String ruta;
    int duracion;
    int mute=1;
    public ReproductorMuski() {
        player.addBasicPlayerListener(this);
    }

    public static void main(String args[]) {
        try {
            autor.setText("Autor: ");
            autor.setFont(new Font("Lucida Handwriting", 1, 16));
            autor.setForeground(Color.white);
            artista.setText("Artista: ");
            artista.setFont(new Font("Lucida Handwriting", 1, 16));
            artista.setForeground(Color.white);
            album.setText("Album: ");
            album.setFont(new Font("Lucida Handwriting", 1, 16));
            album.setForeground(Color.white);
            titulo.setText("Titulo: ");
            titulo.setFont(new Font("Lucida Handwriting", 1, 16));
            titulo.setForeground(Color.white);
            año.setText("Año: ");
            año.setFont(new Font("Lucida Handwriting", 1, 16));
            año.setForeground(Color.white);
            genero.setText("Genero: ");
            genero.setFont(new Font("Lucida Handwriting", 1, 16));
            genero.setForeground(Color.white);
            compositor.setText("Compositor: ");
            compositor.setFont(new Font("Lucida Handwriting", 1, 16));
            compositor.setForeground(Color.white);
            publicador.setText("Publicador: ");
            publicador.setFont(new Font("Lucida Handwriting", 1, 16));
            publicador.setForeground(Color.white);
            inicio.setForeground(Color.white);
            fin.setForeground(Color.white);
            inicio.setText("00:00:00");
            fin.setText("00:00:00");
            
            inicio.setBounds(30,290,150,200);
            fin.setBounds(545,290,150,200);
            paninfo.setLayout(new GridLayout(8,1));
            paninfo.setBackground(Color.black);
            paninfo.setForeground(Color.white);
            paninfo.add(autor);
            paninfo.add(artista);
            paninfo.add(album);
            paninfo.add(titulo);
            paninfo.add(año);
            paninfo.add(genero);
            paninfo.add(compositor);
            paninfo.add(publicador);
            volumen.setValue(100);
            volumen.setBackground(Color.red);
            volumen.setBounds(500,90,150,200);
            volumen.setOpaque(false);
            volumen.setMinimum(0);
            volumen.setMaximum(100);
            volumen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pancentro.setLayout(null);
            progreso.setValue(0);
            
            progreso.setEnabled(false);
            progreso.setBounds(30,275,550,150);
            progreso.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            progreso.setOpaque(false);
            repro.setText("Reproduciendo...");
            reproduciendo.setBounds(0,220,1000,200);
            repro.setBounds(220,170,500,200);
            repro.setFont(new Font("Lucida Handwriting", 1, 20));
            repro.setForeground(Color.red);
            reproduciendo.setFont(new Font("Lucida Handwriting", 1, 20));
            reproduciendo.setForeground(Color.red);
            imagenmusica.setBounds(150,50,340,200);
            pancentro.add(volumen);
            pancentro.add(imagenmusica);
            pancentro.add(inicio);
            pancentro.add(fin);
            pancentro.add(repro);
            pancentro.add(reproduciendo);
            pancentro.add(progreso);
            progreso.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    player.pause();
                } catch (BasicPlayerException ex) {
                   
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    progreso.setValue(progreso.getValue());
                    player.seek(progreso.getValue());
                    player.resume();
                    
                    
                    Image img = null;        
        try { 
            Mp3File Mp3A = new Mp3File(ruta);
            if (Mp3A.hasId3v2Tag()){
                ID3v2 idTag = Mp3A.getId3v2Tag();
                byte[] datosImagen = idTag.getAlbumImage();               
                img = ImageIO.read(new ByteArrayInputStream(datosImagen));
            }
            else {
                img = ImageIO.read(getClass().getResource("/imagen/musicaaa.jpg"));					        
            }
        } 
        catch (IllegalArgumentException | IOException ex1){System.out.printf("No Expecificas ruta"); }
        catch (NullPointerException e1) {
         
        try {
            img = ImageIO.read(getClass().getResource("/imagen/musicaaa.jpg"));
        } 
        catch (IOException ex) {}
        } 
        catch (UnsupportedTagException | InvalidDataException ex) {}
        int ancho = img.getWidth(null);
        int alto = img.getHeight(null);       
        if (ancho>390){
            ancho=390;
        } 
        if (alto>270){
            alto=270;
        }
        img = img.getScaledInstance(ancho, alto,0);
        imagenmusica.setIcon(new ImageIcon(img));
                    
                    
                    
                } catch (Exception ex) {

                }
            }
        });
            paninfo.setBorder(BorderFactory.createTitledBorder(null, "Informacion cancion", max, max, new Font("Lucida Handwriting", 1, 16), Color.yellow));
            pancentro.setBackground(Color.black);
            paninfo.setPreferredSize(new Dimension(300,0));
            g.add(paninfo,BorderLayout.WEST);
            g.add(pancentro, BorderLayout.CENTER);
            g.setVisible(true);
            
        } catch (Exception ex) {
            System.err.println("Ha ocurrido un error " + ex);
        }

    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        ReproductorMuski.max = max;
    }

    public void Play() throws Exception {
        player.play();
    }

    public void AbrirFichero(String ruta) throws Exception {
        player.open(new File(ruta));
    }

    public void Pausa() throws Exception {
        player.pause();
    }

    public void Continuar() throws Exception {
        player.resume();
    }

    public void Stop() throws Exception {
        player.stop();
    }

    @Override
    public void opened(Object o, Map map) { 
        repro.setText("Reproduciendo");
        System.out.println(map.toString());
        String milisegundos=map.get("duration").toString();
        int horas, mint;
        int segundostotal = Integer.parseInt(milisegundos) / 1000000;
        horas = (int) segundostotal / 3600;
        mint = (int) segundostotal / 60 - horas * 60;
        segundostotal = segundostotal - mint * 60 - horas * 3600;
        duracion = Integer.parseInt(map.get("audio.length.bytes").toString());
        progreso.setMinimum(0);
        progreso.setMaximum(duracion);
        progreso.setEnabled(true);
        inicio.setText("00:00:00");
        if(segundostotal<10&&mint<10){
            fin.setText("0"+String.valueOf(horas)+":0"+String.valueOf(mint)+":0"+String.valueOf(segundostotal));
        }else{
            fin.setText("0"+String.valueOf(horas)+":0"+String.valueOf(mint)+":"+String.valueOf(segundostotal));
        }
        try {
            
            reproduciendo.setText(map.get("title").toString());
            autor.setText("Autor: "+map.get("author").toString());
            artista.setText("Artista: "+map.get("mp3.id3tag.orchestra").toString());
            album.setText("Album: "+map.get("album").toString());
            titulo.setText("Titulo: "+map.get("title").toString());
            año.setText("Año: "+map.get("date").toString());
            genero.setText("Genero: "+map.get("mp3.id3tag.genre").toString());
            compositor.setText("Compositor: "+map.get("mp3.id3tag.composer").toString());
            publicador.setText("Publicador: "+map.get("mp3.id3tag.publisher").toString());
        } catch (Exception ex) {
            reproduciendo.setText(titsecu);
            autor.setText("Autor: Desconocido");
            artista.setText("Artista: Desconocido");
            album.setText("Album: Desconocido");
            titulo.setText("Titulo: "+titsecu);
            año.setText("Año: Desconocido");
            genero.setText("Genero: Desconocido");
            compositor.setText("Compositor: Desconocido");
            publicador.setText("Publicador: Desconocido");
        }

    }

    @Override
    public void progress(int i, long l, byte[] bytes, Map map) {
        int pos = (int) l;
        try {
            if(mute==0){
                player.setGain(mute);
            }else{
            player.setGain((double)volumen.getValue()/100);
            }
        } catch (BasicPlayerException ex) {
            Logger.getLogger(ReproductorMuski.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object bytesTranscurrido = map.get("mp3.position.byte");
        bytesTranscurrido = Integer.parseInt(bytesTranscurrido.toString());
        progreso.setValue((int) bytesTranscurrido);
        String milisegundos=map.get("mp3.position.microseconds").toString();
        int horas, mint;
        int segundostotal = Integer.parseInt(milisegundos) / 1000000;
        horas = (int) segundostotal / 3600;
        mint = (int) segundostotal / 60 - horas * 60;
        segundostotal = segundostotal - mint * 60 - horas * 3600;
        if(segundostotal<10&&mint<10){
            inicio.setText("0"+String.valueOf(horas)+":0"+String.valueOf(mint)+":0"+String.valueOf(segundostotal));
        }else{
            inicio.setText("0"+String.valueOf(horas)+":0"+String.valueOf(mint)+":"+String.valueOf(segundostotal));
        }
    }

    @Override
    public void stateUpdated(BasicPlayerEvent bpe) {
        if (bpe.getPosition() == -1) {
            autor.setText("Autor: ");
            artista.setText("Artista: ");
            album.setText("Album: ");
            titulo.setText("Titulo: ");
            año.setText("Año: ");
            genero.setText("Genero: ");
            compositor.setText("Compositor: ");
            publicador.setText("Publicador: ");
            imagenmusica.setIcon(null);
            repro.setText("Reproduciendo...");
            progreso.setEnabled(false);
            progreso.setValue(0);
            inicio.setText("00:00:00");
            fin.setText("00:00:00");
            reproduciendo.setText("");
            
            
        }
    }

    @Override
    public void setController(BasicController bc) {
    }

    public void CalculoSeg(String milisegundos) {
        float horas, mint;
        float segundostotal = Integer.parseInt(milisegundos) / 1000000;//Almacenamos y pasamos a segundos los microsegundos obtenenidos.

        horas = (int) segundostotal / 3600;		//Conversion de segundostotal a horas.
        mint = (int) segundostotal / 60 - horas * 60;		//Conversion de segundostotal a minutos.
        segundostotal = segundostotal - mint * 60 - horas * 3600;//Conversion de segundostotal a segundos.

        String secundero = (int) horas + ":" + (int) mint + ":" + (int) segundostotal;//Creamos una variable String para almacenar el tiempo total horas, minutos, segundos.              

    }

    public String getTitsecu() {
        return titsecu;
    }

    public void setTitsecu(String titsecu) {
        ReproductorMuski.titsecu = titsecu;
    }

    
    
    public void CaratulaCancion(String rut){
        Image img = null;        
        try { 
            Mp3File Mp3A = new Mp3File(rut);
            if (Mp3A.hasId3v2Tag()){
                ID3v2 idTag = Mp3A.getId3v2Tag();
                byte[] datosImagen = idTag.getAlbumImage();               
                img = ImageIO.read(new ByteArrayInputStream(datosImagen));
            }
            else {
                img = ImageIO.read(getClass().getResource("/imagen/musicaaa.jpg"));					        
            }
        } 
        catch (IllegalArgumentException | IOException ex1){System.out.printf("No Expecificas ruta"); }
        catch (NullPointerException e1) {
         
        try {
            img = ImageIO.read(getClass().getResource("/imagen/musicaaa.jpg"));
        } 
        catch (IOException e) {}
        } 
        catch (UnsupportedTagException | InvalidDataException e) {}
        int ancho = img.getWidth(null);
        int alto = img.getHeight(null);       
        if (ancho>390){
            ancho=390;
        } 
        if (alto>270){
            alto=270;
        }
        img = img.getScaledInstance(ancho, alto,0);
        imagenmusica.setIcon(new ImageIcon(img));
    }

    public int getMute() {
        return mute;
    }

    public void setMute(int mute) {
        this.mute = mute;
    }
    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        ReproductorMuski.ruta = ruta;
    }

    public JPanel getPancentro() {
        return pancentro;
    }

    public void setPancentro(JPanel pancentro) {
        ReproductorMuski.pancentro = pancentro;
    }
    
}
