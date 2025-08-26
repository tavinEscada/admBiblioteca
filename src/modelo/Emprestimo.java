package modelo;

import basedados.Banco;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Emprestimo {
    
    private Date dataI;
    private Date dataF;
    private String stat;

    private static Scanner entrada = new Scanner(System.in);
    
    
    //associações entre classes
    private ArrayList <Livro> registroLivros;
    private Cliente cliente;
    private final ArrayList <Livro> carrinhoEmprestimo;
    
    
    //construtores
    public Emprestimo(Cliente leitor){
        this.cliente = leitor;
        this.carrinhoEmprestimo = new ArrayList<>();
        
    }
    
    public Emprestimo(Date dataI, Date dataF, String stat, ArrayList<Livro> registroLivros, Cliente cliente, ArrayList<Livro> carrinhoEmprestimo) {
        this.dataI = dataI;
        this.dataF = dataF;
        this.stat = stat;
        this.registroLivros = registroLivros;
        this.cliente = cliente;
        this.carrinhoEmprestimo = new ArrayList<>();
    }


    public void setDataI(Date dataI) {
        this.dataI = dataI;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public void setRegistroLivros(ArrayList<Livro> registroLivros) {
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

    public ArrayList<Livro> getRegistroLivros() {
        return registroLivros;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    public void addLivroCarrinho(Livro livroEstoque){
        this.carrinhoEmprestimo.add(livroEstoque);
    }

    public static void realizaEmprestimo(){
        System.out.println("---Realizando novo empréstimo---");

        Cliente c;
        
        do{
            System.out.println("Informe o CPF do cliente: (Se deseja sair, digite 0)");
            String cpf = entrada.nextLine();
            
            
            if(cpf.equals("0")){
                System.out.println("");
                return;
            }
            
            
            c = Banco.retornaClienteCpf(cpf);
            
            if(c == null){
                System.out.println("Cliente não encontrado.\n");
            }else{
                System.out.println("Cliente encontrado: " + c);
                break;
            }
            
        }while(c == null);
        
        Emprestimo novoEmprestimo = new Emprestimo(c);
        
        String cod;
            
        Livro livroSelec = null;
        for(int cont = 0; cont < 3; cont++){
            
            do{
                
                System.out.println("Informe o código do livro (Se deseja finalizar o empréstimo, digite 0):");
                cod = entrada.nextLine();
                
                
                if(cod.equals("0")){
                    System.out.println("");
                    if(cont == 0){
                        return;
                    }else{
                        break;
                    }
                }
                
                livroSelec = Banco.retornaLivroCod(cod);
                
                if(livroSelec != null){
                    //System.out.println("Livro encontrado.");
                    
                    if(livroSelec.getStatus() == true && livroSelec.getQuant() > 0){
                        livroSelec.decrementaLivro();
                        break;
                    }else{
                        System.out.println("Livro indisponível.\n");
                        cont--;
                    }
                    
                }else{
                    System.out.println("Livro não encontrado.\n");
                }
                
            }while(!cod.equals("0") || livroSelec == null);

            if(cod.equals("0")){
                break;
            }
            
            /*if(status == true){
                novoEmprestimo.addLivroCarrinho(livroSelec);
            
                //livroSelec.setQuant(livroSelec.getQuant()-1);
                
                if(livroSelec.getQuant()<=0){
                    livroSelec.setStatus(false);
                }

                System.out.println(novoEmprestimo);
            }*/
            
            novoEmprestimo.addLivroCarrinho(livroSelec);
            
            System.out.println("Cesto de livros do cliente: \n"+novoEmprestimo);
                
        }
        //Date agora = new Date();
            
        //SimpleDateFormat dd = new SimpleDateFormat();
            
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            
        Calendar calendario = Calendar.getInstance(); 
        calendario.add(Calendar.DAY_OF_MONTH, 14);
        Date diaEntrega = calendario.getTime();
        
        System.out.println("Empréstimo realizado com sucesso!");
        System.out.println("\n**********************\nLivros:\n"+novoEmprestimo);
        System.out.println("Data de entrega: "+formatoData.format(diaEntrega)+"\n**********************\n");
        
        Banco.insertEmprestimo(novoEmprestimo);
        
    }

    public static void realizaDevolucao(){
        System.out.println("---Realizando devolução---");
        
        System.out.println("Informe o CPF do cliente: ");
        
        String consult = entrada.nextLine();
        
        Cliente c = Banco.retornaClienteCpf(consult);
        
        if(c != null){
            System.out.println("Cliente encontrado.");
            
            ArrayList<Emprestimo> a = Banco.sellectAllEprestimos();
            System.out.println("Empréstimos relacionados ao usuário (por livros): ");
            
            int cont = 0;
            for(int i = 0; i < a.size(); i++){
                if(a.get(i).getCliente().equals(c)){
                    System.out.println(i+" - "+a.get(i));
                    cont++;
                }
            }
            if(cont == 0){
                System.out.println("Não há empréstimos relacionados ao usuário.\n");
                return;
            }
            
            Boolean status = false;
            do{
                System.out.println("Informe qual deseja deletar:");
                int numeroEmp = entrada.nextInt();
                numeroEmp--;
                
                if(numeroEmp > 0 || numeroEmp < a.size()){
                    status = true;
                    
                    Banco.deletaEmprestimo(consult, numeroEmp);
                    
                    System.out.println("Empréstimo deletado com sucesso!");
                    
                }else{
                    System.out.println("Entrada inválida.");
                }
                
            }while(status == false);
            
        }else{
            System.out.println("Cliente não encontrado.");
        }
        
        
    }
    
    @Override
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
