import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import image from '../images/location.jpg';
import { findAllByDisplayValue } from '@testing-library/react';


const useStyles = makeStyles({
    root: {
      maxWidth: 345,
    },
    media: {
      height: 140,
    },
  });
  
export default function LocationDetails(props) {
  const classes = useStyles();

// handleClick = e => {

//     e.preventDefault(); 
//     // localStorage.setItem("selectedcar", props._id);
// //    window.open('/dp', "_self");
//   }

  return (
   
    <Card className={classes.root} justify="center"  style={{padding: '25px'}}>
      <CardActionArea>
      <CardMedia
          component="img"
          alt="map image"
          
          src={image}
          title="map image"
         style ={{display: 'block', marginLeft: 'auto',
          marginRight: 'auto',
          width: '50%'}} 
        />
        <CardContent>
          <Typography gutterBottom variant="h5" component="h2">
          Location: {props.city}
          </Typography>
          <Typography variant="body2" color="textSecondary" component="p">
            Location Address :{props.address}
          </Typography>
          <Typography variant="body2" color="textSecondary" component="p">
            Capacity :{props.capacity}
          </Typography>
          <Typography variant="body2" color="textSecondary" component="p">
            Filled Spots :{props.filled}
          </Typography>
        </CardContent>
      </CardActionArea>
      <CardActions>
        <Button size="small" color="primary" onClick>
          View Cars in the Location
        </Button>
        
      </CardActions>
    </Card>
  
  );
}