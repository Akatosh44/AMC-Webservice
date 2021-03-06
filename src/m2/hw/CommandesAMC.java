package m2.hw;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import com.sun.istack.internal.logging.Logger;

public class CommandesAMC {


	
	/**
	 * Fonction permettant de lancer la phase de preparation d'AMC pour le projet
	 * @param nom
	 * @param fileName
	 */
	public static void prepareProject(String fileName,String username){
			Utilisateur u = Utilisateurs.getUtilisateur(username);
			String filePath = u.getProjectPath()+ fileName;
			String projectPath = u.getProjectPath();
			String name = u.getUserName();
	        
	        String cmd[] = {"auto-multiple-choice", "prepare", "--mode", "s", "--prefix", projectPath, filePath};

	        executerCommande(cmd, name);
	}
	/**
	 * Fonction permettant de mettre en forme la commande d'AMC afin de creer les Layouts.
	 * La chaine d'entrée doit etre sous la forme "username/project/path/"
	 * @param projectPath
	 */
	public static void creationLayout(String projectPath){
		if (projectPath.contains("/")){
			String path[] = projectPath.split("/");
			String username = projectPath.split("/")[0];
			String projectsDirectory = path[path.length-2];
			String project = path[path.length-1];
	
			String[] cmd = { "auto-multiple-choice", "meptex", "--src",
							 projectPath + projectsDirectory + project+ "calage.xy",
							 "--data",
							 projectPath+"data/" };
			
			executerCommande(cmd, username);
		}
		
	}
	/**
	 * Fonction permettant de mettre en forme la commande d'AMC afin de generer 
	 * les fichiers d'images des copies.
	 * La chaine d'entrée doit etre sous la forme "username/project/path/"
	 * @param projectPath
	 */
	public static void generationImagesCopies(String projectPath){
		if (projectPath.contains("/")){
			String username = projectPath.split("/")[0];

			String[] cmd = { "auto-multiple-choice", "getimages", "--copy-to",
							 projectPath+"scans/",
							 //projectPath+"copies.pdf",
							 "--list","list-file", projectPath+"copies/listeCopies.txt"};
			
			executerCommande(cmd, username);
		}
		
	}
	/**
	 * Fonction permettant d'analyser les reponses des copies d'un projet
	 * La chaine d'entrée doit etre sous la forme "username/project/path/"
	 * @param projectPath
	 */
	public static void analyseReponses(String projectPath){
		if (projectPath.contains("/")){
			String username = projectPath.split("/")[0];

			String[] cmd = { "auto-multiple-choice", "analyse", "--projet",
							 projectPath,
							 //projectPath+"scans/*" ,
							 "--list","list-file", projectPath+"copies/listeCopies.txt"};
			
			executerCommande(cmd, username);
		}
		
	}
	/**
	 * Fonction permettant de noter les copies d'un projet
	 * La chaine d'entrée doit etre sous la forme "username/project/path/"
	 * @param projectPath
	 */
	public static void notation(String projectPath){
		if (projectPath.contains("/")){
			String username = projectPath.split("/")[0];
	
			String[] cmd = { "auto-multiple-choice", "note", "--data",
							 projectPath+"data/",
							 projectPath+"cr" };
			
			executerCommande(cmd, username);
		}
		
	}
	/**
	 * Fonction permettant d'effectuer l'association entre les noms des eleves 
	 * et leurs copies automatiquement
	 * La chaine d'entrée doit etre sous la forme "username/project/path/"
	 * @param projectPath
	 */
	/* Fonction non testee
	public static void associationAuto(String projectPath){
		if (projectPath.contains("/")){
			String username = projectPath.split("/")[0];
	
			String[] cmd = { "auto-multiple-choice", "association-auto", "--data",
							 projectPath+"data/",
							 "--notes-id", "numero", "--liste",
							 projectPath+"student.csv",
							 "--liste-key", "numero" };
			
			executerCommande(cmd, username);
		}
		
	}//*/
	/**
	 * Fonction permettant d'exporter les notes de chaque eleves dans un fichier .csv
	 * La chaine d'entrée doit etre sous la forme "username/project/path/"
	 * @param projectPath
	 */
	public static void extractionNotesEleves(String projectPath){
		if (projectPath.contains("/")){
			String username = projectPath.split("/")[0];
	
			String[] cmd = { "auto-multiple-choice", "export", "--data",
							 projectPath+"data",
							 "--module", "CSV", "--fich-nom",
							 projectPath+"student.csv",
							 "--o",
							 projectPath+"exports/notes.csv" };
			
			executerCommande(cmd, username);
		}
		
	}
	/**
	 * Permet d'executer la commande fournie en entrée, dans le dossier de l'utilisateur specifié
	 * @param amcCmd
	 * @param username
	 */
	private static void executerCommande(String[] amcCmd , String username){
		try{
			ProcessBuilder pb = null;
	        Process p = null;
	        /*TODO comment this to work with docker
	        String[] dockerCmd = { "sudo", "docker", "run", "-d", "-v", 
	        					   "/home/user/Projects:/media/"+username, "dockeramc:v1"};
	        String[] completeCmd = new String[dockerCmd.length+amcCmd.length];
	        System.arraycopy(dockerCmd, 0, completeCmd, 0, dockerCmd.length);
	        System.arraycopy(amcCmd, 0, completeCmd, dockerCmd.length, amcCmd.length);
	        
	        pb = new ProcessBuilder(completeCmd);
	        pb.directory(new File("/media"));//*/
	        
	        //*TODO comment this to work without docker
	        String[] completeCmd = amcCmd;
	        pb = new ProcessBuilder(completeCmd);
	        pb.directory(new File(System.getProperty("user.dir")));//*/
	        System.out.println(System.getProperty("user.dir"));
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
	        while ((s = stdInput.readLine()) != null) {
	            System.out.println(s);
	        }
	
	        // read any errors from the attempted command
	        System.out.println("Here is the standard error of the command (if any):\n");
	        while ((s = stdError.readLine()) != null) {
	            System.out.println(s);
	        }
	    } catch (IOException ex) {
	        Logger.getLogger(Process.class.getName(), null).log(Level.SEVERE, null, ex);
	    }
	}
	
	public static void lancerCorrection(String projectPath){
		
		//TODO recupererClasseCSV(classe);
		System.out.println("Layout");
		CommandesAMC.creationLayout(projectPath);
		System.out.println("generationImagesCopies");
		CommandesAMC.generationImagesCopies(projectPath);
		System.out.println("analyseReponses");
		CommandesAMC.analyseReponses(projectPath);
		System.out.println("notation");
		CommandesAMC.notation(projectPath);
		//System.out.println("associationAuto");
		//CommandesAMC.associationAuto(projectPath);
		System.out.println("extractionNotesEleves");
		CommandesAMC.extractionNotesEleves(projectPath);
	}
}
