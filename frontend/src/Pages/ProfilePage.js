import {useEffect, useState} from "react";
import { fetchUserProfile } from "../service/ProfileService";
import { fetchUserNotifications, fetchUserNotifications } from "../service/NotificationService";
import ProfileInfo from "../components/Profile";
import NotificationsList from "../components/Notifications";

function ProfilePage() {
  const [profile, setProfile] = useState(null);
  const [notifications, setNotifications] =useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {

    async function loadUserData(){

      try{
        // Fetch profile first
        const data = await fetchUserProfile();
        setProfile(data);

        //fetch notifications for the loged in user,if any
        const userNotifications = await fetchUserNotifications();
        setNotifications(userNotifications);

      }catch(err){
        console.error("Error fetshing userdata or notifications:", err);
        setError("could not fetch user data or notifications")
      }

    }
     loadUserData();
  }, []);

  // If the user cant be fetched it will show error or loading state
  if (error) return <p>{error}</p>;
  if (!profile) return <p>Laddar....</p>; 

  return(
    //The <div> is just a wrapper element. React components must return a single JSX element. 
    // So if you have multiple components or elements to render, you need to wrap them in a container like a
    //  <div> or a React Fragment (<>...</>).
    <div>
      <ProfileInfo data={profile} />
      <NotificationsList notifications = {notifications} />
    </div>
  );
}
  export default ProfilePage;
  

