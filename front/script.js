
document.addEventListener("DOMContentLoaded", function () {
    let email;
    let link;
    const usersJSON = localStorage.getItem('users');
    console.log("users: " + usersJSON);
    const userNameHeader = document.getElementById("userNameHeader");

    if (usersJSON) {
        const usersData = JSON.parse(usersJSON);
        const userList = usersData.users;
        console.log("list: ", userList);
        userList.forEach(user => {
            userNameHeader.textContent = user.firstName + " " + user.surname;
            document.getElementById("firmaTitle").textContent = user.firmaTitle;
            document.getElementById("email").textContent = user.email;
            document.getElementById("experience").textContent = `${user.experience} years`;
            document.getElementById("reputation").textContent = user.reputation;
            document.getElementById("serviceTitle").textContent = user.services[0].serviceTitle;
            document.getElementById("maxPrice").textContent = user.services[0].maxPrice;
            document.getElementById("minPrice").textContent = user.services[0].minPrice;
            document.getElementById("typeOfService").textContent = user.services[0].typeOfService;
            email = user.email;
            link = user.photo;
        });
    }
    userImage.src = link;

    const photoInput = document.getElementById("photoInput");
    const addPhotoButton = document.getElementById("addPhotoButton");
    const photoList = document.getElementById("photoList");

    addPhotoButton.addEventListener("click", function () {
        if (photoInput.files.length > 0) {
            const photoItem = document.createElement("div");
            photoItem.classList.add("photo-item");

            const img = document.createElement("img");
            img.src = URL.createObjectURL(photoInput.files[0]);
            img.alt = "User Photo";

            const deleteButton = document.createElement("button");
            deleteButton.textContent = "Delete";
            deleteButton.addEventListener("click", function () {
                photoList.removeChild(photoItem);
            });

            photoItem.appendChild(img);
            photoItem.appendChild(deleteButton);

            photoList.appendChild(photoItem);
            photoInput.value = ""; // Очищаем поле выбора файла
        }
    });
});

document.getElementById('getEmailButton').addEventListener('click', function () {
    var gmailLink = 'https://mail.google.com/mail/?view=cm&fs=1&to=' + email;
    window.open(gmailLink);
});