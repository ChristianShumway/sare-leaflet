const handleChangeOptions = option => {
  const title = document.getElementById(`option-${option}`)
  const checkBox = document.getElementById(`checkbox-${option}`)
  checkBox.checked ? title.classList.add('option-active') : title.classList.remove('option-active')
}
