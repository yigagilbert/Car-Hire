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
import Navbar from '../../components/navbar';


const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    background:'black',
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

export default class AdminSignin extends Component {
  constructor(props){
    super(props)
    this.state={
      email:"",
      password:""
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
    const data={
      adminEmailId:this.state.email,
      adminPassword:this.state.password
    }


    axios.post('http://localhost:8080/api/admins/login',data)
    .then(response => {  
        if(response.status === 200){
           console.log(response)
              window.open('/adminLanding', "_self");
          }  
               else
               alert("Something went wrong");
              })   
             }
  
  
render(){
  localStorage.setItem('emailid',this.state.email);
  return (
    <div> 
    <Navbar/>
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={useStyles.paper}>
        <Avatar className={useStyles.avatar} style={{marginLeft: "150px", }}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography style={{marginLeft: "120px", }} component="h1" variant="h5">
         Admin Sign in
        </Typography>
        <form className={useStyles.form} noValidate>
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="email"
            label="Email Address"
            name="email"
            onChange={this.handleChange}
            autoComplete="email"
            autoFocus
          />
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            name="password"
            onChange={this.handleChange}
            label="Password"
            type="password"
            id="password"
            autoComplete="current-password"
          />
          <FormControlLabel
            control={<Checkbox value="remember" color="primary" />}
            label="Remember me"
          />
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={useStyles.submit}
            onClick={this.handleClick}
          >
            Sign In
          </Button>
          <br/>
          {/* <Grid container>
            <Grid item xs>
              <Link href="/signin" variant="body2">
                Sign In as User
              </Link>
            </Grid>
           
          </Grid> */}
          
        </form>
      </div>
      
    </Container>
    </div>
   
  );
}
}