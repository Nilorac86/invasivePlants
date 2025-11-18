// AdminAddReward.jsx
import React, {useState} from "react";
import "./AdminAddReward.css";




function AdminAddReward({ rewards, onAddReward}) {
    const [form, setForm] = useState({
        rewardTitle: "",
        description: "",
        rewardAmount: "",
        points: ""


    });

    const [message, setMessage] = useState("");
    const [errors, setErrors] = useState({});

    const handleChange = (e) => {
        setForm({...form, [e.target.name]: e.target.value});
    };

 /*   const handleSubmit = (e) => {
        e.preventDefault();
        onAddReward(form);
    };
*/
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
            // Skicka till PAGE, inte direkt till backend
            await onAddReward(rewardData);

            setMessage("Belöningen har sparats!");

            setForm({
                rewardTitle: "",
                description: "",
                rewardAmount: "",
                points: ""
            });

        } catch (error) {
            console.log("Backend validation errors:", error);

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


    /*    return (
            <div>
                <form onSubmit={handleSubmit}>
                    <input name="rewardTitle" value={form.rewardTitle} onChange={handleChange} placeholder="Titel" />
                    <textarea name="description" value={form.description} onChange={handleChange} placeholder="Beskrivning"/>
                    <input name="rewardAmount" type="number"  placeholder="Antal"/>
                    <input name="points" type="number" placeholder="Poängkostnad"/>
                    <button type="submit">Spara</button>
                </form>

                <div>
                    {rewards.map(r => (
                        <div key ={r.rewardId}>{r.rewardTitle}</div>
                    ))}
                </div>
            </div>
        );
    }*/

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

            <h2>Befintliga belöningar</h2>

            <div className="reward-list">
                {rewards.map((r) => (
                    <div key={r.rewardId} className="reward-card">
                        <h3>{r.rewardTitle}</h3>
                        <p>{r.description}</p>
                        <p>Poäng: {r.points}</p>
                        <p>Antal: {r.rewardAmount}</p>
                    </div>
                ))}
            </div>

        </div>
    );
}

export default AdminAddReward;

