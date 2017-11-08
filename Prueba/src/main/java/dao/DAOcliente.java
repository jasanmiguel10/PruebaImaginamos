package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cliente;

/**
 * Clase encargada de la interaccion con la base de datos para la entidad
 * @author Juan Antonio Sanmiguel
 *
 */
public class DAOcliente
{
	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;
	/**
	 * Método constructor que crea DAOcliente
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOcliente() 
	{
		recursos = new ArrayList<Object>();
	}

	/**
	 * Método que cierra todos los recursos que estan en el arreglo de recursos
	 * <b>post: </b> Todos los recurso del arreglo de recursos han sido cerrados
	 */
	public void cerrarRecursos() 
	{
		for(Object ob : recursos)
		{
			if(ob instanceof PreparedStatement)
				try 
				{
					((PreparedStatement) ob).close();
				} 
				catch (Exception ex) 
				{
					ex.printStackTrace();
				}
		}
	}

	/**
	 * Método que inicializa la connection del DAO a la base de datos con la conexión que entra como parámetro.
	 * @param con  - connection a la base de datos
	 */
	public void setConn(Connection con)
	{
		this.conn = con;
	}


	/**
	 * Método que, usando la conexión a la base de datos, saca todos los ClienteS de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM CLIENTE;
	 * @return Arraylist con los clientes de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Cliente> darClientes() throws SQLException, Exception 
	{
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		String sql = "SELECT * FROM CLIENTES";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int id = Integer.parseInt(rs.getString("ID"));
			String name = rs.getString("NOMBRE");
			int edad = Integer.parseInt(rs.getString("EDAD"));
			String documento = rs.getString("DOCUMENTO");
			String correo = rs.getString("CORREO");
			String usuario = rs.getString("USUARIO");
			String cont = rs.getString("CONTRASENA");
			clientes.add(new Cliente(id, name, edad, documento,correo, usuario, cont));
		}
		return clientes;
	}

	/**
	 * Método que busca el/los clientes con el nombre que entra como parámetro.
	 * @param name - Nombre de el/los clientes a buscar
	 * @return Arraylist con los clientes encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Cliente> buscarClienterPorId(int id) throws SQLException, Exception 
	{
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		String sql = "SELECT * FROM CLIENTES WHERE ID ='" + id + "'";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String name = rs.getString("NOMBRE");
			int edad = Integer.parseInt(rs.getString("EDAD"));
			String correo = rs.getString("CORREO");
			String documento = rs.getString("DOCUMENTO");
			String usuario = rs.getString("USUARIO");
			String cont = rs.getString("CONTRASENA");
			clientes.add(new Cliente(id, name, edad,documento, correo, usuario, cont));
		}
		return clientes;
	}

	/**
	 * Método que agrega el cliente que entra como parámetro a la base de datos.
	 * @param cliente - el cliente a agregar. cliente !=  null
	 * <b> post: </b> se ha agregado el cliente a la base de datos en la transaction actual. pendiente que el  master
	 * haga commit para que el cliente ingrese  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addCliente(Cliente cliente) throws SQLException, Exception 
	{
		String sql = "INSERT INTO CLIENTES VALUES (";
		sql += cliente.getId() + ",'";
		sql += cliente.getNombre() + ",'";
		sql += cliente.getEdad() + ",'";
		sql += cliente.getCorreo() + ",'";
		sql += cliente.getUsuario() + ",'";
		sql += cliente.getContrasena() + "')";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * Método que actualiza el cliente que entra como parámetro en la base de datos.
	 * @param id - el id del cliente a actualizar. id !=  null, id != ""
	 * @param objetoACambiar - la columna que se va a cambiar del cliente. cliente !=  null
	 * @param cambio - el nuevo valor. cliente !=  null
	 * <b> post: </b> se ha actualizado el cliente en la base de datos en la transaction actual. pendiente que el  master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. 
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateCliente(String id, String objetoACambiar, String cambio) throws SQLException, Exception 
	{
		String sql = "UPDATE CLIENTES SET ";
		sql += objetoACambiar + "='" + cambio + "',";
		sql += " WHERE id = " + id;
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Método que elimina el cliente que entra como parámetro en la base de datos.
	 * @param cliente - el cliente a borrar. cliente !=  null
	 * <b> post: </b> se ha borrado el cleinte en la base de datos en la transaction actual. pendiente que el  master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. 
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteCliente(Cliente cliente) throws SQLException, Exception 
	{

		String sql = "DELETE FROM CLIENTES";
		sql += " WHERE id = " + cliente.getId();
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
}


