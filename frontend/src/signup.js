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
import Navbar from './components/navbar';
// constructor(props){
//   super(props);
//     this.state={
//       CardName:"",
//       CardNo:"",
//       date: new Date()
     
//     }  
//     this.handleChange = this.handleChange.bind(this); 
//     this.onChange=this.onChange.bind(this);
// }

function Copyright() {
  return (
    <Typography variant="body2" color="textSecondary" align="center">
      {'Copyright © '}
      <Link color="inherit" href="https://material-ui.com/">
        Your Website
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

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
//const classes = useStyles();
export default class SignUp extends Component  {
  constructor(props) {
    super(props);
    this.state = {
      firstName:"",
      address:"",
      lno:"",
      email:"",
      password:"",
      stateName:""


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
      const data={driverLicense:this.state.lno,driverAddress:this.state.address,driverName:this.state.firstName,driverEmailId:this.state.email,
        driverPassword:this.state.password,driverState:this.state.stateName}
        console.log("sending data"+ data)
        console.log(data)
        axios.post('http://localhost:8080/api/drivers',data)
            .then(response => {  
              console.log(response)  
                if(response.status === 200){
                  localStorage.setItem("email",this.state.email)
                    window.open('/profile', "_self");
                       }  
                       else
                       alert("Something went wrong");
                      } )}
render(){
  
  return (
    <div> 
      <Navbar/>
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={useStyles.paper}>
        <Avatar style={{
            
           
            marginLeft: "170px",
            
          }} className={useStyles.avatar}>
          <LockOutlinedIcon />
        </Avatar>
        <br/>
        <Typography style={{
            
           
            marginLeft: "150px",
            
          }} component="h1" variant="h5">
          Sign up
        </Typography>
        <br/>
        <form className={useStyles.form} noValidate>
          <Grid container spacing={2}>
            <Grid item xs={12} >
              <TextField
                autoComplete="fname"
                name="firstName"
                onChange={this.handleChange}
                variant="outlined"
                required
                fullWidth
                id="firstName"
                label="Name"
                autoFocus
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="address"
                label="Address"
                name="address"
                onChange={this.handleChange}
                autoComplete="address"
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="lno"
                label="License Number"
                name="lno"
                onChange={this.handleChange}
                autoComplete="lno"
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="stateName"
                label="State"
                name="stateName"
                onChange={this.handleChange}
                autoComplete="stateName"
              />
            </Grid>
            
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                onChange={this.handleChange}
                autoComplete="email"
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                name="password"
                onChange={this.handleChange}
                label="Password"
                type="password"
                id="password"
                autoComplete="current-password"
              />
            </Grid>
            <Grid item xs={12}>
              <FormControlLabel
                control={<Checkbox value="allowExtraEmails" color="primary" />}
                label="I want to receive inspiration, marketing promotions and updates via email."
              />
            </Grid>
          </Grid>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={useStyles.submit}
            onClick={this.handleClick}
          >
            Sign Up
          </Button>
         
          <Grid container justify="flex-end">
            <Grid item>
              <Link href="/signin" variant="body2">
                Already have an account? Sign in
              </Link>
            </Grid>
          </Grid>
        </form>
      </div>
      
    </Container>
    </div>
  );
}
}