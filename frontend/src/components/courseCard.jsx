import React from "react";

function CourseCard({ course }) {
  return (
    <div className="card">
      <div className="card-body">
        <h5 className="card-title">{course.name}</h5>
        <p className="card-text">ID: {course.id}</p>
        <p className="card-text">Description: {course.description}</p>
        <p className="card-text">Duration: {course.duration}</p>
      </div>
    </div>
  );
}

export default CourseCard;