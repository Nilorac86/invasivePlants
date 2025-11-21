
import {Fragment, useEffect, useState} from "react";
import { fetchUserProfile } from "../service/ProfileService";
import {fetchUserNotifications, markNotificationAsRead} from "../service/NotificationService";

// User components
import ProfileInfo from "../components/Profile";
import NotificationList from "../components/NotificationList";

import {getdashboard} from "../service/DashboardUserService";
import Dashboard from "../components/DashboardUser";

//Admin components
import AdminProfileInfo from "../components/AdminProfile";

function UnifiedProfilePage() {
    // Stores fetched user profile data
    const [profile, setProfile] = useState(null);
    // Stores notifications (only for regular users)
    const [notifications, setNotifications] = useState([]);
    const [dashboard,setDashboard]= useState(null);
    // Stores error message if something goes wrong
    const [error, setError] = useState(null);

    // Determine if logged-in user is an admin
    const isAdmin =
        profile?.role?.includes("ADMIN") ||
        profile?.roles?.some(r => r.includes("ADMIN"));


    useEffect(() => {
        // Loads user profile and notifications depending on role
        async function loadUserData() {
            try {
                const data = await fetchUserProfile();
                setProfile(data);

                const admin =
                    data?.role?.includes("ADMIN") ||
                    data?.roles?.some(r => r.includes("ADMIN"));

                // Only fetch notifications if the user is NOT an admin
                if (!admin) {
                    const userNotifications = await fetchUserNotifications();
                    setNotifications(userNotifications);
                }

            } catch (err) {
                console.error("error loading profile:", err);
                setError("Kunde inte hämta profiluppgifter");
            }
        }
        loadUserData();
    }, []);

    // Marks notification as read and reloads updated list
    async function handleMarkRead(id) {
        try {
            await markNotificationAsRead(id);
            const updated = await fetchUserNotifications();
            setNotifications(updated);
        } catch (error) {
            console.error("Misslyckades markera som läst: ", error);
        }
    }

    // fetsh logged in users Page
    useEffect(()=> {
        // only fetch dashboard data if user is NOT admin
        if(profile && !isAdmin){ 
            async function fetchDashboardData(){
    
                try{
                    const data = await getdashboard();
                    setDashboard(data);
                }catch(err){
                    setError("Failed to load dashboard data");
                    console.error(err);
                }
            }
            fetchDashboardData();
        }
    },[profile, isAdmin]);


    if(error) return <p>{error}</p>;
    if(!profile) return <p>Laddar profil...</p>;

    return (
        <div className="unified-profile-page">

            {/* USER SECTION */}
            {!isAdmin && (
                <div className="user-section">
                    <ProfileInfo data={profile} />
                    <NotificationList notifications={notifications} onMarkRead={handleMarkRead} />
                    {dashboard && <Dashboard data={dashboard} />}
                </div>
            )}

            {/* ADMIN SECTION */}
            {isAdmin && (
                <div className="admin-section">
                    <Fragment></Fragment><h2>Admin Profil</h2>
                    <AdminProfileInfo adminData={profile} />
                </div>
    )}

            </div>
    );
}

export default UnifiedProfilePage;