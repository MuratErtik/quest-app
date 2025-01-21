import React from "react";

import { useParams } from "react-router-dom";

function USer(){
    const {userId} = useParams();
    return (
        <div>
            USer {userId}
        </div>
    )
}

export default USer;