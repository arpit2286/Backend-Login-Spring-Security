Backend-Login-Spring-Security:

MYSQL Used as a Database.
User Creation by exposing a Registration API.
Activate account by using auth token via Email to the new user.
Authenticating User by Email and Password using Security filer as DaoAuthenticationProvider
Defining User Roles as ADMIN and USER  
Defining constant using ENUM.
Using Lombok to Cut down the Boilerplate code.

Future Scope :
Use Localization file for defining token timeout and USING Google Guice to fetch the property value.
Also Double check If token is expired then remove Username and password from Database. (A kind of Bug will be fixed in next phase).
