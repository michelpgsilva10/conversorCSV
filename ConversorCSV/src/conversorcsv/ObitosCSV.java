/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversorcsv;

import java.io.BufferedReader;
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
public class ObitosCSV {
    
    public ObitosCSV() {
        try {
            converteListaObitos("C:\\Users\\michelpl\\Documents\\Projetos\\SIE\\Redmine 9571\\obi201609\\OBI201609.txt",
                    "C:\\Users\\michelpl\\Documents\\Projetos\\SIE\\Redmine 9571\\obi201609\\OBI201609.csv");
        } catch (IOException ex) {
            Logger.getLogger(ObitosCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        new ObitosCSV();
    }
    
    private void converteListaObitos(String caminhoArquivo, String arquivoSaida) throws FileNotFoundException, IOException {
        String linha;        
        
        FileReader reader = new FileReader(caminhoArquivo);
        BufferedReader leitor = new BufferedReader(reader);
        
        FileWriter writer = new FileWriter(arquivoSaida);
        PrintWriter escritor = new PrintWriter(writer);
        
        linha = leitor.readLine();
        linha = "\"9999999\";\"" + linha.substring(0, 6) + "\";\"" + linha.substring(6, 11) + "\";\"" + linha.substring(11, 21) + "\";\"" +
                converteData(linha.substring(21, 29)) + "\";\"" + linha.substring(29, 39) + "\";\"" + linha.substring(39, 115).trim() + "\";\"" +
                linha.substring(115, 147).trim() + "\";\"" + converteData(linha.substring(147, 155)) + "\";\"" + converteData(linha.substring(155, 163)) + "\";\"" +
                linha.substring(163, 174) + "\";\"" + linha.substring(174, 185) + "\";\"" + linha.substring(185, 186) + "\";\"" +
                linha.substring(186).trim() + "\"";
        
        escritor.print(linha);
        escritor.println();
        
        while (linha != null) {
            linha = leitor.readLine();
            if (linha != null) {
                linha = "\"9999999\";\"" + linha.substring(0, 6) + "\";\"" + linha.substring(6, 11) + "\";\"" + linha.substring(11, 21) + "\";\"" +
                converteData(linha.substring(21, 29)) + "\";\"" + linha.substring(29, 39) + "\";\"" + linha.substring(39, 115).trim() + "\";\"" +
                linha.substring(115, 147).trim() + "\";\"" + converteData(linha.substring(147, 155)) + "\";\"" + converteData(linha.substring(155, 163)) + "\";\"" +
                linha.substring(163, 174) + "\";\"" + linha.substring(174, 185) + "\";\"" + linha.substring(185, 186) + "\";\"" +
                linha.substring(186).trim() + "\"";
        
                escritor.print(linha);
                escritor.println();
            }
        }
         
        escritor.close();
        leitor.close();
    }
    
    public String converteData(String data) {
        String dataRetorno;
        
        dataRetorno = data.substring(6, 8) + "/" + data.substring(4, 6) + "/" + data.substring(0, 4);
        
        return dataRetorno;
    }
    
}
