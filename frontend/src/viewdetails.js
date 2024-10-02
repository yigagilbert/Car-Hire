import React, {Component} from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import logo from './car.jpg';
import {pink, blue} from '@material-ui/core/colors';
import axios from 'axios'
import Navbar from './Navigationbar'
import DatePicker from 'react-datepicker';
import {addDays} from 'date-fns';
import {subDays} from 'date-fns';
import "react-datepicker/dist/react-datepicker.css";
import {
    MuiPickersUtilsProvider,
    KeyboardTimePicker,
    KeyboardDatePicker,
} from '@material-ui/pickers';
import Grid from '@material-ui/core/Grid';
import DateFnsUtils from '@date-io/date-fns';
import 'date-fns';
import TextField from '@material-ui/core/TextField';

import DateTimePicker from 'react-datetime-picker'

const useStyles = makeStyles({
    root: {
        maxWidth: 345,
    },

});
const useStyless = makeStyles((theme) => ({
    container: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    textField: {
        marginLeft: theme.spacing(1),
        marginRight: theme.spacing(1),
        width: 200,
    },
}));
//const classes = useStyles();
export default class Cardetails extends Component {
    constructor(props) {
        super(props);
        this.state = {
            prod: [],
          location:[],
            hours: 1,
            pickupDateTime: new Date(),

        };
        // this.h=this.setTime.bind(this);
        this.bookthiscar = this.bookthiscar.bind(this);
        this.handleHour = this.handleHour.bind(this);
    }

    // setTime = e => {
    // this.setState({
    //   [e.target.date]: e.target.value
    //  });

    // }

    handleDateTimeChange = (dateTime) => {
        this.setState({
            pickupDateTime: dateTime
        })
    }


    handleChange = (event) => {
        this.logs.unshift("change: " + event.target.value);

        this.setState({
            value: event.target.value,
            events: this.logs.slice()
        });
    }

    handleHour = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        });

    }

    bookthiscar = (e) => {

        var date = new Date(this.state.pickupDateTime),
            year = date.getFullYear(),
            month = ("0" + (date.getMonth() + 1)).slice(-2),
            day = ("0" + date.getDate()).slice(-2),
            hours1 = ("0" + date.getHours()).slice(-2),
            minutes = ("0" + date.getMinutes()).slice(-2);
        console.log(year, month, day, hours1, minutes)
        let dateString = String(year) + "-" + month + "-" + day + "T" + hours1 + ":" + minutes
        console.log("dateString-->", dateString)


        const data = {
            pickup: dateString,
            hours: parseInt(this.state.hours)
        }

        console.log(data)

        let selectedCarId = localStorage.getItem("selectedcar");
        let email = localStorage.getItem("email");


        //let email="user3@carrental.com"
        //window.open('/Pickup', "_self");
        axios.post("http://localhost:8080/api/reservation?driverEmailId=" + email + "&vehicle_id=" + selectedCarId, data)
            .then(response => {
                    if (response.status === 200) {
                        localStorage.setItem("reservationID", response.data.reservationId)
                        console.log(localStorage.getItem("reservationID"));
                        alert("Car reserved")
                        window.open('/Pickup', "_self");
                    } else if (response.status === 400) {
                        alert("Some Error Occured")
                    }
                }
            )
            .catch(err => {

                    alert(err.response.data);
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
                    prod: response.data,
                    location: response.data.parkingLocation
                });
            });

    }


    render() {

        //let price=this.state.prod.vehicleBasePrice*this.state.hours;
        let price = 0;
        let baseP = this.state.prod.vehicleBasePrice;
        let hr = this.state.hours;
        for (let i = 1; i <= hr; i++) {
            price += baseP;

            if (i % 8 == 0 && baseP > 0)
                baseP--;
        }

        return (
            <div>
                <Navbar/>
                <div backGroundColor={blue}>
                    <Card className={useStyles.root}
                          style={{
                              display: 'inline-block',
                              marginTop: '60px',
                              marginLeft: '550px',
                              width: '500px',
                              height: '500px'
                          }}>
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
                                    This is Great car<br></br>
                                    Base price {this.state.prod.vehicleBasePrice} <br></br>
                                    Pickup location {this.state.location.city} <br></br>
                                    Drop location {this.state.location.city} <br></br>

                                    <h3>Total Cost : {price}</h3>
                                </Typography>
                            </CardContent>
                        </CardActionArea>
                        <CardActions>

                            <form className={useStyless.container}>

                                <div className="form-group">
                                    <label htmlFor="exampleInputPassword1" style={{fontSize: "130%"}}>Pickup
                                        Date-Time</label>
                                    <br/>
                                    <DatePicker
                                        selected={this.state.pickupDateTime}
                                        onChange={dateTime => this.handleDateTimeChange(dateTime)}
                                        showTimeSelect
                                        timeFormat="HH:mm"
                                        timeIntervals={30}
                                        timeCaption="time"
                                        minDate={subDays(new Date(), 0)}
                                        maxDate={addDays(new Date(), 2)}
                                        dateFormat="yyyy-MM-dd hh:mm aa"
                                    />
                                </div>
                                <input
                                    type="number"
                                    label="Select No of Hours"
                                    value={this.state.hours}
                                    name="hours"
                                    min={1}
                                    max={72}
                                    onChange={this.handleHour}
                                />
                                {/* <InputMoment
  moment={this.state.moment}
  onChange={this.handleChange}
  onSave={this.handleSave}
  minStep={1} // default
  hourStep={1} // default
  prevMonthIcon="ion-ios-arrow-left" // default
  nextMonthIcon="ion-ios-arrow-right" // default
/> */}
                            </form>
                            <br/>
                            <Button size="small" color="primary" style={{marginLeft: '100px'}}
                                    onClick={this.bookthiscar}>
                                Book this car
                            </Button>

                        </CardActions>
                    </Card>
                </div>
            </div>
        );
    }
}