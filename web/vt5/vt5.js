'use strict';

//Tarvittava data:
//Joukkueet: nimi, rastien koodit
window.onload = () => {
    trimmaaNimet();
    haeRastienKoordinaatit();
    laskeJoukkueenMatka();
    luoJoukkuelistaus();
    paivitaMatkat();
    //Lisaa rastit detailsiin
    lisaaDragJaDrop();
    luoKartta();
    piirraRastitKartalle();
}


function paivitaMatkat() {
    var span;
    var matka = 0;
    for(var i = 0; i < data.joukkueet.length; i++){
        span = $(document.getElementById(data.joukkueet[i].nimi)).find('span');
        span.text(data.joukkueet[i].matka.toFixed(2));
    }
}


// Lisää rastien koordinaatit joukkueen rastien listaukseen
function haeRastienKoordinaatit() {
let joukkueet = data.joukkueet;

Object.keys(joukkueet).forEach(i => { //data.joukkueet
    Object.keys(joukkueet[i].rastit).forEach(j => { //yhden joukkueen rastit
        Object.keys(data.rastit).forEach(k => { //verrataan rasteihin
            if(joukkueet[i].rastit[j].id == data.rastit[k].id){
                joukkueet[i].rastit[j].lat = data.rastit[k].lat;
                joukkueet[i].rastit[j].lon = data.rastit[k].lon;
                joukkueet[i].rastit[j].koodi = data.rastit[k].koodi;
            } 
        });
    });
});
}

//Tekee listauksen joukkueiden nimistä aakkosjärjestyksessä ja lisää joukkueet listaan
function luoJoukkuelistaus() {
  var joukkueidenTiedot = haeTiedot();
  var ul = document.getElementById('joukkuelistaus');
  var li, details, summary, span, detailsUL;

  /*while(ul.firstChild){
      ul.removeChild(ul.firstChild);
  }*/

  joukkueidenTiedot.sort((a,b) => {
      if(b[0] < a[0]) return 1;
      if(b[0] > a[0]) return -1;
      return 0; 
  });

  for(var i = 0; i < joukkueidenTiedot.length; i++){
      li = ul.appendChild(document.createElement('li'));
      li.className = 'oikealla';
      li.id = joukkueidenTiedot[i][0];
      details = li.appendChild(document.createElement('details'));
      summary = details.appendChild(document.createElement('summary'));
      summary.appendChild(document.createTextNode(joukkueidenTiedot[i][0] + ' ('));
      span = summary.appendChild(document.createElement('span'));
      span.appendChild(document.createTextNode('0.00'));
      summary.appendChild(document.createTextNode('km)'));
  }

  varitaListaus();

  for(var i = 0; i < ul.getElementsByTagName('summary').length; i++) {
    detailsUL = document.getElementsByTagName('details')[i].appendChild(document.createElement('ul'));
    lisaaKoodit(detailsUL, joukkueidenTiedot[i][1]);
    for(var j = 0; j < joukkueidenTiedot[i][1].length; j++){

    }
  }
}

function lisaaKoodit(ul, rastit){
    var li;
    for(var i = 0; i < rastit.length; i++) {
        li = ul.appendChild(document.createElement('li'));
        li.appendChild(document.createTextNode(rastit[i]));
    }
}


//Hakee joukkuiden nimet taulukkoon ja palauttaa sen
function haeTiedot(){
  var arr = [];
  var rastit = [];
  for(var i in data.joukkueet){
      for(var j in data.joukkueet[i].rastit){
          rastit.push(data.joukkueet[i].rastit[j].koodi);
      }
      arr.push([data.joukkueet[i].nimi, rastit]);
      rastit = [];
  }
  return arr;
}


//Asettaa joukkuelistauksen li-elementtien taustavärit sateenkaaren väreihin
function varitaListaus() {
  $('#joukkuelistaus li').each(function(i){
    $(this).css('background-color', rainbow($('#joukkuelistaus li').length, i));
  });
}


