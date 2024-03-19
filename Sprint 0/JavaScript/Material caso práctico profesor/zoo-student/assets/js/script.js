const colors = ['ligth-grey', 'red', 'blue', 'green', 'yellow', 'orange', 'purple'];
const colorslist = document.querySelector('.profile-color-options-container');
const colorsContainer = document.querySelector('.profile-color-options');
const hideColorsOnCLick = document.querySelector('.profile-settings-btn');
const infoContainer = document.querySelector('.profile-bio-container');
const dialog = document.querySelector('#modal');
const divImages = document.querySelectorAll('.gallery-item');
const closeModal = document.querySelector('#close-modal');

closeModal.addEventListener('click', () => {
    dialog.close();
    document.querySelector('.modal-content').innerHTML = '';
})

divImages.forEach((divImage) =>{
    divImage.addEventListener('click', () => {
        dialog.showModal();
        const sourceImage = divImage.querySelector('img').src;
        const image = document.createElement('img');

        image.src = sourceImage;

        document.querySelector('.modal-content').appendChild(image); 
    })
})

infoContainer.addEventListener('dblclick', editInfo);

for(let i = 0; i < colors.length; i++){
    const color = colors[i];
    const li = document.createElement('li');
    li.classList.add(color, 'box-color');
    colorslist.appendChild(li);
    li.addEventListener('click', changeColor);

}

function editInfo() {
    const info = infoContainer.querySelector('p');
    const input = document.createElement('input');
    input.type = "text";
    input.classList.add('profile-bio-input');
    input.value = info.textContent;
    input.focus();
    info.textContent = '';

    infoContainer.appendChild(input);

    input.addEventListener('blur', () => {
        info.textContent = input.value.trim()
        infoContainer.removeChild(input)
    });
}

function toggleColorsList() {
    colorsContainer.classList.toggle('show');
}

function changeColor(event) {
    const color = event.target.classList[0]
    document.querySelector('body').classList.remove(...colors);
    document.querySelector('body').classList.add(color)  
}

hideColorsOnCLick.addEventListener('click', toggleColorsList);

