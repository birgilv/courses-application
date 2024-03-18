// main.jsx

import App from './App.jsx'
import './css/index.css'
import appIcon from '/appIcon.svg'
import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";
import CourseCard from "./components/CourseCard.jsx";
import NavBar from "./components/NavBar/NavBar";

function main() {

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
      <NavBar/>
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

export default main;