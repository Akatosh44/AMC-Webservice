<?php
session_start();
if (isset($_SESSION['username'])){
?>
<!DOCTYPE html >
<html lang="fr">
<head>
	<title>AMC - Creation de projet</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="description" content="Creation de projet">
    <meta name="author" content="Alexis DUMAS && Erwan Briand">
	
    <link href="css/styles.css" rel="stylesheet">
</head>
<body>

	<header class="main-header">AMC Webservice - Creation</header>

	<div id="content" class="contenu index">
		<form id="creationProjet" method="post" action="rest/creationProjet" enctype="multipart/form-data" onsubmit="return creationValide();">
			<p id="nomProjet">Nom du projet: <input id="nomProjetInput" name="nom" type="text" class="inputButton inputText"/></p>
			<p id="fichierTex">Questionnaire (.tex): <input id="fichierTexInput" name="file" type="file" class="inputButton"> <input type="button" value="Supprimer le fichier" onclick="eraseFile()"/></p>
			<input id="submit" type="submit" value="Creer Projet" class="inputButton orangeButton"/>
		</form>
		<p><a href="index.php">
			<input type="button" value="Accueil" class="inputButton orangeButton"/>
		</a></p>
	</div>
	<footer class="footer">Work in progress - Erwan BRIAND && Alexis DUMAS</footer>
	
	<script src="js/creation.js"></script>
	
</body>
</html>
<?php 
}
else {
	header('location: identification.php');
}
?>
