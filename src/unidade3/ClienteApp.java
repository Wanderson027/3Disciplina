package unidade3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JOptionPane;
//Head----------------------------------INICIO-------------------------------------------------------------------


public class ClienteApp {
	
	static String url="jdbc:mysql://localhost/curso_java";
	static String usuario ="root";
	static String senha="";
	static Connection conexao;
	
//Conectar-----------------------------------------------------------------------------------------------------	
	public static void conectar() throws SQLException {
		conexao = DriverManager.getConnection("jdbc:mysql://localhost/curso_java?useTimezone=true&serverTimezone=UTC", "root", "");
		conexao.setAutoCommit(false);
	}
//Conectar-----------------------------------------------------------------------------------------------------	
	
//Desconectar--------------------------------------------------------------------------------------------------	
	public static void desconectar() throws SQLException {
		
		conexao.close();
	}
	

//Desconectar-------------------------------------------------------------------------------------------------	
	
	//Inserir**************************************************************************	
	public static void inserir(long cpf, String nome, String email) throws SQLException 
	//Inserir**************************************************************************
	{
		String sql =  "insert into cliente value ('"+cpf+"','"+nome+"','"+email+"')";
		Statement statement = conexao.createStatement();
		statement .execute(sql);
		conexao.commit();
		
	}
	
	
//Consultar-----------------------------------------------------------------------------------------------
	public static void consultar(long cpf) throws SQLException {
		
		String sql = "select * from cliente where cpf="+cpf+"";
		Statement statement = conexao.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()) {
			System.out.println("Cpf:"+rs.getInt(1)+
					" Nome:"+rs.getString(2)+ " Email: "+rs.getString(3));
			
		}System.out.println("\n");
	}
//Consultar------------------------------------------------------------------------------------------------
	
//Consultar todos------------------------------------------------------------------------------------------		
	public static void consultarTodos() throws SQLException {
		
		String sql = "select * from cliente";
		Statement statement = conexao.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		int contador = 0;
		while(rs.next()) {
			System.out.println("Cpf:"+rs.getInt(1)+
					" Nome:"+rs.getString(2)+ " Email: "+rs.getString(3));
			contador++;
			System.out.println("================================================");
		}
		
		System.out.println("Número de Clientes Listados>>>"+contador+"\n");
	}
//Consultar todos------------------------------------------------------------------------------------------
	
	
//Alterar---------------------------------------------------------------------------------------------
	public static void alterar(long cpf, String nome, String email) throws SQLException {
	
		String sql = "update cliente set nome ='"+nome+"', email='"+email+"' where  cpf="+cpf;
		Statement statement = conexao .createStatement();
		statement.executeUpdate(sql);
		conexao.commit();
	}
//Alterar---------------------------------------------------------------------------------------------

	
//Excluir---------------------------------------------------------------------------------------------	
	public static void excluir(long cpf) throws SQLException {
		String sql = "delete from cliente where  cpf="+cpf;
		Statement statement = conexao .createStatement();
		statement.executeUpdate(sql);
		conexao.commit();
	}
//Excluir---------------------------------------------------------------------------------------------	
	
//Metodo principal------------------------------------------------------------------------------------
//public static void main(String[] args) throws SQLException {
	//conectar();
	//consultarTodos();
//}
	
	
public static void main(String[] args) throws SQLException {
		
	try {
		conectar();
		Scanner entrada = new Scanner(System.in);
		int opcao = 0;long cpf;
		String nome, email;
		while(opcao != 6) {
			System.out.println("Sistema de Gerenciamento de Clientes");
			System.out.println("====================================");
			System.out.println("Digite [1] para CONSULTAR todos os Clientes");
			System.out.println("Digite [2] para CONSULTAR um  Cliente especifico");
			System.out.println("Digite [3] para CADASTRAR um novo Cliente");
			System.out.println("Digite [4] para ALTERAR um Cliente");
			System.out.println("Digite [5] para EXCLUIR um Cliente");
			System.out.println("Digite [6] para SAIR ");
			System.out.println("=====================================");
			opcao = entrada.nextInt();
			
			switch(opcao) {
				case 1: //Consultar todos
				{
					System.out.println("[1] CONSULTAR todos");
					consultarTodos();
					break;
				}
				case 2: // Consultar um cliente
				{
					System.out.println("[2] CONSULTAR um cliente especifico");
					System.out.println("Favor informar o cpf: ");
					cpf = entrada.nextLong();
					consultar(cpf);
					break;
					
				}
				case 3://Cadastrar um novo  cliente
				{
					System.out.println("[3] CADASTRAR um novo cliente ");
					System.out.println("Favor informar o cpf: ");
					cpf = entrada.nextLong();
					consultar(cpf);
					entrada.nextLine();//esvaziar o Buffre do teclado
					System.out.println("Favor informar o Nome: ");
					nome = entrada.nextLine();
					System.out.println("Favor informar o E-mail: ");
					email = entrada.nextLine();
					inserir(cpf, nome, email);
					break;
				}
				case 4://Alterar Cliente 
				{
					System.out.println("[4] ALterar o cliente ");
					System.out.println("Favor informar o cpf: ");
					cpf = entrada.nextLong();
					consultar(cpf);
					entrada.nextLine();//esvaziar o Buffre do teclado
					System.out.println("Favor informar o Nome: ");
					nome = entrada.nextLine();
					System.out.println("Favor informar o E-mail: ");
					email = entrada.nextLine();
					alterar(cpf, nome, email);
					break;
				}
				case 5://Excluir Um cliente
				{
					System.out.println("[5] Excluir um  cliente ");
					System.out.println("Favor informar o cpf: ");
					cpf = entrada.nextLong();
					consultar(cpf);
					excluir(cpf);
					System.out.println("Cliente excluido(a) Com sucesso! ");
					break;
				}
				case 6://Sair
				{
					System.out.println("encerrando o Sistema!");
					break;
				}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
