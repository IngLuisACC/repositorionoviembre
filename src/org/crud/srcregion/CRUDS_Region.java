package org.crud.srcregion;

import java.awt.image.ImagingOpException;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLDataException;

import javax.lang.model.element.Name;
import javax.naming.spi.DirStateFactory.Result;

public class CRUDS_Region {
	
	@SuppressWarnings("unused")
	private static Connection connection = null;
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	
	public static void connectDataBase()throws ImagingOpException,SQLDataException{
		try {
			Class.forName(driver).newInstance();
			System.out.println("CARGO DRIVER CORRECTAMENTE: ojdbc6.jar");
		} catch(Exception e) {
			System.out.println("Exception:" + e.getMessage());
		}
		try {
			connection = DriverManager.getConnection(URL,"System","CMLuis123");
			System.out.println("CONEXION EXITOSA: Oracle 11g" );
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		}
	}
	
	
	public static void insertS_Region(int id, String name) throws IOException, SQLDataException{
		try {
			connectDataBase();
			String sql = "INSERT INTO S_REGION (ID,NAME) VALUES(?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1,id);
			ps.setNString(2,name);
			ps.executeQuery();
			System.out.println("INSERTO EN EL REGISTRO:" + id + "," + name);
		} catch (Exception e) {
			System.out.println("Exception" + e.getMessage());
			}
		}
	
	public static void updateS_Region(int id, String name) throws IOException, SQLDataException{
		try {
			connectDataBase();
			String sql = "UPDATE S_REGION SET NAME = ? WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1,name);
			ps.setInt(2,id);
			ps.executeQuery();
			System.out.println("ACTUALIZAR EL REGISTRO:" + id + "," + name);
		} catch (Exception e) {
			System.out.println("Exception" + e.getMessage());
			}
		}
	
	public static void deleteS_Region(int id) throws IOException, SQLDataException{
		try {
			connectDataBase();
			String sql = "DELETE FROM S_REGION WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1,id);
			ps.executeQuery();
			System.out.println("ELIMINO EL REGISTRO:" + id);
		} catch (Exception e) {
			System.out.println("Exception" + e.getMessage());
			}
		}
	
	

	public static void selectS_Region(int id) throws IOException, SQLDataException{
		try {
			connectDataBase();
			String sql = "SELECT * FROM S_REGION WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rSet = ps.executeQuery();
			if (rSet.next()) {
				System.out.println(rSet.getInt("id") + "," + rSet.getString("name"));
			}			
		} catch (Exception e) {
			System.out.println("Exception" + e.getMessage());
			}
		}

	public static void ivocarProcedureS_Region(int id, String name) throws IOException, SQLDataException{
		try {
			connectDataBase();
			String sql = "CALL proc(?,?)";
			CallableStatement cs = connection.prepareCall(sql);
			cs.setInt(1, id);
			cs.setString(2, name);
			cs.execute();
			System.out.println("EJECUTO CORRECTAMENTE EL PROCEDIMIENTO!!");
		} catch (Exception e) {
			System.out.println("Exception" + e.getMessage());
			}
		}
	
	public static void selectAllS_Region() throws IOException, SQLDataException{
		try {
			connectDataBase();
			String sql = "SELECT * FROM S_REGION";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rSet = ps.executeQuery();
			while (rSet.next()) {
				System.out.println(rSet.getInt("id")+ "," + rSet.getString("name"));
			}
			
		} catch (Exception e) {
			System.out.println("Exception" + e.getMessage());
			}
		}

	
	public static void main(String[] args) throws IOException, SQLDataException {
		
		//insertS_Region(33, "Guerrero");
		//updateS_Region(33, "Angel");
		//deleteS_Region(1);
		//selectS_Region(2);
		//ivocarProcedureS_Region(40, "ONORA");
		selectAllS_Region();
		}
}
