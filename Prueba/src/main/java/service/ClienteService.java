package service;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DAOcliente;
import model.Cliente;

@Path("/clientes")
public class ClienteService {
	private DAOcliente dao = new  DAOcliente();

    // URI:
    // /contextPath/servletPath/clientes
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Cliente> getClientes_JSON() throws SQLException, Exception {
        List<Cliente> listOfCountries = dao.darClientes();
        return listOfCountries;
    }
 
    // URI:
    // /contextPath/servletPath/clientes
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Cliente getCliente(@PathParam("usuario") String usuario,  @PathParam("conta")String conta) throws SQLException, Exception  {
        return dao.buscarClienterPorUsuario(usuario, conta);
    }
 
    // URI:
    // /contextPath/servletPath/clientes
    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    public void addCliente(Cliente emp) throws SQLException, Exception {
         dao.addCliente(emp);
    }
 
    // URI:
    // /contextPath/servletPath/clientes
    @PUT
    @Produces({ MediaType.APPLICATION_JSON })
    public void updateCliente(String id, String objetoACambiar, String cambi) throws SQLException, Exception {
         dao.updateCliente(id, objetoACambiar, cambi);
    }
 
    @DELETE
    @Path("/{empNo}")
    @Produces({ MediaType.APPLICATION_JSON })
    public void deleteCliente(@PathParam("empNo") Cliente empNo) throws SQLException, Exception {
        dao.deleteCliente(empNo);
    }
}
