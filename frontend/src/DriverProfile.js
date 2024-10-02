import React, {Component} from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import axios from 'axios';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Navbar from './Navigationbar'
import PaymentForm from '../src/components/creditcard'

const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(1),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
}));

export default class Profile extends Component {
  constructor(props){
    super(props)
    this.state={
      
      drivername:"",
      address:"",
      password:"",
      reservation:[]
    }
    this.handleChange = this.handleChange.bind(this); 
    this.handleClick = this.handleClick.bind(this); 
  }
  handleChange(e) {
    this.setState({
        [e.target.name]: e.target.value
    });
  }
  handleClick(e){
     e.preventDefault();
   const data={driverName:this.state.drivername,driverAddress:this.state.address,driverPassword:this.state.password}
     axios.put('http://localhost:8080/api/drivers/'+localStorage.getItem("email"),data).then(response => {  
        if(response.status === 200){
          console.log("User details updated succesfully")
            window.open('/userHome', "_self");
               }  
               else
               alert("Something went wrong");
              })  
              }
componentDidMount(){
                let email=localStorage.getItem("email");
//let email="user2@carrental.com";
axios.get('http://localhost:8080/api/drivers/' + email)
.then(response => {        
 console.log(response);
  this.setState({
   drivername:response.data.driverName,
   address:response.data.driverAddress,
   reservation:this.state.reservation.concat(response.data.reservations)
  });
}).catch(err => {

        alert(err.response.data);
    }
);
 }
  
  
render(){
    let details = this.state.reservation.map(r => {
        return(
        <div style={{padding:'2%',border:'1px solid black'}}><h5>Reservation Id :{r.reservationId}</h5>
        <h5>Reservation Status:{r.status}</h5><br/></div>
        )
    })
    console.log(this.state.reservation)
  return (
      <div>
          <Navbar/>
        <div>
        <PaymentForm/>
          </div>
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={useStyles.paper}>
        
        
        <form className={useStyles.form} noValidate>
          <h2>Driver Profile</h2>  
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            label="Driver Name"
            id="drivername"    
            name="drivername"
            value={this.state.drivername}
            onChange={this.handleChange}
          />
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            label="address"
            name="address"
            onChange={this.handleChange} 
            value={this.state.address}
          />
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            label="update password"
            name="password"
            onChange={this.handleChange} 
            value={this.state.password}
          />

          <Button  style={{background:'#28a745'}} variant="h6" noWrap onClick={this.handleClick}>
        Save Changes
          </Button>
            <hr></hr>
          <h3>Driver Reservations</h3>

{details}
          
          
        </form>
      </div>
      
    </Container>
  
    </div>
  );
}
}