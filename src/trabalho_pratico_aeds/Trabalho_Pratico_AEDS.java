package trabalho_pratico_aeds;

import java.util.*;

public class Trabalho_Pratico_AEDS {

    public class CountingSort {

        public static void countingSortCrescente(int[] Array) {
            int tamanho = Array.length;

            // 1. Encontra o maior valor para saber o tamanho do vetor de contagem
            int maiorValor = Array[0];
            for (int i = 1; i < tamanho; i++) {
                if (Array[i] > maiorValor) {
                    maiorValor = Array[i];
                }
            }

            // 2. Cria o array de contagem (frequência)
            int[] contador = new int[maiorValor + 1]; // Índices vão de 0 até o maior número

            // 3. Conta quantas vezes cada valor aparece
            for (int i = 0; i < tamanho; i++) {
                contador[Array[i]]++;
            }

            // 4. Soma acumulada: transforma contador[i] em posição final do elemento no array ordenado
            for (int i = 1; i < contador.length; i++) {
                contador[i] += contador[i - 1];
            }

            // 5. Cria um array temporário para guardar os elementos ordenados
            int[] resultadoOrdenado = new int[tamanho];

            // 6. Preenche o array ordenado (de trás pra frente para manter estabilidade)
            for (int i = tamanho - 1; i >= 0; i--) {
                int valorAtual = Array[i];
                int posicaoOrdenada = contador[valorAtual] - 1;
                resultadoOrdenado[posicaoOrdenada] = valorAtual;
                contador[valorAtual]--;
            }

            // 7. Copia o resultado de volta para o array original
            for (int i = 0; i < tamanho; i++) {
                Array[i] = resultadoOrdenado[i];
            }
        }

        public static void countingSortDecrescente(int[] Array) {
            int tamanho = Array.length;

            // 1. Encontra o maior valor do array
            int maiorValor = Array[0];
            for (int i = 1; i < tamanho; i++) {
                if (Array[i] > maiorValor) {
                    maiorValor = Array[i];
                }
            }

            // 2. Cria o array de contagem
            int[] contador = new int[maiorValor + 1];

            // 3. Conta quantas vezes cada número aparece
            for (int i = 0; i < tamanho; i++) {
                contador[Array[i]]++;
            }

            // 4. Acumula as posições, MAS do fim para o início (decrescente)
            for (int i = contador.length - 2; i >= 0; i--) {
                contador[i] += contador[i + 1];
            }

            // 5. Cria array para o resultado ordenado
            int[] resultadoOrdenado = new int[tamanho];

            // 6. Coloca os valores no array de forma decrescente (mantendo estabilidade)
            for (int i = tamanho - 1; i >= 0; i--) {
                int valorAtual = Array[i];
                int posicaoOrdenada = contador[valorAtual] - 1;
                resultadoOrdenado[posicaoOrdenada] = valorAtual;
                contador[valorAtual]--;
            }

            // 7. Copia o resultado de volta para o array original
            for (int i = 0; i < tamanho; i++) {
                Array[i] = resultadoOrdenado[i];
            }
        }

    }

    public class RadixSort {

        // Função que retorna o maior número do array
        public static int encontrarMaiorNumero(int[] Array) {
            int maior = Array[0];
            for (int i = 1; i < Array.length; i++) {
                if (Array[i] > maior) {
                    maior = Array[i];
                }
            }
            return maior;
        }

        // Função que faz a ordenação por um dígito específico (usando Counting Sort)
        public static void ordenarPorDigito(int[] Array, int posicaoDecimal) {

            int tamanho = Array.length;
            int[] resultadoOrdenado = new int[tamanho]; // Array temporário para guardar os valores ordenados
            int[] contadorDigitos = new int[10]; // Array para contar os dígitos (0 a 9)

            // 1. Conta a frequência dos dígitos naquela posição decimal (unidade, dezena, centena...)
            for (int i = 0; i < tamanho; i++) {
                int digito = (Array[i] / posicaoDecimal) % 10;
                contadorDigitos[digito]++;
            }

            // 2. Transforma as contagens em posições reais no array de saída
            for (int i = 1; i < 10; i++) {
                contadorDigitos[i] += contadorDigitos[i - 1];
            }

            // 3. Monta o array de saída (ordenação estável: de trás pra frente)
            for (int i = tamanho - 1; i >= 0; i--) {
                int digito = (Array[i] / posicaoDecimal) % 10;
                int posicao = contadorDigitos[digito] - 1;
                resultadoOrdenado[posicao] = Array[i];
                contadorDigitos[digito]--;
            }

            // 4. Copia o array ordenado de volta para o array original
            for (int i = 0; i < tamanho; i++) {
                Array[i] = resultadoOrdenado[i];
            }
        }

