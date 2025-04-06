package trabalho_pratico_aeds;

import java.util.*;

public class Trabalho_Pratico_AEDS {

    public static void countingSort(int[] Array) {
        int maior_Chave = 0;

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

    public static void ordenacao_Crescente_CountingSort() {
        int[] tamanho_Vetores = {100, 500, 1000};

        System.out.print("Vetores Desordenados: ");
        for (int i = 0; i < tamanho_Vetores.length; i++) {
            int[] vetores_Preencheer = new int[tamanho_Vetores[i]];
            Random seedFixa = new Random(12345L);

            for (int j = 0; j < vetores_Preencheer.length; j++) {
                vetores_Preencheer[j] = (int) seedFixa.nextLong(100);
            }

            System.out.println("");
            for (int j = 0; j < vetores_Preencheer.length; j++) {
                System.out.print(vetores_Preencheer[j] + " ");
            }
        }
        System.out.println("");
        System.out.print("Vetores Ordenados: ");
        for (int i = 0; i < tamanho_Vetores.length; i++) {
            int[] vetores_Preencheer = new int[tamanho_Vetores[i]];
            Random seedFixa = new Random(12345L);

            for (int j = 0; j < vetores_Preencheer.length; j++) {
                vetores_Preencheer[j] = (int) seedFixa.nextLong(100);
            }
            System.out.println("");
            for (int j = 0; j < vetores_Preencheer.length; j++) {
                countingSort(vetores_Preencheer);
                System.out.print(vetores_Preencheer[j] + " ");
            }
        }
    }

    public static void main(String[] args) {
        ordenacao_Crescente_CountingSort();
    }
}
