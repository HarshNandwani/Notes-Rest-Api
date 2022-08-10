# Notes Rest Api
**Notes-Rest-Api** is a sample project made to demonstrate my skills of developing RESTful APIs. 

**Notes-Rest-Api** has apis for user registration, login and CRUD operations on notes. 

All endpoints end points use proper HTTP Status codes also all APIs expect /auth/.. require authorization.

# Built With :wrench:
- **Spring Boot**
- **Kotlin**
- **JWT**

# How to run the project
You would need [JDK 17 (preferably liberica jdk)](https://bell-sw.com/pages/downloads/#/java-17-current) and [MySQL server](https://dev.mysql.com/downloads/mysql/) installed.
- Clone the project
- Goto src\main\resources and open application-dev.properties
- Change the database URL, user and password to yours.
- Come back to root folder and run ```./gradlew bootRun``` command

Voilà! You have the project running. Use any API client to check the end points.

# API end points
## User
### POST /auth/register 
Creates a user.

Takes below application/json in request body.
```
{
    "name": "User",
    "email": "user@example.com",
    "password": "password"
}
```
If successful returns **200 OK** with message success. If email already exists returns **409 Conflict** with message indicating the same. **400 Bad Request** if any field is missing.

### POST /auth/login 

Takes email, password in application/x-www-form-urlencoded and provides access token.

Returns a JWT access token with status **200 OK** if email, password are valid else returns **401 unauthorized**.


## Notes end points

All below end points require Authorization key and JWT access token in header, if not they return **401 unauthorized**.

### POST api/notes 
Creates a single note.
Takes below application/json in request body.
```
{
    "title": "Title",
    "content": "Some random content here"
}
```
Note: No need to pass user id, it will be distinguished based on access token in header

If successful returns **201 Created** with message success and id of note. If request is not formed properly returns **400 Bad Request**.

### GET api/notes/{noteId} 
Returns a single note with the id.

If note is found returns note with **200 OK** else gives **404 Not Found**.

### GET api/notes 
Returns a list of Notes for that user

### PUT api/notes 
Updates a note if already exists or else creates new.

If note is found and updated returns **204 No Content** with empty body. If note is not found a new note is created and **201 Created** with message success and id of note is returned.

### DELETE api/notes/{noteId} 
Deletes a note.

If note is found it is deleted and **204 No Content** is returned with empty body else gives **404 Not Found**.

# Contact me :email:
- LinkedIn: [Harsh Nandwani](https://www.linkedin.com/in/harsh-nandwani/)
- E-Mail: developer.harsh.nandwani@gmail.com
