import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Sun, Moon } from "lucide-react";

export default function Navbar() {
  const [darkMode, setDarkMode] = useState(false);
  const [role, setRole] = useState(""); // ADMIN / DONOR / CHARITY
  const navigate = useNavigate();

  useEffect(() => {
    const theme = localStorage.getItem("theme");
    if (theme === "dark") {
      setDarkMode(true);
      document.documentElement.classList.add("dark");
    }

    const token = localStorage.getItem("token");
    if (token) {
      try {
        const payload = JSON.parse(atob(token.split(".")[1]));
        if (payload.role) setRole(payload.role.toUpperCase());
      } catch (e) {
        console.error("JWT parse error", e);
      }
    }
  }, []);

  const toggleDarkMode = () => {
    const newMode = !darkMode;
    setDarkMode(newMode);
    localStorage.setItem("theme", newMode ? "dark" : "light");
    document.documentElement.classList.toggle("dark");
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/login");
  };

  <button
    onClick={() => {
      document.documentElement.classList.toggle('dark');
    }}
    className="p-2 bg-white dark:bg-gray-800 rounded-lg"
  >
    🌙
  </button>


  return (
    <nav className="w-full bg-white dark:bg-gray-900 shadow-md">
      <div className="max-w-7xl mx-auto px-4 py-4 flex justify-between items-center">
        {/* Logo */}
        <Link to="/" className="text-2xl font-bold text-blue-600 dark:text-white">
          Transparency
        </Link>

        {/* Nav Links */}
        <div className="flex space-x-8 items-center text-base font-medium">
          {!role && (
            <>
              <Link to="/login" className="text-blue-700 dark:text-white hover:underline">Login</Link>
              <Link to="/register" className="text-blue-700 dark:text-white hover:underline">Register</Link>
            </>
          )}

          {role === "ADMIN" && (
            <Link to="/admin" className="text-blue-700 dark:text-white hover:underline">Admin</Link>
          )}

          {["ADMIN", "CHARITY", "DONOR"].includes(role) && (
            <Link to="/charities" className="text-blue-700 dark:text-white hover:underline">Charities</Link>
          )}

          {role && (
            <button onClick={handleLogout} className="text-red-600 dark:text-red-400 hover:underline">Logout</button>
          )}

          {/* Dark Mode Toggle */}
          <button onClick={toggleDarkMode} title="Toggle theme">
            {darkMode ? (
              <Sun className="text-yellow-400" />
            ) : (
              <Moon className="text-blue-700" />
            )}
          </button>
        </div>
      </div>
    </nav>
  );
}
