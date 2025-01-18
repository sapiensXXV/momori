import {useNavigate} from "react-router-dom";
import {useAuth} from "../../../context/AuthContext.tsx";
import {useEffect} from "react";
import {axiosJwtInstance} from "../../../global/configuration/axios.ts";

import styles from "./OAuth2Callback.module.css"
// import {BASE_URI} from "../../../uri.ts";

export default function OAuth2Callback() {
  const navigate = useNavigate();
  const authContext = useAuth();
  useEffect(() => {
    const fetchAuthInfo = async () => {
      try {
        if (!authContext) {
          console.log("AuthContext is not initialized");
          navigate("/login");
          return;
        }
        const { updateAuthContext } = authContext;
        const response = await axiosJwtInstance.get(`/api/auth/check`)
        const { provider, roles } = response.data;
        console.log(response.data)
        updateAuthContext(provider, roles, true);
        console.log(`provider: ${provider}, roles: ${roles}`);
        navigate("/");
      } catch (error) {
        console.log("Fail to fetch auth info", error);
        // navigate("/login");
      }
    }

    fetchAuthInfo()
  }, [authContext, navigate])

  return (
    <section className={styles.callbackPage}>
      <span>로그인 처리중...</span>
    </section>
  )
}