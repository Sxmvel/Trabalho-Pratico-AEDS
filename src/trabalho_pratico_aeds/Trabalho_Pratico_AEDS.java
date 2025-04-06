package trabalho_pratico_aeds;

import java.util.*;

public class Trabalho_Pratico_AEDS {

    public static int[] seedFixa() {

        int[] tamanhoDosVetores = {10, 20, 30}; // Este vetor sera responsavel por dizer o tamanho de outros vetores para ordenacao 

        // Primeiro for sera responsavel por percorrer os 3 tamanho de vetores {10,20,30}
        for (int i = 0; i < tamanhoDosVetores.length; i++) {

            int[] vetor_Preencher = new int[tamanhoDosVetores[i]]; // Estou dizendo que o vetor a preencher quado i for = 0 seu tamanho sera 10 e i for = 1 seu tamanho sera 20...
            Random numero_Aleatorio = new Random(12345); // 12345 eh minha seed, para que em situacoes de teste os numeros aleatorios se repita.

            // Este for eh responsavel por preencher as posicoes dos array conforme seus tamanhos
            for (int j = 0; j < vetor_Preencher.length; j++) {
                vetor_Preencher[j] = numero_Aleatorio.nextInt(100); // Aqui estou preencher as posicoes do array utilizando a minha Seed 12345. O 100 quer dizer valores de 0 a 99.
            }
            return vetor_Preencher;
        }
        return null;
    }

    public static void countingSort(int[] Array) {
        int maior_Chave = -1;

        for (int i = 1; i < Array.length; i++) {
            if (Array[i] > maior_Chave) {
                maior_Chave = Array[i];
            }
        }

        // Cria array de contagem
        int[] contagem_De_Chaves = new int[maior_Chave + 1];

        // Conta a frequência de cada elemento
        for (int i = 0; i < Array.length; i++) {
            int num = Array[i];
            contagem_De_Chaves[num]++;
        }

        // Acumula os valores
        for (int i = 1; i < contagem_De_Chaves.length; i++) {
            contagem_De_Chaves[i] += contagem_De_Chaves[i - 1];
        }

        // Cria array de saída
        int[] Array_Ordenado = new int[Array.length];

        // Preenche o array de saída (de trás para frente) - Para manter a estabilidade do algortimo
        for (int i = Array.length - 1; i >= 0; i--) {
            int num = Array[i];
            Array_Ordenado[contagem_De_Chaves[num] - 1] = num;
            contagem_De_Chaves[num]--;
        }

        // Copia o resultado para o array original
        for (int i = 0; i < Array.length; i++) {
            Array[i] = Array_Ordenado[i];
        }
    }

    public static void main(String[] args) {
        int[] meusVetores = seedFixa();

        for (int i = 0; i < meusVetores.length; i++) {
            System.out.print(meusVetores[i] + " ");
        }

        countingSort(meusVetores);
        System.out.println("");

        for (int i = 0; i < meusVetores.length; i++) {
            System.out.print(meusVetores[i] + " ");

        }
    }
}
