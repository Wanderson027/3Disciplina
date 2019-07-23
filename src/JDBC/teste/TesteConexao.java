package JDBC.teste;

import java.sql.Connection;
import java.sql.SQLException;

import JDBC.ConnectionFactory;

public class TesteConexao {
	public static void main(String[] args) throws SQLException {
		Connection connection = new ConnectionFactory().getConnection();
		System.out.println("Aberta 3Disciplina");
		connection.close();
	}
}
