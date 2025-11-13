
import "./NotificationList.css";

function NotificationList({notifications, onMarkRead}){
    if(!notifications || notifications.length === 0){
        return <p className= "notification-item">Inga nya notiser</p>
    }

    return (
      <ul className="ul-list">
        {notifications.map((notif) =>{
            return(
          <li key={notif.id} className="notification-item">
            <span>
              {notif.message} {/*adjusting fields to data shape*/}
            </span>
            <button
              className="mark-read-btn"
              onClick={() => onMarkRead(notif.id)}
            >
              Mark as Read
            </button>
          </li>
        )
    })}

      </ul>
    );
}
export default NotificationList;
