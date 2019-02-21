const handleVisibleSearch = () => {
  const containerSearch = document.getElementById('container-search')
  const arrowSearch = document.getElementById('arrow-search')
  const row = document.getElementById('row-search')
  let visible = containerSearch.dataset.visible
  if(visible == 'show'){
    containerSearch.style.animation = 'hideSearch 1.2s 1 linear forwards' 
    arrowSearch.style.animation = 'rotateArrow 1.2s 1 linear forwards' 
    row.style.display  = 'none'
    containerSearch.dataset.visible = 'hide'
  } else if (visible == 'hide') {
    containerSearch.style.animation = 'showSearch 1.2s 1 linear forwards' 
    arrowSearch.style.animation = 'rotateArrow2 1.2s 1 linear forwards'
    row.style.display  = 'flex'
    containerSearch.dataset.visible = 'show'
  }
 
}
