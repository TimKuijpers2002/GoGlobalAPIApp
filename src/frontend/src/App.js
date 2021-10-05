import React from "react";
import Home from "./Pages/Home";
import Admin from "./Pages/Admin";
import Location from "./Pages/Location";
import LocationSubmit from "./Pages/LocationSubmit";
import Profile from "./Pages/Profile";
import Report from "./Pages/Report";
import {Route, BrowserRouter as Router} from "react-router-dom";
import './App.css';

function App() {

  return (
    <Router>
      <Route path="/" exact component={Home}/>
      <Route path="/Admin" component={Admin}/>
      <Route path="/Location" component={Location}/>
      <Route path="/LocationSubmit" component={LocationSubmit}/>
      <Route path="/Profile" component={Profile}/>
      <Route path="/Report" component={Report}/>
    </Router>
  );
}

export default App;
