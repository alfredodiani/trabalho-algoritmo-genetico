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
 * os bits são representados como array de chars onde
 * o primeiro bit indica o sinal: 0 para positivo e 1 para negativo
 * os 4 proximos bits sao equivalentes aos bits do numero indicado 
 * na variavel valor
*/

public class Individuo {
    int valor;
    char[] cromossomos;
    int pontuacao;
    
    
    /**
     * construtor: cria os cromossomos a partir de um valor recebido
     * @param v : valor do individuo em decimal
    */
    public Individuo(int v){
        this.valor = v;
        this.cromossomos = new char[5];
        toBinary();
        calcPoints();
    }
    
    /**
     * construtor: calcula o valor a partir dos cromossomos recebidos
     * @param c : array de char simbolizando os cromossomos
    */
    public Individuo(char[] c){
        this.cromossomos = c;
        toInt();
        calcPoints();
    }
    
    /**
     * calcula a pontuacao do individuo     
    */    
    private void calcPoints(){
        this.pontuacao = ((this.valor * this.valor)-(3 * this.valor) + 4);
    }
    
    
    /**
     * transforma o valor em binário e preenche o array cromossomos
    */
    private void toBinary(){
        int resultado = this.valor;
        if (this.valor >= 0){
            this.cromossomos[0] = (char)0+'0';
        }else{
            this.cromossomos[0] = (char)0+'1';
            resultado = resultado * (-1);
        }        
        for(int i = (this.cromossomos.length)-1 ; i>=1 ; i--){
            this.cromossomos[i] = (char)((resultado%2)+'0');
            resultado = resultado/2;
        }
    }
    
    /**
     * tranforma o binário em inteiro e preenche a variavel valor
    */
    private void toInt() {
        int pos;
        int resultado = 0;
        int v;
        for(int i=0; i<((this.cromossomos.length)-1);i++){
            pos = ((this.cromossomos.length)-1)-i;
            v = Character.getNumericValue(cromossomos[pos]);
            resultado = resultado + (v)* ((int)(Math.pow(2, i)));
        }
        if (this.cromossomos[0]=='1'){
            resultado = 0 - resultado;
        }
        this.valor = resultado;
    }
    
    /**
     * aplica mutação em algum cromossomo randomico de acordo com chance de acontecer 
     * @param chance : recebe em porcentagem a chance de acontecer uma mutação (entre 0 e 100)
     * 
    */
    public void aplicaMutacao(int chance){
        int rand = ((int) (Math.random() * 100))+1;
        if (rand <= chance){
            rand = ((int) (Math.random() * 5));
            if (this.cromossomos[rand] == '0'){
                this.cromossomos[rand] = '1';
            }else{
                this.cromossomos[rand] = '0';
            }
        }
        this.toInt();
        this.ajustaValor();
        this.toBinary();
        this.calcPoints();
    }
    
    /**
     * ajusta o valor para os limites caso seja maior ou menor
    */
    public void ajustaValor(){
        if (this.valor>10){
            this.valor = 10;
            this.toBinary();
            this.calcPoints();
        }else if(this.valor<-10){
            this.valor = -10;
            this.toBinary();
            this.calcPoints();
        }
    }
    
    /**
     * imprime os atributos do individuo
     */
    public void imprimeIndividuo(){
        System.out.println("Valor: " + this.valor);
        System.out.println(this.cromossomos);
        System.out.println("Pontuacao: " + this.pontuacao);
        System.out.println();
    }
    
}
