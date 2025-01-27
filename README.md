# Quest-App

Quest-App is a backend application designed to provide users with a platform where they can:

- **Share Ideas:** Users can post their thoughts, opinions, and ideas on the platform.
- **Like Posts:** Interact with content by liking posts they find interesting or enjoyable.
- **Comment on Posts:** Engage in discussions by leaving comments on posts.

## Features

1. **User Authentication**
   - Secure authentication using JWT (JSON Web Token).
   - Refresh token support for seamless user sessions.

2. **Post Management**
   - Create, update, and delete posts.
   - Retrieve posts by specific users or all users.

3. **Comments**
   - Add comments to posts.
   - Update and delete comments.

4. **Likes**
   - Like posts and track popular content.

5. **Security**
   - Integrated with Spring Security for role-based access control.
   - Custom `JwtAuthenticationFilter` and `JwtTokenProvider` for secure API interactions.

## Project Structure

```
src
├── main
│   ├── java/com/project/questapp
│   │   ├── config
│   │   │   └── SecurityConfig.java
│   │   ├── controllers
│   │   │   ├── AuthController.java
│   │   │   ├── CommentController.java
│   │   │   ├── LikeController.java
│   │   │   ├── PostController.java
│   │   │   └── UserController.java
│   │   ├── entities
│   │   │   ├── Comment.java
│   │   │   ├── Like.java
│   │   │   ├── Post.java
│   │   │   ├── RefreshToken.java
│   │   │   └── User.java
│   │   ├── exceptions
│   │   │   └── UsernameNotFoundException.java
│   │   ├── repository
│   │   │   ├── CommentRepository.java
│   │   │   ├── LikeRepository.java
│   │   │   ├── PostRepository.java
│   │   │   ├── RefreshTokenRepository.java
│   │   │   └── UserRepository.java
│   │   ├── requests
│   │   │   ├── CommentCreateRequest.java
│   │   │   ├── CommentUpdateRequest.java
│   │   │   ├── LikeCreateRequest.java
│   │   │   ├── PostCreateRequest.java
│   │   │   ├── PostUpdateRequest.java
│   │   │   ├── RefreshRequest.java
│   │   │   └── UserRequest.java
│   │   ├── responses
│   │   │   ├── AuthResponse.java
│   │   │   ├── CommentResponse.java
│   │   │   ├── LikeResponse.java
│   │   │   ├── PostResponse.java
│   │   │   └── UserResponse.java
│   │   ├── security
│   │   │   ├── JwtAuthenticationEntryPoint.java
│   │   │   ├── JwtAuthenticationFilter.java
│   │   │   ├── JwtTokenProvider.java
│   │   │   └── JwtUserDetails.java
│   │   ├── services
│   │   │   ├── CommentService.java
│   │   │   ├── LikeService.java
│   │   │   ├── PostService.java
│   │   │   ├── RefreshTokenService.java
│   │   │   ├── UserDetailsServiceImpl.java
│   │   │   └── UserService.java
│   │   └── QuestAppApplication.java
│   ├── resources
│   │   ├── static
│   │   ├── templates
│   │   └── application.properties
├── test
├── target
└── .gitattributes
```

## Technologies Used

- **Java**: Core programming language.
- **Spring Boot**: Framework for building the backend application.
- **Spring Security**: Handles authentication and authorization.
- **JPA/Hibernate**: Manages database interactions.
- **MySQL**: Database used for storing application data.
- **Lombok**: Simplifies code with annotations like `@Data`, `@Getter`, and `@Setter`.



## API Endpoints

### Authentication
- **POST** `/auth/login`: User login.
- **POST** `/auth/register`: User registration.

### Posts
- **GET** `/posts`: Get all posts.
- **POST** `/posts`: Create a new post.
- **PUT** `/posts/{postId}`: Update a post.
- **DELETE** `/posts/{postId}`: Delete a post.

### Comments
- **GET** `/comments`: Get all comments.
- **POST** `/comments`: Add a comment.
- **PUT** `/comments/{commentId}`: Update a comment.
- **DELETE** `/comments/{commentId}`: Delete a comment.

### Likes
- **POST** `/likes`: Like a post.
- **DELETE** `/likes/{likeId}`: Remove a like.


