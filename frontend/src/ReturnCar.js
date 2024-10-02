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
import TextField from '@material-ui/core/TextField';

const useStyles = makeStyles({
  root: {
    maxWidth: 345,
  },
  
});

//const classes = useStyles();
export default class ReturnCar extends Component{
  constructor(props) {
    super(props);
    this.state = {
      prod: [],
      
    };
    
    this.buttonClick=this.buttonClick.bind(this);
    
  }
 

  buttonClick=(e)=>{
    let id=localStorage.getItem("reservationId")
    axios.put('http://localhost:8080/api/reservation/'+localStorage.getItem("reservationID")+'/end').then(response => {  
      if(response.status === 200){
          localStorage.removeItem("selectedcar");
        alert("Trip Ended")
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
  
  return (
      <div><Navbar/>
      <div backGroundColor={blue}>
    <Card className={useStyles.root} 
    style={{ display: 'inline-block', marginTop: '60px', marginLeft: '550px', width: '500px', height: '500px' }}>
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
           Car Name {this.state.prod.vehicleName}
          </Typography>
          <Typography variant="body2" color="textSecondary" component="p">
            
            Car Cost ${this.state.prod.vehicleBasePrice} per hour<br/>
            
          </Typography>
        </CardContent>       
      </CardActionArea>

      <CardActions>
      <TextField
      label="Add a Rating"
      value={this.state.hours}
      name="hours"
      onChange={this.handleHour}
      />  
       <Button size="small" color="primary" style={{marginLeft:'100px'}}  >
               Add Review
        </Button>      
      </CardActions>
<br/>
      <CardActions>  
        <Button size="small" color="primary" style={{marginLeft:'100px'}} onClick={this.buttonClick} >
               Return Car
        </Button>   
      </CardActions>
    </Card>
    </div>
    </div>
  );
}
}