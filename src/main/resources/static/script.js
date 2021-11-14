document.addEventListener("DOMContentLoaded", () => {
    const readButton = document.getElementById("readFilesButton");

    readButton.addEventListener('click', () => {
        fetch('/files/read')
            .then(response => console.log(response))
            .then(_ => loadFiles())
            .then(_ => clearFiles())
    });

});

function loadFiles() {
    const fileList = document.getElementById("files");
    fileList.innerHTML = '';
    fetch('/files')
        .then(response => response.json())
        .then(data => [...data]
            .map(f => {
                const listItem = document.createElement('li');
                listItem.textContent = f.name

                const img = document.createElement("img");
                img.src = f.type === 'FOLDER' ? './images/folder.png' : './images/file.png'
                listItem.prepend(img);
                return listItem;
            })
            .forEach(li => fileList.appendChild(li)));
}

function clearFiles() {
    fetch('/files/clear')
        .then(data => console.log(data))
}