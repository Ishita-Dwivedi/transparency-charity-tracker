import React from "react";
import { BrowserRouter as Router, Routes, Route, useLocation } from "react-router-dom";

import Navbar from "./components/Navbar";
import Footer from "./components/Footer";

import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import CharityList from "./pages/CharityList";
import AdminDashboard from "./pages/AdminDashboard";

function AppLayout({ children }) {
  const location = useLocation();
  const isAuthPage = ["/login", "/register"].includes(location.pathname);


  if (isAuthPage) {
    return children; // 👉 Allow full screen layout for login/register
  }

  return (
    <div className="flex flex-col min-h-screen">
      <Navbar />
      <main className="flex-grow p-6 bg-gray-50 dark:bg-gray-900">
        {children}
      </main>
      <Footer />
    </div>
  );
}

function App() {
  return (
    <Router>
      <AppLayout>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/charities" element={<CharityList />} />
          <Route path="/admin" element={<AdminDashboard />} />
        </Routes>
      </AppLayout>
    </Router>
  );
}

export default App;
