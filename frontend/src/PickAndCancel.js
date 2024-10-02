import React ,{Component} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import logo from './car.jpg';
import { pink, blue } from '@material-ui/core/colors';
import axios from 'axios'
import Navbar from './Navigationbar'


const useStyles = makeStyles({
  root: {
    maxWidth: 345,
  },
  
});

//const classes = useStyles();
export default class Cardetails extends Component{
  constructor(props) {
    super(props);
    this.state = {
      prod: [],
      
    };
    
    this.buttonClick=this.buttonClick.bind(this);
    this.handleCancel=this.handleCancel.bind(this);
  }
 

  buttonClick=(e)=>{
    let id=localStorage.getItem("reservationID")
    console.log(localStorage.getItem("reservationID"))
    axios.put('http://localhost:8080/api/reservation/'+localStorage.getItem("reservationID")+'/current').then(response => {  
      if(response.status === 200){  
        window.open('/return', "_self");    
             } 
             else if(response.status === 400){
              alert("Some Error Occured")
             }
            }
            
            )      
  }
  handleCancel=(e)=>{
    let id=localStorage.getItem("reservationId")
    console.log(localStorage.getItem("reservationID"))
    axios.put('http://localhost:8080/api/reservation/'+localStorage.getItem("reservationID")+'/cancel').then(response => {  

        if(response.status === 200){
          localStorage.removeItem("selectedcar");
        alert("Reservation has been Canceled")
        window.open('/userHome', "_self");    
             } 
             else if(response.status === 400){
              alert("Some Error Occured")
             }
            }
            
            ) 
    
  }

  componentDidMount() {
    let selectedCarId = localStorage.getItem("selectedcar");
    console.log(selectedCarId)
    axios.get('http://localhost:8080/api/vehicles/' + selectedCarId)
      .then(response => {        
       console.log(response);
        this.setState({
          prod: response.data
        });
      });


  }
  
  
render(){
    console.log(this.state.prod);
  return (
      <div><Navbar/>
      <div backGroundColor={blue}>
          {localStorage.getItem("selectedcar")!=null? <Card className={useStyles.root}
                                                      style={{ display: 'inline-block', marginTop: '60px', marginLeft: '550px', width: '500px', height: '350px' }}>
              <CardActionArea>
                  <CardMedia
                      component="img"
                      alt="car image"
                      height="140"
                      src={logo}
                      title="car image"
                  />
                  <CardContent>
                      <Typography gutterBottom variant="h5" component="h2">
                          {this.state.prod.vehicleName}
                      </Typography>
                      <Typography variant="body2" color="textSecondary" component="p">
                          Car Cost ${this.state.prod.vehicleBasePrice} per hour


                      </Typography>
                  </CardContent>
              </CardActionArea>
              <CardActions>

                  <Button size="small" color="primary" style={{marginLeft:'100px'}} onClick={this.buttonClick} >
                      PickUp Car
                  </Button>
                  <Button size="small" color="primary" style={{marginLeft:'100px'}} onClick={this.handleCancel} >
                      Cancel Reservation
                  </Button>

              </CardActions>
          </Card> :<h1>No bookings</h1>}



      </div>
    </div>
  );
}
}