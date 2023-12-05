import React, { useEffect, useState } from "react";
import axios from "axios";

export default function OverviewLogs() {
  const [actions, setActions] = useState([]);

  useEffect(() => {
    const fetchActions = async () => {
      const token = localStorage.getItem("access_token");
      try {
        const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8081";
        const response = await axios.get(`${BASE_URL}/system-events/actions`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        setActions(response.data);
      } catch (error) {
        console.error("Failed to fetch actions:", error);
      }
    };

    fetchActions();
    //filterMovies();
  }, []);

  const trimMilliseconds = (eventTime) => {
    return eventTime.substring(0, eventTime.length - 10).replace("T", " ");
  };

  return (
    <div style={{ marginTop: "20px", display: "flex", justifyContent: "center", alignItems: "center", flexDirection: "column" }}>
      <h1>Logovi</h1>
      <table style={{ borderCollapse: "collapse", textAlign: "center" }}>
        <thead>
          <tr>
            <th style={{ border: "1px solid white", padding: "8px" }}>ID</th>
            <th style={{ border: "1px solid white", padding: "8px" }}>Akcija</th>
            <th style={{ border: "1px solid white", padding: "8px" }}>Status</th>
            <th style={{ border: "1px solid white", padding: "8px" }}>Naziv resursa</th>
            <th style={{ border: "1px solid white", padding: "8px" }}>Vrijeme izvršavanja</th>
          </tr>
        </thead>
        <tbody>
          {actions.map((action) => (
            <tr key={action.id}>
              <td style={{ border: "1px solid white", padding: "8px" }}>{action.id}</td>
              <td style={{ border: "1px solid white", padding: "8px" }}>{action.action}</td>
              <td style={{ border: "1px solid white", padding: "8px" }}>{action.status}</td>
              <td style={{ border: "1px solid white", padding: "8px" }}>{action.resourceName}</td>
              <td style={{ border: "1px solid white", padding: "8px" }}>{trimMilliseconds(action.eventTime)}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
