//AdminAddRewardPage.js
// Page layer — receives new reward data from component and sends to SERVICE layer
// This layer communicates with the SERVICE, not backend directly.

import React, { useState, useEffect } from "react";
import { adminAddRewardService } from "../service/AdminAddRewardService";
import AdminAddReward from "../components/AdminAddReward";

function AdminAddRewardPage() {

    // Receives cleaned reward data from component => sends to SERVICE => backend
    async function handleAddReward({ rewardTitle, description, rewardAmount, points }) {
        await adminAddRewardService({ rewardTitle, description, rewardAmount, points });
    }

    return (
        <AdminAddReward
            onAddReward={handleAddReward}
        />
    );
}

export default AdminAddRewardPage;
