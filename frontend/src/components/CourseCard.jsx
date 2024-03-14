
/**
 * A card for a couse
 * @param course Course data to show in the card
 * @return {JSX.Element}
 * @constructor
 */
export function CourseCard({ course }) {
  return (
    <div className="card">
      <div className="course-image-container">
        <img src={images[course.id - 1]} alt="A course image" />
      </div>
      <p>{course.name}</p>
    </div>
  );
}