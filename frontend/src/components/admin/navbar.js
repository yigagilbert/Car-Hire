import React ,{Component} from 'react';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
import InputBase from '@material-ui/core/InputBase';
import { fade, makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import SearchIcon from '@material-ui/icons/Search';



const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
    display: 'none',
    [theme.breakpoints.up('sm')]: {
      display: 'block',
    },
  },
  search: {
    position: 'relative',
    borderRadius: theme.shape.borderRadius,
    backgroundColor: fade(theme.palette.common.white, 0.15),
    '&:hover': {
      backgroundColor: fade(theme.palette.common.white, 0.25),
    },
    marginLeft: 0,
    width: '100%',
    [theme.breakpoints.up('sm')]: {
      marginLeft: theme.spacing(1),
      width: 'auto',
    },
  },
  searchIcon: {
    padding: theme.spacing(0, 2),
    height: '100%',
    position: 'absolute',
    pointerEvents: 'none',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  },
  inputRoot: {
    color: 'inherit',
  },
  inputInput: {
    padding: theme.spacing(1, 1, 1, 0),
    // vertical padding + font size from searchIcon
    paddingLeft: `calc(1em + ${theme.spacing(4)}px)`,
    transition: theme.transitions.create('width'),
    width: '100%',
    [theme.breakpoints.up('sm')]: {
      width: '12ch',
      '&:focus': {
        width: '20ch',
      },
    },
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
}));

export default function Navbar(){

const classes = useStyles()

function logout(e){
 
    e.preventDefault();
   localStorage.removeItem('emailid');
   window.open('/', "_self");

 };

  return (
    <div >
      <AppBar position="static" color="black">
        <Toolbar>
          
          <Typography className={classes.title} variant="h6"  noWrap>
          <a  href="/adminLanding">
          Rent a Vehicle
          </a> 
          </Typography>
         
          <Button className={classes.title} href="/addcar" variant="h6" noWrap>
            Add Vehicle
          </Button>
          <Button className={classes.title} href="/removecar" variant="h6" noWrap>
           Remove Vehicle
          </Button>
          <Button className={classes.title} href="/locations" variant="h6" noWrap>
          Locations
          </Button>
          <Button className={classes.title} href="/addlocation" variant="h6" noWrap>
              Add Locations
          </Button>
          <div className={classes.search}>
            <div className={classes.searchIcon}>
              <SearchIcon />
            </div>
            <InputBase
              placeholder="Search…"
              classes={{
                root: classes.inputRoot,
                input: classes.inputInput,
              }}
              inputProps={{ 'aria-label': 'search' }}
            />
          </div>
          <div>
          <Button variant="contained" color="primary"  onClick={logout} >
  Logout
</Button>
            </div>
        </Toolbar>
      </AppBar>
     
      
    </div>
  );
}