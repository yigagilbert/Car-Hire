
import React ,{Component} from 'react';
import axios from 'axios';

import Navbar from '../src/Navigationbar';
import Paper from '@material-ui/core/Paper';
import Button from '@material-ui/core/Button';

import {  makeStyles } from '@material-ui/core/styles';
import SearchIcon from '@material-ui/icons/Search';
import Details from './detailspage';

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  paper: {
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,
  },
}));

export default class Landingpage extends Component {
  constructor(props){
    super(props);
    this.state={cars:[]}
  }
  componentDidMount(){
   const data={
      city:localStorage.getItem("city"),
      type:localStorage.getItem("type")
    }
  
    axios.post('http://localhost:8080/api/vehicles/search',data)
            .then((response) => {              
              console.log(response.data);
              this.setState({
               cars : this.state.cars.concat(response.data) 
            });
            
        });
        
}

render(){
  console.log(this.state.cars)
  let details = this.state.cars.map(product => {
    return(
        <Details car_name={product.vehicleName} car_type={product.vehicleType} 
         car_id={product.vehicleId} car_baseprice ={product.vehicleBasePrice} car_location = {product.parkingLocation.city}/>   
    )
})
//product_id={product._id}
  return (
    <div>
    <Navbar/>    
   <div style={{marginTop:'2%'}}>
   <div style={{display: 'flex', justifyContent: 'space-evenly',flexWrap: 'wrap', paddingLeft:'10%' ,width:'90%'}}>
     {details}
</div>
</div>
</div> 
  );
}
}
