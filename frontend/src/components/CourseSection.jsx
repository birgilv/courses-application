import { useEffect, useState } from "react";
import { requestApiData } from "../tools/apiRequests";
import { CourseCard } from "./CourseCard";

/**
 * A page section showing books.
 * @param {boolean} favoritesOnly When true, query only favorite course, when false - all course
 * @param {string} title The title of the section
 * @param {string} description A descriptive text to show in the section
 * @return {JSX.Element}
 * @constructor
 */
export function CourseSection({ favoritesOnly, description, name }) {
  const [courses, setCourses] = useState([]);
  useEffect(loadCoursesFromApi, [favoritesOnly]);

  function loadCoursesFromApi() {
    const apiUrl = favoritesOnly ? "/courses/favorite" : "/courses";
    requestApiData(apiUrl, function (newCourses) {
      setCourses(newCourses);
    });
  }

  const descriptionParagraph = description ? <p>{description}</p> : <></>;

  let content = <p>Loading...</p>;
  if (books.length > 0) {
    content = books.map((book, index) => <BookCard key={index} book={book} />);
  }

  return (
    <section>
      <h2>{title}</h2>
      {descriptionParagraph}
      <div className="book-container">{content}</div>
    </section>
  );
}
