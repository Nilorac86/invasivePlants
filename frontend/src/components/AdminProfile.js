

function AdminProfileInfo({ data }) {
  return (

    <div className="profile-info">
      <h3>Välkommen Admin {data.email}!</h3>
    
    </div>
  );
}

export default AdminProfileInfo;