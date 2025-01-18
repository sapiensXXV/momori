import React, { createContext, useContext, useState, useEffect } from "react";
import axios from "axios";

type AuthContextType = {
  user: string | null;
  roles: string[];
  isAuthenticated: boolean;
  updateAuthContext: (user: string, roles: string[]) => void;
};

const AuthContext = createContext<AuthContextType | null>(null);

export const AuthProvider: React.FC = ({ children }) => {
  const [user, setUser] = useState<string | null>(null);
  const [roles, setRoles] = useState<string[]>([]);
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  const updateAuthContext = (user: string, roles: string[]) => {
    setUser(user);
    setRoles(roles);
    setIsAuthenticated(true);
  };

  return (
    <AuthContext.Provider value={{ user, roles, isAuthenticated, updateAuthContext }}>
  {children}
  </AuthContext.Provider>
);
};

export const useAuth = () => {
  return useContext(AuthContext);
};