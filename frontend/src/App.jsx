import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import NavBar from "./components/NavBar";
import HomePage from "./pages/HomePage";
import AboutPage from "./pages/AboutPage";
import CoursesPage from "./pages/CoursesPage";
import ReactDOM from 'react-dom';



function App() {
  return (
    <Router>
      <NavBar/>
        <Routes>
          <Route path="/" exact component={HomePage} /> 
          <Route path="/about" component={AboutPage} />
          <Route path="/courses" component={CoursesPage} />
            {/* Add more routes as needed */}
        </Routes>
    </Router>

  );
}

export default App;