<?php
session_start();
if (isset($_SESSION['username'])){
?>
<!DOCTYPE html >
<html lang="fr">
<head>
	<title>AMC - Creation de questionnaire</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="description" content="Creation de QCM">
    <meta name="author" content="Alexis DUMAS">
	
    <link href="css/styles.css" rel="stylesheet">
</head>
<body onload="demanderQuestionnaire(chargerQuestionnaire)">


	<div class="page">
	<div class="bloc-principal">
	<header class="main-header">AMC Webservice - Creation questionnaire: <span id="nomProjet"></span></header>
		<div class="ID">Logg&eacute; en tant que <?php echo $_SESSION['username'] ?></div>
	<div class="logout"><a href="logout.php">Se d<?php echo htmlspecialchars("é") ?>connecter</a></div>
	<div class="waiting" id="message" style="visibility:hidden;position:absolute;padding:10px;width:300px;height:150px;left:50%;top:50%;margin-top:-75px;margin-left:-150px;background-color:#F4F5F5;text-align:center;">
	<p>Création du questionnaire</p>
	<img src="src/waiting.gif"/>
	<p>Veuillez patienter...</p>
	</div>
	
	<div id="content" class="contenu-quest"><h2>Creation d'un nouveau questionnaire:</h2>
		<form id="creationForm" method="post" onsubmit="return questionnaireValide();">
			<div id="questionnaire">
				<p id="entete">
					<span id="matiere">Matiere:<input id="matiereInput" name="matiere" type="text" class="inputText inputButton"/></span>
					<span id="duree">Durée:<select id="dureeInput" name="duree" class="inputText inputButton">
						<option value="0h10">0h10</option>
						<option value="0h15">0h15</option>
						<option value="0h20">0h20</option>
						<option value="0h25">0h25</option>
						<option value="0h30">0h30</option>
						<option value="0h35">0h35</option>
						<option value="0h40">0h40</option>
						<option value="0h45">0h45</option>
						<option value="0h50">0h50</option>
						<option value="0h55">0h55</option>
						<option value="1h00">1h00</option>
						<option value="1h05">1h05</option>
						<option value="1h10">1h10</option>
						<option value="1h15">1h15</option>
						<option value="1h20">1h20</option>
						<option value="1h25">1h25</option>
						<option value="1h30">1h30</option>
						<option value="1h35">1h35</option>
						<option value="1h40">1h40</option>
						<option value="1h45">1h45</option>
						<option value="1h50">1h50</option>
						<option value="1h55">1h55</option>
						<option value="2h00">2h00</option>
					</select></span>
					<span id="date">Date (jj/mm/aaaa):<input id="dateInput" name="date" type="text" class="inputText inputButton"/></span>
					<span id="nbCopies">Nombre d'exemplaires de copies:<input id="nbCopiesImput" name="nbCopies" type="number" min="1" max="10" value="1" class="inputText inputButton"/></span>
				</p>
				<blocQR class="blocQR">
					<p class="question">
						Question: <input type="text" id="question1" name="question" class="questionInput inputText inputButton"/>
						<a class="linkLatex" href="javascript:OpenLatexEditor('question1','latex','fr-fr')"><img src="src/formula_icon.png"></a>
					</p>
					
					<reponses>
						<p class="reponse">
							Reponse: <input type="text" id="reponse 1,1" name="reponse" class="reponseInput inputText inputButton"/>
							<a class="linkLatex" href="javascript:OpenLatexEditor('reponse 1,1','latex','fr-fr')"><img src="src/formula_icon.png"></a> 
							<span class="checkbox">Bonne reponse?<input class="bonneInput" type="checkbox" name="bonne" checked/></span>
							<span class="delQ"><input type="button" name="delQ" value="Supprimer reponse" onclick="supprReponse(this)" class="inputButton blueButton"/></span>
						</p>
					</reponses>
					
					<options>
						<span class="del"><input type="button" name="delQ" value="Supprimer question" onclick="supprQuestion(this)" class="inputButton blueButton"/></span>
						<span class="addQ"><input type="button" name="addQ" value="Ajouter reponse" onclick="ajoutReponse(this)" class="inputButton blueButton" /></span>
						<span class="checkbox">Reponses en colonnes?<input type="checkbox" name="horizontal"/></span>
						<span class="bareme">bareme:<input class="baremeImput inputText" name="bareme" type="number" min="1" max="20" value="1"/></span>
					</options>
				</blocQR>
	
			</div>
			
			<p class="addQ">
				<input type="button" name="addQ" value="Ajouter question" onclick="ajoutQuestion(this)" class="inputButton blueButton"/>
			</p>
				
			<p id="submit">
				<input type="submit" name="submit" value="Creer questionnaire" class="inputButton orangeButton">
			</p>
		</form>
		<p>
			<a href="index.php">
				<input type="button" value="Accueil" class="inputButton orangeButton"/>
			</a>
		</p>
	</div> <!-- /content -->
	</div><!-- bloc -->
	<footer class="footer">Work in progress - Erwan BRIAND && Alexis DUMAS</footer>
	</div><!-- page -->
	<script src="js/creation.js"></script>
	<script src="js/projet.js"></script>
	<script src="js/latexEditor.js"></script>
	<script src="js/erreurs.js"></script>

</body>
</html>

<?php 
}
else {
	header('location: identification.php');
}
?>
