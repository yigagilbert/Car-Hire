import React, { Component } from "react";
import Navbar from './navbar';
import First from '../components/images/1.png'
import Second from '../components/images/2.png';
import Third from '../components/images/3.png';
import Footer from "./Footer";

class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }
  render() {
    return (
      <div>
        <Navbar/>
        <section>
            <img src={First} style={{width:'100%'}}></img> 
        </section>
        <section>
            <img src={Second} style={{width:'100%'}}></img> 
        </section>
        <section>
            <img src={Third} style={{width:'100%'}}></img> 
        </section>
        <Footer/>
      </div>
     
    );
  }
}

export default Home;
