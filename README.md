Quest-App is a backend application designed to provide users with a platform where they can freely share their ideas, express their opinions by liking posts they find interesting, and engage in discussions by leaving comments on various topics.


Project Structure

The project is organized into the following packages:

1. config

Contains the configuration files for security and other settings.

SecurityConfig.java: Configures Spring Security for authentication and authorization.

2. controllers

Defines REST API endpoints to handle requests from the client.

AuthController.java: Manages authentication and token generation.

CommentController.java: Handles operations related to comments.

LikeController.java: Handles like-related operations.

PostController.java: Manages post-related operations.

UserController.java: Manages user-related operations.

3. entities

Defines the core data models used in the application.

Comment.java: Represents the comments entity.

Like.java: Represents the likes entity.

Post.java: Represents the posts entity.

RefreshToken.java: Represents the refresh tokens used for authentication.

User.java: Represents the users entity.

4. exceptions

Custom exception handling.

UsernameNotFoundException.java: Handles errors when a username is not found.

5. repository

Interfaces for database operations.

CommentRepository.java: Manages database interactions for comments.

LikeRepository.java: Manages database interactions for likes.

PostRepository.java: Manages database interactions for posts.

RefreshTokenRepository.java: Manages database interactions for refresh tokens.

UserRepository.java: Manages database interactions for users.

6. requests

Contains DTOs (Data Transfer Objects) for incoming API requests.

CommentCreateRequest.java: Handles comment creation requests.

CommentUpdateRequest.java: Handles comment update requests.

LikeCreateRequest.java: Handles like creation requests.

PostCreateRequest.java: Handles post creation requests.

PostUpdateRequest.java: Handles post update requests.

RefreshRequest.java: Handles token refresh requests.

UserRequest.java: Handles user-related requests.

7. responses

Contains DTOs for API responses.

AuthResponse.java: Authentication response object.

CommentResponse.java: Comment-related response object.

LikeResponse.java: Like-related response object.

PostResponse.java: Post-related response object.

UserResponse.java: User-related response object.

8. security

Handles security features like JWT authentication and token management.

JwtAuthenticationEntryPoint.java: Handles unauthorized access attempts.

JwtAuthenticationFilter.java: Filters incoming requests for JWT validation.

JwtTokenProvider.java: Provides utilities for generating and validating JWT tokens.

JwtUserDetails.java: Manages user details for authentication.

9. services

Business logic layer for handling the core functionality.

CommentService.java: Manages comment-related operations.

LikeService.java: Manages like-related operations.

PostService.java: Manages post-related operations.

RefreshTokenService.java: Manages refresh token-related operations.

UserDetailsServiceImpl.java: Implements user detail service for Spring Security.

UserService.java: Manages user-related operations.

Technologies Used

Spring Boot: Backend framework.

Spring Security: Authentication and authorization.

JWT (JSON Web Token): For stateless authentication.

JPA/Hibernate: ORM for database operations.

H2 Database: In-memory database for development.

Maven: Dependency management and build tool.

Features

User Authentication:

Registration and login.

JWT-based authentication.

Post Management:

Create, update, delete, and view posts.

Comment Management:

Add, update, delete, and view comments.

Like System:

Add or remove likes on posts.

Token Refresh:

Refresh expired JWT tokens.
