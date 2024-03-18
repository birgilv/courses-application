
import appIcon from '/appIcon.svg'
import React from 'react';

/**
 * Content for the "Home" page
 * @return {JSX.Element}
 * @constructor
 */
export function AboutPage() {

  return (
    <article id="about-page">
      <section>
      <div>
        <img src={appIcon} className="logo" alt="app logo" />
      </div>
      <h1>Welcome to about!</h1>
      </section>
    </article>
  );
}

export default AboutPage;