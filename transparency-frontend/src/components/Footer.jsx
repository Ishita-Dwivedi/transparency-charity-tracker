import React from "react";

// src/components/Footer.jsx
export default function Footer() {
  return (
    <footer className="w-full bg-gray-200 dark:bg-gray-800 text-center py-4 text-sm text-gray-700 dark:text-gray-300">
      © {new Date().getFullYear()} Transparency. All rights reserved.
    </footer>
  );
}
