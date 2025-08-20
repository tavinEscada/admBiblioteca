package projetodabiblioteca;

import basedados.Banco;
import java.util.Scanner;
import modelo.Cliente;
import modelo.Emprestimo;
import modelo.Livro;

public class ProjetoBiblioteca {
    
    private static final Scanner entrada = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        //carregando exemplos
        Banco.iniciaBanco();
        
        
        int escolha;
        do{
            System.out.println("""
                                ----Menu da biblioteca----
                                Digite:
                                1 - Consultar um livro;
                                2 - Cadastrar um cliente;
                                3 - Efetuar novo empr\u00e9stimo;
                                4 - Efetuar devolu\u00e7\u00e3o de livro;
                                5 - Cadastrar novo livro.
                                0 - Fechar""");

            escolha = entrada.nextInt();

            entrada.nextLine();

            switch(escolha){
                case 1 -> Banco.consultaLivro();
                
                case 2 -> Cliente.cadastraCliente();
                    
                case 3 -> Emprestimo.realizaEmprestimo();
                    
                case 4 -> Emprestimo.realizaDevolucao();
                    
                case 5 -> Livro.cadastraLivro();
                
                case 0 -> {
                    return;
                }
                    
                default -> {
                    System.out.println("Entrada invalida.");
                }

            }
        }while(escolha != 0);
        
    }

}
