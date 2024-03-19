<!DOCTYPE html>
<html>
<head>
    <title>Register Page</title>
    <link rel="stylesheet" type="text/css" href="registerStyle.css">
</head>
<body>
<div class="container">
    <h1>Register</h1>
    <form action="/register" method="POST">
        <label for="email">Email</label>
        <input type="email" id="email" name="email" placeholder="Enter Email" required>
        ${emailErrorMessage}

        <label for="username">Username</label>
        <input type="text" id="username" name="username" placeholder="Enter Username" required>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="Enter Password" required>

        <input type="submit" value="Register">
    </form>
</div>
</body>
</html>
