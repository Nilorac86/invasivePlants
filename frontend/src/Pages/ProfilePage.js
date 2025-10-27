import {useEffect, useState} from "react";
import { fetchUserProfile } from "../service/ProfileService";
import ProfileInfo from "../components/Profile";

function ProfilePage({ userId }) {
  const [profile, setProfile] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    if (!userId) return;

    async function loadProfile() {
      try {
        const data = await fetchUserProfile(userId);
        setProfile(data);
      } catch (err) {
        setError("Could not fetch userdata");
      }
    }

    loadProfile();
  }, [userId]);

  // If the user cant be fetched it will show error
  if (error) return <p>{error}</p>;
  if (!profile) return <p>Välkommen!</p>;

  // User data will get fetched but needs to be more details when the profile gets done.
  return <ProfileInfo data={profile} />;
}

export default ProfilePage;

