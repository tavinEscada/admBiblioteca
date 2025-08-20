package modelo;

import basedados.Banco;
import java.util.Scanner;

public class Cliente {
    private static Scanner entrada = new Scanner(System.in);
    //atributos
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
    
    
    //construtores
    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
    public Cliente(String nome, String cpf, String telefone, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }
    
    
    //encapsulamento
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }


    public static void cadastraCliente(){
        
        System.out.println("---Cadastrando novo cliente---");
        System.out.println("""
                            Digite:
                            1 - Cadastrar apenas por nome e CPF;
                            2 - Cadastrar  por nome, CPF, telefone e endere\u00e7o
                            0 - Cancelar""");
        
        Cliente novoCliente;
        
        int opcao = entrada.nextInt();
        
        entrada.nextLine();
        
        if(opcao == 1){
            System.out.println("Informe o nome do usuário:");
            
            String nome = entrada.nextLine();
        
            System.out.println("Informe o CPF:");
            String cpf = entrada.nextLine(); 
            
            novoCliente = new Cliente(nome, cpf);
            
            Banco.insertCliente(novoCliente);
            System.out.println("Cliente cadastrado com sucesso!!!\n");
        }else{
        
            if(opcao == 2){
                System.out.println("Informe o nome do usuário:");
                String nome = entrada.nextLine();

                System.out.println("Informe o CPF:");
                String cpf = entrada.nextLine();

                System.out.println("Informe o telefone:");
                String telefone = entrada.nextLine();

                System.out.println("Informe o endereço:");
                String endereco = entrada.nextLine();

                novoCliente = new Cliente(nome, cpf, telefone, endereco);

                Banco.insertCliente(novoCliente);
                System.out.println("Cliente cadastrado com sucesso!!!\n");
                
            }else{
                
                System.out.println("Entrada inválida!!!");
            }
        }
    }
    
    
}
