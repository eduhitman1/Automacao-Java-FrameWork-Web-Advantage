package com.edsoft.framework.utilities;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;

import com.edsoft.framework.configs.Settings;

public class DatabaseUtil {
	
	/**
	 * abrir conexão, e o tipo de banco de dados 
	 */
	public static Connection Open(String connectionString) {
		try {
			Class.forName(Settings.DriverType).newInstance();
			return DriverManager.getConnection(connectionString);
		} catch (Exception e) {
			System.out.println("Erro data: " + e.getMessage());
		}
		return null;
	}

	public static void Close() {
	}

	/**
	 * ExecuteQuery metodo de criação para acesso ao banco
	 * @param query
	 * @param connection
	 */
	public static void ExecuteQuery(String query, Connection connection) {
		Statement statement = null;
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet = statement.executeQuery(query);
			resultSet.first();
			do {
				System.out.println(resultSet.getString("username"));
			} while (resultSet.next());
		} catch (Exception e) {
			System.out.println("Erro :" + e.getMessage());
		}
	}

	/**
	 * ExecuteStoredProc metodo de criação para acesso ao banco
	 * @param procedureName
	 * @param parameters
	 * @param connection
	 */
	public static void ExecuteStoredProc(String procedureName, Hashtable parameters, Connection connection) {
		try {
			int parameterLength = parameters.size();
			String paraAppender = null;
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < parameters.size(); i++) {
				builder.append("?,");
			}
			paraAppender = builder.toString();
			paraAppender = paraAppender.substring(0, paraAppender.length() - 1);
			CallableStatement stmt = connection.prepareCall("{Call " + procedureName + "{" + paraAppender + ")}");
			Enumeration params = parameters.keys();
			while (params.hasMoreElements()) {
				String paramsName = (String) params.nextElement();
				stmt.setString(paramsName, parameters.get(paramsName).toString());
			}
			stmt.execute();
		} catch (Exception e) {
		}
	}
}
