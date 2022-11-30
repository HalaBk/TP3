package tp3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.io.*;

public class VueController {
	@FXML
	private Button btnClickMe;
	@FXML
	private Button btnVef;
	@FXML
	private Label lblResultat;
	@FXML
	private TextArea bill;
	@FXML
	private TextArea idTips;
	@FXML
	private TextArea tipsPers;
	@FXML
	private TextArea nbPersonne;
	@FXML
	private TextArea date;
	@FXML
	private Label message;
	@FXML
	
	private TextArea billPers;
	private void estVide(String bill, String pourcentage, String nombre) throws IndexOutOfBoundsException{
        if (bill == "" || pourcentage == "" || nombre == "") {
            idTips.setText("");
            billPers.setText("");
            message.setText("Champs vide !!");
        }
    }

    private void negatif(int bill, int pourcentage, int nombre) {
        if (bill < 0 || pourcentage < 0 || nombre < 0) {
            idTips.setText("");
            billPers.setText("");
            message.setText("Champs négative !!");
        }
    }

    private void division(int nombre) {
        if (nombre == 0) {
            idTips.setText("");
            billPers.setText("");
            message.setText("Nombre égale à 0");
        }
    }
    private void numerique(int bill, int pourcentage, int nombre) {
    	
    }
    private void date(String date)  throws ParseException{
    	 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
         format.setLenient(false);
         try
         {
             Date d = format.parse(date); 
             System.out.println(date+" est une date valide");
         }
         catch (ParseException e)
         {
             System.out.println(date+" est une date invalide");
         }
         
    }
    
    public void enregistrement(String d, String b, String p, String n) throws IOException {
        ArrayList<String> tab = new ArrayList<>();
        ArrayList<String> tabDate = new ArrayList<>();
        File file = new File("fichier.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        int index = 0;
        boolean ok = false;
        String s = d+";"+b+";"+p+";"+n;

        while((line = br.readLine()) != null) {
            tab.add(line);
            String dateT = line.substring(0,10);
            tabDate.add(dateT);
            if (dateT.equals(d)) {
                tab.set(index, s);
                ok = true;

            }
            index+=1;
        }
        fr.close();
        br.close();

        if (ok) {
            if (file.delete()) {
                FileWriter writer = new FileWriter("fichier.txt", true);
                for(int i=0; i<tab.size(); i++) {
                    writer.write(tab.get(i) + "\n");
                }
                writer.close();
            }
        }else {
            FileWriter writer = new FileWriter("fichier.txt", true);
            writer.write(s);
            writer.write("\n");
            writer.close();
        }
    }
    
    public void clickVef(ActionEvent e) throws ParseException, IOException {
        String d = date.getText();
        date(d);
        ArrayList<String> tab = new ArrayList<>();
        ArrayList<String> tabDate = new ArrayList<>();
        File file = new File("fichier.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        boolean b = false;
        while((line = br.readLine()) != null) {

            tab.add(line.substring(11, line.length()));
            String dateT = line.substring(0,10);
            tabDate.add(dateT);
            if (dateT.equals(d)) {
                String[] words = line.substring(11,line.length()).split(";");
                for (String word : words) {
                    bill.setText(words[0]);
                    idTips.setText(words[1]);
                    nbPersonne.setText(words[2]);
                }
                b=true;
            }
        }
        if (!b) {
            bill.setText("");
            idTips.setText("");
            nbPersonne.setText("");
        }
        fr.close();
        br.close();
    }
    
	public void clickMe(ActionEvent ee) throws ParseException, IOException {
		try {
			String bl=bill.getText();
			String tp=idTips.getText();
			String nb=nbPersonne.getText();
			String dt=date.getText();
			
			estVide(bl,tp,nb);
			date(dt);
	     
			int clcBill =Integer.valueOf(bill.getText()) ;
			int tips =Integer.valueOf(idTips.getText()) ;
			int Nombre_Personne =Integer.valueOf(nbPersonne.getText()) ;
			
			negatif(clcBill,tips,Nombre_Personne);
			division(Nombre_Personne);
			
			double resultat = ((clcBill*tips)/100)/Nombre_Personne;
			double rsltPrs= (clcBill/Nombre_Personne)+resultat;
			tipsPers.setText(Double.toString(resultat));
			billPers.setText(Double.toString(rsltPrs));
            enregistrement(dt,bl,tp,nb);
			
		} catch (IndexOutOfBoundsException e) {
			System.err.println( "Le numéro saisie est invalide :" + e.getMessage());
		} catch (NumberFormatException e) {
			message.setText("Veulliez saisir un nombre");
			System.err.println("Entrée incorrecte : " + e.getMessage());
		}}
}