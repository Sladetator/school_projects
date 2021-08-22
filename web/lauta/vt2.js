// Whole-script strict mode syntax
"use strict";

/*
  Poistetaan sivun valmis taulukko, luodaan virheilmoitus valmiiksi.
  Inputista tehdään button päivittymisen välttämiseksi.
  */
window.onload = function() {
	var table = document.getElementsByTagName("table")[0];
    if(table) table.parentNode.removeChild(table);
	
	
	var newError = document.createElement("p");
	newError.setAttribute("id", "virhe");
	newError.textContent = "Virheellinen syöte, anna arvo väliltä 8-16";
	document.getElementsByTagName("fieldset")[0].appendChild(newError);
	newError.style.visibility = "hidden";
	
    document.getElementById("luo").addEventListener('click', teePeli, false);
}

/*
  Luodaan peli luo-painiketta klikattaessa.
  Vanha peli poistetaan, jos sellainen on, ja haetaan input-kentästä arvo. Jos ei väliltä 8-16,
  näytetään virheilmoitus.
*/
function teePeli(e){
	e.preventDefault();
	var table = document.getElementsByTagName("table")[0];
    if(table) table.parentNode.removeChild(table);
	
	var n = document.getElementsByName("x")[0].value;
	if(n <= 16 && n >= 8) teeTaulukko(n);
	else{
		document.getElementById("virhe").style.visibility = "visible";
	}
}

/*
  Piilotetaan virheilmoitus ja luodaan taulukko, jonka sivun koko on n solua
  Luodaan kahteen ensimmäiseen sarakkeeseen punaisia nappuloita ja kahteen viimeiseen sinisiä.
  
*/
function teeTaulukko(n){
	document.getElementById("virhe").style.visibility = "hidden";
	
	var newTable = document.createElement("table");
    newTable.setAttribute("id", "newTable");
	document.body.appendChild(newTable);
	
	for(var i = 0; i < n; i++){
		var tr = document.createElement("tr");
		tr.setAttribute("id", "newTR" + i);
		document.getElementById("newTable").appendChild(tr);
		
		for(var j = 0; j < n; j++){
			var td = document.createElement("td");
			td.setAttribute("id", "newTD" + i + "q" + j);
			
			if(i%2 == 1 && j%2 == 0){
				 td.style.backgroundColor = "#000000";
			}
			
			if(i%2 == 0 && j%2 == 1){
				 td.style.backgroundColor = "#000000";
			}
			
			document.getElementById("newTR" + i).appendChild(td);
			
			if(j < 2){
				var img = document.createElement("img");
				img.setAttribute("src", "red.svg");
				document.getElementById("newTD" + i + "q" + j).appendChild(img);
			}
			
			if(j > n-3){
				var img = document.createElement("img");
				img.setAttribute("src", "blue.svg");
				document.getElementById("newTD" + i + "q" + j).appendChild(img);
			}
		}
	} 
}