import React ,{Component} from 'react';
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

export default class Addcar extends Component  {
  constructor(props) {
    super(props);
    this.state = {
       vehicleName: '', 
       vehicleType: '', 
      //  vehicleStatus: '',
       vehicleBasePrice: '',
       locationid: '',
       
      
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
    //  const parking_location_id = 2
      const data={
       vehicleName: this.state.vehicleName, 
       vehicleType: this.state.vehicleType, 
      //  vehicleStatus: 'AVAILABLE',
       vehicleBasePrice: this.state.vehicleBasePrice,
       locationid: this.state.locationid

      }
       // console.log(data)
        axios.post('http://localhost:8080/api/vehicles/' + this.state.locationid , data)
            .then(response => {  
              console.log(response)  
                if(response.status === 200){
                  alert("added car successfully")
                       }  
                       else
                       alert("Something went wrong");
                      } )
                    }
render(){
  
  return (
      <div>
      <Navbar/>
    <Container component="main" maxWidth="xs">
      <CssBaseline />
     
      <div className={useStyles.paper}>
        
        <br/>
        <Typography style={{
            
           
            marginLeft: "100px",
            
          }} component="h1" variant="h5">
           Add Vehicle
        </Typography>
        <br/>
        <form className={useStyles.form} >
          <Grid container spacing={2}>
            <Grid item xs={12} >
              <TextField
                autoComplete="vehicleName"
                name="vehicleName"
                onChange={this.handleChange}
                variant="outlined"
                required
                fullWidth
                id="vehicleName"
                label="Name"
                autoFocus
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="vehicleType"
                label="Vehicle Type"
                name="vehicleType"
                onChange={this.handleChange}
                autoComplete="vehicleType"
              />
            </Grid>
            {/* <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="vehicleStatus"
                label="Status"
                name="vehicleStatus"
                onChange={this.handleChange}
                autoComplete="vehicleStatus"
              />
            </Grid> */}
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="vehicleBasePrice"
                label="BasePrice"
                name="vehicleBasePrice"
                onChange={this.handleChange}
                autoComplete="vehicleBasePrice"
              />
            </Grid>
         
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="locationid"
                label="Location"
                name="locationid"
                onChange={this.handleChange}
                autoComplete="locationid"
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
           Add Car
          </Button>
         
          
        </form>
      </div>
      
    </Container>
    </div>
  );
}
}