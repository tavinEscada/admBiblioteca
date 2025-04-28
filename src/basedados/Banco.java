package basedados;

import java.util.Vector;
import modelo.Cliente;
import modelo.Emprestimo;
import modelo.Livro;

public class Banco {
    
    private static Vector<Emprestimo> emprestimosBanco = new Vector<>();
    private static Vector<Livro> livrosBanco = new Vector<>();
    private static Vector<Cliente> clientesBanco = new Vector<>();
    
    public static void iniciaBanco(){
        
        //Realizando INSERTS dos Livros no BD
        livrosBanco.add(new Livro("João e o pé de feijão", "fgjkdagkugdriuh", "Folha de S. Paulo", "Bolsonaro",1970,"111111", 2, true));
        livrosBanco.add(new Livro("O pequeno Príncipe","dkgjfglk","CineLivro","Hebert Richard",2007,"222222", 0, false));
        
        //INSERTS dos clientes
        clientesBanco.add(new Cliente("Josenilda","756456794"));
        clientesBanco.add(new Cliente("Crebeuto", "143673657", "(32) 93454-6747", "Rua Avenida Pinheiros, 59"));
        clientesBanco.add(new Cliente("Jonas", "2345678678", "(11) 34564-4567", "Casa do carai"));
        clientesBanco.add(new Cliente("andre", "234565"));
        
        //INSERT de emprestimos
        emprestimosBanco.add(new Emprestimo(clientesBanco.get(0)));
    
    }
    
    //SELECT * FROM Livro
    public static Vector<Livro> selectAllLivros(){
        return livrosBanco;
    }
    
    //SELECT * FROM Cliente
    public static Vector<Cliente> selectAllClientes(){
        return clientesBanco;
    }
    
    //select * from emprestimo
    public static Vector<Emprestimo> sellectAllEprestimos(){
        return emprestimosBanco;
    }
    
    public static Livro retornaLivroCod(String cod){
        Vector<Livro> livroEstoque = selectAllLivros();
        
        for(Livro li : livroEstoque){
            if(li.getCod().equals(cod)){
                return li;
            }
        }
        return null;
    }
    
    public static Livro retornaLivroNome(String consulta){
        
        Vector<Livro> livros = selectAllLivros();
        
        for(Livro l : livros){
            if(l.getNome().equalsIgnoreCase(consulta)){
                return l;
            }
        }
        return null;
    }
    
    
    public static Cliente retornaClienteCpf(String cpf){
        Vector<Cliente> clienteBanco = selectAllClientes();
        
        for(Cliente c : clienteBanco){
            if(c.getCpf().equals(cpf)){
                return c;
            }
        }
        return null;
    }
    
    /*public static Emprestimo retornaEmprestimoCliente(Cliente c){
        
    }*/
    
    
    public static void insertLivro(Livro novoLivro){
        livrosBanco.add(novoLivro);
    }
    
    public static void insertCliente(Cliente novoCliente){
        clientesBanco.add(novoCliente);
    }
    
    public static void insertEmprestimo(Emprestimo novoEmprestimo){
        emprestimosBanco.add(novoEmprestimo);
    }
    
    public static void deletaEmprestimo(String cpf, int num){
        
        Vector<Emprestimo> empsCliente = new Vector<>();
        
        for(int i = 0; i < emprestimosBanco.size(); i++){
            
            if(emprestimosBanco.get(i).getCliente().getCpf().equals(cpf)){
                empsCliente.add(emprestimosBanco.get(i));
            }
            
        }
        
        Emprestimo ex = empsCliente.get(num);
        
        for(int a = 0; a < emprestimosBanco.size(); a++){
            
            if(emprestimosBanco.get(a).equals(ex)){
                emprestimosBanco.remove(a);
            }
            
        }
        
    }
}
