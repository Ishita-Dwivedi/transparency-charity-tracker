import { useEffect, useState } from "react";
import axios from "axios";

export default function AdminDashboard() {
  const [donations, setDonations] = useState([]);
  const [formData, setFormData] = useState({
    name: "",
    description: "",
    motto: "",
    leader: "",
    state: "",
  });
  const [message, setMessage] = useState("");

  const token = localStorage.getItem("token");

  useEffect(() => {
    fetchDonations();
  }, []);

  const fetchDonations = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/donations", {
        headers: { Authorization: `Bearer ${token}` },
      });
      setDonations(response.data);
    } catch (error) {
      console.error("Error fetching donations:", error);
      setMessage("Failed to load donations");
    }
  };

  const handleChange = (e) => {
    setFormData((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:8080/admin/add-charity", formData, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setMessage("Charity added successfully!");
      setFormData({
        name: "",
        description: "",
        motto: "",
        leader: "",
        state: "",
      });
    } catch (error) {
      console.error("Error adding charity:", error);
      setMessage("Failed to add charity");
    }
  };

  return (
    <div className="p-6 max-w-4xl mx-auto">
      <h1 className="text-2xl font-bold mb-4">Admin Dashboard</h1>

      <section className="mb-6">
        <h2 className="text-xl font-semibold mb-2">Add / Edit Charity</h2>
        <form onSubmit={handleSubmit} className="grid gap-4">
          {["name", "description", "motto", "leader", "state"].map((field) => (
            <input
              key={field}
              name={field}
              type="text"
              placeholder={field}
              value={formData[field]}
              onChange={handleChange}
              required
              className="p-2 border rounded"
            />
          ))}
          <button
            type="submit"
            className="bg-blue-600 hover:bg-blue-700 text-white py-2 px-4 rounded"
          >
            Submit
          </button>
        </form>
        {message && <p className="mt-2 text-sm text-gray-600">{message}</p>}
      </section>

      <section>
        <h2 className="text-xl font-semibold mb-2">All Donations</h2>
        {donations.length === 0 ? (
          <p>No donations found.</p>
        ) : (
          <ul className="divide-y">
            {donations.map((donation) => (
              <li key={donation.id} className="py-2">
                <strong>Amount:</strong> ₹{donation.amount} —{" "}
                <strong>Donor:</strong> {donation.donorName || "N/A"}
              </li>
            ))}
          </ul>
        )}
      </section>
    </div>
  );
}
