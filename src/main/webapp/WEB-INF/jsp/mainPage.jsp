<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main Page</title>
    <link rel="stylesheet" type="text/css" href="mainPageStyle.css">
</head>
<body>
<div class="container">
    <div class="user-area">
        <h2>User Area</h2>
        <p>Welcome, ${user_name}<span id="user-name"></span></p>
    </div>
    <div class="subscription-area">
        <h2>Subscription Area</h2>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Title</th>
                <th>Artist</th>
                <th>Year</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${subscribedMusic}" var="display">
                <tr>
                    <td>${display.title}</td>
                    <td>${display.artist}</td>
                    <td>${display.year}</td>
                    <td><a type="button" class="btn btn-lg btn-primary btn-block" href="/remove?title=${display.title}"> Remove</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div id="subscription-list"></div>
    </div>


    <div class="query-area">
        <h2>Query Area</h2>
        <form action="/query" method="POST">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title"><br>

            <label for="artist">Artist:</label>
            <input type="text" id="artist" name="artist"><br>

            <label for="year">Year:</label>
            <input type="text" id="year" name="year"><br>

            <input type="submit" value="Submit">
        </form>
        <table class="table table-striped">
        <thead>
        <tr>
            <th>Title</th>
            <th>Artist</th>
            <th>Year</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${queryMusic}" var="displayQuery">
            <tr>
                <td>${displayQuery.title}</td>
                <td>${displayQuery.artist}</td>
                <td>${displayQuery.year}</td>
                <td><a type="button" class="btn btn-lg btn-primary btn-block" href="/subscribe?title=${displayQuery.title}"> Subscribe</a></td>
            </tr>
        </c:forEach>
        </tbody>
        </table>
<%--        ${queryResult}--%>
<%--        ${queryErrorMessage}--%>

        <div id="query-list"></div>
    </div>


</div>
<div class="logout-link">
    <a href=login>Logout</a>
</div>
<script type="text/javascript">
        // Get the username from the session storage
           const userName = sessionStorage.getItem('userName');
        document.getElementById('user-name').textContent = userName;
        // Get the subscription list from DynamoDB and display it in the subscription area
        const subscriptionList = JSON.parse(sessionStorage.getItem('subscriptionList'));
        if (subscriptionList) {
            const subscriptionDiv = document.getElementById('subscription-list');
            subscriptionList.forEach(item => {
                const musicDiv = document.createElement('div');
                musicDiv.classList.add('music-info');

                const titleSpan = document.createElement('span');
                titleSpan.classList.add('music-title');
                titleSpan.textContent = item.title;

                const artistSpan = document.createElement('span');
                artistSpan.classList.add('music-artist');
                artistSpan.textContent = item.artist;

                const yearSpan = document.createElement('span');
                yearSpan.classList.add('music-year');
                yearSpan.textContent = item.year;

                const image = document.createElement('img');
                image.classList.add('music-image');
                image.setAttribute('src', item.image);

                const removeButton = document.createElement('button');
                removeButton.classList.add('remove-button');
                removeButton.textContent = 'Remove';
                removeButton.addEventListener('click', () => {
                    removeSubscription(item.title);
                });

                musicDiv.appendChild(titleSpan);
                musicDiv.appendChild(artistSpan);
                musicDiv.appendChild(yearSpan);
                musicDiv.appendChild(image);
                musicDiv.appendChild(removeButton);
                subscriptionDiv.appendChild(musicDiv);
            });
        }

        // Query music information and display the results in the query area
        const queryButton = document.getElementById('query-button');
        queryButton.addEventListener('click', () => {
            const title = document.getElementById('query-title').value;
            const year = document.getElementById('query-year').value;
            const artist = document.getElementById('query-artist').value;
            queryMusic(title, year, artist);
        });

        function queryMusic(title, year, artist) {
        // Send a request to the backend to
            / query the music information
// Use fetch API to send a GET request to the backend endpoint
            fetch(/query?title=${title}&year=${year}&artist=${artist})
                .then(response => response.json())
                .then(result => {
// Display the query result in the query area
                    const queryResultDiv = document.getElementById('query-result');
                    queryResultDiv.innerHTML = '';
                    if (result.length === 0) {
                        // If no result is retrieved, display an error message
                        const queryErrorDiv = document.createElement('div');
                        queryErrorDiv.classList.add('query-error');
                        queryErrorDiv.textContent = 'No result is retrieved. Please query again.';
                        queryResultDiv.appendChild(queryErrorDiv);
                    } else {
                        // If result is retrieved, display the music information and subscribe button
                        result.forEach(item => {
                            const musicDiv = document.createElement('div');
                            musicDiv.classList.add('music-info');

                            const titleSpan = document.createElement('span');
                            titleSpan.classList.add('music-title');
                            titleSpan.textContent = item.title;

                            const artistSpan = document.createElement('span');
                            artistSpan.classList.add('music-artist');
                            artistSpan.textContent = item.artist;

                            const yearSpan = document.createElement('span');
                            yearSpan.classList.add('music-year');
                            yearSpan.textContent = item.year;

                            const image = document.createElement('img');
                            image.classList.add('music-image');
                            image.setAttribute('src', item.image);

                            const subscribeButton = document.createElement('button');
                            subscribeButton.classList.add('subscribe-button');
                            subscribeButton.textContent = 'Subscribe';
                            subscribeButton.addEventListener('click', () => {
                                subscribeMusic(item);
                            });

                            musicDiv.appendChild(titleSpan);
                            musicDiv.appendChild(artistSpan);
                            musicDiv.appendChild(yearSpan);
                            musicDiv.appendChild(image);
                            musicDiv.appendChild(subscribeButton);
                            queryResultDiv.appendChild(musicDiv);
                        });
                    }
                }));
        }

        function removeSubscription(title) {
            // Send a request to the backend to remove the subscribed music information
            // Use fetch API to send a DELETE request to the backend endpoint
            fetch(`/remove?title=${title}`, {method: 'DELETE'})
                .then(response => response.json())
                .then(result => {
                    // Update the subscription list in session storage and refresh the page
                    sessionStorage.setItem('subscriptionList', JSON.stringify(result));
                    location.reload();
                });
        }

        function subscribeMusic(item) {
            // Send a request to the backend to subscribe the music information
            // Use fetch API to send a POST request to the backend endpoint
            fetch('/subscribe', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(item)
            })
                .then(response => response.json())
                .then(result => {
                    // Update the subscription list in session storage and refresh the page
                    sessionStorage.setItem('subscriptionList', JSON.stringify(result));
                    location.reload();
                });
        }
</script>
</body>
</html>


