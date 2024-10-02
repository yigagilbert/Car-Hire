import React from 'react';
import Landingpage from "./landingpage"
import Signin from "./signin"
import Signup from "./signup"
import Home from './components/Home'
import Viewdetails from "./viewdetails"
import Navbar from './components/admin/navbar'
import Addcar from './components/admin/addcar'
import Removecar from './components/admin/removecar'
import AdminSignin from './components/admin/adminsignin'
import AdminLanding from './components/admin/adminLandingPage'
import Location from './components/admin/locations'
import AddLocation from './components/admin/addLocation'
import Pickup from './PickAndCancel'
import Returncar from './ReturnCar'
import Profile from './DriverProfile'
import UserNavBar from './Navigationbar'
//import './App.css';
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
const App = ()  => {
  return (
    
    
    <Router>
      <div>
        <Route exact path="/" component={Home}/>
        </div>

     <div>
        <Route path="/signin" component={Signin}/>
        <Route path="/signup" component={Signup}/>
        <Route path="/userHome" component={Landingpage}/>
        <Route path="/carDetailsPage" component={Viewdetails}/>
        <Route path="/nbar" component={Navbar}/>
        <Route path="/navbar" component={UserNavBar}/>


        <Route path="/locations" component={Location}/>
        <Route path="/addlocation" component={AddLocation}/>
        <Route path="/addcar" component={Addcar}/>
        <Route path="/removecar" component={Removecar}/>
        <Route path="/adminsignin" component={AdminSignin}/>
        <Route path="/adminLanding" component={AdminLanding}/>
        <Route path="/Pickup" component={Pickup}/>
        <Route path="/return" component={Returncar}/>
        <Route path="/profile" component={Profile}/>
        
</div>
      
        
      </Router>
      
         
  );
}

export default App;
