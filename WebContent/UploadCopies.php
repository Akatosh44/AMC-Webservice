<?php
session_start();
if (isset($_SESSION['username'])){
?>
<!DOCTYPE html >
<html lang="fr">
<head>
	<title>AMC - Upload des copies</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="description" content="Upload des copies">
    <meta name="author" content="Erwan BRIAND && Alexis DUMAS">
	
    <link href="css/styles.css" rel="stylesheet">
</head>
<body>
	<header class="main-header">AMC Webservice - Import des copies scannées: <span id="nomProjet"></span></header>	
	<div id="content" class="contenu index">
	
		<p> Veuillez uploader les copies scannées au format PDF </p>
		
		<form id=uploadCopies method=post action="rest/uploadCopies" enctype="multipart/form-data" onsubmit="return uploadValide();">
			<copies id="copies">
				<input type="button" value="Ajouter fichier" onclick="addFile()"/>
				<p class="fichierCopies">Copies (.pdf): <input class="copiesPDFInput" name="file" type="file"> 
					<input type="button" value="Supprimer fichier" onclick="delFile(this)"/>
				</p>
			</copies>
			<classes id="classes">
				<input type="button" value="Ajouter classe" onclick="addClasse()"/>
				<p class="choixClasse">Classe: <select class="classeInput" name="classe">
					<option value="EI3-INFO">EI3-INFO</option>
					<option value="EI3-PROD">EI3-PROD</option>
					<option value="EI3-GC">EI3-GC</option>
					</select>
					<input type="button" value="Supprimer classe" onclick="delClasse(this)"/>
				</p>
			</classes>
				
			<input id="submit" type="submit" value="Correction Copies" class="inputButton orangeButton"/>
		</form>
		<p>
			<a href="index.php">
				<input type="button" value="Accueil" class="inputButton orangeButton"/>
			</a>
		</p>
		
	</div>

	<footer class="footer">Work in progress - Erwan BRIAND && Alexis DUMAS</footer>
	
	<script src="js/projet.js"></script>
	<script src="js/upload.js"></script>
	
</body>
</html>
<?php 
}
else {
	header('location: identification.php');
}
?>