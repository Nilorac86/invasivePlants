import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import SpeciesDetail from "../components/SpeciesDetail";
import { fetchPlantById } from "../service/SpeciesDetailService";


function SpeciesDetailPage() {
  const { id } = useParams();
  const [species, setSpecies] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    async function loadSpecies() {
      try {
        const data = await fetchPlantById(id);
        setSpecies(data);
      } catch (err) {
        setError("Kunde inte hämta växten.");
      } finally {
        setLoading(false);
      }
    }
    loadSpecies();
  }, [id]);

  if (loading) return <p>Laddar art...</p>;
  if (error) return <p>{error}</p>;

  return <SpeciesDetail species={species} />;
}

export default SpeciesDetailPage;
