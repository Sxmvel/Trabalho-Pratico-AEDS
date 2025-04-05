package trabalho_pratico_aeds;

import java.util.Random;
import java.util.Scanner;

public class Trabalho_Pratico_AEDS {

    public static void seedFixa() {
        Scanner t = new Scanner(System.in);

        int[] tamanhoDosVetores = {10, 20, 30}; // Este vetor sera responsavel por dizer o tamanho de outros vetores para ordenacao 

        // Primeiro for sera responsavel por percorrer os 3 tamanho de vetores {10,20,30}
        for (int i = 0; i < tamanhoDosVetores.length; i++) {

            int[] vetor_Preencher = new int[tamanhoDosVetores[i]]; // Estou dizendo que o vetor a preencher quado i for = 0 seu tamanho sera 10 e i for = 1 seu tamanho sera 20...
            
            Random numero_Aleatorio = new Random(12345); // 12345 eh minha seed, para que em situacoes de teste os numeros aleatorios se repita.

            // Este for eh responsavel por preencher as posicoes dos array conforme seus tamanhos
            for (int j = 0; j < vetor_Preencher.length; j++) {
                vetor_Preencher[j] = numero_Aleatorio.nextInt(100); // Aqui estou preencher as posicoes do array utilizando a minha Seed 12345. O 100 quer dizer valores de 0 a 99.
                
            }
            System.out.println("");
            for (int j = 0; j < vetor_Preencher.length; j++) {
                System.out.print(vetor_Preencher[j] + " ");
                
            }
        }

    }

    public static void main(String[] args) {
        seedFixa();
    }

}