        // Função principal que aplica o Radix Sort
        public static void radixSort(int[] Array) {
            // Passo 1: Encontrar o número com mais dígitos
            int maiorNumero = encontrarMaiorNumero(Array);

            // Passo 2: Ordenar cada dígito, começando pela unidade (1), depois dezena (10), centena (100), etc.
            for (int posicaoDecimal = 1; maiorNumero / posicaoDecimal > 0; posicaoDecimal *= 10) {
                ordenarPorDigito(Array, posicaoDecimal);
            }
        }

        // Counting Sort adaptado para ordenar de forma decrescente por um dígito
        public static void ordenarPorDigitoDecrescente(int[] Array, int posicaoDecimal) {
            int tamanho = Array.length;
            int[] resultadoOrdenado = new int[tamanho];
            int[] contadorDigitos = new int[10]; // dígitos de 0 a 9

            // 1. Conta a frequência dos dígitos naquela posição decimal
            for (int i = 0; i < tamanho; i++) {
                int digito = (Array[i] / posicaoDecimal) % 10;
                contadorDigitos[digito]++;
            }

            // 2. Acumula posições de forma decrescente (da direita pra esquerda)
            for (int i = 8; i >= 0; i--) {
                contadorDigitos[i] += contadorDigitos[i + 1];
            }

            // 3. Constrói o array de saída de forma estável
            for (int i = tamanho - 1; i >= 0; i--) {
                int digito = (Array[i] / posicaoDecimal) % 10;
                int posicao = contadorDigitos[digito] - 1;
                resultadoOrdenado[posicao] = Array[i];
                contadorDigitos[digito]--;
            }

            // 4. Copia de volta para o array original
            for (int i = 0; i < tamanho; i++) {
                Array[i] = resultadoOrdenado[i];
            }
        }

        // Método principal do Radix Sort decrescente
        public static void radixSortDecrescente(int[] Array) {
            int maior = encontrarMaiorNumero(Array);

            // Aplica counting sort decrescentFe para cada dígito
            for (int posicaoDecimal = 1; maior / posicaoDecimal > 0; posicaoDecimal *= 10) {
                ordenarPorDigitoDecrescente(Array, posicaoDecimal);
            }
        }

    }

    public class BucketSort {

        // Função principal do Bucket Sort para números inteiros
        public static void bucketSort(int[] Array) {
            int tamanho = Array.length;

            // Encontrar o maior valor no vetor
            int maiorValor = Array[0];
            for (int i = 1; i < tamanho; i++) {
                if (Array[i] > maiorValor) {
                    maiorValor = Array[i];
                }
            }

            // Definir quantos baldes vamos usar
            int quantidadeBaldes = 10;

            // Criar os baldes (listas)
            ArrayList<Integer>[] baldes = new ArrayList[quantidadeBaldes];
            for (int i = 0; i < quantidadeBaldes; i++) {
                baldes[i] = new ArrayList<>();
            }

            // Colocar cada número no balde correspondente
            for (int i = 0; i < tamanho; i++) {
                int posicaoBalde = (Array[i] * quantidadeBaldes) / (maiorValor + 1); // cálculo proporcional
                baldes[posicaoBalde].add(Array[i]);
            }

            // Ordenar cada balde individualmente
            for (int i = 0; i < quantidadeBaldes; i++) {
                Collections.sort(baldes[i]); // pode trocar por Insertion Sort se quiser fazer manual
            }

            // Juntar os baldes no vetor original
            int indice = 0;
            for (int i = 0; i < quantidadeBaldes; i++) {
                for (int j = 0; j < baldes[i].size(); j++) {
                    Array[indice] = baldes[i].get(j);
                    indice++;
                }
            }
        }

        // Ordena em ordem decrescente
        public static void bucketSortDecrescente(int[] numeros) {
            int tamanho = numeros.length;

            int maiorValor = numeros[0];
            for (int i = 1; i < tamanho; i++) {
                if (numeros[i] > maiorValor) {
                    maiorValor = numeros[i];
                }
            }

            int quantidadeBaldes = 10;

            ArrayList<Integer>[] baldes = new ArrayList[quantidadeBaldes];
            for (int i = 0; i < quantidadeBaldes; i++) {
                baldes[i] = new ArrayList<Integer>();
            }

            // Distribui os números nos baldes
            for (int i = 0; i < tamanho; i++) {
                int posicaoBalde = (numeros[i] * quantidadeBaldes) / (maiorValor + 1);
                baldes[posicaoBalde].add(numeros[i]);
            }

            // Ordena cada balde em ordem **decrescente**
            for (int i = 0; i < quantidadeBaldes; i++) {
                Collections.sort(baldes[i], Collections.reverseOrder());
            }

            // Junta os baldes do **último para o primeiro**
            int indice = 0;
            for (int i = quantidadeBaldes - 1; i >= 0; i--) {
                for (int j = 0; j < baldes[i].size(); j++) {
                    numeros[indice] = baldes[i].get(j);
                    indice++;
                }
            }
        }
    }

