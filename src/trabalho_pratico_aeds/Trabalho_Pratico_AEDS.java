package trabalho_pratico_aeds;

import java.util.Random;

public class Trabalho_Pratico_AEDS {

    public static void countingSortCrescente(int[] numeros) {
        int tamanho = numeros.length;

        // 1. Encontra o maior valor para saber o tamanho do vetor de contagem
        int maiorValor = numeros[0];
        for (int i = 1; i < tamanho; i++) {
            if (numeros[i] > maiorValor) {
                maiorValor = numeros[i];
            }
        }

        // 2. Cria o array de contagem (frequência)
        int[] contador = new int[maiorValor + 1]; // Índices vão de 0 até o maior número

        // 3. Conta quantas vezes cada valor aparece
        for (int i = 0; i < tamanho; i++) {
            contador[numeros[i]]++;
        }

        // 4. Soma acumulada: transforma contador[i] em posição final do elemento no array ordenado
        for (int i = 1; i < contador.length; i++) {
            contador[i] += contador[i - 1];
        }

        // 5. Cria um array temporário para guardar os elementos ordenados
        int[] resultadoOrdenado = new int[tamanho];

        // 6. Preenche o array ordenado (de trás pra frente para manter estabilidade)
        for (int i = tamanho - 1; i >= 0; i--) {
            int valorAtual = numeros[i];
            int posicaoOrdenada = contador[valorAtual] - 1;
            resultadoOrdenado[posicaoOrdenada] = valorAtual;
            contador[valorAtual]--;
        }

        // 7. Copia o resultado de volta para o array original
        for (int i = 0; i < tamanho; i++) {
            numeros[i] = resultadoOrdenado[i];
        }
    }

    public static void countingSortDecrescente(int[] numeros) {
        int tamanho = numeros.length;

        // 1. Encontra o maior valor do array
        int maiorValor = numeros[0];
        for (int i = 1; i < tamanho; i++) {
            if (numeros[i] > maiorValor) {
                maiorValor = numeros[i];
            }
        }

        // 2. Cria o array de contagem
        int[] contador = new int[maiorValor + 1];

        // 3. Conta quantas vezes cada número aparece
        for (int i = 0; i < tamanho; i++) {
            contador[numeros[i]]++;
        }

        // 4. Acumula as posições, MAS do fim para o início (decrescente)
        for (int i = contador.length - 2; i >= 0; i--) {
            contador[i] += contador[i + 1];
        }

        // 5. Cria array para o resultado ordenado
        int[] resultadoOrdenado = new int[tamanho];

        // 6. Coloca os valores no array de forma decrescente (mantendo estabilidade)
        for (int i = tamanho - 1; i >= 0; i--) {
            int valorAtual = numeros[i];
            int posicaoOrdenada = contador[valorAtual] - 1;
            resultadoOrdenado[posicaoOrdenada] = valorAtual;
            contador[valorAtual]--;
        }

        // 7. Copia o resultado de volta para o array original
        for (int i = 0; i < tamanho; i++) {
            numeros[i] = resultadoOrdenado[i];
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

    public class RadixSort {

        // Função que retorna o maior número do array
        public static int encontrarMaiorNumero(int[] numeros) {
            int maior = numeros[0];
            for (int i = 1; i < numeros.length; i++) {
                if (numeros[i] > maior) {
                    maior = numeros[i];
                }
            }
            return maior;
        }

        // Função que faz a ordenação por um dígito específico (usando Counting Sort)
        public static void ordenarPorDigito(int[] numeros, int posicaoDecimal) {
            int tamanho = numeros.length;
            int[] resultadoOrdenado = new int[tamanho]; // Array temporário para guardar os valores ordenados
            int[] contadorDigitos = new int[10]; // Array para contar os dígitos (0 a 9)

            // 1. Conta a frequência dos dígitos naquela posição decimal (unidade, dezena, centena...)
            for (int i = 0; i < tamanho; i++) {
                int digito = (numeros[i] / posicaoDecimal) % 10;
                contadorDigitos[digito]++;
            }

            // 2. Transforma as contagens em posições reais no array de saída
            for (int i = 1; i < 10; i++) {
                contadorDigitos[i] += contadorDigitos[i - 1];
            }

            // 3. Monta o array de saída (ordenação estável: de trás pra frente)
            for (int i = tamanho - 1; i >= 0; i--) {
                int digito = (numeros[i] / posicaoDecimal) % 10;
                int posicao = contadorDigitos[digito] - 1;
                resultadoOrdenado[posicao] = numeros[i];
                contadorDigitos[digito]--;
            }

            // 4. Copia o array ordenado de volta para o array original
            for (int i = 0; i < tamanho; i++) {
                numeros[i] = resultadoOrdenado[i];
            }
        }

        // Função principal que aplica o Radix Sort
        public static void radixSort(int[] numeros) {
            // Passo 1: Encontrar o número com mais dígitos
            int maiorNumero = encontrarMaiorNumero(numeros);

            // Passo 2: Ordenar cada dígito, começando pela unidade (1), depois dezena (10), centena (100), etc.
            for (int posicaoDecimal = 1; maiorNumero / posicaoDecimal > 0; posicaoDecimal *= 10) {
                ordenarPorDigito(numeros, posicaoDecimal);
            }
        }

    }

    public static void main(String[] args) {
        ordenacao_CountingSort();

    }
}
