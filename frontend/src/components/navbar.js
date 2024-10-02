import React, { Component } from 'react';

import "bootstrap/dist/css/bootstrap.min.css";

class Navbar extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }
    render() { 
        return (  
            <nav class="navbar navbar-expand-lg" style={{ background: "black" }}>
          <a class="navbar-brand" style={{ color: "white" }} href="/">
           CarHire
          </a>

          <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
              <li class="nav-item active">
                <a
                  class="nav-link"
                  href="/signin"
                 
                  style={{ color: "white" }}
                >
                  Customer
                </a>
              </li>
              <li class="nav-item">
                <a
                  class="nav-link"
                  href="/adminsignin"
                 
                  style={{ color: "white" }}
                >
                  
                  Employeer
                </a>
              </li>
              <li class="nav-item">
                <a
                  class="nav-link"
                  href="https://ncov2019.live/"
                  target="_blank"
                  style={{ color: "white" }}
                >
                  Ncov Help 
                </a>
              </li>
            </ul>
          </div>
        </nav>
        );
    }
}
 
export default Navbar;