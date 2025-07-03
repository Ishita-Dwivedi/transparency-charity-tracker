import { Link } from "react-router-dom";

export default function PageWrapper({ children }) {
  return (
    <div className="min-h-screen w-full bg-gradient-to-br from-blue-50 to-blue-200 dark:from-gray-900 dark:to-black text-gray-900 dark:text-white transition-colors duration-300 flex justify-center items-center px-4 py-6 relative">

      {/* 🔙 Home Button */}
      <Link
        to="/"
        className="absolute top-6 left-6 text-blue-600 dark:text-blue-400 hover:underline text-sm font-medium z-10"
      >
        ← Back to Home
      </Link>

      {children}
    </div>
  );
}
