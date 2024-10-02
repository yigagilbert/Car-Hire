import React ,{Component} from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';

import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import axios from 'axios'
import Navbar from './navbar'


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
    marginTop: theme.spacing(3),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
}));

export default class Removecar extends Component  {
  constructor(props) {
    super(props);
    this.state = {
      vehicleId:''
    };
    this.handleChange = this.handleChange.bind(this); 
    this.handleClick = this.handleClick.bind(this); 
  }
  handleChange(e) {
    this.setState({
        [e.target.name]: e.target.value
    });}
    handleClick(e){
      e.preventDefault();
        
        console.log(this.state.vehicleId)
        axios.delete('http://localhost:8080/api/vehicles/' + this.state.vehicleId)
            .then(response => {  
              console.log(response)  
                if(response.status === 200){
                    window.open('/adminLanding', "_self");
                       }  
                       else
                       window.alert("Something went wrong");
                      } )}
render(){
  
  return (
      <div>
      <Navbar/>
    <Container component="main" maxWidth="xs">
      <CssBaseline />
     
      <div className={useStyles.paper}>
        
        <br/>
        <Typography style={{
            
           
            marginLeft: "90px",
            
          }} component="h1" variant="h5">
          Remove Car
        </Typography>
        <br/>
        <form className={useStyles.form} noValidate>
          <Grid container spacing={2}>
            <Grid item xs={12} >
              <TextField
                autoComplete="vehicleId"
                name="vehicleId"
                onChange={this.handleChange}
                variant="outlined"
                required
                fullWidth
                id="vehicleId"
                label="Enter the Id of the Car to be removed"
                autoFocus
              />
            </Grid>
            
            
            
            
            
            
          </Grid>
          <br/>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={useStyles.submit}
            onClick={this.handleClick}
          >
          Remove Car
          </Button>
         
          
        </form>
      </div>
      
    </Container>
    </div>
  );
}
}