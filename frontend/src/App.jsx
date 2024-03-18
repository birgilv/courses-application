import appIcon from '/appIcon.svg'
import './css/App.css'
import React, { useEffect, useState } from "react";
import axios from "axios";
import HomePage from "./pages/HomePage";
import { Link, useParams } from "react-router-dom";
import CourseCard from "./components/CourseCard";



function App() {
  const [courses, setCourses] = useState([]);

  const { id } = useParams();

  useEffect(() => {
    loadCourses();
  }, []);

  const loadCourses = async () => {
    const result = await axios.get("http://localhost:8080/courses");
    setCourses(result.data);
  };

  return (
    <>
      <div>
        <img src={appIcon} className="logo" alt="app logo" />
      </div>
      <h1>Learniverse Connect</h1>
      <div className="Courses">
        {courses.map((course, index) => (
            <CourseCard key={index} course={course} />
        ))}
      </div>
    </>
  )
}

export default App