function rainbow(numOfSteps, step) {
  // This function generates vibrant, "evenly spaced" colours (i.e. no clustering). This is ideal for creating easily distinguishable vibrant markers in Google Maps and other apps.
  // Adam Cole, 2011-Sept-14
  // HSV to RBG adapted from: http://mjijackson.com/2008/02/rgb-to-hsl-and-rgb-to-hsv-color-model-conversion-algorithms-in-javascript
  let r, g, b;
  let h = step / numOfSteps;
  let i = ~~(h * 6);
  let f = h * 6 - i;
  let q = 1 - f;
  switch(i % 6){
      case 0: r = 1; g = f; b = 0; break;
      case 1: r = q; g = 1; b = 0; break;
      case 2: r = 0; g = 1; b = f; break;
      case 3: r = 0; g = q; b = 1; break;
      case 4: r = f; g = 0; b = 1; break;
      case 5: r = 1; g = 0; b = q; break;
  }
  let c = "#" + ("00" + (~ ~(r * 255)).toString(16)).slice(-2) + ("00" + (~ ~(g * 255)).toString(16)).slice(-2) + ("00" + (~ ~(b * 255)).toString(16)).slice(-2);
  return (c);
}

//Päivittää kartan ja rastien tiedot silloin, kun rastien järjestystä on muutettu
function muutaRastit(ul) {
    var joukkue = ul.parents('li:first').attr('id');
    var rastienJarjestys = [];

    for(var i = 0; i < $(ul).children().length; i++){
        if($($(ul).children()[i]).text() != ''){
            rastienJarjestys.push($($(ul).children()[i]).text());
        }
    }
    
    var rastit;
    var jnro = -1;
    for(var i = 0; i < data.joukkueet.length; i++) {
        if(data.joukkueet[i].nimi == joukkue) {
            rastit = data.joukkueet[i].rastit;
            jnro = i;
        }
    }

    var muutetutRastit = [];
    for(var i = 0; i < rastienJarjestys.length; i++){
        for(var j = 0; j < rastit.length; j++) {
            if(rastit[j].koodi == rastienJarjestys[i]){
                muutetutRastit.push(rastit[j]);
                break;
            }
        }
    }
    
    for(var i = 0; i < muutetutRastit.length; i++) {
        data.joukkueet[jnro].rastit[i] = muutetutRastit[i];
    }

    laskeJoukkueenMatka();
    paivitaMatkat();
    

    for(var i = 0; i < data.joukkueet.length; i++){
        poistaKartalta(data.joukkueet[i].nimi);
    }

    var j = $('#karttalistaus summary').length-1;
    while(j > -1){
        piirraKartalle($(($('#karttalistaus summary'))[j]).parents('li:first').attr('id'), $(($('#karttalistaus summary'))[j]).parents('li:first').css('background-color'));
        j--;
    }
}


//Lisää drag- ja drop-ominaisuudet joukkuelistaukseen
function lisaaDragJaDrop(){
    $('li ul').sortable({
        update: function(e){
            muutaRastit($(e.originalEvent.target).parent());
        }
    });

  hiiri('.oikealla');

  $('#karttalistaus').droppable({
      tolerance: 'pointer',
    drop: function(e, ui) {
        if($(ui.draggable).hasClass('oikealla') || $(ui.draggable).hasClass('vasemmalla')){
            var li = $(ui.draggable).clone();
            var vari = li.css('background-color');
            $(ui.draggable).remove();
            $(li).attr('style', '');
            $(li).css('background-color', vari);
            $(li).removeClass('oikealla');
            $(li).addClass('vasemmalla');
            $('#karttalistaus').prepend(li);
            hiiri('.vasemmalla');
    
            var nimi = $(li).attr('id');
            piirraKartalle(nimi, vari);
            $('#karttalistaus li ul').on('sortupdate', function(e){
                muutaRastit($(e.originalEvent.target).parent());
            });
        }
    }
  });
  
  $('#joukkuelistaus').droppable({
      tolerance: 'pointer',
    drop: function(e, ui) {
        if($(ui.draggable).hasClass('vasemmalla')){
            var li = $(ui.draggable).clone();
            var vari = li.css('background-color');
            $(ui.draggable).remove();
            $(li).attr('style', '');
            $(li).css('background-color', vari);
            $(li).removeClass('vasemmalla');
            $(li).addClass('oikealla');
            $('#joukkuelistaus').append(li);
            hiiri('.oikealla');            

            var nimi = $(li).attr('id');
            poistaKartalta(nimi)
            $('#joukkuelistaus li ul').on('sortupdate', function(e){
                muutaRastit($(e.originalEvent.target).parent());
            });
        }
    }
  });
}


