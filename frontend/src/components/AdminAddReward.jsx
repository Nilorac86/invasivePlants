// AdminAddReward.jsx
// Component responsible for the UI form used to create a new reward.
// Receives 'rewards' and 'onAddReward' from the PAGE component.


import React, {useState} from "react";
import "./AdminAddReward.css";


function AdminAddReward({ onAddReward}) {
    const [form, setForm] = useState({
        // Local form state — controls the form inputs
        rewardTitle: "",
        description: "",
        rewardAmount: "",
        points: ""

    });

    // Feedback message shown after submit
    const [message, setMessage] = useState("");

    // Validation errors returned from backend (if any)
    const [errors, setErrors] = useState({});

    // Updates local form state when user types
    const handleChange = (e) => {
        setForm({...form, [e.target.name]: e.target.value});
    };


    // Called when user submits the form
    // Sends cleaned data to PAGE, whitch then calls SERVICE → backend
    async function handleSubmit(e) {
        e.preventDefault();
        setErrors({});
        setMessage("");

        const rewardData = {
            rewardTitle: form.rewardTitle,
            description: form.description,
            rewardAmount: parseInt(form.rewardAmount),
            points: parseInt(form.points)
        };

        try {
            // Hand over data to page component
            await onAddReward(rewardData);

            setMessage("Belöningen har sparats!");

            // Reset form after success
            setForm({
                rewardTitle: "",
                description: "",
                rewardAmount: "",
                points: ""
            });

        } catch (error) {
            console.log("Backend validation errors:", error);

            // Backend may return validation details array
            if (error.details) {
                const formatted = {};
                error.details.forEach(detail => {
                    if (detail.includes(":")) {
                        const [field, msg] = detail.split(":").map(x => x.trim());
                        formatted[field] = msg;
                    }
                });
                setErrors(formatted);
            } else if (error.message) {
                setMessage(error.message);
            }
        }
    }
    return (
        <div className="admin-reward-page">

            <h1>Hantera Belöningar</h1>

            <form className="reward-form" onSubmit={handleSubmit}>
                <input
                    name="rewardTitle"
                    value={form.rewardTitle}
                    onChange={handleChange}
                    placeholder="Titel"
                />
                {errors.rewardTitle && <div className="error">{errors.rewardTitle}</div>}

                <textarea
                    name="description"
                    value={form.description}
                    onChange={handleChange}
                    placeholder="Beskrivning"
                />
                {errors.description && <div className="error">{errors.description}</div>}

                <input
                    name="rewardAmount"
                    type="number"
                    value={form.rewardAmount}
                    onChange={handleChange}
                    placeholder="Antal"
                />
                {errors.rewardAmount && <div className="error">{errors.rewardAmount}</div>}

                <input
                    name="points"
                    type="number"
                    value={form.points}
                    onChange={handleChange}
                    placeholder="Poängkostnad"
                />
                {errors.points && <div className="error">{errors.points}</div>}

                <button type="submit">Spara</button>

                {message && <div className="success">{message}</div>}
            </form>

        </div>
    );
}

export default AdminAddReward;

