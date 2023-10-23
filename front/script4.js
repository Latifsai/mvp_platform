document.addEventListener("DOMContentLoaded", function () {

    const users = [];
    const response = localStorage.getItem("UserByNeeds");

    function createUserCard(user) {
        const userCard = document.createElement("div");
        userCard.className = "user-card";
        userCard.innerHTML = `
            <img src="${user.photo}" alt="${user.name}">
            <h2>${user.firstName + " " + user.surname}</h2>
            <p>${user.informationAboutUser}</p>
            <p>max price: $<span id="max">${user.services[0].maxPrice}</span></p>
            <p>min price: $<span id="min">${user.services[0].minPrice}</span></p>
            <p>${user.experience}</p>
            <button>more</button>
        `;
        return userCard;
    }

    function addUser(user) {
        users.push(user);
        const userCard = createUserCard(user);
        const userCardsContainer = document.querySelector(".user-cards");
        userCardsContainer.appendChild(userCard);
    }

    const data = JSON.parse(response);
    console.log(data);
    data.forEach(user => {
        addUser(user)

        console.log(`Username: ${username}, Photo: ${photo}, Description: ${description}`);
    });


})