//selvitetään, ollaanko listalla kartalle piirrettävien puolella vai ei
function select(etuliite) {
    if(etuliite = '.oikealla'){
        return '#karttalistaus';
    } else if (etuliite = '.vasemmalla'){
        return '#joukkuelistaus';
    } else {
        return false;
    }
}

//pidetään huoli että hiirellä voidaan siirrellä vain haluttuja asioita listasta
function hiiri(etuliite) {
    $('li ul').sortable();
    
        $(etuliite).draggable({
            revert: true,
            stack: select(etuliite)
        });
    
        $(etuliite + ' summary').on('mousedown', function() {
          $(this.closest('li')).draggable('enable');
        });
      
        $(etuliite + ' summary').on('mouseout', function() {
            $(this.closest('li')).draggable('disable');
        });
      }

function poistaKartalta(nimi) {
    for(var i = 0; i < viivat.length; i++) {
        if(nimi == viivat[i][1]) mymap.removeLayer(viivat[i][0]);
    }
}
//Piirretään rastit kartalle koordinaattien mukaan
function piirraKartalle(nimi, vari){
    var rastit;
    var koordinaatit = [];

    for(var i = 0; i < data.joukkueet.length; i++){
        if(nimi == data.joukkueet[i].nimi) rastit = data.joukkueet[i].rastit;
    }

    for(var i = 0; i < rastit.length; i++){
        if(rastit[i].hasOwnProperty('lat') && rastit[i].hasOwnProperty('lon')){
            koordinaatit.push([rastit[i].lat, rastit[i].lon]);
        }
    }
    let polyline = L.polyline(koordinaatit, {color: vari}).addTo(mymap);
    viivat.push([polyline, nimi]);
}

var mymap;
var viivat = [];

//Lisää kartan sivulle
function luoKartta() {
  var div = $("#map");
  
  mymap = new L.map('map', {
      crs: L.TileLayer.MML.get3067Proj()
     }).setView([62.1, 25.6], 8);
 L.tileLayer.mml_wmts({ layer: "maastokartta" }).addTo(mymap);
}


//Piirtää punaisia ympyröitä kartalle rastien kohdille
function piirraRastitKartalle() {
    for(var i = 0; i < data.rastit.length; i++) {
        luoYmpyra(data.rastit[i].lat, data.rastit[i].lon, data.rastit[i].id);  
    }
    mymap.fitBounds(haeRajat(), {padding: L.point(10,10)});
}

function luoYmpyra(lat, lng, rid) {
    L.circle(
        [lat, lng], {
            color: 'red',
            fillOpacity: 0,
            radius: 150,
        },
        ).addTo(mymap).on('click', ympyraKlikattu).rastin_id = rid;
}

//Etsitään rajat jotta kartta asettuu automaattisesti nätisti ruudulle
function haeRajat() {
    var lat = [], lon = [];
    for(var i = 0; i < data.rastit.length; i++) {
        lat.push(data.rastit[i].lat);
        lon.push(data.rastit[i].lon);
    }

    lat.sort();
    lon.sort();
    var kulma1 = L.latLng(lat[0], lon[0]);
    var kulma2 = L.latLng(lat[lat.length-1], lon[lon.length-1]);
    var bounds = L.latLngBounds(kulma1, kulma2);
    return bounds;
}

