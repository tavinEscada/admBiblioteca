package modelo;

import basedados.Banco;
import java.util.Scanner;

public class Livro {
    
    private static Scanner entrada = new Scanner(System.in);

    //atributos
    private String nome;
    private String sinopse;
    private String editora;
    private String autor;
    private int anoLanc;
    private String cod;
    private Boolean status;
    private int quant;
    
    
    //construtores
    public Livro(String nome, String autor, Boolean status) {
        this.nome = nome;
        this.autor = autor;
        this.status = status;
        
    }

    public Livro(String nome, String sinopse, String editora, String autor, int anoLanc, String cod, int quant, Boolean status) {
        this.nome = nome;
        this.sinopse = sinopse;
        this.editora = editora;
        this.autor = autor;
        this.anoLanc = anoLanc;
        this.cod = cod;
        this.quant = quant;
        this.status = status;
        
    }

    //encapsulamento
    public String getNome() {
        return nome;
    }

    public String getSinopse() {
        return sinopse;
    }

    public String getEditora() {
        return editora;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnoLanc() {
        return anoLanc;
    }

    public String getCod() {
        return cod;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }
    
    public void decrementaLivro(){
        this.setQuant(this.getQuant() - 1);
        if(this.getQuant() <= 0){
            setStatus(false);
        }
    }

    public static void cadastraLivro() {
        
        System.out.println("----Cadastro de um novo livro----");
        
        System.out.println("Informe titulo do livro:");
        String nomeLivro = entrada.nextLine();
        
        System.out.println("Informe a sinopse do livro:");
        String sinopse = entrada.nextLine();
        
        System.out.println("Informe a editora:");
        String editora = entrada.nextLine();
        
        System.out.println("Informe o nome do autor:");
        String autor = entrada.nextLine();
        
        System.out.println("Informe o ano de lançamento:");
        int ano = entrada.nextInt();
        
        entrada.nextLine();
        
        Livro livroBD;
        String cod;
        do{
            System.out.println("Informe o codigo do livro:");
            cod = entrada.nextLine();
            livroBD = Banco.retornaLivroCod(cod);
            
            if(livroBD != null){
                System.err.println("O codigo ja esta cadastrado em outro livro.");
            }
        }while(livroBD != null);
        
        System.out.println("Informe a quantidade de livros:");
        int quant = entrada.nextInt();
        
        Boolean status = quant > 0;
        
        Livro novoLivro = new Livro(nomeLivro, sinopse, editora, autor, ano, cod, quant, status);
        
        Banco.insertLivro(novoLivro);
        
        System.out.println("Livro cadastrado com sucesso.");
    }


    
    @Override
    public String toString(){
        
        String disponibilidade = this.status ? "Disponível.\n":"Indisponível.\n";
        
        String resultado = "Nome: " + this.nome + "\nSinopse: " + this.sinopse + "\nAutor: " + this.autor +
                "\nEditora " + this.editora + "\nAno de lançamento: " + this.anoLanc + "\nCódigo: " + 
                this.cod + "\nQuant: " + this.quant + "\nStatus: " + disponibilidade;
        
        return resultado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    
}
