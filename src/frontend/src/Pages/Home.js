import React from "react";
import {Sidebar} from "../Components/Sidebar";

export default function Home() {
    return (
        <Sidebar data={
            <div>
                    Hello world!
            </div>
        }/>
    );
}