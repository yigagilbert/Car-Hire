import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import image from '../images/user.png';
import Modal from '@material-ui/core/Modal';
import Backdrop from '@material-ui/core/Backdrop';
import Fade from '@material-ui/core/Fade';
import axios from 'axios'


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
  
export default function UserDetails(props) {
  const classes = useStyles();

  const [open, setOpen] = React.useState(false);

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

function terminateuser (e) {
     console.log(props.user_email)

     e.preventDefault();
        
     console.log(this.state.vehicleId)
     axios.delete('http://localhost:8080/api/driver/' + props.user_email)
         .then(response => {  
           console.log(response)  
             if(response.status === 200){
                 window.open('/adminLanding', "_self");
                    }  
                    else
                    window.alert("Something went wrong");
                   } )
                  } 
 

  return (
   
      
    <Card className={classes.root} justify="center">
      <CardActionArea>
      <CardMedia
          component="img"
          alt="user image"
          height="140"
          src={image}
          title="user image"
          style ={{display: 'block', marginLeft: 'auto',
          marginRight: 'auto',
          width: '70%'}} 
        />
        <CardContent>
          <Typography gutterBottom variant="h5" component="h2">
           {props.user_name}
          </Typography>
        </CardContent>
      </CardActionArea>
      <CardActions>
        <div>
        <Button size="small" color="primary" type="button" onClick={handleOpen}>
          View Profile
        </Button>
        <Modal
        aria-labelledby="transition-modal-title"
        aria-describedby="transition-modal-description"
        className={classes.modal}
        open={open}
        onClose={handleClose}
        closeAfterTransition
        BackdropComponent={Backdrop}
        BackdropProps={{
          timeout: 500,
        }}
      >
        <Fade in={open}>
          <div className={classes.paper}>
           <h2 id="transition-modal-title"> {props.user_name}</h2>
            <p id="transition-modal-description">User email :{props.user_email}</p>
            <p id="transition-modal-description">User address :{props.user_address}</p>
            <p id="transition-modal-description">User Membership :{props.user_memebership_end}</p>
          </div>

        </Fade>
      </Modal>
      </div>
      <div>
      <Button size="small" color="primary" type="button" onClick={terminateuser}>
             End Membership
        </Button>
        </div>
      </CardActions>
    </Card>
  );
}