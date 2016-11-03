/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversorcsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author michelpl
 */
public class ConversorCSV {

    public ConversorCSV() {
        try {
            converterArquivo("C:\\Users\\michelpl\\Documents\\Projetos\\SIE\\Redmine 10796\\TITULARES.txt", 
                    "C:\\Users\\michelpl\\Documents\\Projetos\\SIE\\Redmine 10796\\TITULARES.csv");
        } catch (IOException ex) {
            Logger.getLogger(ConversorCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new ConversorCSV();
    }
    
    private void converterArquivo(String caminhoArquivo, String arquivoExportado) throws FileNotFoundException, IOException {
        String linha;
        
        FileReader reader = new FileReader(caminhoArquivo);
        BufferedReader leitor = new BufferedReader(reader);
        
        FileWriter writer = new FileWriter(arquivoExportado);
        PrintWriter escritor = new PrintWriter(writer);
        
        linha = leitor.readLine();        
        linha = "\"" + linha.replace("\t", "\";\"") + "\"";
        escritor.print(linha);    
        escritor.println();
        
        while (linha != null) {
            linha = leitor.readLine();
            if (linha != null) {
                linha = "\"" + linha.replace("\t", "\";\"") + "\"";
                escritor.print(linha);
                escritor.println();
            }
        }
        
        reader.close();
        writer.close();
    }
    
}
