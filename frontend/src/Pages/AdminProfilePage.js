import AdminProfileInfo from "../components/AdminProfile";

function AdminProfilePage ({adminData}) {

    return (
        <div className="admin-profile-page">
            <h2> Admin profilsida</h2>

            <AdminProfileInfo adminData = {adminData}/>

        </div>
    )
}

export default AdminProfilePage;