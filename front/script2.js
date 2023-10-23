const modal = document.getElementById('modal');
const closeBtn = document.querySelector('.close-btn');
modal.style.top = '0';

modal.addEventListener('click', (event) => {
    if (event.target === modal) {
        modal.style.display = 'none';
    }
})

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
profileButton.addEventListener('click', () => {
    window.location.href = 'dataForVisitor.html'; // Замените 'URL_профиля_пользователя' на URL страницы профиля
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

$(document).ready(function () {
    $("#butt").click(function () {
        if (username && token) {
            fetch(`http://localhost:8181/api/needs/${username}`, {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`,
                },
            }).then(response => {
                    console.log(JSON.stringify(response))
                    return response.json();
                })
        }
    })
})

