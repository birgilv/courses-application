import appIcon from '/appIcon.svg'
import './css/App.css'
import React, { useEffect, useState } from "react";
import axios from "axios";
import HomePage from "./pages/HomePage";
import { Link, useParams } from "react-router-dom";



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
      <div className="card">
      <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col">Id</th>
              <th scope="col">Name</th>
              <th scope="col">Description</th>
              <th scope="col">Duration</th>
            </tr>
          </thead>
          <tbody>
            {courses.map((course, index) => (
              <tr>
                <th scope="row" key={index}>
                  {index + 1}
                </th>
                <td>{course.name}</td>
                <td>{course.description}</td>
                <td>{course.duration}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </>
  )
}

export default App