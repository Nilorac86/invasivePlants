// HistoryPage.js

import { useEffect, useState } from "react";
import {getHistory} from "../service/HistoryService";
import History from "../components/History";

export default function HistoryPage(){

const [history, setHistory] = useState(null);
const [error, setError]= useState(null);

useEffect(()=> {
    async function loadHistory(){
        try{
            const data = await getHistory();// fetsch from backend
            setHistory(data);
        }catch(err){
            setError("kunde inte ladda historiken.");
        }
    }
    loadHistory();
},[]);

if (error)return <p>{error}</p>;
if(!history)return <p>Laddar historik...</p>;

return <History data={history}/>;

}
