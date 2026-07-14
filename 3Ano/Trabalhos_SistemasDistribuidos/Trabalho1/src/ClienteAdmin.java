import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class ClienteAdmin {

    public static void main(String[] args) {
        try {
            // Lê config (admin.host e admin.port)
            Properties p = Config.get();
            String host = p.getProperty("admin.host");
            int port = Integer.parseInt(p.getProperty("admin.port", "6000"));

            try (Socket socket = new Socket(host, port);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                    Scanner sc = new Scanner(System.in)) {

                int opcao = -1;
                do {
                    System.out.println();
                    System.out.println("<===========>");
                    System.out.println("CLIENTE ADMIN");
                    System.out.println("<===========>");
                    System.out.println("1 - Listar Utilizadores");
                    System.out.println("2 - Aprovar Utilizadores");
                    System.out.println("3 - Historico de um utilizador");
                    System.out.println("4 - Listar Recursos");
                    System.out.println("5 - Aprovar Recursos");
                    System.out.println("6 - Gestao de atributos/recursos");
                    System.out.println("7 - Listar Emprestimos Ativos");
                    System.out.println("8 - Registrar Livro, Computador, Sala_Estudo");
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");

                    String opStr = sc.nextLine();
                    if (opStr.isEmpty()) {
                        opcao = -1;
                        continue;
                    }
                    opcao = Integer.parseInt(opStr);

                    switch (opcao) {

                        // 1 - Listar Utilizadores (sub-menu com Aprovados/Nao aprovados inline)
                        case 1: {
                            int sub = -1;
                            do {
                                System.out.println("\n===== LISTAR UTILIZADORES =====");
                                System.out.println("1 - Listar Utilizadores  Aprovados");
                                System.out.println("2 - Listar Utilizadores  Nao Aprovados");
                                System.out.println("0 - Voltar");
                                System.out.print("Opcao: ");
                                String s = sc.nextLine();
                                if (s.isEmpty()) {
                                    sub = -1;
                                    continue;
                                }
                                sub = Integer.parseInt(s);

                                if (sub == 1) {
                                    out.println("LIST_USERS_APROVADOS");
                                    lerRespostas(in);
                                } else if (sub == 2) {
                                    out.println("LIST_USERS_PENDENTES");
                                    lerRespostas(in);
                                } else if (sub == 0) {
                                    break;
                                } else {
                                    System.out.println("Opcao invalida.");
                                }
                            } while (sub != 0);
                            break;
                        }

                        // 2 - Aprovar utilizadores
                        case 2: {
                            System.out.print("ID do utilizador a aprovar: ");
                            String id = sc.nextLine();
                            out.println("APROVAR_USER " + id);
                            lerRespostas(in);
                            break;
                        }

                        // 3 - Historico de emprestimos de um utilizador
                        case 3: {
                            System.out.print("ID do utilizador: ");
                            String id = sc.nextLine();
                            out.println("HISTORICO_USER " + id);
                            lerRespostas(in);
                            break;
                        }

                        // 4 - Listar Recursos (sub-menu com Aprovados/Nao aprovados inline)
                        case 4: {
                            int sub = -1;
                            do {
                                System.out.println("\n===== LISTAR RECURSOS =====");
                                System.out.println("1 - Listar Recursos  Aprovados");
                                System.out.println("2 - Listar Recursos  Nao Aprovados");
                                System.out.println("0 - Voltar");
                                System.out.print("Opcao: ");
                                String s = sc.nextLine();
                                if (s.isEmpty()) {
                                    sub = -1;
                                    continue;
                                }
                                sub = Integer.parseInt(s);

                                if (sub == 1) {
                                    out.println("LIST_RECURSOS_APROVADOS");
                                    lerRespostas(in);
                                } else if (sub == 2) {
                                    out.println("LIST_RECURSOS_NAO_APROVADOS");
                                    lerRespostas(in);
                                } else if (sub == 0) {
                                    break;
                                } else {
                                    System.out.println("Opcao invalida.");
                                }
                            } while (sub != 0);
                            break;
                        }

                        // 5 - Aprovar recursos
                        case 5: {
                            System.out.print("ID do recurso a aprovar: ");
                            String id = sc.nextLine();
                            out.println("APROVAR " + id);
                            lerRespostas(in);
                            break;
                        }

                        // 6 - Gestão de atributos/recursos (sub-menu simples)
                        case 6: {
                            System.out.println("\nGestao de atributos/recursos:");
                            System.out.println("1 - Alterar ESTADO de recurso");
                            System.out.println("2 - Alterar numero de copias (livro)");
                            System.out.println("3 - Alterar numero de UTILIZACOES (computador)");
                            System.out.print("Opcao: ");
                            String opGest = sc.nextLine();

                            if ("1".equals(opGest)) {
                                System.out.print("ID do recurso: ");
                                String id = sc.nextLine();
                                System.out.print(
                                        "Novo estado (DISPONIVEL/EMPRESTADO/INDISPONIVEL/MANUTENCAO/RESERVADA): ");
                                String estado = sc.nextLine().toUpperCase();
                                out.println("SET_ESTADO " + id + " " + estado);
                                lerRespostas(in);

                            } else if ("2".equals(opGest)) {
                                System.out.print("ID do recurso (livro): ");
                                String id = sc.nextLine();
                                System.out.print("Novo numero de copias: ");
                                String n = sc.nextLine();
                                out.println("SET_COPIAS " + id + " " + n);
                                lerRespostas(in);

                            } else if ("3".equals(opGest)) {
                                System.out.print("ID do recurso (computador): ");
                                String id = sc.nextLine();
                                System.out.print("Novo numero de utilizacoes: ");
                                String n = sc.nextLine();
                                out.println("SET_UTILIZACOES " + id + " " + n);
                                lerRespostas(in);

                            } else {
                                System.out.println("Opcao invalida.");
                            }
                            break;
                        }

                        // 7 - Listar empréstimos ativos
                        case 7: {
                            out.println("LIST_EMPRESTIMOS_ATIVOS");
                            lerRespostas(in);
                            break;
                        }

                        // 8 - Registrar Livro, Computador, Sala_Estudo
                        case 8: {
                            System.out.println("\nRegistrar:");
                            System.out.println("1 - Livro");
                            System.out.println("2 - Computador");
                            System.out.println("3 - Sala de Estudo");
                            System.out.print("Opcao: ");
                            String opReg = sc.nextLine();

                            if ("1".equals(opReg)) {
                                System.out.print("Titulo: ");
                                String titulo = sc.nextLine();
                                System.out.print("Autor: ");
                                String autor = sc.nextLine();
                                System.out.print("Numero de copias: ");
                                String num = sc.nextLine();

                                // substituir espaços por '_' para bater no protocolo do servidor
                                String tCmd = titulo.replace(' ', '_');
                                String aCmd = autor.replace(' ', '_');
                                out.println("REGISTAR_LIVRO " + tCmd + " " + aCmd + " " + num);
                                lerRespostas(in);

                            } else if ("2".equals(opReg)) {
                                System.out.print("Modelo: ");
                                String modelo = sc.nextLine();
                                String mCmd = modelo.replace(' ', '_');
                                out.println("REGISTAR_COMPUTADOR " + mCmd);
                                lerRespostas(in);

                            } else if ("3".equals(opReg)) {
                                System.out.print("Nome da sala: ");
                                String nome = sc.nextLine();
                                System.out.print("Capacidade: ");
                                String cap = sc.nextLine();
                                String nCmd = nome.replace(' ', '_');
                                out.println("REGISTAR_SALA_ESTUDO " + nCmd + " " + cap);
                                lerRespostas(in);

                            } else {
                                System.out.println("Opcao invalida.");
                            }
                            break;
                        }
                        // 0 - Sair
                        case 0: {
                            System.out.println("A sair do cliente admin...");
                            out.println("QUIT");
                            break;
                        }

                        default:
                            System.out.println("Opcao invalida.");
                    }

                } while (opcao != 0);

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro no ClienteAdmin.");
        }
    }

    private static void lerRespostas(BufferedReader in) throws java.io.IOException {
        // Lê a primeira linha de resposta do servidor
        String resp = in.readLine();
        // Se não houver resposta, termina o método
        if (resp == null)
            return;
        // Exibe a primeira linha de resposta
        System.out.println(resp);

        // Verifica se há mais linhas disponíveis para leitura
        while (in.ready()) {
            resp = in.readLine();
            if (resp == null || resp.isEmpty())
                break;
            System.out.println(resp);
        }
    }
}