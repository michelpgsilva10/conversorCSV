/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversorcsv;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author michelpl
 */
public class TratamentoCSV {

    public TratamentoCSV() {
        try {
            limpezaDados("C:\\Users\\michelpl\\Documents\\Projetos\\SIE\\Redmine 9155\\Empresas Inidôneas.txt",
                    "C:\\Users\\michelpl\\Documents\\Projetos\\SIE\\Redmine 9155\\Empresas Inidôneas - Final.csv");
        } catch (IOException ex) {
            Logger.getLogger(TratamentoCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new TratamentoCSV();
    }

    private void limpezaDados(String arquivoOrigem, String arquivoDestino) throws FileNotFoundException, IOException {
        String linha, resultado, textoCLOB;
        int numColuna, posicao, linhaCont = 2;
        boolean flagCLOB;

        FileInputStream reader = new FileInputStream(arquivoOrigem);
        BufferedReader leitor = new BufferedReader(new InputStreamReader(reader, "ISO-8859-1"));

        FileWriter writer = new FileWriter(arquivoDestino);
        PrintWriter escritor = new PrintWriter(writer);

        leitor.readLine();

        while ((linha = leitor.readLine()) != null) {
            resultado = "";
            textoCLOB = "";
            numColuna = 1;
            flagCLOB = false;

            while ((posicao = linha.indexOf("\t")) != -1) {
                if (numColuna == 1) {
                    resultado += "\"" + ajusteNumDocumento(linha.substring(0, posicao)) + "\";";
                    if (ajusteNumDocumento(linha.substring(0, posicao)).contains("E")) {
                        System.out.println("Linha: " + linhaCont + " " + ajusteNumDocumento(linha.substring(0, posicao)));
                    }
                } else if (numColuna == 3 && posicao > 4000) {
                    flagCLOB = true;
                    resultado += "\"" + linha.substring(0, 4000).replace("\"", "").replace("'", "").replace("\t", "").trim() + "\";";
                    textoCLOB = "\"" + linha.substring(0, posicao).replace("\"", "").replace("'", "").replace("\t", "").trim() + "\"";
                } else if (numColuna == 4 || numColuna == 5) {
                    resultado += "\"" + formatarData(linha.substring(0, posicao)).replace(" ", "") + "\";";
                } else {
                    resultado += "\"" + linha.substring(0, posicao).replace("\"", "").replace("'", "").replace("\t", "").trim() + "\";";
                }
                linha = linha.substring(posicao + 1);
                numColuna++;
            }
            resultado += "\"" + linha + "\";";

            if (flagCLOB) {
                resultado += textoCLOB;
            } else {
                resultado += "\"\"";
            }

            escritor.print(resultado);
            escritor.println();
            linhaCont++;
        }

        escritor.close();
        leitor.close();

    }

    public String formatarData(String data) {
        return data.contains("-") ? "" : data;
    }

    public String ajusteNumDocumento(String numDocumento) {
        String docRetorno;

        numDocumento = numDocumento.replace(".", "").replace("/", "").replace("-", "").replace("'", "").replace(" ", "");
        if (numDocumento.length() < 11) {
            docRetorno = rPadDocumento(numDocumento, 11);
        } else if (numDocumento.length() > 11 && numDocumento.length() < 14) {
            docRetorno = rPadDocumento(numDocumento, 14);
        } else if (numDocumento.length() > 14) { 
            docRetorno = numDocumento.substring(numDocumento.length() - 14);
        } else {
            docRetorno = numDocumento;
        }

        return docRetorno;
    }

    public String rPadDocumento(String numDoc, int tamanhoDoc) {
        String resultado = "";

        for (int i = 0; i < tamanhoDoc - numDoc.length(); i++) {
            resultado += "0";
        }

        resultado += numDoc;

        return resultado;
    }

}
