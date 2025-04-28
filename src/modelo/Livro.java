package modelo;

public class Livro {
    
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
        this.quant--;
    }
    
    public String toString(){
        String resultado = "";
        
       
        resultado += "Nome: "+this.nome + "\nSinopse: "+this.sinopse+"\nAutor: "+this.autor+
                "\nEditora "+this.editora+"\nAno de lançamento: "+this.anoLanc+"\nCódigo: "+this.cod+"\nQuant: "+this.quant+"\nStatus: ";
        
        
        String name = this.status ? "Disponível.\n":"Indisponível.\n";
        
        resultado+=name;
        
        return resultado;
    }
    
    
}
