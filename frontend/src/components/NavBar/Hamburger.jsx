// hamburger.jsx
import '../../css/hamburger.css'
import React from 'react';
import { Nav, Navbar, NavDropdown } from 'react-bootstrap';

export default function HamburgerMenu() {

  return (
    <Navbar.Collapse id='responsive-navbar-nav'>
      <Nav>
        <Nav.Link href='/search'>Search</Nav.Link>
        <Nav.Link href='/about'>About us</Nav.Link>
        <Nav.Link href='/contact'>Contact</Nav.Link>
      </Nav>
    </Navbar.Collapse>
  )
}