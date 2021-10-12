import React from "react";
import Home from "./Pages/Home";
import Admin from "./Pages/Admin";
import Location from "./Pages/Location";
import LocationSubmit from "./Pages/LocationSubmit";
import Profile from "./Pages/Profile";
import Report from "./Pages/Report";
import {Route, BrowserRouter as Router} from "react-router-dom";
import './App.css';
import POSTAdmin from "./Pages/PostAdmin";

function App() {

  return (
    <Router>
      <Route path="/" exact component={Home}/>
      <Route path="/Admin" exact component={Admin}/>
        <Route path="/Admin/Create" exact component={POSTAdmin}/>
      <Route path="/Location" exact component={Location}/>
      <Route path="/LocationSubmit" exact component={LocationSubmit}/>
      <Route path="/Profile" exact component={Profile}/>
      <Route path="/Report" exact component={Report}/>
    </Router>
  );
}

export default App;
