import React from "react";

import ReactDOM from "react-dom";

function Post(){
    const {title,text}=props;

    return (
        <div className="postContainer">
            {title}
            {text}
        </div>
    )
}

export default Post;
//disardan erismek isteyen Post ismi ile erisebilsin diye