//Pätkitään nimet siistiksi
function trimmaaNimet(){
    for(var i = 0; i < data.joukkueet.length; i++) {
        data.joukkueet[i].nimi = data.joukkueet[i].nimi.trim();
    }
}

//Lasketaan kaikkien rastien välinen yhteismatka
function laskeJoukkueenMatka() {
    var matkanPituus = 0;
    for(var i = 0; i < data.joukkueet.length; i++) {
        for(var j = 1; j < data.joukkueet[i].rastit.length; j++){
            matkanPituus += getDistanceFromLatLonInKm(
                data.joukkueet[i].rastit[j-1].lat,
                data.joukkueet[i].rastit[j-1].lon,
                data.joukkueet[i].rastit[j].lat,
                data.joukkueet[i].rastit[j].lon 
            );
        }
        data.joukkueet[i].matka = matkanPituus;
        matkanPituus = 0;
    }
}


// Laskee kahden koordinaatin vÃ¤lisen matkan ja palauttaa sen kilometreissÃ¤
function getDistanceFromLatLonInKm(lat1,lon1,lat2,lon2) {
    var R = 6371; // Radius of the earth in km
    var dLat = deg2rad(lat2-lat1);  // deg2rad below
    var dLon = deg2rad(lon2-lon1); 
    var a = 
      Math.sin(dLat/2) * Math.sin(dLat/2) +
      Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * 
      Math.sin(dLon/2) * Math.sin(dLon/2)
      ; 
    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
    var d = R * c; // Distance in km
    return d;
}
  
// Muuttaa asteet radiaaneiksi
function deg2rad(deg) {
    return deg * (Math.PI/180)
}


var valittu = null;
var marker = null;
//poistaa ja uudelleenpiirtää valitun ympyrän
function muutaValinta(muutettava, opacity, valinta) {
    mymap.removeLayer(muutettava);
    muutettava.options.fillOpacity = opacity;
    muutettava.addTo(mymap).on('click', ympyraKlikattu);
    if(typeof(valinta) !== 'undefined') valittu = valinta;
}


//handleri ympyrän klikkaamiselle
function ympyraKlikattu(){
    if(valittu == this) {
        muutaValinta(this, 0, null);
    } else if(this.options.fillOpacity == 0 && valittu != null){
        muutaValinta(valittu, 0);
        muutaValinta(this, 1, this)
    } else {
        muutaValinta(this, 1, this);
    }

    if(valittu == null){
        mymap.removeLayer(marker);
        marker = null;
    } else {
        if(marker != null) mymap.removeLayer(marker);
        marker = L.marker([this._latlng.lat, this._latlng.lng], {draggable: true}).addTo(mymap);
        marker.addEventListener('dragend', markerSiirtynyt);
    }
}

//Piirretään rastit uudelleen aina kun jotain markeria muutetaan
function markerSiirtynyt(e) {
    Object.keys(data.rastit).forEach(i => {
        if(data.rastit[i].id == valittu.rastin_id) {
            data.rastit[i].lat = e.target._latlng.lat;
            data.rastit[i].lon = e.target._latlng.lng;
        }
    });
    haeRastienKoordinaatit();
    laskeJoukkueenMatka();
    paivitaMatkat();
    mymap.removeLayer(marker);
    mymap.removeLayer(valittu);
    luoYmpyra(e.target._latlng.lat, e.target._latlng.lng, valittu.rastin_id);
    for(var i = 0; i < data.joukkueet.length; i++){
        poistaKartalta(data.joukkueet[i].nimi);
    }
    var j = 0;
    while(j < $('#karttalistaus li').length){
        piirraKartalle($('#karttalistaus li')[j].id, $('#karttalistaus li')[j].style.backgroundColor);
        j++;
    }    
    marker = null;
    valittu = null;
}