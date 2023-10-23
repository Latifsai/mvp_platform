let email;

document.addEventListener("DOMContentLoaded", function () {

    const users = [];
    const response = localStorage.getItem("UserByNeeds");

    function createUserCard(user) {
        const userCard = document.createElement("div");
        email = user.email;
        userCard.className = "user-card";
        userCard.innerHTML = `
            <img src="${user.photo}" alt="${user.name}">
            <h2>${user.firstName + " " + user.surname}</h2>
            <p>${user.informationAboutUser}</p>
            <p>experienxe: ${user.experience}</p>
            <p>Servce: ${user.services[0].typeOfService}</p>
        `;
        return userCard;
    }

    function addUser(user) {
        users.push(user);
        const userCard = createUserCard(user);
        userCard.setAttribute("data-username", user.username);
        const userCardsContainer = document.querySelector(".user-cards");
        userCardsContainer.appendChild(userCard);
    }

    const data = JSON.parse(response);
    console.log(data);
    data.forEach(user => {
        addUser(user)
        console.log(`Username: ${username}, Photo: ${photo}, Description: ${description}`);
    });

    const userCards = document.querySelectorAll(".user-card");

    userCards.forEach(userCard => {
        const moreButton = userCard.querySelector(".more-button");
        moreButton.addEventListener("click", () => {
            window.location.href = 'dataForVisitor.html'; // Замените на путь к другому аккаунту
        });
    });

})

