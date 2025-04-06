package trabalho_pratico_aeds;

import java.util.Random;


public class Trabalho_Pratico_AEDS {

    public static void countingSortCrescente(int[] Array) {
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

    public static void countingSortDecrescente(int[] arr) {
        // Encontra o maior chave
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // Cria array de contagem
        int[] count = new int[max + 1];

        // Conta a frequência de cada elemento
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }

        // Acumula os valores da direita para a esquerda
        for (int i = count.length - 2; i >= 0; i--) {
            count[i] += count[i + 1];
        }

        // Cria array de saída
        int[] sorted = new int[arr.length];

        // Preenche o array de saída (de trás para frente para manter estabilidade)
        for (int i = arr.length - 1; i >= 0; i--) {
            int num = arr[i];
            sorted[count[num] - 1] = num;
            count[num]--;
        }

        // Copia o resultado para o array original
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sorted[i];
        }
    }

    public static void ordenacao_CountingSort() {
        int[] tamanho_Vetores = {10, 20, 30};

        System.out.print("Gerando vetores de forma crescente usando o couting sort: ");
        for (int i = 0; i < tamanho_Vetores.length; i++) {
            int[] vetores_Preencheer = new int[tamanho_Vetores[i]];
            Random seedFixa = new Random(12345L);

            for (int j = 0; j < vetores_Preencheer.length; j++) {
                vetores_Preencheer[j] = (int) seedFixa.nextLong(100);
            }
            System.out.println("");
            for (int j = 0; j < vetores_Preencheer.length; j++) {
                countingSortCrescente(vetores_Preencheer);
            }
            System.out.print("Vetor de tamanho: " + tamanho_Vetores[i] + " gerado e ordenado crescente!");
        }

        System.out.println("\n");
        System.out.println("Gerando vetores de forma Decrescente usando o couting sort: ");
        for (int i = 0; i < tamanho_Vetores.length; i++) {
            int[] vetores_Preencheer = new int[tamanho_Vetores[i]];
            Random seedFixa = new Random(12345L);
            for (int j = 0; j < vetores_Preencheer.length; j++) {
                vetores_Preencheer[j] = (int) seedFixa.nextLong(100);
            }
            for (int j = 0; j < vetores_Preencheer.length; j++) {
                countingSortDecrescente(vetores_Preencheer);
            }
            System.out.print("Vetor de tamanho: " + tamanho_Vetores[i] + " gerado e ordenado Decrescente!\n");
        }
        
        System.out.println("");
        System.out.println("Gerando Vetores aleatorios usando o couting sort:");
        for (int i = 0; i < tamanho_Vetores.length; i++) {
            int[] vetores_Preencheer = new int[tamanho_Vetores[i]];
            Random seedFixa = new Random(12345L);

            for (int j = 0; j < vetores_Preencheer.length; j++) {
                vetores_Preencheer[j] = (int) seedFixa.nextLong(100);
            }
            System.out.println("Vetor de tamanho: " + tamanho_Vetores[i] + " gerado aleatorio!");
        }

    }

    public static void main(String[] args) {
        ordenacao_CountingSort();
       
    }
}
