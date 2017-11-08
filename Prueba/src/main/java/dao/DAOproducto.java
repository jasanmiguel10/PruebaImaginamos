package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Producto;

/**
 * Clase encargada de la interaccion con la base de datos para la entidad
 * @author Juan Antonio Sanmiguel
 *
 */
public class DAOproducto
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
	 * Método constructor que crea DAOproducto
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOproducto() 
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
	 * Método que, usando la conexión a la base de datos, saca todos los ProductoS de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM CLIENTE;
	 * @return Arraylist con los productos de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Producto> darProductos() throws SQLException, Exception 
	{
		ArrayList<Producto> productos = new ArrayList<Producto>();

		String sql = "SELECT * FROM PRODUCTOS";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int id = Integer.parseInt(rs.getString("ID"));
			String name = rs.getString("NOMBRE");
			String descripcion = rs.getString("DESCRIPCION");
			int precio = Integer.parseInt(rs.getString("PRECIO"));
			int codigo = Integer.parseInt(rs.getString("CODIGO"));

			productos.add(new Producto(id, name, descripcion, precio, codigo));
		}
		return productos;
	}

	/**
	 * Método que busca el/los productos con el nombre que entra como parámetro.
	 * @param name - Nombre de el/los productos a buscar
	 * @return Arraylist con los productos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Producto> buscarProductorPorId(int id) throws SQLException, Exception 
	{
		ArrayList<Producto> productos = new ArrayList<Producto>();

		String sql = "SELECT * FROM PRODUCTOS WHERE ID ='" + id + "'";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String name = rs.getString("NOMBRE");
			String descripcion = rs.getString("DESCRIPCION");
			int precio = Integer.parseInt(rs.getString("PRECIO"));
			int codigo = Integer.parseInt(rs.getString("CODIGO"));

			productos.add(new Producto(id, name, descripcion, precio, codigo));
		}
		return productos;
	}

	/**
	 * Método que agrega el producto que entra como parámetro a la base de datos.
	 * @param producto - el producto a agregar. producto !=  null
	 * <b> post: </b> se ha agregado el producto a la base de datos en la transaction actual. pendiente que el  master
	 * haga commit para que el producto ingrese  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addProducto(Producto producto) throws SQLException, Exception 
	{
		String sql = "INSERT INTO PRODUCTOS VALUES (";
		sql += producto.getId() + ",'";
		sql += producto.getNombre() + ",'";
		sql += producto.getDescripcion() + ",'";
		sql += producto.getPrecio() + ",'";
		sql += producto.getCodigo() + "')";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * Método que actualiza el producto que entra como parámetro en la base de datos.
	 * @param id - el id del producto a actualizar. id !=  null, id != ""
	 * @param objetoACambiar - la columna que se va a cambiar del producto. producto !=  null
	 * @param cambio - el nuevo valor. producto !=  null
	 * <b> post: </b> se ha actualizado el producto en la base de datos en la transaction actual. pendiente que el  master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. 
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateProducto(String id, String objetoACambiar, String cambio) throws SQLException, Exception 
	{
		String sql = "UPDATE PRODUCTOS SET ";
		sql += objetoACambiar + "='" + cambio + "',";
		sql += " WHERE id = " + id;
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Método que elimina el producto que entra como parámetro en la base de datos.
	 * @param producto - el producto a borrar. producto !=  null
	 * <b> post: </b> se ha borrado el cleinte en la base de datos en la transaction actual. pendiente que el  master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. 
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteProducto(Producto producto) throws SQLException, Exception 
	{

		String sql = "DELETE FROM PRODUCTOS";
		sql += " WHERE id = " + producto.getId();
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
}



