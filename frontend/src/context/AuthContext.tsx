import React, {createContext, ReactNode, useContext, useEffect, useState} from "react";

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

  useEffect(() => {
    const storedAuth = localStorage.getItem("auth");
    if (storedAuth) {
      const parsedAuth = JSON.parse(storedAuth);
      setProvider(parsedAuth.provider);
      setRoles(parsedAuth.roles);
      setName(parsedAuth.name);
      setIsAuthenticated(parsedAuth.isAuthenticated);
    }
  }, []);

  const updateAuthContext = (provider: string | null, roles: string[], name: string, isAuthenticated: boolean) => {
    setProvider(provider);
    setRoles(roles);
    setName(name);
    setIsAuthenticated(isAuthenticated);

    // localStorage에 저장하여 새로고침 후에도 유지
    localStorage.setItem(
      "auth",
      JSON.stringify({ provider, roles, name, isAuthenticated })
    );

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