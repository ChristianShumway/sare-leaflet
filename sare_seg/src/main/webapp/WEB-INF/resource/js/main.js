const handleChangeOptions = option => {
  const title = document.getElementById(`option-${option}`)
  const checkBox = document.getElementById(`checkbox-${option}`)
  checkBox.checked ? title.classList.add('option-active') : title.classList.remove('option-active')
}

const buscarUE = () => {
  const viewSearchContainer = document.getElementById('arrow-search')
  viewSearchContainer.removeAttribute('onclick')
  handleVisibleSearch()
  handleVisibleRatifica()
}

const ratificar = request => {
  const viewSearchContainer = document.getElementById('arrow-search')
  viewSearchContainer.setAttribute('onclick','handleVisibleSearch()')
  handleVisibleSearch()
  handleVisibleRatifica()
}