    public static void ordenacao_CountingSort() {
        long Inicio_tempo_Total = System.nanoTime(); // Inicio da Contagem

        int[] tamanho_Vetores = {100, 500, 1000, 5000, 20000, 50000, 100000, 500000};

        System.out.println("Gerando vetores de forma crescente usando o couting sort: ");
        for (int i = 0; i < tamanho_Vetores.length; i++) {
            int[] vetores_Preencheer = new int[tamanho_Vetores[i]];
            Random seedFixa = new Random(12345L);
            for (int j = 0; j < vetores_Preencheer.length; j++) {
                vetores_Preencheer[j] = (int) seedFixa.nextLong(100);
            }
            long inicio = System.nanoTime(); // Inicio da Contagem
            CountingSort.countingSortCrescente(vetores_Preencheer);
            long fim = System.nanoTime(); // ️ Fim da contagem
            long duracao = (fim - inicio) / 1_000_000; // Em milissegundos

            System.out.print("Vetor de tamanho: " + tamanho_Vetores[i] + " gerado e ordenado crescente! - Tempo: " + duracao + " ms\n");
        }

        System.out.println("\n");
        System.out.println("Gerando vetores de forma Decrescente usando o couting sort: ");
        for (int i = 0; i < tamanho_Vetores.length; i++) {
            int[] vetores_Preencheer = new int[tamanho_Vetores[i]];
            Random seedFixa = new Random(12345L);
            for (int j = 0; j < vetores_Preencheer.length; j++) {
                vetores_Preencheer[j] = (int) seedFixa.nextLong(100);
            }
            long inicio = System.nanoTime(); // Inicio da Contagem
            CountingSort.countingSortDecrescente(vetores_Preencheer);
            long fim = System.nanoTime(); // ️ Fim da contagem
            long duracao = (fim - inicio) / 1_000_000; // Em milissegundos

            System.out.print("Vetor de tamanho: " + tamanho_Vetores[i] + " gerado e ordenado Decrescente! - Tempo: " + duracao + " ms\n");
        }

        System.out.println("");
        System.out.println("Gerando Vetores aleatorios: ");
        for (int i = 0; i < tamanho_Vetores.length; i++) {
            int[] vetores_Preencheer = new int[tamanho_Vetores[i]];
            Random seedFixa = new Random(12345L);
            long inicio = System.nanoTime(); // Inicio da Contagem
            for (int j = 0; j < vetores_Preencheer.length; j++) {
                vetores_Preencheer[j] = (int) seedFixa.nextLong(100);
            }
            long fim = System.nanoTime(); // ️ Fim da contagem
            long duracao = (fim - inicio) / 1_000_000; // Em milissegundos
            System.out.println("Vetor de tamanho: " + tamanho_Vetores[i] + " gerado aleatorio! - Tempo: " + duracao + " ms");
        }

        long fim_Tempo_Total = System.nanoTime();

        long duracao_total = (fim_Tempo_Total - Inicio_tempo_Total) / 1_000_000; // Em milissegundos

        System.out.println("\nTempo Total da duracao do Algoritmo Counting Sort = " + duracao_total + " ms");
    }

