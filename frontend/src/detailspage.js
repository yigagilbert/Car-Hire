import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import image from './car.jpg';


const useStyles =makeStyles((theme) => ({
  root: {
    maxWidth: 345,
  },
  media: {
    height: 140,
  },
  modal: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  },
  paper: {
    backgroundColor: theme.palette.background.paper,
    border: '2px solid #000',
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3),
  },
}));

export default function Details(props) {
  const classes = useStyles();
  function handleClick(e) {
    e.preventDefault(); 
    // console.log(this.state.location)
    localStorage.setItem("selectedcar", props.car_id);
   window.open('/carDetailsPage', "_self");
  }

  return (
    <Card className={classes.root} justify="center" style={{marginBottom:'5%'}}>
      <CardActionArea>
        <CardMedia
          component="img"
          alt="car image"
          height="140"
          src={image}
          title="car image"
        />
        <CardContent style={{background:'#eee'}}>
          <Typography gutterBottom variant="h5" component="h2">
              {props.car_name}
          </Typography>
          <Typography variant="body2" color="textSecondary" component="p">
            Type :{props.car_type}
          </Typography>
          <Typography variant="body2" color="textSecondary" component="p">
            Price : {props.car_baseprice}
          </Typography>
          <Typography variant="body2" color="textSecondary" component="p">
            Location : {props.car_location}
          </Typography>
        </CardContent>
      </CardActionArea>
      <CardActions>
        <Button size="small" color="primary" onClick={handleClick}>
         View More Details
        </Button>
        
      </CardActions>
    </Card>
    
  );
}