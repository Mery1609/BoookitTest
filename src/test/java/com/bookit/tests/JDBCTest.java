package com.bookit.tests;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.bookit.utilities.DBUtils;

public class JDBCTest {

	String dbUrl = "jdbc:postgresql://localhost:5432/hr";
	String dbUsername = "postgres";
	String dbPassword = "Mery1980";

	@Test(enabled = false)
	public void PostGreSQL() throws SQLException {
		Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet resultset = statement.executeQuery("Select * FROM employees");

		// move pointer to next row
		resultset.next();
		resultset.next();
		// System.out.println(resultset.getString("country_name")); //Japan

		// System.out.println(
		// resultset.getString(1) + "-" + resultset.getString("country_name") + "-" +
		// resultset.getInt(3));
		// JP-Japan-3

		resultset.next();
		// System.out.println(
		// resultset.getString(1) + "-" + resultset.getString("country_name") + "-" +
		// resultset.getInt(3));
		// US-United States of America-2

		// while (resultset.next()) {

		// System.out.println(
		// resultset.getString(1) + "-" + resultset.getString("country_name") + "-" +
		// resultset.getInt(3));

		// }

		resultset.next();
		resultset.next();
		resultset.next();
		resultset.next();
		// System.out.println(resultset.getRow());

		// resultset.first();

		// while (resultset.next()) {

		// System.out.println(resultset.getRow());

		// }

		// or:

		// resultset.last();
		// int rowCount = resultset.getRow();
		// System.out.println("Total number of rows: " + rowCount); //to get the number
		// of rows -->25

		// resultset.beforeFirst(); //will count from the very 1st row, because next
		// will move pointer to the first
		// while (resultset.next()) {

		// System.out.println(

		// resultset.getString(1) + "-" + resultset.getString("country_name") + "-" +
		// resultset.getInt(3));

		// }

		resultset.close();
		statement.close();
		connection.close();

	}

	@Test(enabled = false)
	public void JDBCMetaData() throws SQLException {
		Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		;
		ResultSet resultset = statement.executeQuery("Select * from employees");

		// database metadata(create object)
		DatabaseMetaData dbMetadata = connection.getMetaData();

		// which username are you using?
		System.out.println("User: " + dbMetadata.getUserName());

		// database product name
		System.out.println("Database Product Name: " + dbMetadata.getDatabaseProductName());

		// database product version
		System.out.println("Database Product Version " + dbMetadata.getDatabaseProductVersion());

		// ===========================================

		ResultSetMetaData rsMetaData = resultset.getMetaData();
		// how many columns we have?

		System.out.println("Columns count: " + rsMetaData.getColumnCount());

		// get column name?
		System.out.println(rsMetaData.getColumnName(1));

		// get table name
		System.out.println(rsMetaData.getTableName(1));

		// print all column name using loop

		for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {

			System.out.println(rsMetaData.getColumnName(i));

		}

		resultset.close();
		statement.close();
		connection.close();
	}

	@Test(enabled = false)
	public void DBUtil() throws SQLException {
		Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		;
		ResultSet resultset = statement.executeQuery("Select first_name, last_name, salary, job_id from employees\n");

		// database metadata(create object)
		DatabaseMetaData dbMetadata = connection.getMetaData();

		// result metadata create object
		ResultSetMetaData rsMetadata = resultset.getMetaData();

		// our main structure, it will keep whole query result
		List<Map<String, Object>> queryData = new ArrayList<>();

		// we will add the first row data to this map
		Map<String, Object> row1 = new HashMap<>();

		// point the first row
		resultset.next();

		// key is column name, value is value of that column
		row1.put("first_name", "Steven"); // or row1.put("first_name",resultset.getObject("first_name"));
		row1.put("last_name", "King");
		row1.put("Salary", 24000);
		row1.put("Job_id", "AD_PRES");

		// System.out.println(row1.toString());

		// push row1 map to list as a whole row
		queryData.add(row1);

		// System.out.println(queryData.get(0).get("first_name"));

		// ==================================

		// ADDING ONE MORE ROW

		Map<String, Object> row2 = new HashMap<>();
		resultset.next();

		row2.put("first_name", resultset.getObject("first_name"));
		row2.put("last_name", resultset.getObject("last_name"));
		row2.put("Salary", resultset.getObject("Salary"));
		row2.put("Job_id", resultset.getObject("job_id"));

		queryData.add(row2);

		System.out.println("First Row: " + queryData.get(0).toString());
		System.out.println("Second Row: " + queryData.get(1).toString());

		resultset.close();
		statement.close();
		connection.close();
	}

	@Test(enabled = false)
	public void DBUtilDynamic() throws SQLException {
		Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		;
		ResultSet resultset = statement.executeQuery("Select * FROM countries");

		// database metadata(create object)
		DatabaseMetaData dbMetadata = connection.getMetaData();

		// result metadata create object
		ResultSetMetaData rsMetadata = resultset.getMetaData();

		// =============DYNAMIC LIST FOR EVERY QUERY=================//

		// Main List

		List<Map<String, Object>> queryData = new ArrayList<>(); // creating list (rows, column, values)

		// number of columns
		int colCount = rsMetadata.getColumnCount();

		while (resultset.next()) {

			Map<String, Object> row = new HashMap<>(); // creating map

			// something

			for (int i = 1; i <= colCount; i++) {

				row.put(rsMetadata.getColumnName(i), resultset.getObject(i));

			}

			// adding each row map to list
			queryData.add(row);

		}

		// print first 4 rows
		System.out.println(queryData.get(0));
		System.out.println(queryData.get(1));
		System.out.println(queryData.get(2));
		System.out.println(queryData.get(3));

		// print each country_name from the list
		for (Map<String, Object> map : queryData) {

			System.out.println(map.get("country_id"));

		}

		resultset.close();
		statement.close();
		connection.close();

	}

	@Test
	public void useDBUtils() throws SQLException {

		// create connection with given information
		DBUtils.createConnection();

		
		//for multiple results we use List<Map<String, Object>(first_name, last_name, salary, job_id FROM  employees";

		//String query = "SELECT first_name, last_name, salary, job_id FROM  employees";

		//List<Map<String, Object>> queryData = DBUtils.getQueryResultMap(query);
		
		//System.out.println(queryData.get(0).get("salary"));
		
		
		//For single result we use Map -->WHERE employee_id=107
		String query = "SELECT first_name, last_name, salary, job_id FROM  employees WHERE employee_id=107";
		Map<String, Object> onerowresult = DBUtils.getRowMap(query);
		//print first row salary value		
		System.out.println(onerowresult.get("job_id"));
		
		DBUtils.destroy();

	}

}
