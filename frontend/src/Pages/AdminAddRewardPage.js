
import React, { useState, useEffect } from "react";
import { adminAddRewardService } from "../service/AdminAddRewardService";
import { apiGet } from "../util/Api";
import AdminAddReward from "../components/AdminAddReward";

function AdminAddRewardPage() {
    const [rewards, setRewards] = useState([]);

    useEffect(() => {
        loadRewards();
    }, []);

    async function loadRewards() {
        try {
            const data = await apiGet("/rewards/all");
            setRewards(data);
        } catch (err) {
            console.error("Failed to load:", err);
        }
    }

    async function handleAddReward({ rewardTitle, description, rewardAmount, points }) {
        await adminAddRewardService({ rewardTitle, description, rewardAmount, points });
        await loadRewards();
    }

    return (
        <AdminAddReward
            rewards={rewards}
            onAddReward={handleAddReward}
        />
    );
}

export default AdminAddRewardPage;
