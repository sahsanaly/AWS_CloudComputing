<!DOCTYPE html>
<html xmlns:c="">
<head>
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="loginStyle.css">
</head>
<body>
<div class="login">
    <h1>Log in</h1>
    <form action="/login" method="POST">
        <label >Email</label>
        <input type="text" name="email" placeholder="Enter Email" required>
        <label >Password</label>
        <input type="password" name="password" placeholder="Enter Password" required>
        <input type="submit" name="submit" value="Login">
        <p>Don't have an account? <a href="/register">Register here</a></p>
    </form>
    ${errorMessage}
</div>
</body>
</html>