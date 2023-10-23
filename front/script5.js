document.addEventListener("DOMContentLoaded", function () {
    const searchForm = document.getElementById("search-form");
    const searchResults = document.getElementById("search-results");

    const token = localStorage.getItem("token");

    searchForm.addEventListener("submit", function (e) {
        e.preventDefault();
        searchResults.innerHTML = "Searching..."; // Отображение сообщения о поиске

        const query = searchForm.querySelector("input[name='query']").value;

        fetch(`http://localhost:8181/api/users/skills/${query}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                'Authorization': `Bearer ${token}`,
            },
        })
            .then(response => response.json())
            .then(data => {
                searchResults.innerHTML = "Search results:";
                data.results.forEach(result => {
                    const resultElement = document.createElement("p");
                    resultElement.textContent = result;
                    searchResults.appendChild(resultElement);
                });
            })
            .catch(error => {
                // Обработка ошибок
                searchResults.innerHTML = "Error: " + error.message;
            });
    });
});

document.addEventListener("DOMContentLoaded", function () {
    // Получите данные о пользователях (ваш ответ от сервера)
    const userData = [
        // Ваши данные о пользователях
    ];

    // Найдите контейнер, в который вы будете добавлять карточки
    const usersContainer = document.getElementById("users-container");

    // Переберите данные о пользователях и создайте карточки
    userData.forEach(user => {
        const userCard = document.createElement("div");
        userCard.classList.add("user-card");

        // Создайте элементы и добавьте данные пользователя
        const userImage = document.createElement("img");
        userImage.src = user.photo;
        userImage.alt = "User Image";

        const userName = document.createElement("h2");
        userName.textContent = `${user.firstName} ${user.surname}`;

        const userUsername = document.createElement("p");
        userUsername.textContent = `Username: ${user.username}`;

        const userEmail = document.createElement("p");
        userEmail.textContent = `Email: ${user.email}`;

        const userExperience = document.createElement("p");
        userExperience.textContent = `Experience: ${user.experience} years`;

        const userFirmaTitle = document.createElement("p");
        userFirmaTitle.textContent = `Firma Title: ${user.firmaTitle}`;

        // Добавьте элементы в карточку пользователя
        userCard.appendChild(userImage);
        userCard.appendChild(userName);
        userCard.appendChild(userUsername);
        userCard.appendChild(userEmail);
        userCard.appendChild(userExperience);
        userCard.appendChild(userFirmaTitle);

        // Добавьте карточку пользователя в контейнер
        usersContainer.appendChild(userCard);
    });
});
