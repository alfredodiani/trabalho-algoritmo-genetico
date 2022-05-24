/*
 * Trabalho: Exercício sobre Algoritmos Genéticos
 * 
 * Inteligencia Artificial - GCC128
 * 
 * Sistemas de Informação
 * Universidade Federal de Lavras
 * 
 * 
 * Autor: Alfredo
 * 
 */

/**
 * ENUNCIADO:
 * 
 * Encontrar de x para o qual a função f(x) = x*x - 3x + 4, encontre o valor máximo .
 * 
 * -Assumir que x [-10, +10]
 * -Codificar X como vetor binário
 * -Criar uma população inicial com 4  indivíduos  (pode usar mais para testar até 30) 
 * -Aplicar Mutação com taxa de 1%
 * -Aplicar Crossover com taxa de 70%
 * -Usar seleção por torneio.
 * -Usar 5  gerações. ( pode usar mais para testar até 20) 
 * 
 * Obs. Na implmentação utilizar os parametros ( num. de  indivíduos, num de geração, taxas de crossover e mutação)  como variáveis para ajdar no teste pra avaliar a qualidade dos resultados.
 * 
*/


public class Main {

    public static void main(String[] args) {
        
        int x_min = -10;
        int x_max = 10;
        int num_individuos = 4;
        int num_geracao = 5;
        int tx_crossover = 70;
        int tx_mutacao = 1;

        //instancia o Algotitmo, cria randomicamente os individuos e já calcula o valor da pontuação de cada um
        AlgoritmoGenetico ag = new AlgoritmoGenetico(x_min, x_max, num_individuos, num_geracao, tx_crossover, tx_mutacao);
        
        System.out.println("----------------------");
        System.out.println("Geracao Zero (inicial)");
        System.out.println("----------------------");
        
        ag.imprimeGeracao();
        
        //cria o numero informado de geracoes
        for (int i=0; i < num_geracao; i++){
            System.out.println("----------------------");
            System.out.println("Geracao " + (i+1));
            System.out.println("----------------------");
            ag.novaGeracao();
            ag.imprimeGeracao();
        }
        
        //mostra o melhor individuo
        System.out.println("**********************");
        System.out.println("Melhor Individuo");
        System.out.println("**********************");   
        ag.imprimeMelhorIndividuo();   
    }
    
}
