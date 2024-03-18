import React, { useState } from 'react';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';

import HamburgerMenu from './Hamburger.jsx';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../../css/NavBar.css';
// import CreateMainPage from '../pages/mainPage/MainPage.jsx';

export default function NavigationBar() {


  return (
    <header>
      <Navbar collapseOnSelect expand='lg' bg='dark' variant='dark' className='NavBar'>
        <Navbar.Brand href='/'>
          Learniverse Connect
        </Navbar.Brand>
        <Navbar.Toggle aria-controls='responsive-navbar-nav'/>
        <HamburgerMenu/>
      </Navbar>
      {/* <CreateMainPage expanded={expanded}/> */}
    </header>
  );
};

