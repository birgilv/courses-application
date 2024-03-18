// navbar.jsx
import React from 'react';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../../css/NavBar.css';

export default function NavigationBar() {

  return (
    <header>
      <Navbar collapseOnSelect expand='lg' bg='dark' variant='dark' className='NavBar'>
        <Navbar.Brand href='/'>Learniverse Connect</Navbar.Brand>
        <Navbar.Toggle aria-controls='responsive-navbar-nav' />
        <Navbar.Collapse id='responsive-navbar-nav'>
          <Nav className='mr-auto'>
            <Nav.Link href='/courses'>Course</Nav.Link>
            <Nav.Link href='/about'>About us</Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
    </header>
  );
}

