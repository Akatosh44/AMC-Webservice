
function chargerNotes(elmnt){
	
	var xhr = new XMLHttpRequest();
	document.getElementById("resultats").style.display="none";
	document.getElementById("tableau").style.display="none";
	xhr.open("POST","rest/correction/notes",false);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	xhr.onreadystatechange = function (aEvt){
		document.getElementById("tableau").style.display="block";
		document.getElementById("tableau").innerHTML=xhr.responseText;
		
	};
	xhr.send();
	
	

};

function afficherNotes(elmnt){
	var display = document.getElementById("resultats").style.display;
	if(display =="none"){
		document.getElementById("resultats").style.display="block";
	}
	else
	{
		document.getElementById("resultats").style.display="none";
	}
};

function changerBareme(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET","rest/creationQuestionnaire/getBareme",false);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	xhr.onreadystatechange = function (aEvt){
		document.getElementById("bareme").innerHTML=xhr.responseText;
		document.getElementById("baremePopup").style.display="block";
		
	};
	xhr.send();
};


function hideBareme(){
	document.getElementById("baremePopup").style.display="none";
};

function changerFichiers(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET","rest/correction/getFilesNames",true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	xhr.onreadystatechange = function (aEvt){
		var json = JSON.parse(xhr.responseText);
		document.getElementById("oldFiles").innerHTML="";
		document.getElementById("upload").innerHTML="";
		
		json.forEach(function(text){
			var newNode = document.createElement("p");
			var fileName = document.createTextNode(text);
			var textNode = document.createElement("span");
			
			var supprButton = document.createElement("input");
			supprButton.setAttribute("type", "button");
			supprButton.setAttribute("value", "Supprimer fichier");
			supprButton.setAttribute("onclick", "delFileRest(this)");
			textNode.appendChild(fileName);
			newNode.appendChild(textNode);
			newNode.appendChild(supprButton);
			document.getElementById("oldFiles").appendChild(newNode);
		});
		
		var newNode = document.createElement("p");
		newNode.setAttribute("class", "fichierCopies");
		var textNode = document.createTextNode("Copies (.pdf): ");
		
		var inputFile = document.createElement("input");
		inputFile.setAttribute("class", "copiesPDFInput");
		inputFile.setAttribute("name","file");
		inputFile.setAttribute("type", "file");
		inputFile.setAttribute("onchange", "chooseFile()");
		
		var supprButton = document.createElement("input");
		supprButton.setAttribute("type", "button");
		supprButton.setAttribute("value", "Supprimer fichier");
		supprButton.setAttribute("onclick", "delFile(this)");
		
		newNode.appendChild(textNode);
		newNode.appendChild(inputFile);
		newNode.appendChild(supprButton);
		document.getElementById("upload").appendChild(newNode);
		
		document.getElementById("fichiers").style.display="block";
		
	};
	xhr.send();
};

function hideFichiers(){
	document.getElementById("fichiers").style.display="none";
};

function delFile(elmnt){
	var element = elmnt.parentNode;
	element.parentNode.removeChild(element);
	chooseFile();
};

function chooseFile(){
	var fichiersUploades = document.getElementsByClassName("copiesPDFInput");
	for (var i=0; i< fichiersUploades.length;i++){
		if (fichiersUploades[i].value==""){
			var node = fichiersUploades[i].parentNode;
			node.parentNode.removeChild(node);
		}
	}
	var newNode = document.createElement("p");
	newNode.setAttribute("class", "fichierCopies");
	var textNode = document.createTextNode("Copies (.pdf): ");
	
	var inputFile = document.createElement("input");
	inputFile.setAttribute("class", "copiesPDFInput");
	inputFile.setAttribute("name","file");
	inputFile.setAttribute("type", "file");
	inputFile.setAttribute("onchange", "chooseFile()");
	
	var supprButton = document.createElement("input");
	supprButton.setAttribute("type", "button");
	supprButton.setAttribute("value", "Supprimer fichier");
	supprButton.setAttribute("onclick", "delFile(this)");
	
	newNode.appendChild(textNode);
	newNode.appendChild(inputFile);
	newNode.appendChild(supprButton);
	document.getElementById("upload").appendChild(newNode);
	
}
