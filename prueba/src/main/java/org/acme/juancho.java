package org.acme;

import javax.inject.Inject;
import io.agroal.api.AgroalDataSource;
import io.quarkus.agroal.DataSource;

import oracle.jdbc.OracleTypes;

import java.sql.Connection;
import java.sql.Types;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/juancho")
public class juancho {


    @Inject
    @DataSource("db1")
    AgroalDataSource ds;

    @GET
	@Produces(value="application/json")
    public Response hello() {
        
        Connection con = null;
		Statement stmt = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		String salida;
		List<Persona> pers = new ArrayList<Persona>();

		try {
			con = ds.getConnection();
			/*
			//SELECT

			stmt = con.createStatement();  
			rs = stmt.executeQuery("select * from PRUEBA");
			while(rs.next()){
				System.out.println("Employee ID="+rs.getInt("ID")+", NOMBRE="+rs.getString("NOMBRE"));
			}
			*/
			/*
			//CALL SP SIN RESULTSET
			cstmt = con.prepareCall("CALL SYSTEM.SHOW_SUPPLIERS2(?,?)"); 
			cstmt.setString (1, "hola2"); 
			cstmt.registerOutParameter (2, Types.VARCHAR);
			cstmt.executeUpdate();  
			salida = cstmt.getString(2);
			System.out.println("SALIDA="+salida);
			cstmt.close(); 
			*/


			
			//CALL SP CON RESULTSET
			cstmt = con.prepareCall("BEGIN SHOW_SUPPLIERS(?,?); END;"); 
			cstmt.setString (1, "hola2");
			cstmt.registerOutParameter (2, OracleTypes.CURSOR);
			cstmt.execute(); 
			//cstmt.executeUpdate();    
			

			rs = (ResultSet) cstmt.getObject(2);    
			while (rs.next()) {
				Persona per = new Persona();
		
				Integer userid = rs.getInt("ID");
				String usernombre = rs.getString("NOMBRE");
				per.setPersona(userid,usernombre);
				pers.add(per);
				System.out.println("SALIDA="+userid+":"+usernombre);
			}

			rs.close(); 
			cstmt.close(); 
			



            /*
			 */
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
				try {
					if(rs != null) rs.close();
					if(stmt != null) stmt.close();
					if(con != null) con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		//return Response.status(Status.INTERNAL_SERVER_ERROR).entity(result).build();
		//CysceResult result = new CysceResult("200","todo ok");
		return Response.ok().entity(pers).build();

    }
}