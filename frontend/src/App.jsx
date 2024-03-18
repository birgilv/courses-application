// App.jsx
import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import NavBar from "./components/NavBar/NavBar.jsx"
import MainPage from "./main.jsx";
import AboutPage from "./pages/AboutPage";
import CoursesPage from "./pages/CoursesPage";
import ReactDOM from 'react-dom/client'



function App() {
  return (
    <Router>
      <NavBar />
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/about" element={<AboutPage />} />
        <Route path="/courses" element={<CoursesPage />} />
        {/* Add more routes as needed */}
      </Routes>
    </Router>

  );
}

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
)

export default App;