# GET
	http://localhost:8081/rest/user/authenticate
	
# Requests

	{
		"email":"test@user.com",
		"password":"testuser"
	}
	or
	{
		"email":"test@admin.com",
		"password":"testadmin"
	}
	
# POST
http://localhost:8081/rest/user/register
	
# Requests

	{
		"name" : "new",
		"email" :"newusergmailcom",
		"password" : "newuser",
		"mobile": "375259249572"
	}	

# Books

# GET
	http://localhost:8081/rest/books
	
# GET
	http://localhost:8081/rest/books/search/{searchText}
	
# GET By ID
	http://localhost:8081/rest/books/1

# POST
	http://localhost:8081/rest/books

# PUT
	http://localhost:8081/rest/books

# DELETE
	http://localhost:8081/rest/books/1


# Carts

# GET
	http://localhost:8081/rest/carts
	
# GET
	http://localhost:8081/rest/carts/search/{searchText}

# GET By ID
	http://localhost:8081/rest/carts/1

# POST
	http://localhost:8081/rest/carts

# PUT
	http://localhost:8081/rest/carts

# DELETE
	http://localhost:8081/rest/carts/1

# Requests

