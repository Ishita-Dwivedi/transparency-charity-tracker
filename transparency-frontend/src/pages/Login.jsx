import { useState } from 'react';
import axios from 'axios';
import PageWrapper from '../components/PageWrapper';

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errorMsg, setErrorMsg] = useState('');

  const handleLogin = async (e) => {
    e.preventDefault();
    setErrorMsg('');

    try {
      const res = await axios.post('http://localhost:8080/auth/login', {
        email,
        password,
      });

      console.log('✅ Logged in:', res.data);
      // TODO: Store token and redirect after login

    } catch (error) {
      console.error('❌ Login failed:', error);

      if (error.response?.data) {
        const data = error.response.data;
        const message = typeof data === 'string'
          ? data
          : data?.error || "Login failed. Please try again.";
        setErrorMsg(message);
      } else {
        setErrorMsg("Server did not respond. Please try again later.");
      }
    }
  };

  return (
    <PageWrapper>
      <div className="w-full sm:max-w-md bg-white dark:bg-gray-800 shadow-xl rounded-2xl p-8 sm:p-10 mx-auto">

        <h2 className="text-3xl font-bold text-center text-gray-800 dark:text-white mb-6">
          Welcome Back
        </h2>

        <form onSubmit={handleLogin} className="space-y-5">
          <div>
            <label className="block text-gray-600 dark:text-gray-300 mb-1">Email</label>
            <input
              type="email"
              className="w-full px-4 py-2 border border-gray-300 dark:border-gray-600 rounded-xl bg-white dark:bg-gray-700 text-gray-800 dark:text-white focus:outline-none focus:ring-2 focus:ring-blue-400"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="you@example.com"
              required
            />
          </div>

          <div>
            <label className="block text-gray-600 dark:text-gray-300 mb-1">Password</label>
            <input
              type="password"
              className="w-full px-4 py-2 border border-gray-300 dark:border-gray-600 rounded-xl bg-white dark:bg-gray-700 text-gray-800 dark:text-white focus:outline-none focus:ring-2 focus:ring-blue-400"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="••••••••"
              required
            />
          </div>

          {errorMsg && (
            <div className="text-sm text-red-600 bg-red-100 border border-red-300 dark:bg-red-900 dark:border-red-600 rounded-lg px-3 py-2">
              {errorMsg}
            </div>
          )}

          <button
            type="submit"
            className="w-full bg-blue-600 hover:bg-blue-700 text-white font-semibold py-2.5 rounded-xl transition duration-200"
          >
            Login
          </button>
        </form>

        <p className="text-sm text-gray-600 dark:text-gray-400 mt-6 text-center">
          Don’t have an account?{' '}
          <a href="/register" className="text-blue-600 hover:underline dark:text-blue-400">
            Register
          </a>
        </p>
      </div>
    </PageWrapper>
  );
}

export default Login;
