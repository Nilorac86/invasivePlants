import {useEffect, useState} from "react";
import { fetchUserProfile } from "../service/ProfileService";
import ProfileInfo from "../components/Profile";

function ProfilePage() {
  const [profile, setProfile] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
  

    async function loadProfile() {
      try {
        const data = await fetchUserProfile();
        setProfile(data);
      } catch (err) {
        setError("Could not fetch userdata");
      }
    }

    loadProfile();
  }, []);

  // If the user cant be fetched it will show error
  if (error) return <p>{error}</p>;
  if (!profile) return <p>Laddar....</p>; 

  // User data will get fetched but needs to be more details when the profile gets done.
  return <ProfileInfo data={profile} />;
}

export default ProfilePage;

