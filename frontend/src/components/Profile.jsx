
import "./Profile.css";

function ProfileInfo({ data }) {
  return (
    <div className="profile-info">
      <h3 className="h3name">
        Välkommen {data.firstName} {data.lastName}!
      </h3>
    </div>
  );
}

export default ProfileInfo;
