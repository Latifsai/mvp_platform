const modal = document.getElementById('modal');
const closeBtn = document.querySelector('.close-btn');
modal.style.top = '0';

modal.addEventListener('click', (event) => {
    if (event.target === modal) {
        modal.style.display = 'none';
    }
})

document.addEventListener("DOMContentLoaded", function() {
    const openMenuButton = document.getElementById("open-menu-button");
    const sideMenu = document.getElementById("sideMenu");

    openMenuButton.addEventListener("click", function() {
        if (sideMenu.style.left === "0px" || sideMenu.style.left === "") {
            sideMenu.style.left = "-250px"; // Скрываем меню
        } else {
            sideMenu.style.left = "0px"; // Показываем меню
        }
    });
});


const usersJSON = localStorage.getItem('users');
console.log("users: " + usersJSON);
if (usersJSON) {
    const usersData = JSON.parse(usersJSON);
    const userList = usersData.users;
    console.log("list: ", userList);
    userList.forEach(user => {
        console.log('Username:', user.username);
        document.getElementById('name').textContent = user.firstName + " " + user.surname;
        document.getElementById('firma').textContent = user.firmaTitle;
        document.getElementById('email').textContent = user.email;
        document.getElementById('title').textContent = user.services[0].serviceTitle;
        document.getElementById('max').textContent = user.services[0].maxPrice;
        document.getElementById('min').textContent = user.services[0].minPrice;
    });
}
const profileButton = document.querySelector('button');

document.addEventListener("DOMContentLoaded", function () {
    const profileButton = document.getElementById("profile-button");

    if (profileButton) {
        profileButton.addEventListener("click", () => {
            window.location.href = 'dataForVisitor.html';
        });
    }
});

const token = localStorage.getItem('token');
const username = localStorage.getItem('username');
console.error("token and json" + token, username)

if (username && token) {
    fetch(`http://localhost:8181/api/users/username/${username}`, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
        },
    })

        .then(response => {
            return response.json();
        })
        .then(jsonData => {
            console.error(jsonData);
            document.getElementById("us").textContent = jsonData.username;
            document.getElementById('userEmail').textContent = jsonData.email;
            document.getElementById('uniqueNumber').textContent = jsonData.uniqueNumber || ""; // Проверка на наличие uniqueNumber
            document.getElementById('surname').textContent = jsonData.surname;
            document.getElementById('firmaTitle').textContent = jsonData.firmaTitle;
            document.getElementById('credits').textContent = jsonData.credits || ""; // Проверка на наличие credits
            document.getElementById('reputation').textContent = jsonData.reputation;
            document.getElementById('informationAboutUser').textContent = jsonData.informationAboutUser;
            if (jsonData.services.length > 0) {
                const service = jsonData.services[0]; // Assuming there's only one service in the array
                document.getElementById('serviceTitle').textContent = service.serviceTitle;
                document.getElementById('maxPrice').textContent = service.maxPrice;
                document.getElementById('minPrice').textContent = service.minPrice;
                document.getElementById('typeOfService').textContent = service.typeOfService;
            } else {
                document.getElementById('serviceTitle').textContent = "";
                document.getElementById('maxPrice').textContent = "";
                document.getElementById('minPrice').textContent = "";
                document.getElementById('typeOfService').textContent = "";
            }
            document.getElementById('labels').value = jsonData.labels || "";
            document.getElementById('price').value = jsonData.wantedPrice || "";
            document.getElementById('experienceOfUserToFind').value = jsonData.wantedExperience || "";
            document.getElementById('reput').value = jsonData.wandetReputation || ""
                .catch(error => {
                    console.error('Error:', error);
                });
        })
}

document.addEventListener("DOMContentLoaded", function () {
    const button = document.getElementById("butt");

    if (button) {
        button.addEventListener("click", function () {
            if (username && token) {
                fetch(`http://localhost:8181/api/needs/${username}`, {
                    method: 'GET',
                    headers: {
                        'Authorization': `Bearer ${token}`,
                    },
                }).then(response => {
                    return response.json();
                }).then(data => {
                    console.log(data);
                    localStorage.setItem("UserByNeeds", JSON.stringify(data))
                    window.location.href = 'ListOfUsers.html';
                }).catch(error => {
                    console.error('Error:', error);
                });
            }
        });

    }

})

document.addEventListener("DOMContentLoaded", function () {
    const menuItems = document.querySelectorAll(".menu-item");
    const searchViews = document.querySelectorAll(".search-view");

    menuItems.forEach((item) => {
        item.addEventListener("click", () => {
            const viewName = item.getAttribute("data-view");
            searchViews.forEach((view) => {
                view.style.display = "none";
            });
            document.getElementById(viewName).style.display = "block";
        });
    });


    menuItems.forEach((menuItem) => {
        menuItem.addEventListener("click", function () {
            const view = menuItem.getAttribute("data-view");
            // Здесь вы можете использовать значение "view" для навигации или других действий
            console.log("Selected view:", view);

            if (view === 'view1') {
                window.location.href = 'SkillsSearch.html';
            }
        });

    });
});
