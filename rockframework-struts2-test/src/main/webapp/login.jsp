<html>
	<head>
		<title>Login</title>
	</head>
	<body>
		<form method="POST" action="/j_security_check">
			Username: <input type="text" name="j_username" id="j_username" />
			<br />
			Password: <input type="text" name="j_password" id="j_password" />
			<br />
			<input type="submit" name="submit" id="submit" value="Login" />
		</form>
	</body>
</html>