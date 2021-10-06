import React from "react";
import {Sidebar} from "../Components/Sidebar";

export default function Profile() {
    return (
        <Sidebar data={
            <div>
                Hello profile!
            </div>
        }/>
    );
}