package m2.hw;


import java.util.HashMap;
import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;


import com.sun.jersey.spi.resource.Singleton;

@Path("utilisateurs")
@Singleton
public class Utilisateurs {

	private static HashMap<String,Utilisateur> utilisateurs = null;
	
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
    public void addUtilisateur(@CookieParam("AMC_Webservice") String username){
		if (utilisateurs.get(username)==null){
	    	Utilisateur u = new Utilisateur(username);
	    	utilisateurs.put(username,u);
		}
    }
	
    /**
     * Permet d'obtenir l'utilisateur a partir de son username
     * @param username
     * @return
     */
	public static Utilisateur getUtilisateur(String username){
		return utilisateurs.get(username);
	}
 
    
}
