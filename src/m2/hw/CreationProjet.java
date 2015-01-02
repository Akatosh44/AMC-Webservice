package m2.hw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.logging.Level;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.istack.internal.logging.Logger;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;


/** TODO
 * 		- Gestions des erreurs de dossier/fichier (doublons, noms incorrects ou dangereux...)
 * 		- Envois de données utiles apres creation
 * 		- 
 * 
 */

@Path("creationProjet")
public class CreationProjet {

	private static final String UPLOAD_PATH = "upload/";
	private static final String PROJECTS_PATH = "Projet-QCM";

	/**
	 * Fonction permettant de recuperer le nom du projet a creer les dossiers ainsi que le fichier 
	 * latex.
	 * @param nom
	 * @param uploadedInputStream
	 * @param fileDetail
	 * @return
	 */
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
		@FormDataParam("nom") String nom,
		@FormDataParam("file") InputStream uploadedInputStream,
		@FormDataParam("file") FormDataContentDisposition fileDetail) {

		String fileName = fileDetail.getFileName();
		
		// save it
		if (!fileName.equals("") && !nom.equals("")){
			String uploadedFileLocation = UPLOAD_PATH + fileName;
			saveFile(uploadedInputStream, uploadedFileLocation);
			creationRepertoire(nom);
			
			String output = "File \"" + fileName +"\" uploaded to " + uploadedFileLocation;
			
			return Response.status(200).entity(output).build();
		}
		else {
			if (fileName.equals("") && nom.equals("")){
				return Response.status(204).entity("Aucun fichier selectionne ou nom donne. " +
						"La creation d'un projet requiert un nom et un questionnaire").build();
			}
			else if (nom.equals("")) {
				return Response.status(204).entity("Aucun nom specifie. " +
						"La creation d'un projet requiert un nom").build();
			}
			else if (fileName.equals("")){
				return Response.status(204).entity("Aucun fichier selectionne. " +
						"La creation d'un projet requiert un nom").build();
			}
		}
		
		return Response.status(406).entity("Not reacheable").build();
 
	}
	
	
	/**
	 * Fonction de sauvegarde du fichier latex upload par l'utilisateur
	 * @param uploadedInputStream
	 * @param serverLocation
	 */
	private void saveFile(InputStream uploadedInputStream,
            String serverLocation) {
 
        try {
            OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
            int read = 0;
            byte[] bytes = new byte[1024];
 
            outpuStream = new FileOutputStream(new File(serverLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }
            outpuStream.flush();
            outpuStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
	
	/**
	 * Fonction permettant de creer les dossiers pour le nouveau projet
	 * @param data
	 */
	private void creationRepertoire(String nom){
		try{
			
			ProcessBuilder pb = null;
	        Process p;
	        String cmd2 = "";
	        String workingDir = System.getProperty("user.dir");
	        System.out.println(""+workingDir);
	        String scriptloc=workingDir+"/createProject.sh";
	        String cmd[] = {"/bin/bash",scriptloc ,nom};
	
	        for (int i = 0; i <= cmd.length-1; i++) {
	            cmd2 += " "+cmd[i];
	        }
	        System.out.println("" + cmd2);
	        pb = new ProcessBuilder(cmd);
	        pb.directory(new File(workingDir));
	
	        p = null;
	        try {
	            p = pb.start();
	        } catch (IOException ex) {
	            Logger.getLogger(Process.class.getName(), null).log(Level.SEVERE, null, ex);
	        }
	        
	        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
	
	        // read the output from the command
	        System.out.println("Here is the standard output of the command:\n");
	
	        String s = null;
	        String output = "";
	        while ((s = stdInput.readLine()) != null) {
	            System.out.println(s);
	
	        }
	        output = "";
	
	        // read any errors from the attempted command
	        System.out.println("Here is the standard error of the command (if any):\n");
	        while ((s = stdError.readLine()) != null) {
	            System.out.println(s);
	        }
	    } catch (IOException ex) {
	        Logger.getLogger(Process.class.getName(), null).log(Level.SEVERE, null, ex);
	    }
	}
	
	
}
