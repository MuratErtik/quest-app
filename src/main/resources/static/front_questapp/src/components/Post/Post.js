import React, {useState , useEffect} from "react";

import ReactDOM from "react-dom";

function Post(){
    const [error,setError]= useState(null);

    const [isLoaded,setIsLoaded]= useState(false);

    const [postList,setPostList]= useState([]);

    useEffect(() =>{
        fetch("http://localhost:8080/posts")
        .then(res=>res.json())
        .then(
            (result) => {

                setIsLoaded(true);
                setPostList(result);

            },
            (error) => {
                setIsLoaded(true);//kod bir sekilde calisti
                setError(error);


            }
        )
    },[])
    if(error){
        return <div>error!</div>
    }
    else if(!isLoaded){
        return <div>loading...</div>

    }
    else{
        return(
            <ul>
                {postList.map(post => (
                    <li>
                        {post.title} {post.text}
                    </li>
                ))}
            </ul>
            
        )
    }

}

export default Post;
//disardan erismek isteyen Post ismi ile erisebilsin diye
