package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
public class ConexaoPostgresDB {








        private static final String URL = "jbdc;postgresql://localhost:3306/mydb";
        private static final String USUARIO= "postgres" ;
        private static final String SENHA = "root";

        public static Connection conectar(){
            Connection conexao = null;

            try{
                conexao = DriverManager.getConnection(URL,USUARIO,SENHA);
                System.out.println("Conexão com o banco de dados estabelecida com sucesso");
            }catch(SQLException error){
                System.err.println("Erro ao conectar ao bd:" + error.getMessage());

            } return conexao;
        }
        public static void fecharConexao(Connection conexao){
            if(conexao != null){
                try{
                    conexao.close();
                    System.out.println("Conexao com bd fechada!");

                }catch (SQLException e){
                    System.err.println("Erro ao fechar a conexão com bd:" + e.getMessage());
                }

            }


            public static void setAluno(String nome, int idade, String telefone){
                String sql = "INSERT INTO alunos (nome, idade, telefone) VALUES (?, ?, ?)";
                Connection conexaoDB = null;
                PreparedStatement stmt = null;
                try {
                    conexaoDB = conectar();
                    if (conexaoDB != null) {
                        stmt = conexaoDB.prepareStatement(sql);
                        stmt.setString(1, nome);
                        stmt.setInt(2, idade);
                        stmt.setString(3, telefone);
                        int linhasAfetadas = stmt.executeUpdate(); // Executa o INSERT
                        if (linhasAfetadas > 0) {
                            System.out.println("Aluno " + nome + " inserido no BD com sucesso!");
                        }
                    }
                } catch (SQLException e) {
                    System.err.println("Erro ao inserir aluno no PostgreSQL: " + e.getMessage());
                } finally {
                    try {
                        if (stmt != null) stmt.close();
                        if (conexaoDB != null) fecharConexao(conexaoDB); // Usa nosso método de fechar conexão
                    } catch (SQLException e) {
                        System.err.println("Erro ao fechar recursos após inserção: " + e.getMessage());
                    }
                }

                public static void getAlunos() {
                    String sql = "SELECT id_aluno, nome, idade, telefone FROM aluno ORDER BY id_aluno";
                    Connection conexaoPostgresDB = null;
                    PreparedStatement stmt = null;
                    ResultSet rs = null; // Objeto para armazenar os resultados da consulta
                    try {
                        conexao = conectar();
                        if (conexao != null) {
                            stmt = conexao.prepareStatement(sql);
                            rs = stmt.executeQuery(); // Executa a consulta SELECT
                            System.out.println("\n-- Alunos Cadastrados no BD ---");
                            boolean encontrouAluno = false;
                            while (rs.next()) { // Loop enquanto houver próximas linhas no resultado
                                encontrouAluno = true;
                                int id = rs.getInt("id_aluno"); // Pega o valor da coluna 'id_aluno'
                                String nome = rs.getString("nome"); // Pega o valor da coluna 'nome'
                                int idade = rs.getInt("idade");
                                String telefone = rs.getString("telefone");
                                System.out.println("ID: " + id + ", Nome: " + nome + ", Idade: " + idade + ", Telefone: " + telefone);
                            }
                            if (!encontrouAluno) {
                                System.out.println("Nenhum aluno encontrado.");
                            }
                            System.out.println("\n----------------------------------\n");
                        }
                    } catch (SQLException e) {
                        System.err.println("Erro ao consultar alunos no DB: " + e.getMessage());
                    } finally {
                        try { // Sempre fechar os recursos! Na ordem inversa de abertura.
                            if (rs != null) rs.close();
                            if (stmt != null) stmt.close();
                            if (conexao != null) fecharConexao(conexao);
                        } catch (SQLException e) {
                            System.err.println("Erro ao fechar recursos após consulta: " + e.getMessage());
                        }
                    }
                }

            }

        }
        public static void main(String[] args) {
            Connection testeConexao = Conexao.ConexaoPostgresDB.conectar();
            if (testeConexao != null) {
                Conexao.ConexaoPostgresDB.fecharConexao(testeConexao);

            }


        }
    }