    public static void ordenacao_RadixSort() {
        long Inicio_tempo_Total = System.nanoTime(); // Inicio da Contagem

        int[] tamanho_Vetores = {100, 500, 1000, 5000, 20000, 50000, 100000, 500000};

        System.out.println("Gerando vetores de forma crescente usando o Radix Sort: ");
        for (int i = 0; i < tamanho_Vetores.length; i++) {
            int[] vetores_Ordenar = new int[tamanho_Vetores[i]];
            Random seedFixa = new Random(12345L);
            for (int j = 0; j < vetores_Ordenar.length; j++) {
                vetores_Ordenar[j] = seedFixa.nextInt(100);
            }
            long inicio = System.nanoTime();

            RadixSort.radixSort(vetores_Ordenar);

            long fim = System.nanoTime();
            long duracao = (fim - inicio) / 1_000_000; // milissegundos

            System.out.println("Vetor de tamanho: " + tamanho_Vetores[i] + " gerado e ordenado Crescente! - Tempo: " + duracao + " ms");
        }

        System.out.println("\n");
        System.out.println("Gerando vetores de forma Decrescente usando o Radix Sort: ");
        for (int i = 0; i < tamanho_Vetores.length; i++) {
            int[] vetores_Ordenar = new int[tamanho_Vetores[i]];
            Random seedFixa = new Random(12345L);
            for (int j = 0; j < vetores_Ordenar.length; j++) {
                vetores_Ordenar[j] = seedFixa.nextInt(100);
            }
            long inicio = System.nanoTime();
            RadixSort.radixSortDecrescente(vetores_Ordenar);
            long fim = System.nanoTime();
            long duracao = (fim - inicio) / 1_000_000; // milissegundos
            System.out.print("Vetor de tamanho: " + tamanho_Vetores[i] + " gerado e ordenado Decrescente! - Tempo: " + duracao + " ms\n");
        }

        System.out.println("\n");
        System.out.println("Gerando array aleatorios: ");
        for (int i = 0; i < tamanho_Vetores.length; i++) {
            int[] vetores_Ordenar = new int[tamanho_Vetores[i]];
            Random seedFixa = new Random(12345L);
            for (int j = 0; j < vetores_Ordenar.length; j++) {
                vetores_Ordenar[j] = seedFixa.nextInt(100);
            }
            long inicio = System.nanoTime();
            for (int j = 0; j < vetores_Ordenar.length; j++) {
                vetores_Ordenar[j] = (int) seedFixa.nextLong(100);
            }
            long fim = System.nanoTime();
            long duracao = (fim - inicio) / 1_000_000; // milissegundos
            System.out.println("Vetor de tamanho: " + tamanho_Vetores[i] + " gerado aleatorio! - Tempo: " + duracao + " ms");
        }

        long fim_Tempo_Total = System.nanoTime();
        long duracao_total = (fim_Tempo_Total - Inicio_tempo_Total) / 1_000_000; // Em milissegundos
        System.out.println("\nTempo Total da duracao do Algoritmo Radix Sort = " + duracao_total + " ms");
    }

    public static void ordenacao_BucketSort() {
        long Inicio_tempo_Total = System.nanoTime(); // Inicio da Contagem

        int[] tamanho_Vetores = {100, 500, 1000, 5000, 20000, 50000, 100000, 500000};

        System.out.println("Gerando vetores de forma crescente usando o Bucket Sort: ");
        for (int i = 0; i < tamanho_Vetores.length; i++) {
            int[] vetores_Ordenar = new int[tamanho_Vetores[i]];
            Random seedFixa = new Random(12345L);
            for (int j = 0; j < vetores_Ordenar.length; j++) {
                vetores_Ordenar[j] = seedFixa.nextInt(100);
            }
            long inicio = System.nanoTime();
            BucketSort.bucketSort(vetores_Ordenar);
            long fim = System.nanoTime();
            long duracao = (fim - inicio) / 1_000_000; // milissegundos
            System.out.println("Vetor de tamanho: " + tamanho_Vetores[i] + " gerado e ordenado Crescente! - Tempo: " + duracao + " ms");
        }

        System.out.println("\n");
        System.out.println("Gerando vetores de forma Decrescente usando o Bucket Sort: ");
        for (int i = 0; i < tamanho_Vetores.length; i++) {
            int[] vetores_Ordenar = new int[tamanho_Vetores[i]];
            Random seedFixa = new Random(12345L);
            for (int j = 0; j < vetores_Ordenar.length; j++) {
                vetores_Ordenar[j] = seedFixa.nextInt(100);
            }
            long inicio = System.nanoTime();
            BucketSort.bucketSortDecrescente(vetores_Ordenar);
            long fim = System.nanoTime();
            long duracao = (fim - inicio) / 1_000_000; // milissegundos
            System.out.println("Vetor de tamanho: " + tamanho_Vetores[i] + " gerado e ordenado Decrescente! - Tempo: " + duracao + " ms");
        }

        System.out.println("\n");
        System.out.println("Gerando array aleatorios: ");
        for (int i = 0; i < tamanho_Vetores.length; i++) {
            int[] vetores_Ordenar = new int[tamanho_Vetores[i]];
            Random seedFixa = new Random(12345L);
            for (int j = 0; j < vetores_Ordenar.length; j++) {
                vetores_Ordenar[j] = seedFixa.nextInt(100);
            }
            long inicio = System.nanoTime();
            for (int j = 0; j < vetores_Ordenar.length; j++) {
                vetores_Ordenar[j] = (int) seedFixa.nextLong(100);
            }
            long fim = System.nanoTime();
            long duracao = (fim - inicio) / 1_000_000; // milissegundos
            System.out.println("Vetor de tamanho: " + tamanho_Vetores[i] + " gerado aleatorio! - Tempo: " + duracao + " ms");
        }

        long fim_Tempo_Total = System.nanoTime();
        long duracao_total = (fim_Tempo_Total - Inicio_tempo_Total) / 1_000_000; // Em milissegundos
        System.out.println("\nTempo Total da duracao do Algoritmo Bucket Sort = " + duracao_total + " ms");
    }

    public static void main(String[] args) {
        ordenacao_CountingSort();
        ordenacao_RadixSort();
        ordenacao_BucketSort();
    }
}
