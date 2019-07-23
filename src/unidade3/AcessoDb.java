package unidade3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.DatabaseMetaData;



public class AcessoDb {

	static String url="jdbc:mysql://localhost/curso_java";
	static String usuario ="root";
	static String senha="";
	static Connection conexao;
	
	
	public static void conectar() throws SQLException {
		conexao = DriverManager.getConnection("jdbc:mysql://localhost/curso_java?useTimezone=true&serverTimezone=UTC", "root", "");
		conexao.setAutoCommit(false);
	}
	
	public static void consultarCliente() throws SQLException {
		String consulta = "SELECT * FROM Cliente";
		Statement statement = conexao.createStatement();
		ResultSet rs = statement.executeQuery(consulta);
		while(rs.next()) {
			JOptionPane.showMessageDialog(null, "Cpf:"+rs.getInt(1)+"\n"
					+ "Nome: "+rs.getString(2)+ "\nEmail: "+rs.getString(3));
		}
	}
	
	public static void mostrarMetaInfoBD()throws SQLException 
	{
		DatabaseMetaData meta = (DatabaseMetaData) conexao.getMetaData();
		String fabricanteBD = meta.getDatabaseProductName();
		String versaoBD = meta.getDatabaseProductVersion();
		JOptionPane.showMessageDialog(null, fabricanteBD+"<==>"+versaoBD);
	}

public static void main(String[] args) {
	try{
		conectar();
		mostrarMetaInfoBD();
		consultarCliente();
		conexao.close();

	}catch(SQLException e){
		e.printStackTrace();
	}
}
}
