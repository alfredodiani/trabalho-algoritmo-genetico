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



import java.util.ArrayList;
import java.util.Random;

public class AlgoritmoGenetico {
    int x_min;
    int x_max;
    int num_individuos;
    int num_geracao;
    int tx_crossover;
    int tx_mutacao;
    Individuo melhorIndividuo;
    ArrayList<Individuo> populacao;
    
    /**
     * Construtor do Algoritmo genetico
     * Cria todos individuos dentro do range informado
     */
    public AlgoritmoGenetico(int xmin, int xmax, int indv, int gerac, int crossover, int mutacao){
        int rand;
        this.x_min = xmin;
        this.x_max = xmax;
        this.num_individuos = indv;
        this.num_geracao = gerac;
        this.tx_crossover = crossover;
        this.tx_mutacao = mutacao;
        this.populacao = new ArrayList<>();
        int range = xmax - xmin +1;
        for (int i=0; i<this.num_individuos; i++){
            rand = ((int) (Math.random() * range) + xmin);
            this.populacao.add(new Individuo(rand));
        }
        this.guardaMelhorIndividuo();
    }
    
    /**
     * metodo recebe dois individuos, faz o crossover de acordo com a probabilidade
     * e retorna os filhos do crossover
     * ou retorna os dois individuos iniciais caso não faça o crossover
    */
    public ArrayList<Individuo> fazCrossover(Individuo individuo1, Individuo individuo2){
        int ptCorte = 3;
        char[] aux1 = new char[5];
        char[] aux2 = new char[5];
        ArrayList<Individuo> retorno = new ArrayList<>();
        Individuo filho1;
        Individuo filho2;
        Random random = new Random();
        int rand = random.nextInt(100);
        if (rand < this.tx_crossover){
            //faz crossover
            for(int i=0; i<5;i++){
                if(i<ptCorte){
                    aux1[i] = individuo1.cromossomos[i];
                    aux2[i] = individuo2.cromossomos[i];
                }else{
                    aux1[i] = individuo2.cromossomos[i];
                    aux2[i] = individuo1.cromossomos[i];
                }
            }
            filho1 = new Individuo(aux1);
            filho2 = new Individuo(aux2);
            filho1.ajustaValor();
            filho2.ajustaValor();
            retorno.add(filho1);
            retorno.add(filho2);
        }else{
            //retorna os mesmos individuos
            retorno.add(individuo1);
            retorno.add(individuo2);
        }
        return retorno;
    }
    
    /**
     * faz torneio entre dois individuos distintos dentro da populacao
     * @return retorna individuo com maior pontuacao
     */
    public Individuo torneio(){
        Individuo individuo1;
        Individuo individuo2;
        int rand1;
        int rand2;
        rand1 = ((int) (Math.random() * this.num_individuos) );
        individuo1 = new Individuo(this.populacao.get(rand1).valor);
        do{
        rand2 = ((int) (Math.random() * this.num_individuos) );
        }while (rand2 == rand1);
        individuo2 = new Individuo(this.populacao.get(rand2).valor);
        if (individuo1.pontuacao > individuo2.pontuacao){
            return individuo1;
        }else{
            return individuo2;
        }
    }
    
    /**
     * metodo criar nova geracao
     * cria nova geracao e sobrescreve a geracao atual
     */
    public void novaGeracao(){
        ArrayList<Individuo> novaGeracao = new ArrayList<>();
        ArrayList<Individuo> aux = new ArrayList<>();
        Individuo pai1;
        Individuo pai2;
        Individuo novoIndividuo;
        while(novaGeracao.size() < this.num_individuos){
            aux.clear();
            pai1 = this.torneio();                      //seleciona primeiro pai
            pai2 = this.torneio();                      //seleciona segundo pai
            aux = this.fazCrossover(pai1, pai2);        //crossover com os dois pais
            for (Individuo it : aux){
                novoIndividuo = new Individuo(it.valor);
                novoIndividuo.aplicaMutacao(this.tx_mutacao);   //Aplica mutacao
                novaGeracao.add(novoIndividuo);
            }
        }
        this.populacao = novaGeracao;
        this.guardaMelhorIndividuo(); 
    }
    
    /**
     * guarda melhor individuo da geracao caso seja melhor que os anteriores
     */
    private void guardaMelhorIndividuo(){
        if(this.melhorIndividuo==null){
            this.melhorIndividuo = new Individuo(this.populacao.get(0).valor);
        }else{
            for(Individuo it: this.populacao){
                if (it.valor < this.melhorIndividuo.valor){
                    melhorIndividuo = new Individuo(it.valor);
                }
            }
        }
    }
    
    /**
     * imprime a geracao atual
     */
    public void imprimeGeracao(){
        for(Individuo it: this.populacao){
            it.imprimeIndividuo();
        }
    }
    
    /**
     * imprime melhor individuo
     */
    public void imprimeMelhorIndividuo(){
        this.melhorIndividuo.imprimeIndividuo();
    }
    
}
