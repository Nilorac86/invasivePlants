export async function sendRemovalReport(formData) {
    try {
        const response = await fetch("http://localhost:8080/remove-plant/form", {
            method: "POST",
            credentials: "include", // include cookies for authentication
            body: formData //formdata contains plantId, removedCount, photoAfter for right headers
        });

        if (!response.ok) {
            const errorData = await response.json().catch(() => null);
        
            if (errorData?.message) {
                throw new Error(errorData.message);
            }

             if (errorData?.details) {
                throw errorData;
            }

            throw new Error("Något gick fel vid borttagning");
        }

        return await response.json();
    } catch (err) {
        throw err;
    }
}