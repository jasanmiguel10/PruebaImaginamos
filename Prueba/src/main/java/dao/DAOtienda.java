package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Tienda;

/**
 * Clase encargada de la interaccion con la base de datos para la entidad
 * @author Juan Antonio Sanmiguel
 *
 */
public class DAOtienda
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
	 * Método constructor que crea DAOtienda
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOtienda() 
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
	 * Método que, usando la conexión a la base de datos, saca todos los TiendaS de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM CLIENTE;
	 * @return Arraylist con los tiendas de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Tienda> darTiendas() throws SQLException, Exception 
	{
		ArrayList<Tienda> tiendas = new ArrayList<Tienda>();

		String sql = "SELECT * FROM TIENDAS";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int id = Integer.parseInt(rs.getString("ID"));
			String name = rs.getString("NOMBRE");
			String horario = rs.getString("HORARIO");
			String direccion = rs.getString("DIRECCION");

			tiendas.add(new Tienda(name, id, horario, direccion));
		}
		return tiendas;
	}

	/**
	 * Método que busca el/los tiendas con el nombre que entra como parámetro.
	 * @param name - Nombre de el/los tiendas a buscar
	 * @return Arraylist con los tiendas encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Tienda> buscarTiendarPorId(int id) throws SQLException, Exception 
	{
		ArrayList<Tienda> tiendas = new ArrayList<Tienda>();

		String sql = "SELECT * FROM TIENDAS WHERE ID ='" + id + "'";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String name = rs.getString("NOMBRE");
			String horario = rs.getString("HORARIO");
			String direccion = rs.getString("DIRECCION");


			tiendas.add(new Tienda(name, id, horario, direccion));
		}
		return tiendas;
	}

	/**
	 * Método que agrega el tienda que entra como parámetro a la base de datos.
	 * @param tienda - el tienda a agregar. tienda !=  null
	 * <b> post: </b> se ha agregado el tienda a la base de datos en la transaction actual. pendiente que el  master
	 * haga commit para que el tienda ingrese  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addTienda(Tienda tienda) throws SQLException, Exception 
	{
		String sql = "INSERT INTO TIENDAS VALUES (";
		sql += tienda.getId() + ",'";
		sql += tienda.getNombre() + ",'";
		sql += tienda.getHorario() + ",'";
		sql += tienda.getDireccion() + "')";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * Método que actualiza el tienda que entra como parámetro en la base de datos.
	 * @param id - el id del tienda a actualizar. id !=  null, id != ""
	 * @param objetoACambiar - la columna que se va a cambiar del tienda. tienda !=  null
	 * @param cambio - el nuevo valor. tienda !=  null
	 * <b> post: </b> se ha actualizado el tienda en la base de datos en la transaction actual. pendiente que el  master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. 
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateTienda(String id, String objetoACambiar, String cambio) throws SQLException, Exception 
	{
		String sql = "UPDATE TIENDAS SET ";
		sql += objetoACambiar + "='" + cambio + "',";
		sql += " WHERE id = " + id;
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Método que elimina el tienda que entra como parámetro en la base de datos.
	 * @param tienda - el tienda a borrar. tienda !=  null
	 * <b> post: </b> se ha borrado el cleinte en la base de datos en la transaction actual. pendiente que el  master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. 
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteTienda(Tienda tienda) throws SQLException, Exception 
	{

		String sql = "DELETE FROM TIENDAS";
		sql += " WHERE id = " + tienda.getId();
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
}
