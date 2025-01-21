import React, { useState, useEffect } from "react";  
import Post from "../Post/Post";

function Home() {
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [postList, setPostList] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/posts")
            .then(res => res.json())
            .then(
                (result) => {
                    console.log(result);
                    setIsLoaded(true);
                    setPostList(result);
                },
                (error) => {
                    setIsLoaded(true);  
                    setError(error);
                }
            );
    }, []);

    if (error) {
        return <div>error!</div>;
    } else if (!isLoaded) {
        return <div>loading...</div>;
    } else {
        return (
            <div className="container">
                Home...
                {postList.map(post => (
                    <Post  title={post.title} text={post.text} />  // props olarak doğru şekilde geçirme
                ))}
            </div>
        );
    }
}

export default Home;
