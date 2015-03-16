package m2.hw;

import java.net.URI;
import java.util.HashMap;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import com.sun.jersey.spi.resource.Singleton;

@Path("utilisateurs")
@Singleton
public class Utilisateurs {

	private static HashMap<String,Utilisateur> utilisateurs = null;
	private static Utilisateur currentUser = null;
	
	/**
	 * Initialise le systeme d'utilisateur
	 */
	@Path("load")
    @POST
    public void loadConfiguration() {
    	if (utilisateurs==null){
    		utilisateurs = new HashMap<String,Utilisateur>();
    	}
    }

	/**
	 * Permet d'ajouter un utilisateur a la banque d'utilisateur du service.
	 * Pour le moment, le dernier utilisateur ajouté deviens l'utilisateur actuel.
	 * @param username
	 * @return
	 */
	@Path("add")
	@POST
    public Response addUtilisateur(String username){

    	Utilisateur u = new Utilisateur(username);
    	utilisateurs.put(username,u);
    	Utilisateurs.currentUser=u;
    	
    	URI uri = UriBuilder.fromUri("http://localhost:8080/REST.Test/")
				.path("{a}")
				.build("index.php");
		
		return Response.seeOther(uri).build();
    }
    /**
     * Permet d'obtenir l'utilisateur a partir de son username
     * @param username
     * @return
     */
	public static Utilisateur getUtilisateur(String username){
		return utilisateurs.get(username);
	}
    /**
     * Renvois l'utilisateur actuel
     * @return
     */
	public static Utilisateur getCurrentUser(){
		return currentUser;
	}    
    
}
