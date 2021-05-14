let containerGral = document.getElementById('container-search-gral');
let searching = document.getElementById('wrapper-searching');
let containerResultSearch = document.getElementById('show-results');
let cardOtherResults = document.querySelector('.wrapper-other-results');
let cardListLocations = document.querySelector('.wrapper-locations-result');
let cardWhatsHere = document.querySelector('.wrapper-whats-here');
let activeloading = false;
let existData = false;
let locationSelected = false;

function searchApi() {
  let inputSearch = document.getElementById('busqueda-api');
  if(inputSearch.value)  {
    console.log(inputSearch.value);
    expandPanelSearch();
    searchingInfo();
  } else {
    alert('Ingresa info a buscar');
  }
}

function expandPanelSearch() {
  containerGral.classList.add('active');
  if(existData) {
    containerResultSearch.style.display = 'block';
  }
}

function contractrPanelSearch() {
  containerGral.classList.remove('active');
  containerResultSearch.style.display = 'none';
}

function hidePanel() {
  containerGral.classList.toggle('hide-panel');
}

function activeSearching() {
 activeloading = true;
 searching.style.display = 'flex';
}

function desActiveSearching() {
  activeloading = false;
  searching.style.display = 'none';
 }

 function searchingInfo() {
  activeSearching();
  setTimeout ( () => {
    desActiveSearching();
    existData = true;
    containerResultSearch.style.display = 'block';
  }, 300);

 }

 function collapsableCardOtherResults(){
  cardOtherResults.classList.toggle('hide-info');
 }

 function showLocation() {
   locationSelected = true;
  cardWhatsHere.style.display = 'block';
  cardOtherResults.style.display = 'none';
  cardListLocations.style.display = 'none';

 }