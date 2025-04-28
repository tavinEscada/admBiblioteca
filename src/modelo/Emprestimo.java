package modelo;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

public class Emprestimo {
    
    private Date dataI;
    private Date dataF;
    private String stat;
    
    
    //associações entre classes
    private Vector <Livro> registroLivros;
    private Cliente cliente;
    private Vector <Livro> carrinhoEmprestimo;
    
    
    //construtores
    public Emprestimo(Cliente leitor){
        this.cliente = leitor;
        this.carrinhoEmprestimo = new Vector<>();
        
    }
    
    public Emprestimo(Date dataI, Date dataF, String stat, Vector<Livro> registroLivros, Cliente cliente, Vector<Livro> carrinhoEmprestimo) {
        this.dataI = dataI;
        this.dataF = dataF;
        this.stat = stat;
        this.registroLivros = registroLivros;
        this.cliente = cliente;
        this.carrinhoEmprestimo = new Vector<>();
    }


    public void setDataI(Date dataI) {
        this.dataI = dataI;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public void setRegistroLivros(Vector<Livro> registroLivros) {
        this.registroLivros = registroLivros;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    //encapsulamento
    public Date getDataI() {
        return dataI;
    }

    public Date getDataF() {
        return dataF;
    }

    public String getStat() {
        return stat;
    }

    public Vector<Livro> getRegistroLivros() {
        return registroLivros;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    public void addLivroCarrinho(Livro livroEstoque){
        this.carrinhoEmprestimo.add(livroEstoque);
    }
    
    
    
    public String toString(){
        String retorno = "";
        
        for(int i = 0; i < this.carrinhoEmprestimo.size(); i++){
            Livro livroTemp = this.carrinhoEmprestimo.get(i);
            
            String infLivro = livroTemp.getNome() + ";";
            
            
            
            retorno = retorno + infLivro + "\n";
        }
        return retorno;
    }
    
}
