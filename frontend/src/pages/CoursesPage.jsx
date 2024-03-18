
import appIcon from '/appIcon.svg'
import React from 'react';

/**
 * Content for the "Home" page
 * @return {JSX.Element}
 * @constructor
 */
export function CoursesPage() {

  return (
    <article id="course-page">
      <section>
      <div>
        <img src={appIcon} className="logo" alt="app logo" />
      </div>
      <h1>Welcome to Course!</h1>
      </section>
    </article>
  );
}

export default CoursesPage;