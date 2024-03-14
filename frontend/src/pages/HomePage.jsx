
import appIcon from '/appIcon.svg'
import React from 'react';

/**
 * Content for the "Home" page
 * @return {JSX.Element}
 * @constructor
 */
export function HomePage() {

  return (
    <article id="home-page">
      <section>
      <div>
        <img src={appIcon} className="logo" alt="app logo" />
      </div>
      <h1>Welcome to home!</h1>
      </section>
    </article>
  );
}

export default HomePage;