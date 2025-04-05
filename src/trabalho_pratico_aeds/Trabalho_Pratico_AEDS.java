package trabalho_pratico_aeds;

import java.util.*;

public class Trabalho_Pratico_AEDS {

    public static int[][] seedFixa() {

        int[] tamanhoDosVetores = {10, 20, 30}; // Este vetor sera responsavel por dizer o tamanho de outros vetores para ordenacao 
        int[][] arraysGerados = new int[tamanhoDosVetores.length][]; // Primeiro a quantidade de vetores que queremos, no caso 3 de tamanhos diferentes

        // Primeiro for sera responsavel por percorrer os 3 tamanho de vetores {10,20,30}
        for (int i = 0; i < tamanhoDosVetores.length; i++) {

            int[] vetor_Preencher = new int[tamanhoDosVetores[i]]; // Estou dizendo que o vetor a preencher quado i for = 0 seu tamanho sera 10 e i for = 1 seu tamanho sera 20...
            Random numero_Aleatorio = new Random(12345); // 12345 eh minha seed, para que em situacoes de teste os numeros aleatorios se repita.

            // Este for eh responsavel por preencher as posicoes dos array conforme seus tamanhos
            for (int j = 0; j < vetor_Preencher.length; j++) {
                vetor_Preencher[j] = numero_Aleatorio.nextInt(100); // Aqui estou preencher as posicoes do array utilizando a minha Seed 12345. O 100 quer dizer valores de 0 a 99.
            }
            arraysGerados[i] = vetor_Preencher; // Armazena o vetor gerado
        } 
        return arraysGerados;
    }

    public static void main(String[] args) {
        int[][] meusVetores = seedFixa();

        
        for (int i = 0; i < meusVetores.length; i++) {
            for (int j = 0; j < meusVetores[i].length; j++) {
                System.out.print(meusVetores[i][j] + " ");
            }
            System.out.println("\n");
        }
    }
}
