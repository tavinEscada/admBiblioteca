package projetodabiblioteca;

import basedados.Banco;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import modelo.Cliente;
import modelo.Emprestimo;
import modelo.Livro;

public class ProjetoBiblioteca {

    private static final Scanner entrada = new Scanner(System.in);

    public static void consultaLivro() {
        String consulta;

        System.out.println("Informe o titulo do livro: ");
        consulta = entrada.nextLine();

        Livro lConsulta = Banco.retornaLivroNome(consulta);

        if (lConsulta == null) {
            System.out.println("Livro nao encontrado.\n");
        } else {
            System.out.println("\nLivro encontrado:");
            System.out.println(lConsulta);
        }
    }

    public static void cadastraCliente() {
        System.out.println("---Cadastrando novo cliente---");
        System.out.println("""
                            Digite:
                            1 - Cadastrar apenas por nome e CPF;
                            2 - Cadastrar  por nome, CPF, telefone e endere\u00e7o
                            0 - Cancelar""");

        Cliente novoCliente;

        int opcao = entrada.nextInt();

        entrada.nextLine();

        if (opcao == 1) {
            System.out.println("Informe o nome do usuário:");

            String nome = entrada.nextLine();

            System.out.println("Informe o CPF:");
            String cpf = entrada.nextLine();

            novoCliente = new Cliente(nome, cpf);

            Banco.insertCliente(novoCliente);
            System.out.println("Cliente cadastrado com sucesso!!!\n");
        } else {

            if (opcao == 2) {
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

            } else {
                System.out.println("Entrada inválida!!!");
            }
        }

    }

    public static void realizaEmprestimo() {
        System.out.println("---Realizando novo empréstimo---");

        Cliente c;

        do {
            System.out.println("Informe o CPF do cliente: (Se deseja sair, digite 0)");
            String cpf = entrada.nextLine();

            if (cpf.equals("0")) {
                System.out.println("");
                return;
            }

            c = Banco.retornaClienteCpf(cpf);

            if (c == null) {
                System.out.println("Cliente não encontrado.\n");
            } else {
                System.out.println("Cliente encontrado: " + c);
                break;
            }

        } while (c == null);

        Emprestimo novoEmprestimo = new Emprestimo(c);

        String cod;

        Livro livroSelec = null;
        for (int cont = 0; cont < 3; cont++) {

            do {

                System.out.println("Informe o código do livro (Se deseja finalizar o empréstimo, digite 0):");
                cod = entrada.nextLine();

                if (cod.equals("0")) {
                    System.out.println("");
                    if (cont == 0) {
                        return;
                    } else {
                        break;
                    }
                }

                livroSelec = Banco.retornaLivroCod(cod);

                if (livroSelec != null) {
                    //System.out.println("Livro encontrado.");

                    if (livroSelec.getStatus() == true && livroSelec.getQuant() > 0) {
                        livroSelec.decrementaLivro();
                        break;
                    } else {
                        System.out.println("Livro indisponível.\n");
                        cont--;
                    }

                } else {
                    System.out.println("Livro não encontrado.\n");
                }

            } while (!cod.equals("0") || livroSelec == null);

            if (cod.equals("0")) {
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

            System.out.println("Cesto de livros do cliente: \n" + novoEmprestimo);

        }
        //Date agora = new Date();

        //SimpleDateFormat dd = new SimpleDateFormat();
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

        Calendar calendario = Calendar.getInstance();
        calendario.add(Calendar.DAY_OF_MONTH, 14);
        Date diaEntrega = calendario.getTime();

        System.out.println("Empréstimo realizado com sucesso!");
        System.out.println("\n**********************\nLivros:\n" + novoEmprestimo);
        System.out.println("Data de entrega: " + formatoData.format(diaEntrega) + "\n**********************\n");

        Banco.insertEmprestimo(novoEmprestimo);

    }

    public static void realizaDevolucao() {
        System.out.println("---Realizando devolução---");

        System.out.println("Informe o CPF do cliente: ");

        String consult = entrada.nextLine();

        Cliente c = Banco.retornaClienteCpf(consult);

        if (c != null) {
            System.out.println("Cliente encontrado.");

            ArrayList<Emprestimo> a = Banco.sellectAllEprestimos();
            ArrayList<Emprestimo> emps = new ArrayList<>();
            for (int i = 0; i < a.size(); i++) {
                if (a.get(i).getCliente().equals(c)) {
                    emps.add(a.get(i));
                }
            }
            if (emps.isEmpty()) {
                System.out.println("Não há empréstimos relacionados ao usuário.\n");
                return;
            }

            System.out.println("Empréstimos relacionados ao usuário (por livros): ");

            for(Emprestimo e : emps){
                System.out.println(e);
            }

            Boolean status = false;
            do {
                System.out.println("Informe qual deseja deletar:");
                int numeroEmp = entrada.nextInt();
                numeroEmp--;

                if (numeroEmp > 0 || numeroEmp < a.size()) {
                    status = true;

                    Banco.deletaEmprestimo(consult, numeroEmp);

                    System.out.println("Empréstimo deletado com sucesso!");

                } else {
                    System.out.println("Entrada inválida.");
                }

            } while (status == false);

        } else {
            System.out.println("Cliente não encontrado.");
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
        do {
            System.out.println("Informe o codigo do livro:");
            cod = entrada.nextLine();
            livroBD = Banco.retornaLivroCod(cod);

            if (livroBD != null) {
                System.err.println("O codigo ja esta cadastrado em outro livro.");
            }
        } while (livroBD != null);

        System.out.println("Informe a quantidade de livros:");
        int quant = entrada.nextInt();

        Boolean status = quant > 0;

        Livro novoLivro = new Livro(nomeLivro, sinopse, editora, autor, ano, cod, quant, status);

        Banco.insertLivro(novoLivro);

        System.out.println("Livro cadastrado com sucesso.");
    }

    public static void main(String[] args) {

        //carregando exemplos
        Banco.iniciaBanco();

        int escolha;
        do {
            System.out.println("""
                                ----Menu da biblioteca----
                                Digite:
                                1 - Consultar um livro;
                                2 - Cadastrar um cliente;
                                3 - Efetuar novo emprestimo;
                                4 - Efetuar devolucao de livro;
                                5 - Cadastrar novo livro.
                                0 - Fechar""");

            escolha = entrada.nextInt();

            entrada.nextLine();

            switch (escolha) {
                case 1 -> consultaLivro();
                case 2 -> cadastraCliente();
                case 3 -> realizaEmprestimo();
                case 4 -> realizaDevolucao();
                case 5 -> cadastraLivro();

                case 0 -> {
                    return;
                }

                default -> System.out.println("Entrada invalida.");
                
            }
        } while (escolha != 0);

    }

}
