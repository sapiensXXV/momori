import React, {createContext, ReactNode, useContext, useState} from "react";

type AuthContextType = {
  provider: string | null;
  roles: string[];
  isAuthenticated: boolean;
  updateAuthContext: (provider: string | null, roles: string[], isAuthenticated: boolean) => void;
};

type AuthProviderProps = {
  children: ReactNode;
};

const AuthContext = createContext<AuthContextType | null>(null);

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
  const [provider, setProvider] = useState<string | null>(null);
  const [roles, setRoles] = useState<string[]>([]);
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  const updateAuthContext = (provider: string, roles: string[], isAuthenticated: boolean) => {
    setProvider(provider);
    setRoles(roles);
    setIsAuthenticated(isAuthenticated);
  };

  return (
    <AuthContext.Provider value={{provider, roles, isAuthenticated, updateAuthContext}}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = (): AuthContextType => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used within a AuthProvider");
  }
  return context;
};