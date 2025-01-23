import React, {createContext, ReactNode, useContext, useState} from "react";

type AuthContextType = {
  provider: string | null;
  roles: string[];
  name: string | null;
  isAuthenticated: boolean;
  updateAuthContext: (provider: string | null, roles: string[], name: string, isAuthenticated: boolean) => void;
};

type AuthProviderProps = {
  children: ReactNode;
};

const AuthContext = createContext<AuthContextType | null>(null);

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
  const [provider, setProvider] = useState<string | null>(null);
  const [roles, setRoles] = useState<string[]>([]);
  const [name, setName] = useState<string | null>(null);
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  const updateAuthContext = (provider: string | null, roles: string[], name: string, isAuthenticated: boolean) => {
    setProvider(provider);
    setRoles(roles);
    setName(name);
    setIsAuthenticated(isAuthenticated);
  };

  return (
    <AuthContext.Provider value={{provider, roles, name, isAuthenticated, updateAuthContext}}>
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